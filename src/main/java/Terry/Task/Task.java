package Terry.Task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type = "T";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public Task markAsDone() {
        isDone = true;
        return this;
    }

    public Task markAsUndone() {
        this.isDone = false;
        return this;
    }

    public String toFileFormat() {
        return  " | " + (isDone ? "1" : "0") + " | " + description;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}