package Terry.Command;

import Terry.Storage.Storage;
import Terry.UI.UI;
import Terry.Task.TaskManager;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskManager tasks, UI ui, Storage storage) {
        ui.showExitMessage();
    }

    public boolean isExit() {
        return true;
    }
}
