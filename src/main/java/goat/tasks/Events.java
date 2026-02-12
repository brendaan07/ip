package goat.tasks;

import goat.tasklist.Priority;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Goat chatbot application.
 * Each event has a start and an end date.
 */
public class Events extends Task {

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    private final LocalDate from;
    private final LocalDate to;

    /**
     * Creates an event task with a start and end date.
     *
     * @param name Name of the event
     * @param from Start date in yyyy-MM-dd format
     * @param to End date in yyyy-MM-dd format
     */
    public Events(String name, String from, String to, Priority priority) {
        super(name, priority);
        this.from = LocalDate.parse(from, INPUT_FORMAT);
        this.to = LocalDate.parse(to, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(OUTPUT_FORMAT)
                + " to: " + this.to.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (this.getIsDone() ? "1" : "0")
                + " | " + this.getName()
                + " | " + this.from.format(OUTPUT_FORMAT)
                + " | " + this.to.format(OUTPUT_FORMAT)
                + " | " + this.getPriority();
    }
}

