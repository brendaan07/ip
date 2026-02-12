package goat.tasklist;

import java.io.IOException;
import java.util.ArrayList;

import goat.data.Storage;
import goat.exceptions.GoatException;
import goat.tasks.Task;


/**
 * Represents a list of tasks in the Goat chatbot application.
 * The TaskList class manages a list of tasks internally
 */

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a TaskList with the input tasks and storage handler.
     *
     * @param tasks   the initial list of tasks
     * @param storage the storage handler used to save task updates on the hard disk
     */
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    //asked AI how to handle exceptions in the JavaDoc header comment
    /**
     * Adds a task to the list and saves the updated list to storage.
     *
     * @param task the task to add
     * @throws IOException if saving to the storage file fails
     */
    public void add(Task task) throws IOException {
        assert task != null : "Cannot add null task";
        tasks.add(task);
        storage.save(tasks);
        System.out.println("added " + task.toString());
    }
    /**
     * Deletes a task from the list and saves the updated list to storage.
     *
     * @param index index of the task to delete
     * @throws IOException if saving to the storage file fails
     * @throws GoatException if the input index is invalid
     */
    public void delete(int index) throws IOException, GoatException {
        if (index < 0 || index >= tasks.size()) {
            throw new GoatException("Invalid index! There are only " + tasks.size() + " tasks");
        }

        Task removed = tasks.remove(index);
        storage.save(tasks);
        System.out.println("Noted. I've removed this task:\n" + removed);
    }

    /**
     * Marks a task at that index as done and saves the updated list to storage.
     *
     * @param index the index of the task to mark (0-based)
     * @throws IOException if saving to the storage file fails
     */
    public void mark(int index) throws IOException {
        Task task = tasks.get(index);
        task.mark();
        storage.save(tasks);
        System.out.println("Nice! I've marked this task as done\n" + task);
    }

    /**
     * Marks a task at that index as not done and saves the updated list to storage.
     *
     * @param index the index of the task to mark (0-based)
     * @throws IOException if saving to the storage file fails
     */
    public void unmark(int index) throws IOException {
        Task task = tasks.get(index);
        task.unmark();
        storage.save(tasks);
        System.out.println("Ok, I've marked this task as not done yet\n" + task);
    }

    /**
     * Prints all tasks in the list.
     */
    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Allows users to search for tasks that contain the keyword searched
     *
     * @param keyword
     * @return list of tasks containing the keyword
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                matchedTasks.add(task);
            }
        }

        return matchedTasks;
    }

    public String listAsString() {
        if (tasks.isEmpty()) {
            return "No tasks in your list!";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

}

