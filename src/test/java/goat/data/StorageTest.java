package goat.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import goat.exceptions.GoatException;
import goat.tasklist.Priority;
import goat.ui.Goat;
import org.junit.jupiter.api.Test;

import goat.tasks.Deadlines;
import goat.tasks.Events;
import goat.tasks.Task;
import goat.tasks.ToDos;
import static org.junit.jupiter.api.Assertions.assertEquals;



/**
 * Unit tests for Storage class.
 * Verifies that tasks are saved and loaded correctly.
 */
public class StorageTest {

    //asked AI to guide me on implementing assert methods
    //asked AI how to give an overiew on testing if tasks are saved
    @Test
    public void saveAndLoad_tasksPreserved() throws IOException {
        // Step 1: create temporary file for testing
        Path tempFile = Files.createTempFile("goat_storage_test", ".txt");
        Storage storage = new Storage(tempFile.toString());

        // Step 2: create tasks to save
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new ToDos("Read book", Priority.LOW));
        try {
            tasksToSave.add(new Deadlines("Return book", "2026-02-29", Priority.LOW));
            tasksToSave.add(new Events("Project meeting", "2026-03-01", "2026-03-02", Priority.LOW));
        } catch (GoatException e) {
            System.out.println("Warning: date should be in yyyy-mm-dd format");
        }

        // Step 3: save tasks
        storage.save(tasksToSave);

        // Step 4: load tasks back
        ArrayList<Task> loadedTasks = storage.load();

        // Step 5: verify loaded tasks match saved tasks
        assertEquals(tasksToSave.size(), loadedTasks.size(), "Number of tasks should match");

        for (int i = 0; i < tasksToSave.size(); i++) {
            assertEquals(tasksToSave.get(i).toString(), loadedTasks.get(i).toString(),
                    "Task at index " + i + " should match after load");
        }

        // Step 6: delete temporary file after test
        Files.deleteIfExists(tempFile);
    }
}

