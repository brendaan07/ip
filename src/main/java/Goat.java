import java.util.Scanner;
import java.util.ArrayList;

public class Goat {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Goat Chatbot \n" +
                "What Can I do for you?  \n"
        );
        ArrayList<Task> lst = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userinput = sc.nextLine().trim(); //remove trailing spaces
            String[] parts = userinput.split("\\s+", 2); //split into command and arguments
            String command = parts[0].toLowerCase();
            String arguments = parts.length > 1 ? parts[1] : "";


            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) { //display list
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println((i + 1) + ". " + lst.get(i).toString());
                }
            } else if (command.equals("mark")) { //mark task
                int index = Integer.parseInt(arguments) - 1;
                Task currTask = lst.get(index);
                currTask.mark();
                System.out.println("Nice! I've marked this task as done \n" + currTask);
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(arguments) - 1;
                Task currTask = lst.get(index);
                currTask.unmark();
                System.out.println("Ok, I've marked this task as not done yet \n" + currTask);
            } else {
                Task newTask = new Task(userinput);
                lst.add(newTask);
                System.out.println("added: " + userinput);
            }
        }
    }
}
