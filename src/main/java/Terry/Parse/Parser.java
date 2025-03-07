package Terry.Parse;

import Terry.Command.Command;
import Terry.Task.TaskManager;
import Terry.UI.UI;
import Terry.Storage.Storage;
import Terry.Task.*;
import Terry.Exception.TerryException;
import Terry.Command.*;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for interpreting user input and converting it into commands.
 */
public class Parser {

    private final TaskManager taskManager;
    private final UI ui;
    private final Storage storage;

    private static final String dottedLine = "------------------------------";

    /**
     * Constructs a Parser with the given TaskManager, UI, and Storage.
     *
     * @param taskManager The TaskManager instance handling tasks.
     * @param ui The UI instance for displaying messages.
     * @param storage The Storage instance handling file operations.
     */
    public Parser(TaskManager taskManager, UI ui, Storage storage) {
        this.taskManager = taskManager;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input as a string.
     * @return A Command object corresponding to the user input.
     * @throws TerryException If the input is invalid or unrecognized.
     */
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

    /**
     * Determines and returns the appropriate command based on the user input.
     *
     * @param command The command keyword.
     * @param parts The split parts of the input.
     * @param description The task description (if any).
     * @param input The full user input.
     * @return The corresponding Command object.
     * @throws TerryException If the command is invalid or incomplete.
     */
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
            if (description.toLowerCase().contains("/from") && description.toLowerCase().contains("/to")) {
                String[] dates = description.toLowerCase().split("/from|/to");
                return new FindTasksInTimeRangeCommand(dates[1].trim(), dates[2].trim());
            } else {
                return createFindCommand(description);
            }
        default:
            return null;
        }
    }

    /**
     * Creates a DeleteCommand.
     *
     * @param description The task description.
     * @return A DeleteCommand instance.
     * @throws TerryException If the description is missing.
     */
    private Command createDeleteCommand(String description) throws TerryException {
        if (description == null) {
            throw new TerryException(TerryException.deleteErrorMessage());
        }
        return new DeleteCommand(description);
    }

    /**
     * Creates a FindCommand.
     *
     * @param description The search query.
     * @return A FindCommand instance.
     * @throws TerryException If the description is missing.
     */
    private Command createFindCommand(String description) throws TerryException {
        if (description == null) {
            throw new TerryException(TerryException.findErrorMessage());
        }
        return new FindCommand(description);
    }

    /**
     * Creates an AddTaskCommand for a given task.
     *
     * @param task The task to be added.
     * @return An AddTaskCommand instance.
     */
    private Command createTaskCommand(Task task) {
        return new AddTaskCommand(task);
    }

    /**
     * Creates a ToDo task command.
     *
     * @param description The task description.
     * @return An AddTaskCommand for a ToDo task.
     * @throws TerryException If the description is missing.
     */
    private Command createTodoCommand(String description) throws TerryException {
        if (description == null) {
            throw new TerryException(TerryException.todoErrorMessage());
        }
        return createTaskCommand(new ToDos(description));
    }

    /**
     * Creates a Deadline task command.
     *
     * @param description The task description.
     * @param input The full user input.
     * @return An AddTaskCommand for a Deadline task.
     * @throws TerryException If the deadline format is invalid.
     */
    private Command createDeadlineCommand(String description, String input) throws TerryException {
        if (!input.toLowerCase().contains("/by ")) {
            throw new TerryException(TerryException.invalidDeadlineMessage());
        }
        try {
            String[] partDeadline = description.split("/by ", 2);
            return createTaskCommand(new Deadlines(partDeadline[0].trim(), partDeadline[1].trim()));
        } catch (DateTimeParseException e) {
            throw new TerryException(TerryException.invalidDeadlineMessage());
        }
    }

    /**
     * Creates an Event task command.
     *
     * @param description The task description.
     * @param input The full user input.
     * @return An AddTaskCommand for an Event task.
     * @throws TerryException If the event format is invalid.
     */
    private Command createEventCommand(String description, String input) throws TerryException {
        if (!input.toLowerCase().contains("/from") || !input.toLowerCase().contains("/to")) {
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
