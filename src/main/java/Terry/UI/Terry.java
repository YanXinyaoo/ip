package Terry.UI;

import Terry.Command.Command;
import Terry.Exception.TerryException;
import Terry.Parse.Parser;
import Terry.Storage.Storage;
import Terry.Task.TaskManager;
import Terry.UI.UI;

public class Terry {
    private final UI ui;
    private final Storage storage;
    private TaskManager tasks;
    private Parser parser;

    public Terry(String filePath) {
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

    public static void main(String[] args) {
        new Terry("./data/Terry.txt").run();
    }
}
