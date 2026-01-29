package goat.tasks;//asked AI what are the imports needed and how to use them

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Events type of task in the Goat chatbot application.
 * Each event also has a from and to date
 */

public class Events extends Task {
    private final LocalDate from;
    private final LocalDate to;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Events(String name, String from, String to) {
        super(name);
        this.from = LocalDate.parse(from, INPUT_FORMAT);
        this.to = LocalDate.parse(to, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(OUTPUT_FORMAT) + " to: " + this.to.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (this.isDone() ? "1" : "0") + " | " + this.getName() + " | " + this.from.format(OUTPUT_FORMAT) + " | " + this.to.format(OUTPUT_FORMAT);
    }

}
