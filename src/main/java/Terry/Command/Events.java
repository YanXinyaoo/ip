package Terry.Command;

public class Events extends Task {
    private String from;
    private String to;
    protected String type = "E";

    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    public Events(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    public String toFileFormat() {
        return this.type + super.toFileFormat() + "| " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}