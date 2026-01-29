package goat.tasks;//asked AI what are the imports needed and how to use them

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import goat.exceptions.GoatException;


/**
 * Represents a Deadline type of task in the Goat chatbot application.
 * Each deadline also has a deadline date
 */

public class Deadlines extends Task {
    private final LocalDate deadline;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadlines (String name, String deadline)  {
        super(name);
        this.deadline = LocalDate.parse(deadline, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.isDone() ? "1" : "0") + " | " + this.getName() + " | " + this.deadline.format(OUTPUT_FORMAT);
    }

}
