import java.util.Scanner;

public class Goat {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Goat Chatbot \n" +
                "What Can I do for you?  \n"
        );
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userinput = sc.nextLine();
            if (userinput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(userinput);
        }
    }
}
