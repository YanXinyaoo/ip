package Terry.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Terry.Exception.TerryException;

/**
 * Represents a task with a deadline.
 * This class extends the {@link Task} class and includes a deadline as part of the task.
 * It also provides functionality for setting the deadline and formatting it for display or file storage.
 */
public class Deadlines extends Task {
    protected LocalDateTime deadline;
    private String type = "D";

    /**
     * Constructs a Deadlines task with a description and a deadline.
     * @param description The description of the task.
     * @param deadlineString The deadline of the task in the format "d/M/yyyy HHmm".
     */
    public Deadlines(String description, String deadlineString) throws TerryException {
        super(description);
        setDeadline(deadlineString);
    }

    /**
     * Constructs a Deadlines task with a description, completion status, and a deadline.
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     * @param deadlineString The deadline of the task in the format "d/M/yyyy HHmm".
     */
    public Deadlines(String description, boolean isDone, String deadlineString) throws TerryException {
        super(description, isDone);
        setDeadline(deadlineString);
    }

    /**
     * Sets the deadline for the task by parsing the provided deadline string.
     * @param deadlineString The deadline of the task in the format "d/M/yyyy HHmm".
     * @throws TerryException If the deadline string is in an invalid format.
     */
    public void setDeadline(String deadlineString) throws TerryException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.deadline = LocalDateTime.parse(deadlineString, formatter);
        } catch (DateTimeParseException e) {
            throw new TerryException(TerryException.invalidDeadlineMessage());
        }
    }

    /**
     * Returns a string representation of the task in a format suitable for file storage.
     * @return A formatted string representing the task for file storage.
     */
    public String toFileFormat() {
        String deadlineTimeString = deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return type + " | " + (isDone ? "1" : "0") + " | " + description + " | " + deadlineTimeString;
    }

    /**
     * Returns a string representation of the task, including its description and deadline.
     * @return A formatted string representing the task for display purposes.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns the deadline of the task.
     * @return The {@link LocalDateTime} representing the task's deadline.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }
}
