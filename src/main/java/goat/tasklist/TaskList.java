package goat.tasklist;

import goat.data.Storage;
import goat.exceptions.GoatException;
import goat.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public void add(Task task) throws IOException {
        tasks.add(task);
        storage.save(tasks);
        System.out.println("added " + task.toString());
    }

    public void delete(int index) throws IOException, GoatException {
        if (index < 0 || index >= tasks.size()) {
            throw new GoatException("Invalid index! There are only " + tasks.size() + " tasks");
        }

        Task removed = tasks.remove(index);
        storage.save(tasks);
        System.out.println("Noted. I've removed this task:\n" + removed);
    }

    public void mark(int index) throws IOException {
        Task task = tasks.get(index);
        task.mark();
        storage.save(tasks);
        System.out.println("Nice! I've marked this task as done\n" + task);
    }

    public void unmark(int index) throws IOException {
        Task task = tasks.get(index);
        task.unmark();
        storage.save(tasks);
        System.out.println("Ok, I've marked this task as not done yet\n" + task);
    }

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

}

