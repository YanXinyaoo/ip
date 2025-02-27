package Terry.Task;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
        this.type = "T";
    }

    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileFormat() {
        return this.type + super.toFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}