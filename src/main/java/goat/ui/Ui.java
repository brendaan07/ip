package goat.ui;

import java.util.ArrayList;
import java.util.Scanner;

import goat.tasks.Task;

/**
 * Handles user interaction by reading commands and displaying messages.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Creates a Ui instance.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The trimmed user input
     */
    public String readCommand() {
        return sc.nextLine().trim(); // Remove trailing spaces
    }

    /**
     * Displays a normal message to the user.
     *
     * @param message Message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message Error message to display
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays the list of tasks matching the find command.
     *
     * @param tasks List of matching tasks
     */
    public void showFindResults(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            showMessage("No matching tasks found");
            return;
        }

        showMessage("Here are the matching tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            showMessage((i + 1) + ". " + tasks.get(i));
        }
    }
}

