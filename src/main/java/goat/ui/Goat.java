package goat.ui;

import java.io.IOException;
import java.util.ArrayList;

import goat.data.Parser;
import goat.data.Storage;
import goat.exceptions.GoatException;
import goat.exceptions.MissingArgumentException;
import goat.tasklist.TaskList;
import goat.tasks.Deadlines;
import goat.tasks.Events;
import goat.tasks.Task;
import goat.tasks.ToDos;


/**
 * Entry point of the Goat chatbot application.
 * Handles program initialization and the main command-processing loop.
 */
public class Goat {
    private static final String DATA_PATH = "data/goat.txt";

    /**
     * Handles input from user. Executes relevant action based on the user input on the GUI
     * @param input
     * @return
     */

    public String getResponse(String input) {
        Storage storage = new Storage(DATA_PATH);
        ArrayList<Task> tasks = storage.load();
        TaskList taskList = new TaskList(tasks, storage);

        try {
            Parser parser = new Parser(input);
            String command = parser.getCommand();
            String arguments = parser.getArguments();

            switch (command) {
            case "bye":
                return "Bye. Hope to see you again soon!";
            case "list":
                taskList.list();
                return taskList.listAsString();
            case "todo":
                return handleTodo(arguments, taskList);
            case "deadline":
                return handleDeadline(arguments, taskList);
            case "event":
                return handleEvent(arguments, taskList);
            case "delete":
                return handleDelete(arguments, taskList);
            case "mark":
                return handleMark(arguments, taskList);
            case "unmark":
                return handleUnmark(arguments, taskList);
            case "find":
                return handleFind(arguments, taskList);
            default:
                return "I'm sorry, I don't understand";
            }
        } catch (MissingArgumentException | GoatException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Unable to access data file";
        }
    }

    private String handleTodo(String arguments, TaskList taskList) throws MissingArgumentException, IOException {
        Parser.requireArgs(arguments, "todo");
        taskList.add(new ToDos(arguments));
        return "Added todo: " + arguments;
    }

    private String handleDeadline(String arguments, TaskList taskList) throws MissingArgumentException, IOException {
        Parser.requireArgs(arguments, "deadline");
        String[] parts = arguments.split("/by", 2);

        if (parts.length < 2 || parts[1].isBlank()) {
            // throw a clear exception instead of letting it crash
            throw new MissingArgumentException("Deadline must have a '/by' date");
        }
        String name = parts[0].trim();
        String by = parts[1].trim();
        taskList.add(new Deadlines(name, by));
        return "Added deadline: " + name + " by " + by;
    }

    private String handleEvent(String arguments, TaskList taskList) throws MissingArgumentException, IOException {
        Parser.requireArgs(arguments, "event");
        String[] parts = arguments.split("/from", 2);
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new MissingArgumentException("Event must include a '/from' date");
        }

        String name = parts[0].trim();

        String[] dates = parts[1].split("/to", 2);
        if (dates.length < 2 || dates[0].isBlank() || dates[1].isBlank()) {
            throw new MissingArgumentException("Event must include both '/from' and '/to' dates");
        }
        String from = dates[0].trim();
        String to = dates[1].trim();
        taskList.add(new Events(name, from, to));
        return "Added event: " + name + " from " + from + " to " + to;
    }

    private String handleDelete(String arguments, TaskList taskList) throws MissingArgumentException, GoatException, IOException {
        Parser.requireArgs(arguments, "delete");
        int index = Integer.parseInt(arguments) - 1;
        taskList.delete(index);
        return "Deleted: " + arguments;
    }

    private String handleMark(String arguments, TaskList taskList) throws MissingArgumentException, IOException {
        Parser.requireArgs(arguments, "mark");
        int index = Integer.parseInt(arguments) - 1;
        taskList.mark(index);
        return "Marked " + arguments + " as done!";
    }

    private String handleUnmark(String arguments, TaskList taskList) throws MissingArgumentException, IOException {
        Parser.requireArgs(arguments, "unmark");
        int index = Integer.parseInt(arguments) - 1;
        taskList.unmark(index);
        return "Unmarked " + arguments;
    }

    private String handleFind(String arguments, TaskList taskList) throws MissingArgumentException {
        Parser.requireArgs(arguments, "find");
        ArrayList<Task> results = taskList.find(arguments);
        if (results.isEmpty()) return "No matching tasks found";
        StringBuilder sb = new StringBuilder("Matching tasks:\n");
        for (int i = 0; i < results.size(); i++) {
            sb.append(i + 1).append(". ").append(results.get(i)).append("\n");
        }
        return sb.toString();
    }

}












