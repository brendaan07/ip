package goat.data;

import goat.tasks.Deadlines;
import goat.tasks.Events;
import goat.tasks.Task;
import goat.tasks.ToDos;
import goat.exceptions.GoatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

/**
 * Handles storage of the list in the hard disk. Allows the same list to be loaded
 * in between restarts of the chatbot
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs the Storage filePath
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * loads the list from the hard disk into the chatbot instance
     *
     * @return tasks list of tasks saved in the hard disk
     */
    public ArrayList<Task> load()  {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Files.createDirectories(filePath.getParent());
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                return tasks;
            }
            List<String> lines = Files.readAllLines(filePath);

            //asked AI for an edited version to skip lines if .txt file is empty
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

    /**
     * saves the updated tasks into the hard disk
     *
     * @param tasks
     * @throws IOException
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileString());
        }
        Files.write(filePath, lines);
    }
}
