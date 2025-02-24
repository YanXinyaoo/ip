package Terry.Command;

import Terry.Storage.Storage;
import Terry.UI.Terry;
import Terry.UI.UI;
import Terry.Task.TaskManager;
import Terry.Exception.TerryException;

/**
 * Represents a command to delete a task by its index.
 * This command allows the user to delete a task from the task manager by specifying
 * its index. If the index is invalid, an error message will be displayed.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     * @param indexStr The string representation of the index of the task to be deleted.
     * @throws TerryException If the index is invalid or cannot be parsed into an integer.
     */
    public DeleteCommand(String indexStr) throws TerryException {
        try {
            this.index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw new TerryException(TerryException.deleteErrorMessage());
        }
    }

    /**
     * Executes the delete command to remove the task at the specified index.
     * @param taskManager The TaskManager used to delete the task at the specified index.
     * @param ui          The UI used to display any error or success messages (not used in this method).
     * @param storage     The storage used for saving/loading tasks (not used in this method).
     * @throws TerryException If an error occurs while deleting the task.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui, Storage storage) throws TerryException {
        taskManager.deleteTask(index);
    }
}
