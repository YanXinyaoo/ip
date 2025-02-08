package Terry.UI;

import java.util.Scanner;

import Terry.Command.Deadlines;
import Terry.Command.Events;
import Terry.Command.ToDos;

public class Terry {
    public static void main(String[] args) throws TerryException {
        Scanner sc = new Scanner(System.in);
        String dottedLine = "    ____________________________________________________________";
        String dottedLineNextLine = "\n" + dottedLine;
        String bye = "bye";
        String list = "list";
        String todoErrorMessage = "    ╭(°□°╭)   OOPS!!! A todo without a description?\n" +
                                  "    (╯°□°)╯  Sounds like you're trying to achieve... nothing?\n" +
                                  "    Please tell me what needs to be done!";
        String unknownCommand = "   (￣o￣) . z Z  Huh? What was that?\n" +
                                "   (╯°□°)╯︵ ┻━┻  I don’t understand your command!\n" +
                                "   Let’s try something I know, like todo, list, mark";
        String invalidTaskNumberMessage = "     (ಠ_ಠ) Uh, that task number doesn’t exist...  \n" +
                                          "          Please enter a proper task number.";
        String invalidDeadlineMessage = "   （╯°□°）╯︵( .o.)  Deadlines are serious business!  \n" +
                                        "   Use this format: `deadline Homework /by tonight`  \n" +
                                        "   Let’s try again, champ!";
        String invalidEventMessage = "      (⊙_☉) Whoa! Events need clear timing:  \n" +
                                     "          Like this: `event Meeting /from 10am /to 12pm`  \n" +
                                     "      ༼ つ ಥ_ಥ ༽つ  Give me something I can work with, please!";
        String generalError = "     (╯°□°）╯︵ ┻━┻  ERROR! I ran into something weird.  \n" +
                              "     ┬─┬ノ( º _ ºノ)  Let’s calm down and try that again.";

        System.out.println(dottedLine);
        System.out.println("    Hello! I'm Terry");
        System.out.println("    What can I do for you?");
        System.out.println(dottedLine);

        TaskManager taskManager = new TaskManager();

        while (sc.hasNext()) {
            String userInput = sc.nextLine().trim();
            String[] parts = userInput.split(" ", 2);//take out the first word
            String command = parts[0].toLowerCase();//switch case according to the first word
            if (userInput.isEmpty()) continue; //skip empty line

            System.out.println(dottedLine);

            try {
                switch (command) {
                case "bye":
                    System.out.println("    Bye. Hope to see you again soon!");
                    System.out.println(dottedLine);
                    return;
                case "list":
                    taskManager.listTasks();
                    break;
                case "mark":
                case "unmark":
                    if (parts.length < 2 || !taskManager.taskExists(Integer.parseInt(parts[1]))) {
                        throw new TerryException(invalidTaskNumberMessage + "from 1 to " + taskManager.taskNumber());
                    } else {
                        Integer number = Integer.parseInt(parts[1]);
                        if (command.equals("mark")) {
                            taskManager.markTaskAsDone(number);
                        } else {
                            taskManager.markTaskAsUndone(number);
                        }
                    }
                    break;
                case "todo":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new TerryException(todoErrorMessage);
                    } else {
                        taskManager.addTask(new ToDos(parts[1]));
                    }
                    break;

                case "deadline":
                    if (!userInput.contains("/by ")) {
                        throw new TerryException(invalidDeadlineMessage);
                    } else {
                        String[] partDeadline = userInput.split("/by ", 2);
                        taskManager.addTask(new Deadlines(partDeadline[0].trim(), partDeadline[1].trim()));
                    }
                    break;
                case "event":
                    if (!userInput.contains("/from") || !userInput.contains("/to")) {
                        throw new TerryException(invalidEventMessage);
                    } else {
                        String[] partEvent = userInput.split("/from|/to");
                        taskManager.addTask(new Events(partEvent[0].trim(), partEvent[1].trim(), partEvent[2].trim()));
                    }
                    break;
                default:
                    System.out.println(unknownCommand);
                    break;
                }
                System.out.println(dottedLine);
            } catch (TerryException e) {
                System.out.println(e.getMessage() + dottedLineNextLine);
            } catch (Exception e) {
                System.out.println(generalError + "\n   " + e.getMessage() + dottedLineNextLine);
            }
        }
    }
}
