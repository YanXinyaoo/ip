package Terry.Task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Terry.Exception.TerryException;

public class Events extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    private String type = "E";

    public Events(String description, String startTimeString, String endTimeString) throws TerryException {
        super(description);
        setStartTime(startTimeString);
        setEndTime(endTimeString);
    }

    public Events(String description, boolean isDone, String startTimeString, String endTimeString) throws TerryException {
        super(description, isDone);
        setStartTime(startTimeString);
        setEndTime(endTimeString);
    }

    public void setStartTime(String startTimeString) throws TerryException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.startTime = LocalDateTime.parse(startTimeString, formatter);
        } catch (DateTimeException e) {
            throw new TerryException(TerryException.invalidEventMessage());
        }
    }

    public void setEndTime(String endTimeString) throws TerryException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.endTime = LocalDateTime.parse(endTimeString, formatter);
        } catch (DateTimeException e) {
            throw new TerryException(TerryException.invalidEventMessage());
        }
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String toFileFormat() {
        String startTimeString = startTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        String endTimeString = endTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

        return type + " | " + (isDone ? "1" : "0") + " | " + description + " | " + startTimeString + " | " + endTimeString;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (from: " + startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                " to: " + endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
