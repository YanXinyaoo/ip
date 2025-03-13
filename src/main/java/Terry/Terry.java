package Terry;

import Terry.Command.Command;
import Terry.Exception.TerryException;
import Terry.Parse.Parser;
import Terry.Storage.Storage;
import Terry.Task.TaskManager;
import Terry.UI.UI;

/**
 * The main entry point of the Terry application. This class initializes
 * necessary components and manages the flow of the program execution.
 * It handles reading commands, parsing them, executing the appropriate tasks,
 * and managing the exit condition.
 */
public class Terry {
    private final UI ui;
    private final Storage storage;
    private TaskManager tasks;
    private Parser parser;

    /**
     * Constructs a new Terry application instance.
     *
     * @param filePath The file path where the task data is stored.
     * @throws TerryException If an error occurs during initialization or data loading.
     */
    public Terry(String filePath) throws TerryException {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskManager(storage.loadTasks(), ui);
            parser = new Parser(tasks, ui, storage);
        } catch (TerryException e) {
            ui.showError(e.getMessage());
            tasks = new TaskManager(ui);
        }
    }

    /**
     * Runs the Terry application by displaying the welcome message,
     * reading user commands, and executing the respective actions
     * until the user decides to exit.
     *
     * The program continuously reads commands from the user, parses them,
     * and executes the corresponding task, while handling any exceptions that occur.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TerryException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.closeScanner();
    }

    /**
     * The entry point for the program, initializing the Terry application
     * and starting the execution.
     */
    public static void main(String[] args) throws TerryException {
        new Terry("./data/Terry.txt").run();
    }
}
