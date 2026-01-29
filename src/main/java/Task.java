import java.util.ArrayList;
import java.util.Scanner;

public abstract class Task {
    private boolean isDone;
    private String name;

    public abstract String toFileString(); //file saving handling

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

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
