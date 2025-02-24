package Terry.UI;

import java.util.List;
import java.util.Scanner;

import Terry.Task.Task;

public class UI {
    private static final String dottedLine = "  ------------------------------";
    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(dottedLine);
        System.out.println("    Hello! I'm Terry");
        System.out.println("    What can I do for you?");
        System.out.println(dottedLine);
    }

    public void showExitMessage() {
        System.out.println("    Goodbye! Hope to see you again.");
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public void showLine() {
        System.out.println(dottedLine);
    }

    public void showNoTasks() {
        System.out.println("    Chill! No task!");
    }

    public void showTaskList(List<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("    Chill! No task!");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskMarkedAsDone(Task task) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
    }

    public void showTaskMarkedAsUndone(Task task) {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task);
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
    }

    public String readCommand() {
        return scanner.hasNextLine() ? scanner.nextLine().trim() : "";
    }

    public void closeScanner() {
        scanner.close();
    }
}
