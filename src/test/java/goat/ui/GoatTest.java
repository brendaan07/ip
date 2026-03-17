package goat.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GoatTest {
    private static final Path DATA_PATH = Path.of("data", "goat.txt");

    private boolean dataFileExisted;
    private List<String> originalData;

    @BeforeEach
    public void setUpDataFile() throws IOException {
        dataFileExisted = Files.exists(DATA_PATH);
        if (dataFileExisted) {
            originalData = Files.readAllLines(DATA_PATH);
        }

        Files.createDirectories(DATA_PATH.getParent());
        Files.write(DATA_PATH, List.of("T | 0 | Read book | LOW"));
    }

    @AfterEach
    public void restoreDataFile() throws IOException {
        if (dataFileExisted) {
            Files.write(DATA_PATH, originalData);
        } else {
            Files.deleteIfExists(DATA_PATH);
        }
    }

    @Test
    public void getResponse_priorityInvalidTaskNumber_returnsMessage() {
        Goat goat = new Goat();

        String response = goat.getResponse("priority abc HIGH");

        assertEquals("Please provide a valid task number for priority", response);
    }

    @Test
    public void getResponse_priorityIndexOutOfBounds_returnsMessage() {
        Goat goat = new Goat();

        String response = goat.getResponse("priority 999 HIGH");

        assertEquals("Invalid index! There are only 1 tasks", response);
    }

    @Test
    public void getResponse_priorityValueInvalid_returnsMessage() {
        Goat goat = new Goat();

        String response = goat.getResponse("priority 1 SUPER");

        assertEquals("Priority must be included in the format LOW, MED or HIGH", response);
    }
}
