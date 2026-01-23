public class ToDos extends Task {
    public ToDos(String name) {
        super(name);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
