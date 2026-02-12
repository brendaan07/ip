package goat.tasks;

// Asked AI what are the imports needed and how to use them

import goat.tasklist.Priority;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



/**
 * Represents a Deadline type of task in the Goat chatbot application.
 * Each deadline also has a deadline date
 */
public class Deadlines extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final LocalDate deadline;

    /**
     * Builds a Deadline instance. Each deadline has a name and a deadline date.
     * @param name
     * @param deadline
     */
    public Deadlines(String name, String deadline, Priority priority) {
        super(name, priority);
        this.deadline = LocalDate.parse(deadline, INPUT_FORMAT);
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
