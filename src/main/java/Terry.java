import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Terry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dottedLine = "    ____________________________________________________________";
        String bye = "bye";
        String list = "list";

        System.out.println(dottedLine);
        System.out.println("    Hello! I'm Terry");
        System.out.println("    What can I do for you?");
        System.out.println(dottedLine);

        TaskManager taskManager = new TaskManager();

        while (sc.hasNext()) {
            String userInput = sc.nextLine().trim();
            String[] parts = userInput.split(" ", 2);
            String command = parts[0].toLowerCase();
            if (userInput.isEmpty()) continue; //skip empty line

            System.out.println(dottedLine);

            switch (command) {
            case "bye":
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(dottedLine);
                return;
            case "list":
                taskManager.listTasks();
                break;
            case "mark":
                Integer markNumber = Integer.parseInt(parts.length > 1 ? parts[1] : "0");
                taskManager.markTaskAsDone(markNumber);
                break;
            case "unmark":
                Integer unmarkNumber = Integer.parseInt(parts.length > 1 ? parts[1] : "0");
                taskManager.markTaskAsUndone(unmarkNumber);
                break;
            case "todo":
                taskManager.addTask(new ToDos(parts[1]));
                break;
            case "deadline":
                String[] partDeadline = userInput.split("/by ", 2);
                taskManager.addTask(new Deadlines(partDeadline[0], partDeadline[1]));
                break;
            case "event":
                String[] partEvent = userInput.split("/from|/to");
                taskManager.addTask(new Events(partEvent[0], partEvent[1], partEvent[2]));
                break;
            default:
            }
            System.out.println(dottedLine);
        }
    }
}
