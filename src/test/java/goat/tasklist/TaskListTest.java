package goat.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import goat.data.Storage;
import goat.tasks.Task;
import goat.tasks.ToDos;

public class TaskListTest {
    private TaskList createTaskList() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path tempFile = Files.createTempFile("goat_test", ".txt");
        Storage storage = new Storage(tempFile.toString()); // Storage pointing to temp file
        return new TaskList(tasks, storage);
    }

    @Test
    public void addTask_increasesSizeAndSaves() throws IOException {
        TaskList taskList = createTaskList();

        assertEquals(0, taskList.size());

        taskList.add(new ToDos("Read book"));
        assertEquals(1, taskList.size());

        taskList.add(new ToDos("Write essay"));
        assertEquals(2, taskList.size());
    }
}



