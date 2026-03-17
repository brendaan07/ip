package goat.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import goat.exceptions.GoatException;
import goat.tasklist.Priority;

class DateParsingTest {

    @Test
    void events_acceptSingleDigitDay_andNormalizeForStorage() throws GoatException {
        Events event = new Events("Event", "2026-02-2", "2026-02-03", Priority.LOW);

        assertEquals("E | 0 | Event | 2026-02-02 | 2026-02-03 | LOW", event.toFileString());
    }

    @Test
    void deadlines_acceptSingleDigitDay_andNormalizeForStorage() throws GoatException {
        Deadlines deadline = new Deadlines("Assignment", "2026-2-3", Priority.MED);

        assertEquals("D | 0 | Assignment | 2026-02-03 | MED", deadline.toFileString());
    }

    @Test
    void events_rejectInvalidCalendarDate() {
        assertThrows(GoatException.class, () -> new Events("Event", "2026-02-30", "2026-03-01", Priority.LOW));
    }

    @Test
    void deadlines_rejectInvalidCalendarDate() {
        assertThrows(GoatException.class, () -> new Deadlines("Assignment", "2026-02-30", Priority.LOW));
    }
}

