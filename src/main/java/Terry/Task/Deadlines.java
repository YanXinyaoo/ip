package Terry.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Terry.Exception.TerryException;

public class Deadlines extends Task {
    protected LocalDateTime deadline;
    private String type = "D";

    public Deadlines(String description, String deadlineString) throws TerryException {
        super(description);
        setDeadline(deadlineString);
    }

    public Deadlines(String description, boolean isDone, String deadlineString) throws TerryException {
        super(description, isDone);
        setDeadline(deadlineString);
    }

    public void setDeadline(String deadlineString) throws TerryException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.deadline = LocalDateTime.parse(deadlineString, formatter);
        } catch (DateTimeParseException e) {
            throw new TerryException(TerryException.invalidDeadlineMessage());
        }
    }

    public String toFileFormat() {
        String deadlineTimeString = deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return type + " | " + (isDone ? "1" : "0") + " | " + description + " | " + deadlineTimeString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
}
