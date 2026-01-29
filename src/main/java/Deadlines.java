public class Deadlines extends Task {
    private String deadline;
    public Deadlines (String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.isDone() ? "1" : "0") + " | " + this.getName() + " | " + this.deadline;
    }

}
