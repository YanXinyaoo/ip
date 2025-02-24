package Terry.Command;

import Terry.Storage.Storage;
import Terry.UI.UI;
import Terry.Task.TaskManager;

/**
 * Represents a command to list all tasks.
 * <p>
 * This command displays all the tasks in the task manager.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to display all tasks.
     * @param tasks  The TaskManager that contains and manages the tasks.
     * @param ui     The UI used to display the list of tasks to the user.
     * @param storage The storage used for saving/loading tasks (not used in this method).
     */
    @Override
    public void execute(TaskManager tasks, UI ui, Storage storage) {
        tasks.listTasks();
    }
}
