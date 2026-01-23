import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    private boolean done;
    private String name;

    public Task(String name) {
        this.done = false;
        this.name = name;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String toString() {
        String check = "";
        if (!this.done) {
            check = "[ ]";
        } else {
            check = "[X]";
        }
        return check + " " + this.name;
    }
}
