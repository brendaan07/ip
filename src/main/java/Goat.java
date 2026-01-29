import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Goat {

    //used AI to learn how to get path txt
    private static final Path DATA_FILE = Paths.get("data", "goat.txt");

    public static void main(String[] args) {
        Ui ui = new Ui();

        ui.showMessage("Hello! I'm Goat Chatbot \n What can I do for you?");

        ArrayList<Task> lst = new ArrayList<>();
        loadTasks(lst); //loadtasks from hard drive


        while (true) {
            try {
                String userinput = ui.readCommand(); //remove trailing spaces
                //used AI to learn how to split Strings into commands and arguments
                String[] parts = userinput.split("\\s+", 2); //split into command and arguments
                String command = parts[0].toLowerCase();
                String arguments = parts.length > 1 ? parts[1] : "";



                if (command.equals("bye")) {
                    System.out.print("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) { //display list
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println((i + 1) + ". " + lst.get(i).toString());
                    }
                } else if (command.equals("delete")) {
                    requireArgs(arguments, "delete");
                    if (arguments.isEmpty()) {
                        throw new MissingArgumentException("delete");
                    }
                    //Asked AI how to convert arguments into int
                    int index = Integer.parseInt(arguments) - 1;
                    Task currTask = lst.get(index);
                    System.out.println(" Noted. I've removed this task:\n" + currTask);
                    lst.remove(index);
                    saveTasks(lst);
                } else if (command.equals("mark")) { //mark task
                    requireArgs(arguments, "mark");
                    if (arguments.isEmpty()) {
                        throw new MissingArgumentException("mark");
                    }
                    int index = Integer.parseInt(arguments) - 1;
                    Task currTask = lst.get(index);
                    currTask.mark();
                    System.out.println("Nice! I've marked this task as done\n" + currTask);
                    saveTasks(lst);
                } else if (command.equals("unmark")) {
                    requireArgs(arguments, "unmark");
                    if (arguments.isEmpty()) {
                        throw new MissingArgumentException("unmark");
                    }
                    int index = Integer.parseInt(arguments) - 1;
                    Task currTask = lst.get(index);
                    currTask.unmark();
                    System.out.println("Ok, I've marked this task as not done yet\n" + currTask);
                    saveTasks(lst);
                } else if (command.equals("todo")) {
                    requireArgs(arguments, "todo");
                    if (arguments.isEmpty()) {
                        throw new MissingArgumentException("todo");
                    }
                    ToDos newToDo = new ToDos(arguments);
                    lst.add(newToDo);
                    System.out.println("added " + newToDo.toString());
                    saveTasks(lst);
                } else if (command.equals("deadline")) {
                    requireArgs(arguments, "deadline");
                    if (arguments.isEmpty()) {
                        throw new MissingArgumentException("deadline");
                    }
                    String[] argParts = arguments.split("/by", 2);
                    String name = argParts.length > 0 ? argParts[0].trim() : "";
                    String deadline = argParts.length > 1 ? argParts[1].trim() : "";

                    Deadlines newDeadline = new Deadlines(name, deadline);
                    lst.add(newDeadline);
                    System.out.println("added " + newDeadline.toString());
                    saveTasks(lst);
                } else if (command.equals("event")) {
                    requireArgs(arguments, "event");
                    if (arguments.isEmpty()) {
                        throw new MissingArgumentException("event");
                    }
                    String[] argparts = arguments.split("/from", 2);  // split on every slash
                    String name = argparts.length > 0 ? argparts[0].trim() : "";
                    String dates = argparts.length > 1 ? argparts[1].trim() : "";
                    String[] dateparts = dates.split("/to", 2);
                    String from = dateparts.length > 0 ? dateparts[0].trim() : "";
                    String to = dateparts.length > 1 ? dateparts[1].trim() : "";

                    Events newEvent = new Events(name, from, to);
                    lst.add(newEvent);
                    System.out.println("added " + newEvent.toString());
                    saveTasks(lst);
                } else {
                    throw new GoatException("Error: I'm sorry, I don't understand that command");
                }
                //asked AI to combine both exceptio s
            } catch (MissingArgumentException | GoatException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Can't access data file");;
            }
        }
    }

    private static void requireArgs(String args, String command)
            throws MissingArgumentException {
        if (args.isEmpty()) {
            throw new MissingArgumentException(command);
        }
    }

    //Handle files
    //asked AI for guidance on how to handle files
    private static void loadTasks(ArrayList<Task> lst) {
        try {
            Files.createDirectories(DATA_FILE.getParent());

            if (!Files.exists(DATA_FILE)) {
                Files.createFile(DATA_FILE);
                return;
            }

            List<String> lines = Files.readAllLines(DATA_FILE);
            for (String line : lines) {
                String[] parts = line.split("\\s*\\|\\s*");
                String type = parts[0];
                boolean done = parts[1].equals("1");

                Task task = null;

                if (type.equals("T")) {
                    task = new ToDos(parts[2]); //todos event type
                } else if (type.equals("D")) {
                    task = new Deadlines(parts[2], parts[3]); //deadlines task type
                } else if (type.equals("E")) {
                    task = new Events(parts[2], parts[3], parts[4]); //events task type
                }

                if (task != null && done) {
                    task.mark();
                }
                lst.add(task);
            }

        } catch (IOException e) {
            System.out.println("Error loading tasks from file");
        }
    }

    private static void saveTasks(ArrayList<Task> lst) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task task : lst) {
            lines.add(task.toFileString());
        }
        Files.write(DATA_FILE, lines);
    }

}
