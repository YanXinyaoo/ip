package Terry.Command;

import Terry.Storage.Storage;
import Terry.UI.UI;
import Terry.Task.TaskManager;
import Terry.Exception.TerryException;

/**
 * Represents a command to mark or unmark a task as done or undone.
 *
 * This command can either mark a task as done or unmark a task, depending on the provided
 * command ("mark" or "unmark") and the task number.
 */
public class MarkUnmarkCommand extends Command {
    private final String command;
    private final String[] parts;

    /**
     * Constructs a MarkUnmarkCommand with the given command and parts.
     *
     * @param parts  The parts of the command, where parts[1] is the task number.
     * @param command The command indicating whether to mark or unmark the task.
     */
    public MarkUnmarkCommand(String[] parts, String command) {
        this.parts = parts;
        this.command = command;
    }

    /**
     * Executes the mark or unmark command to update the task status.
     * If the command is "mark", it marks the task as done. If the command is "unmark", it
     * marks the task as undone. If the input is invalid (e.g., missing task number or invalid
     * number format), an error message is displayed to the user.
     *
     * @param tasks  The TaskManager that handles the tasks.
     * @param ui     The UI used to show error messages.
     * @param storage The storage that may be used for saving/loading tasks (not used in this method).
     */
    @Override
    public void execute(TaskManager tasks, UI ui, Storage storage)  {
        try {
            if (parts.length < 2) {
                throw new TerryException(TerryException.markUnmarkErrorMessage());
            }
            int number = Integer.parseInt(parts[1]);
            if (command.equals("mark")) {
                tasks.markTaskAsDone(number);
            } else {
                tasks.markTaskAsUndone(number);
            }
        } catch (NumberFormatException e) {
            ui.showError("Invalid task number format. Please enter a valid number.");
        } catch (TerryException e) {
            ui.showError(e.getMessage());
        }
    }
}
