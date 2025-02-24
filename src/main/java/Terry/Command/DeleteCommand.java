package Terry.Command;

import Terry.Storage.Storage;
import Terry.UI.Terry;
import Terry.UI.UI;
import Terry.Task.TaskManager;
import Terry.Exception.TerryException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String indexStr) throws TerryException {
        try {
            this.index = Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            throw new TerryException(TerryException.deleteErrorMessage());
        }
    }

    @Override
    public void execute(TaskManager taskManager, UI ui, Storage storage) throws TerryException {
        taskManager.deleteTask(index);
    }
}
