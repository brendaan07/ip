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

    public String getResponse(String input) {
        Ui ui = new Ui();
        Storage storage = new Storage(DATA_PATH);
        ArrayList<Task> tasks = storage.load();
        TaskList taskList = new TaskList(tasks, storage);
        //ui.showMessage("Hello! I'm Goat.Goat Chatbot \n What can I do for you?");

        while (true) {
            try {
                //String userInput = ui.readCommand(); // Remove trailing spaces
                Parser parser = new Parser(input);
                String command = parser.getCommand();
                String arguments = parser.getArguments();

                // Used AI to give an example of a switch usage
                switch (command) {
                case "bye":
                    //ui.showMessage("Bye. Hope to see you again soon!");
                    return "Bye. Hope to see you again soon!";

                case "list":
                    taskList.list(); // Print list
                    return taskList.listAsString();

                case "todo":
                    Parser.requireArgs(arguments, "todo");
                    taskList.add(new ToDos(arguments));
                    return "Added todo: " + arguments;

                case "deadline":
                    Parser.requireArgs(arguments, "deadline");
                    String[] deadlineParts = arguments.split("/by", 2);
                    String name = deadlineParts[0].trim();
                    String by = deadlineParts[1].trim();
                    taskList.add(new Deadlines(name, by));
                    return "Added deadline: " + name + " by " + by;

                case "event":
                    Parser.requireArgs(arguments, "event");
                    String[] eventParts = arguments.split("/from", 2);
                    String eventName = eventParts[0].trim();
                    String[] dates = eventParts[1].split("/to", 2);
                    String from = dates[0].trim();
                    String to = dates[1].trim();
                    taskList.add(new Events(eventName, from, to));
                    return "Added event: " + eventName + " from " + from + " to " + to;

                case "delete":
                    Parser.requireArgs(arguments, "delete");
                    taskList.delete(Integer.parseInt(arguments) - 1);
                    return "Deleted: " + arguments;

                case "mark":
                    Parser.requireArgs(arguments, "mark");
                    taskList.mark(Integer.parseInt(arguments) - 1);
                    return "Marked " + arguments + " as done!";

                case "unmark":
                    Parser.requireArgs(arguments, "unmark");
                    taskList.unmark(Integer.parseInt(arguments) - 1);
                    return "Unmarked " + arguments;

                case "find":
                    Parser.requireArgs(arguments, "find");
                    ArrayList<Task> results = taskList.find(arguments);
                    // Asked AI how to return a string here
                    if (results.isEmpty()) return "No matching tasks found";
                    StringBuilder sb = new StringBuilder("Matching tasks:\n");
                    for (int i = 0; i < results.size(); i++) {
                        sb.append(i + 1).append(". ").append(results.get(i)).append("\n");
                    }
                    return sb.toString();

                default:
                    return "I'm sorry, I don't understand";
                }
            } catch (MissingArgumentException | GoatException e) {
                //ui.showError(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                //ui.showError("Unable to access data file");
                return "Unable to access data file";
            }
        }
    }
}












