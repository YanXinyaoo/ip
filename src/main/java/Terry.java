import java.util.Scanner;

public class Terry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dottedLine = "    ____________________________________________________________";
        System.out.println(dottedLine);
        System.out.println("    Hello! I'm Terry");
        System.out.println("    What can I do for you?");
        System.out.println(dottedLine);

        String userInput;
        while(sc.hasNext()) {
            userInput = sc.nextLine();
            System.out.println(dottedLine);
            if ("bye".equalsIgnoreCase(userInput)) {
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(dottedLine);
                break;
            } else {
                System.out.println("    " + userInput);
                System.out.println(dottedLine);
            }
        }
    }
}
