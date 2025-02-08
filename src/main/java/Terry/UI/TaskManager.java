package Terry.UI;

import java.util.ArrayList;

import Terry.Command.Task;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
    }

    public void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("    Chill! No task!");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void markTaskAsDone(int index) {
        Task task = tasks.get(index - 1).markAsDone();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
    }

    public void markTaskAsUndone(int index) {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + tasks.get(index - 1).markAsUndone());
    }

    public int taskNumber() {
        return this.tasks.size();
    }

    public boolean taskExists(int number) {
        return number > 0 && number <= this.tasks.size();
    }
}