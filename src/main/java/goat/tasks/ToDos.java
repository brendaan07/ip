package goat.tasks;

import goat.tasklist.Priority;

/**
 * Represents a ToDo task in the Goat chatbot application.
 * Each ToDo has a name and a completion status (done or not done).
 */

public class ToDos extends Task {

    public ToDos(String name, Priority priority) {
        super(name, priority);
    }


    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (this.getIsDone() ? "1" : "0") + " | " + this.getName() + " | "
                + this.getPriority();
    }

}
