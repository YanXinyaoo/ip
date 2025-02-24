package Terry.Command;

import Terry.Storage.Storage;
import Terry.UI.UI;
import Terry.Task.TaskManager;

/**
 * Represents a command to exit the application.
 * This command is used to terminate the application by displaying an exit message to the user.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command to display the exit message and terminate the application.
     * @param tasks   The TaskManager used for task management (not used in this method).
     * @param ui      The UI used to display the exit message.
     * @param storage The Storage used for saving/loading tasks (not used in this method).
     */
    @Override
    public void execute(TaskManager tasks, UI ui, Storage storage) {
        ui.showExitMessage();
    }

    /**
     * Returns true, indicating that this command results in the application exiting.
     * @return A boolean indicating that the application should exit after this command.
     */
    public boolean isExit() {
        return true;
    }
}
