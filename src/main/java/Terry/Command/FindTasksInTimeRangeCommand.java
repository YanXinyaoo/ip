package Terry.Command;

import Terry.Storage.Storage;
import Terry.Task.Task;
import Terry.Exception.TerryException;
import Terry.Task.TaskManager;
import Terry.UI.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FindTasksInTimeRangeCommand extends Command {
    private String startDateString;
    private String endDateString;

    public FindTasksInTimeRangeCommand(String startDateString, String endDateString) {
        this.startDateString = startDateString;
        this.endDateString = endDateString;
    }

    @Override
    public void execute(TaskManager tasks, UI ui, Storage storage) throws TerryException {
        tasks.findTaskInTimeRange(startDateString, endDateString);
    }
}
