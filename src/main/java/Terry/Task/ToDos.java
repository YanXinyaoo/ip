package Terry.Task;

/**
 * Represents a ToDo task, which is a simple task that can be marked as done or undone.
 * This class extends the {@link Task} class and provides functionality to
 * format the task for display or file storage in a way specific to ToDo tasks.
 */
public class ToDos extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     * The task is initially marked as not done.
     * @param description The description of the ToDo task.
     */
    public ToDos(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Constructs a ToDo task with the specified description and completion status.
     * @param description The description of the ToDo task.
     * @param isDone The completion status of the task. True if done, false otherwise.
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the ToDo task in a format suitable for file storage.
     * @return A formatted string representing the ToDo task for file storage.
     */
    @Override
    public String toFileFormat() {
        return this.type + super.toFileFormat();
    }

    /**
     * Returns a string representation of the ToDo task for display.
     * @return A string representing the ToDo task for display purposes.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
