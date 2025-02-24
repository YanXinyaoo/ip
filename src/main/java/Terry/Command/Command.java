package Terry.Command;

import Terry.Exception.TerryException;
import Terry.Task.TaskManager;
import Terry.Storage.Storage;
import Terry.UI.UI;

/**
 * Represents an abstract command that can be executed.
 * This class serves as a base class for various specific commands that can be executed
 * within the application. Each command must implement the {@link #execute(TaskManager, UI, Storage)} method.
 */
abstract public class Command {

    /**
     * Executes the command, performing the action specified by the command.
     * @param tasks   The TaskManager used to manage tasks in the application.
     * @param ui      The UI used to display results or messages to the user.
     * @param storage The Storage used for saving and loading task data.
     * @throws TerryException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskManager tasks, UI ui, Storage storage) throws TerryException;

    /**
     * Returns whether this command indicates the application should exit.
     * @return A boolean indicating whether the application should exit after this command is executed.
     */
    public boolean isExit() {
        return false;
    }
}
