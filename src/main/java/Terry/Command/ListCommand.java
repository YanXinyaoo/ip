package Terry.Command;

import Terry.Storage.Storage;
import Terry.UI.UI;
import Terry.Task.TaskManager;

public class ListCommand extends Command {
    @Override
    public void execute(TaskManager tasks, UI ui, Storage storage) {
        tasks.listTasks();
    }
}
