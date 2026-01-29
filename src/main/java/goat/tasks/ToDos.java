package goat.tasks;

/**
 * Represents a ToDo task in the Goat chatbot application.
 * Each ToDo has a name and a completion status (done or not done).
 */

public class ToDos extends Task {
    public ToDos(String name) {
        super(name);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (this.isDone() ? "1" : "0") + " | " + this.getName();
    }

}
