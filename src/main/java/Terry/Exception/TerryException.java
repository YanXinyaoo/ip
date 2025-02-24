package Terry.Exception;

public class TerryException extends Exception {
    public TerryException(String message) {
        super(message);
    }

    public static String todoErrorMessage() {
        return "   (╯°□°)╯︵ OOPS!!! A todo without a description? \n" +
                "   Please provide a valid description for the todo task. \n" +
                "   Example: `todo Finish homework`.";
    }

    public static String markUnmarkErrorMessage() {
        return "   (╯°□°)╯︵ Invalid input format! \n" +
                "   Please use the correct format: `mark <task_number>` or `unmark <task_number>`. \n" +
                "   Example: `mark 1` or `unmark 3`.";
    }

    public static String taskAlreadyMarked() {
        return "   (╯°□°)╯︵ Task already marked as done! You cannot mark it again.";
    }

    public static String taskNotMarked() {
        return "   (⊙_☉) This task has not been marked as done yet! You cannot unmark it.";
    }

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

    public static String invalidTaskNumberMessage(int maxTaskNumber) {
        return "     (ಠ_ಠ) Uh, that task number doesn’t exist...  \n" +
                "          Please enter a proper task number from 1 to " + maxTaskNumber + ".";
    }

    public static String invalidDeadlineMessage() {
        return "   （╯°□°）╯︵( .o.)  Deadlines are serious business!  \n" +
                "   Use this format: `deadline description /by d/M/yyyy HHmm`  \n" +
                "   Let’s try again, champ!";
    }

    public static String invalidEventMessage() {
        return "      (⊙_☉) Whoa! Events need clear timing:  \n" +
                "          Like this: `event description /from d/M/yyyy HHmm /to d/M/yyyy HHmm`  \n" +
                "      ༼ つ ಥ_ಥ ༽つ  Give me something I can work with, please!";
    }

    public static String generalError() {
        return "     (╯°□°）╯︵ ┻━┻  ERROR! I ran into something weird.  \n" +
                "     ┬─┬ノ( º _ ºノ)  Let’s calm down and try that again.";
    }

    public static String showLoadingError() {
       return "   (╯°□°)╯︵ Loading failed! Something went wrong during the loading process.\n" +
                "   Please check the file and try again!";
    }

    public static String deleteErrorMessage() {
        return "   (╯°□°)╯︵ Invalid task number! \n" +
                "   Please use the correct format: `delete <task_number>`. \n" +
                "   Example: `delete 2`.";
    }

    public static String noTasksInTimeRange() {
        return "   (╯°□°)╯︵ Please check if your time range is correct and try again. \n" +
                "   Example: `find /from 1/1/2023 1200 /to 31/12/2023 1800`.";
    }

}
