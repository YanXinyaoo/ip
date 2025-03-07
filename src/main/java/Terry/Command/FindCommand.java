package Terry.Command;

import Terry.Storage.Storage;
import Terry.UI.UI;
import Terry.Task.TaskManager;

/**
 * Represents a command to find tasks by a specific keyword.
 * This command searches for tasks that contain the specified keyword in their description.
 */
public class FindCommand extends Command {
    private final String description;

    /**
     * Constructs a FindCommand with the specified description keyword.
     * @param description The keyword to search for in task descriptions.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the find command to search for tasks containing the given description keyword.
     * @param tasks   The TaskManager used to search for tasks by the description keyword.
     * @param ui      The UI used to display the tasks found that match the keyword.
     * @param storage The storage used for saving/loading tasks (not used in this method).
     */
    @Override
    public void execute(TaskManager tasks, UI ui, Storage storage) {
        tasks.findTasksByKeyword(description);
    }
}
