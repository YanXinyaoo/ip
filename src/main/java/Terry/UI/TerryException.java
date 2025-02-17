package Terry.UI;

public class TerryException extends Exception {
    public TerryException(String message) {
        super(message);
    }

    public static String todoErrorMessage() {
        return "    ╭(°□°╭)   OOPS!!! A todo without a description?\n" +
                "    (╯°□°)╯  Sounds like you're trying to achieve... nothing?\n" +
                "    Please tell me what needs to be done!";
    }

    public static String unknownCommand() {
        return "   (￣o￣) . z Z  Huh? What was that?\n" +
                "   (╯°□°)╯︵ ┻━┻  I don’t understand your command!\n" +
                "   Let’s try something I know, like todo, list, mark";
    }

    public static String invalidTaskNumberMessage(int maxTaskNumber) {
        return "     (ಠ_ಠ) Uh, that task number doesn’t exist...  \n" +
                "          Please enter a proper task number from 1 to " + maxTaskNumber + ".";
    }

    public static String invalidDeadlineMessage() {
        return "   （╯°□°）╯︵( .o.)  Deadlines are serious business!  \n" +
                "   Use this format: `deadline Homework /by tonight`  \n" +
                "   Let’s try again, champ!";
    }

    public static String invalidEventMessage() {
        return "      (⊙_☉) Whoa! Events need clear timing:  \n" +
                "          Like this: `event Meeting /from 10am /to 12pm`  \n" +
                "      ༼ つ ಥ_ಥ ༽つ  Give me something I can work with, please!";
    }

    public static String generalError() {
        return "     (╯°□°）╯︵ ┻━┻  ERROR! I ran into something weird.  \n" +
                "     ┬─┬ノ( º _ ºノ)  Let’s calm down and try that again.";
    }
}
