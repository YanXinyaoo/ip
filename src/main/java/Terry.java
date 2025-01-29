import java.util.ArrayList;
import java.util.Scanner;

public class Terry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dottedLine = "    ____________________________________________________________";
        ArrayList<Task> tasks = new ArrayList<>();
        String bye = "bye";
        String list = "list";
        int currentTask = 0;

        System.out.println(dottedLine);
        System.out.println("    Hello! I'm Terry");
        System.out.println("    What can I do for you?");
        System.out.println(dottedLine);

        while(sc.hasNext()) {
            String userInput = sc.nextLine().trim();
            String[] parts = userInput.split(" ", 2);
            String command = parts[0].toLowerCase();
            Integer commandNumber = Integer.parseInt(parts.length > 1 ? parts[1] : "0");
            if (userInput.isEmpty()) continue; //skip empty line

            System.out.println(dottedLine);
            switch (command) {
            case "bye":
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(dottedLine);
                return;
            case "list":
                if (tasks.size() == 0) {
                    System.out.println("    Chill! No task!");
                } else {
                    System.out.println("    Here are the tasks in your list");
                    for (Task task : tasks) {
                        int index = tasks.indexOf(task);
                        System.out.println("    " + (index + 1) + "." + task.toString());
                    }
                }
                break;
            case "mark":
                if (commandNumber > 0 && commandNumber < tasks.size()) {
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + tasks.get(commandNumber).markAsDone());
                } else {
                    System.out.println("    Invalid task number!");
                }
                break;

            case "unmark":
                if (commandNumber > 0 && commandNumber < tasks.size()) {
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("      " + tasks.get(commandNumber).markAsUndone());
                } else {
                    System.out.println("    Invalid task number!");
                }
                break;
            default:
                tasks.add(new Task(userInput));
                System.out.println("    added: " + userInput);
            }
            System.out.println(dottedLine);
        }
    }
}
