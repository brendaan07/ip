public class Events extends Task {
    private String from;
    private String to;

    public Events(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (this.isDone() ? "1" : "0") + " | " + this.getName() + " | " + this.from + " | " + this.to;
    }

}
