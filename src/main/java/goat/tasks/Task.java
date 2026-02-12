package goat.tasks;

import goat.tasklist.Priority;

/**
 * Represents a task in the Goat task list.
 * A task has a name, done status and priority.
 * Priorities are set to LOW by default
 */
public abstract class Task {
    private boolean isDone;
    private String name;
    private Priority priority;

    /**
     * Constructs a Task with the given name.
     * The task is set to initially not done.
     *
     * @param name the name of the task
     */

    public Task(String name, Priority priority) {
        this.isDone = false;
        this.name = name;
        this.priority = priority;
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
     * Allows code to get the priority to print or store
     * @return
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Allows users to edit priority of task item
     * @param priority
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
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
        return check + " " + this.name + " (P: " + this.priority + ")";
    }
}
