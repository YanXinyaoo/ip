package Terry.UI;

import java.util.List;
import java.util.Scanner;

import Terry.Task.Task;

/**
 * Represents the user interface of the Terry application. This class handles
 * displaying messages to the user and receiving input from the user.
 * It provides various methods to show task lists, error messages, and other
 * relevant information to the user.
 */
public class UI {
    private static final String dottedLine = "  ------------------------------";
    private final Scanner scanner;

    /**
     * Constructs a new UI instance, initializing the scanner for user input.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user when the application starts.
     */
    public void showWelcome() {
        System.out.println(dottedLine);
        System.out.println("    Hello! I'm Terry");
        System.out.println("    What can I do for you?");
        System.out.println(dottedLine);
    }

    /**
     * Displays a message indicating the program is exiting.
     */
    public void showExitMessage() {
        System.out.println("    Goodbye! Hope to see you again.");
    }

    /**
     * Displays an error message to the user.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Displays a line separator to visually separate sections in the UI.
     */
    public void showLine() {
        System.out.println(dottedLine);
    }

    /**
     * Displays the list of tasks to the user.
     */
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

    /**
     * Displays a message confirming that a task has been added to the list.
     *
     * @param task       The task that was added.
     * @param totalTasks The total number of tasks after the new task has been added.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     */
    public void showTaskMarkedAsDone(Task task) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
    }

    /**
     * Displays a message confirming that a task has been marked as undone.
     */
    public void showTaskMarkedAsUndone(Task task) {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task);
    }

    /**
     * Displays a message confirming that a task has been deleted from the list.
     *
     * @param task       The task that was deleted.
     * @param totalTasks The total number of tasks after the deletion.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The command entered by the user, trimmed of leading/trailing spaces.
     */
    public String readCommand() {
        return scanner.hasNextLine() ? scanner.nextLine().trim() : "";
    }

    /**
     * Displays the result of a search, showing all tasks that match the search criteria.
     *
     * @param tasks The list of tasks that match the search query.
     */
    public void showFindResult(List<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("    Nothing is found!");
        } else {
            System.out.println("    Here are the matching tasks in your list:");
            for (Task task : tasks) {
                System.out.println("    " + task);
            }
        }
    }

    /**
     * Closes the scanner to prevent resource leaks.
     */
    public void closeScanner() {
        scanner.close();
    }
}
