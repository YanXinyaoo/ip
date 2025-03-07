package Terry.Exception;

/**
 * Represents a custom exception for the Terry application.
 * This exception is thrown when invalid user commands are encountered.
 * It also provides various static methods to return predefined error messages.
 */
public class TerryException extends Exception {

    /**
     * Constructs a new {@code TerryException} with the specified error message.
     *
     * @param message The detail message of the exception.
     */
    public TerryException(String message) {
        super(message);
    }

    /**
     * Returns an error message for when a "todo" command is missing a description.
     *
     * @return The error message string.
     */
    public static String todoErrorMessage() {
        return "   (╯°□°)╯︵ OOPS!!! A todo without a description? \n" +
                "   Please provide a valid description for the todo task. \n" +
                "   Example: `todo Finish homework`.";
    }

    /**
     * Returns an error message for an invalid "mark" or "unmark" command format.
     *
     * @return The error message string.
     */
    public static String markUnmarkErrorMessage() {
        return "   (╯°□°)╯︵ Invalid input format! \n" +
                "   Please use the correct format: `mark <task_number>` or `unmark <task_number>`. \n" +
                "   Example: `mark 1` or `unmark 3`.";
    }

    /**
     * Returns an error message when a task is already marked as done.
     *
     * @return The error message string.
     */
    public static String taskAlreadyMarked() {
        return "   (╯°□°)╯︵ Task already marked as done! You cannot mark it again.";
    }

    /**
     * Returns an error message when a task is not yet marked as done but attempted to be unmarked.
     *
     * @return The error message string.
     */
    public static String taskNotMarked() {
        return "   (⊙_☉) This task has not been marked as done yet! You cannot unmark it.";
    }

    /**
     * Returns an error message for an unknown command input.
     *
     * @return The error message string.
     */
    public static String unknownCommand() {
        return "   (￣o￣) . z Z  Huh? What was that?\n" +
                "   (╯°□°)╯︵ ┻━┻  I don’t understand your command!\n" +
                "   Let’s try something I know, like: \n" +
                "   `todo <task_description>`\n" +
                "   `list`\n" +
                "   `mark <task_number>`\n" +
                "   `unmark <task_number>`\n" +
                "   `delete <task_number>`\n" +
                "   Example: `todo Finish homework`, `list`, `mark 2`, `delete 3`.";
    }

    /**
     * Returns an error message when an invalid task number is provided.
     *
     * @param maxTaskNumber The maximum valid task number.
     * @return The error message string.
     */
    public static String invalidTaskNumberMessage(int maxTaskNumber) {
        return "     (ಠ_ಠ) Uh, that task number doesn’t exist...  \n" +
                "          Please enter a proper task number from 1 to " + maxTaskNumber + ".";
    }

    /**
     * Returns an error message when an invalid deadline format is used.
     *
     * @return The error message string.
     */
    public static String invalidDeadlineMessage() {
        return "   （╯°□°）╯︵( .o.)  Deadlines are serious business!  \n" +
                "   Use this format: `deadline description /by d/M/yyyy HHmm`  \n" +
                "   Let’s try again, champ!";
    }

    /**
     * Returns an error message when an invalid event format is used.
     *
     * @return The error message string.
     */
    public static String invalidEventMessage() {
        return "      (⊙_☉) Whoa! Events need clear timing:  \n" +
                "          Like this: `event description /from d/M/yyyy HHmm /to d/M/yyyy HHmm`  \n" +
                "      ༼ つ ಥ_ಥ ༽つ  Give me something I can work with, please!";
    }

    /**
     * Returns a general error message when an unexpected issue occurs.
     *
     * @return The error message string.
     */
    public static String generalError() {
        return "     (╯°□°）╯︵ ┻━┻  ERROR! I ran into something weird.  \n" +
                "     ┬─┬ノ( º _ ºノ)  Let’s calm down and try that again.";
    }

    /**
     * Returns an error message when loading tasks from a file fails.
     *
     * @return The error message string.
     */
    public static String showLoadingError() {
        return "   (╯°□°)╯︵ Loading failed! Something went wrong during the loading process.\n" +
                "   Please check the file and try again!";
    }

    /**
     * Returns an error message for an invalid task deletion command format.
     *
     * @return The error message string.
     */
    public static String deleteErrorMessage() {
        return "   (╯°□°)╯︵ Invalid task number! \n" +
                "   Please use the correct format: `delete <task_number>`. \n" +
                "   Example: `delete 2`.";
    }

    /**
     * Returns an error message for an invalid search command format.
     *
     * @return The error message string.
     */
    public static String findErrorMessage() {
        return "   (╯°□°)╯︵ Invalid input format! \n" +
                "   Please use the correct format: `find <keyword>`. \n" +
                "   Example: `find book`.";
    }

    /**
     * Returns an error message when no tasks are found within a specified time range.
     *
     * @return The error message string.
     */
    public static String noTasksInTimeRange() {
        return "   (╯°□°)╯︵ Please check if your time range is correct and try again. \n" +
                "   Example: `find /from 1/1/2023 1200 /to 31/12/2023 1800`.";
    }
}
