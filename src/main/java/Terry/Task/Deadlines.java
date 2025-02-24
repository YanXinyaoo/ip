package Terry.Task;

public class Deadlines extends Task{
    private String deadline;
    protected String type = "D";

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadlines(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public String toFileFormat() {
        return this.type + super.toFileFormat() + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[" + this.type +"]" + super.toString() + " (by: " + deadline + ")";
    }
}