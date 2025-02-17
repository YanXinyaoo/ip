package Terry.Command;

public class Deadlines extends Task{
    private String ddl;

    public Deadlines(String description, String ddl) {
        super(description);
        this.ddl = ddl;
    }

    public Deadlines(String description, String ddl, boolean isDone) {
        super(description, isDone);
        this.ddl = ddl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ddl + ")";
    }
}