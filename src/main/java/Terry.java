import java.util.Scanner;

public class Terry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dottedLine = "    ____________________________________________________________";
        String[] tasks = new String[100];
        String bye = "bye";
        String list = "list";
        int currentTask = 0;

        System.out.println(dottedLine);
        System.out.println("    Hello! I'm Terry");
        System.out.println("    What can I do for you?");
        System.out.println(dottedLine);

        while(sc.hasNext()) {
            String userInput = sc.nextLine();
            if(userInput.equals("")) { //avoid empty line
                continue;
            }
            System.out.println(dottedLine);
            if (bye.equalsIgnoreCase(userInput)) { //bye
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(dottedLine);
                break;
            } else if(list.equalsIgnoreCase(userInput)) { //list
                if (currentTask == 0) {
                    System.out.println("    Chill! No task!");
                } else {
                    for (int i = 0; i < currentTask; i++) {
                        System.out.println("    " + (i + 1) + ". " + tasks[i]);
                    }
                }
            } else { //add
                tasks[currentTask] = userInput;
                currentTask++;
                System.out.println("    added: " + userInput);
            }
            System.out.println(dottedLine);
        }
    }
}
