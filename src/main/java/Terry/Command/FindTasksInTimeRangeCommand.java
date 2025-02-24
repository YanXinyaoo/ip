package Terry.Command;

import Terry.Storage.Storage;
import Terry.Task.Task;
import Terry.Exception.TerryException;
import Terry.Task.TaskManager;
import Terry.UI.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents a command to find tasks within a specified time range.
 * <p>
 * This command allows the user to search for tasks that fall within a given start and end date range.
 */
public class FindTasksInTimeRangeCommand extends Command {
    private String startDateString;
    private String endDateString;

    /**
     * Constructs a FindTasksInTimeRangeCommand with the specified start and end date strings.
     *
     * @param startDateString The start date of the time range in string format.
     * @param endDateString   The end date of the time range in string format.
     */
    public FindTasksInTimeRangeCommand(String startDateString, String endDateString) {
        this.startDateString = startDateString;
        this.endDateString = endDateString;
    }

    /**
     * Executes the command to find tasks that fall within the specified time range.
     *
     * @param tasks   The TaskManager used to find tasks within the given time range.
     * @param ui      The UI used to display the tasks found within the time range.
     * @param storage The storage used for saving/loading tasks (not used in this method).
     * @throws TerryException If an error occurs while finding tasks in the specified time range.
     */
    @Override
    public void execute(TaskManager tasks, UI ui, Storage storage) throws TerryException {
        tasks.findTaskInTimeRange(startDateString, endDateString);
    }
}
