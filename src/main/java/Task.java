import java.util.Scanner;
import java.util.ArrayList;

public class Goat {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Goat Chatbot \n" +
                "What Can I do for you?  \n"
        );
        ArrayList<String> lst = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userinput = sc.nextLine();
            if (userinput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userinput.equals("list")) { //display list
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println((i + 1) + ". " + lst.get(i));
                }
            } else {
                lst.add(userinput);
                System.out.println("added: " + userinput);
            }
        }
    }
}
