import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Files.createDirectories(filePath.getParent());
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                return tasks;
            }
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split("\\s*\\|\\s*");
                String type = parts[0];
                boolean done = parts[1].equals("1");

                Task task = null;
                if (type.equals("T")) task = new ToDos(parts[2]);
                else if (type.equals("D")) task = new Deadlines(parts[2], parts[3]);
                else if (type.equals("E")) task = new Events(parts[2], parts[3], parts[4]);

                if (task != null && done) task.mark();
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file");
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileString());
        }
        Files.write(filePath, lines);
    }
}
