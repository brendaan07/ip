package goat.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import goat.exceptions.GoatException;
import goat.tasklist.Priority;

/**
 * Represents a Deadline type of task in the Goat chatbot application.
 * Each deadline also has a deadline date.
 */
public class Deadlines extends Task {
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    private final LocalDate deadline;

    /**
     * Builds a Deadline instance. Each deadline has a name and a deadline date.
     *
     * @param name task name
     * @param deadline deadline date
     * @param priority task priority
     */
    public Deadlines(String name, String deadline, Priority priority) throws GoatException {
        super(name, priority);
        try {
            this.deadline = LocalDate.parse(deadline, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new GoatException("Invalid date format. Please use yyyy-MM-dd (e.g. 2026-12-11)");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.getIsDone() ? "1" : "0") + " | "
                + this.getName() + " | " + this.deadline.format(OUTPUT_FORMAT)
                + " | " + this.getPriority();
    }
}
