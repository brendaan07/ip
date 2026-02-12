package goat.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import goat.tasks.Deadlines;
import goat.tasks.Events;
import goat.tasks.Task;
import goat.tasks.ToDos;

/**
 * Handles reading from and writing to the task storage file.
 * Creates the file if it does not exist and converts lines into Task objects.
 */
public class Storage {

    private final Path filePath;

    /**
     * Constructs a Storage object pointing to a file.
     *
     * @param filePath path to the storage file
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isBlank() : "filePath must not be null or empty";
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return list of tasks read from the file
     */
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
                if (line.trim().isEmpty()) {
                    continue; // skip empty lines
                }

                String[] parts = line.split("\\s*\\|\\s*");
                if (parts.length < 2) {
                    System.out.println("Warning: invalid line in file: " + line);
                    continue; // skip invalid line
                }

                String type = parts[0];
                boolean done = parts[1].equals("1");

                Task task = null;
                if (type.equals("T")) {
                    task = new ToDos(parts[2]);
                } else if (type.equals("D")) {
                    task = new Deadlines(parts[2], parts[3]);
                } else if (type.equals("E")) {
                    task = new Events(parts[2], parts[3], parts[4]);
                }

                if (task != null && done) {
                    task.mark();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file");
        }
        return tasks;
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks list of tasks to save
     * @throws IOException if writing fails
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileString());
        }
        Files.write(filePath, lines);
    }
}
