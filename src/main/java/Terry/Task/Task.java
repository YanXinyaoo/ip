package Terry.Task;

/**
 * Represents a task with a description and a completion status.
 * This class provides functionality to mark the task as done or undone,
 * retrieve its status, and format it for display or file storage.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type = "T";

    /**
     * Constructs a Task with a description. The task is initially marked as not done.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with a description and a specified completion status.
     * @param description The description of the task.
     * @param isDone The completion status of the task. True if done, false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon representing the completion status of the task.
     * @return The status icon ("X" or " ").
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done and returns the updated task.
     * @return The updated task with the completion status set to true.
     */
    public Task markAsDone() {
        isDone = true;
        return this;
    }

    /**
     * Marks the task as undone and returns the updated task.
     * @return The updated task with the completion status set to false.
     */
    public Task markAsUndone() {
        this.isDone = false;
        return this;
    }

    /**
     * Returns a string representation of the task in a format suitable for file storage.
     * The returned string includes the task's completion status (1 for done, 0 for undone) and the description.
     * @return A formatted string representing the task for file storage.
     */
    public String toFileFormat() {
        return  " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task for display.
     * The returned string includes the status icon (X for done, blank for undone) and the description.
     * @return A string representing the task for display purposes.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
