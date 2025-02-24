package Terry.Parse;

import Terry.Command.Command;
import Terry.Task.TaskManager;
import Terry.UI.UI;
import Terry.Storage.Storage;
import Terry.Task.*;
import Terry.Exception.TerryException;
import Terry.Command.*;
import java.time.format.DateTimeParseException;

public class Parser {

    private final TaskManager taskManager;
    private final UI ui;
    private final Storage storage;

    private static final String dottedLine = "------------------------------";

    public Parser(TaskManager taskManager, UI ui, Storage storage) {
        this.taskManager = taskManager;
        this.ui = ui;
        this.storage = storage;
    }

    public Command parse(String input) throws TerryException {
        if (input.isEmpty()) return null;

        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String description = parts.length > 1 ? parts[1] : null;

        Command cmd = getCommand(command, parts, description, input);
        if (cmd == null) {
            throw new TerryException(TerryException.generalError());
        }

        return cmd;
    }

    private Command getCommand(String command, String[] parts, String description, String input) throws TerryException {
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
        case "unmark":
            return new MarkUnmarkCommand(parts, command);
        case "todo":
            return createTodoCommand(description);
        case "deadline":
            return createDeadlineCommand(description, input);
        case "event":
            return createEventCommand(description, input);
        case "delete":
            return createDeleteCommand(description);  
        case "find":
            if(description.contains("/from")) {
                String[] dates = description.split("/from|/to");
                return new FindTasksInTimeRangeCommand(dates[1].trim(), dates[2].trim());
            } else {
              return createFindCommand(description);
            }

        default:
            return null;
        }
    }

    private Command createDeleteCommand(String description) throws TerryException {
        if(description == null) {
            throw new TerryException(TerryException.deleteErrorMessage());
        } else {
            return new DeleteCommand(description);
        }
    }

    private Command createFindCommand(String description) throws TerryException {
        if(description == null) {
            throw new TerryException(TerryException.findErrorMessage());
        } else {
            return new FindCommand(description);
        }
    }

    private Command createTaskCommand(Task task) {
        return new AddTaskCommand(task);
    }

    private Command createTodoCommand(String description) throws TerryException {
        if(description == null) {
            throw new TerryException(TerryException.todoErrorMessage());
        } else {
            return createTaskCommand(new ToDos(description));
        }
    }

    private Command createDeadlineCommand(String description, String input) throws TerryException {
        if (!input.contains("/by ")) {
            throw new TerryException(TerryException.invalidDeadlineMessage());
        }
        try {
            String[] partDeadline = description.split("/by ", 2);
            return createTaskCommand(new Deadlines(partDeadline[0].trim(), partDeadline[1].trim()));
        } catch (DateTimeParseException e) {
            throw new TerryException(TerryException.invalidDeadlineMessage());
        }
    }

    private Command createEventCommand(String description, String input) throws TerryException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new TerryException(TerryException.invalidEventMessage());
        }

        String[] partEvent = description.split("/from|/to");
        if (partEvent.length < 3) {
            throw new TerryException(TerryException.invalidEventMessage());
        }
        try {
            String startTimeString = partEvent[1].trim();
            String endTimeString = partEvent[2].trim();
            return createTaskCommand(new Events(partEvent[0].trim(), startTimeString, endTimeString));
        } catch (DateTimeParseException e) {
            throw new TerryException(TerryException.invalidEventMessage());
        }
    }
}
