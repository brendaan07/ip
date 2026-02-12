package goat.tasks;

/**
 * Represents a task in the Goat task list.
 * A task has a name and a done status.
 */
public abstract class Task {
    private boolean isDone;
    private String name;

    /**
     * Constructs a Task with the given name.
     * The task is set to initially not done.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     * Subclasses must implement this method.
     *
     * @return a formatted string representing the task for file storage
     */
    public abstract String toFileString();

    /**
     * Returns Done boolean is true or false
     *
     * @return true or false
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return the task's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return a string in the format "[ ] name" or "[X] name"
     */
    public String toString() {
        String check = "";
        if (!this.isDone) {
            check = "[ ]";
        } else {
            check = "[X]";
        }
        return check + " " + this.name;
    }
}
