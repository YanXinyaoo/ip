package Terry.Command;

import Terry.Exception.TerryException;
import Terry.Storage.Storage;
import Terry.UI.UI;
import Terry.Task.TaskManager;
import Terry.Task.Task;

/**
 * Represents a command to add a new task to the task manager.
 * This command allows a new task to be added to the list of tasks managed by the application.
 */
public class AddTaskCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddTaskCommand with the specified task to be added.
     *
     * @param task The task to be added to the task manager.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add task command to add the specified task to the task manager.
     * <p>
     * @param tasks   The TaskManager used to manage tasks.
     * @param ui      The UI used to display results or error messages (not used in this method).
     * @param storage The Storage used for saving/loading tasks (not used in this method).
     * @throws TerryException If an error occurs while adding the task to the TaskManager.
     */
    @Override
    public void execute(TaskManager tasks, UI ui, Storage storage) throws TerryException {
        tasks.addTask(task);
    }
}
