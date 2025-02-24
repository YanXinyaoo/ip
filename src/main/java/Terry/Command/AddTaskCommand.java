package Terry.Command;

import Terry.Exception.TerryException;
import Terry.Storage.Storage;
import Terry.UI.UI;
import Terry.Task.TaskManager;
import Terry.Task.Task;

public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskManager tasks, UI ui, Storage storage) throws TerryException {
        tasks.addTask(task);
    }
}
