package goat.ui;

import goat.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() { return sc.nextLine().trim(); // Remove trailing spaces
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showFindResults(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            showMessage("No matching tasks found");
        }

        showMessage("Here are the matching tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            showMessage((i + 1) + ". " + tasks.get(i));
        }

    }
}
