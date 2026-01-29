package goat.tasks;

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
