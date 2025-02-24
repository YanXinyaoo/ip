package Terry.Command;

import Terry.Storage.Storage;
import Terry.UI.UI;
import Terry.Task.TaskManager;
import Terry.Exception.TerryException;

public class MarkUnmarkCommand extends Command {
    private final String command;
    private final String[] parts;

    public MarkUnmarkCommand(String[] parts, String command) {
        this.parts = parts;
        this.command = command;
    }

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
        } catch (TerryException e) {
            ui.showError(e.getMessage());
        }
    }
}
