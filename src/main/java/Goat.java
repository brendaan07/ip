import java.util.Scanner;
import java.util.ArrayList;

public class Goat {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Goat Chatbot\n" +
                "What Can I do for you?\n"
        );
        ArrayList<Task> lst = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String userinput = sc.nextLine().trim(); //remove trailing spaces
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
                    if (arguments.isEmpty()) {
                        throw new MissingArgumentException("delete");
                    }
                    //Asked AI how to convert arguments into int
                    int index = Integer.parseInt(arguments) - 1;
                    Task currTask = lst.get(index);
                    System.out.println(" Noted. I've removed this task:\n" + currTask);
                    lst.remove(index);
                } else if (command.equals("mark")) { //mark task
                    if (arguments.isEmpty()) {
                        throw new MissingArgumentException("mark");
                    }
                    int index = Integer.parseInt(arguments) - 1;
                    Task currTask = lst.get(index);
                    currTask.mark();
                    System.out.println("Nice! I've marked this task as done\n" + currTask);
                } else if (command.equals("unmark")) {
                    if (arguments.isEmpty()) {
                        throw new MissingArgumentException("unmark");
                    }
                    int index = Integer.parseInt(arguments) - 1;
                    Task currTask = lst.get(index);
                    currTask.unmark();
                    System.out.println("Ok, I've marked this task as not done yet\n" + currTask);
                } else if (command.equals("todo")) {
                    if (arguments.isEmpty()) {
                        throw new MissingArgumentException("todo");
                    }
                    ToDos newToDo = new ToDos(arguments);
                    lst.add(newToDo);
                    System.out.println("added " + newToDo.toString());
                } else if (command.equals("deadline")) {
                    if (arguments.isEmpty()) {
                        throw new MissingArgumentException("deadline");
                    }
                    String[] argParts = arguments.split("/by", 2);
                    String name = argParts.length > 0 ? argParts[0].trim() : "";
                    String deadline = argParts.length > 1 ? argParts[1].trim() : "";

                    Deadlines newDeadline = new Deadlines(name, deadline);
                    lst.add(newDeadline);
                    System.out.println("added " + newDeadline.toString());
                } else if (command.equals("event")) {
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
                } else {
                    throw new GoatException("Error: I'm sorry, I don't understand that command");
                }
            } catch (MissingArgumentException e) {
                System.out.println(e.getMessage());
            } catch (GoatException e) {
                System.out.println(e.getMessage());;
            }
        }
    }
}
