package Terry.Command;
import Terry.Exception.TerryException;
import Terry.Task.TaskManager;
import Terry.Storage.Storage;
import Terry.UI.UI;

abstract public class Command {
    public abstract void execute(TaskManager tasks, UI ui, Storage storage) throws TerryException;

    public boolean isExit() {
        return false;
    }
}
