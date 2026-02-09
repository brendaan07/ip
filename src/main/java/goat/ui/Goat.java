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

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage(DATA_PATH);
        ArrayList<Task> tasks = storage.load();
        TaskList taskList = new TaskList(tasks, storage);
        ui.showMessage("Hello! I'm Goat.Goat Chatbot \n What can I do for you?");

        while (true) {
            try {
                String userInput = ui.readCommand(); // Remove trailing spaces
                Parser parser = new Parser(userInput);
                String command = parser.getCommand();
                String arguments = parser.getArguments();

                // Used AI to give an example of a switch usage
                switch (command) {
                case "bye":
                    ui.showMessage("Bye. Hope to see you again soon!");
                    return;

                case "list":
                    taskList.list(); // Print list
                    break;

                case "todo":
                    Parser.requireArgs(arguments, "todo");
                    taskList.add(new ToDos(arguments));
                    break;

                case "deadline":
                    Parser.requireArgs(arguments, "deadline");
                    String[] deadlineParts = arguments.split("/by", 2);
                    String name = deadlineParts[0].trim();
                    String by = deadlineParts[1].trim();
                    taskList.add(new Deadlines(name, by));
                    break;

                case "event":
                    Parser.requireArgs(arguments, "event");
                    String[] eventParts = arguments.split("/from", 2);
                    String eventName = eventParts[0].trim();
                    String[] dates = eventParts[1].split("/to", 2);
                    String from = dates[0].trim();
                    String to = dates[1].trim();
                    taskList.add(new Events(eventName, from, to));
                    break;

                case "delete":
                    Parser.requireArgs(arguments, "delete");
                    taskList.delete(Integer.parseInt(arguments) - 1);
                    break;

                case "mark":
                    Parser.requireArgs(arguments, "mark");
                    taskList.mark(Integer.parseInt(arguments) - 1);
                    break;

                case "unmark":
                    Parser.requireArgs(arguments, "unmark");
                    taskList.unmark(Integer.parseInt(arguments) - 1);
                    break;

                case "find":
                    Parser.requireArgs(arguments, "find");
                    ArrayList<Task> results = taskList.find(arguments);
                    ui.showFindResults(results);
                    break;

                default:
                    throw new GoatException("I'm sorry, I don't understand");
                }
            } catch (MissingArgumentException | GoatException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Unable to access data file");
            }
        }
    }
}












