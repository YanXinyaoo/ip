package Terry.Command;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}