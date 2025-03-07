package Terry.Task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Terry.Exception.TerryException;

/**
 * Represents a task that occurs during a specific time range (event).
 * This class extends the {@link Task} class and includes a start time and end time as part of the event.
 * It also provides functionality for setting the start and end times, formatting them for display or file storage, and retrieving the times.
 */
public class Events extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    private String type = "E";

    /**
     * Constructs an Events task with a description, start time, and end time.
     * @param description The description of the event.
     * @param startTimeString The start time of the event in the format "d/M/yyyy HHmm".
     * @param endTimeString The end time of the event in the format "d/M/yyyy HHmm".
     */
    public Events(String description, String startTimeString, String endTimeString) throws TerryException {
        super(description);
        setStartTime(startTimeString);
        setEndTime(endTimeString);
    }

    /**
     * Constructs an Events task with a description, completion status, start time, and end time.
     * @param description The description of the event.
     * @param isDone The completion status of the event.
     * @param startTimeString The start time of the event in the format "d/M/yyyy HHmm".
     * @param endTimeString The end time of the event in the format "d/M/yyyy HHmm".
     */
    public Events(String description, boolean isDone, String startTimeString, String endTimeString) throws TerryException {
        super(description, isDone);
        setStartTime(startTimeString);
        setEndTime(endTimeString);
    }

    /**
     * Sets the start time for the event by parsing the provided start time string.
     * @param startTimeString The start time of the event in the format "d/M/yyyy HHmm".
     * @throws TerryException If the start time string is in an invalid format.
     */
    public void setStartTime(String startTimeString) throws TerryException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.startTime = LocalDateTime.parse(startTimeString, formatter);
        } catch (DateTimeException e) {
            throw new TerryException(TerryException.invalidEventMessage());
        }
    }

    /**
     * Sets the end time for the event by parsing the provided end time string.
     * @param endTimeString The end time of the event in the format "d/M/yyyy HHmm".
     * @throws TerryException If the end time string is in an invalid format.
     */
    public void setEndTime(String endTimeString) throws TerryException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.endTime = LocalDateTime.parse(endTimeString, formatter);
        } catch (DateTimeException e) {
            throw new TerryException(TerryException.invalidEventMessage());
        }
    }

    /**
     * Returns the start time of the event.
     * @return The {@link LocalDateTime} representing the event's start time.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the event.
     * @return The {@link LocalDateTime} representing the event's end time.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns a string representation of the event in a format suitable for file storage.
     * @return A formatted string representing the event for file storage.
     */
    public String toFileFormat() {
        String startTimeString = startTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        String endTimeString = endTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

        return type + " | " + (isDone ? "1" : "0") + " | " + description + " | " + startTimeString + " | " + endTimeString;
    }

    /**
     * Returns a string representation of the event, including its description, start time, and end time.
     * @return A formatted string representing the event for display purposes.
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (from: " + startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                " to: " + endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
