package Terry.Command;

import Terry.Storage.Storage;
import Terry.UI.UI;
import Terry.Task.TaskManager;

public class FindCommand extends Command {
    private final String description;

    public FindCommand(String description) {
        this.description = description;
    }
    @Override
    public void execute(TaskManager tasks, UI ui, Storage storage) {
        tasks.findTasksByKeyword(description);
    }
}