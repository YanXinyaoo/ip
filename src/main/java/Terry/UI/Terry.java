package Terry.UI;

import java.util.Scanner;
import Terry.Command.Deadlines;
import Terry.Command.Events;
import Terry.Command.Task;
import Terry.Command.ToDos;

public class Terry {
    public static void printWelcome() {
        String dottedLine = "    ____________________________________________________________";
        String dottedLineNextLine = "\n" + dottedLine;

        System.out.println(dottedLine);
        System.out.println("    Hello! I'm Terry");
        System.out.println("    What can I do for you?");
        System.out.println(dottedLine);
    }

    public static void main(String[] args) throws TerryException {
        Scanner sc = new Scanner(System.in);
        String dottedLine = "    ____________________________________________________________";
        String dottedLineNextLine = "\n" + dottedLine;

        String filePath = "./data/Terry.txt";

        printWelcome();

        TaskManager taskManager = new TaskManager(Storage.loadTasks());

        while (sc.hasNext()) {
            String userInput = sc.nextLine().trim();
            String[] parts = userInput.split(" ", 2);
            String command = parts[0].toLowerCase();
            String description = parts.length > 1 ? parts[1] : null;
            if (userInput.isEmpty()) continue;

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
                        throw new TerryException(TerryException.invalidTaskNumberMessage(taskManager.taskNumber()));
                    }
                    int number = Integer.parseInt(parts[1]);
                    if (command.equals("mark")) {
                        taskManager.markTaskAsDone(number);
                    } else {
                        taskManager.markTaskAsUndone(number);
                    }
                    break;
                case "todo":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new TerryException(TerryException.todoErrorMessage());
                    }
                    taskManager.addTask(new ToDos(description));
                    break;
                case "deadline":
                    if (!userInput.contains("/by ")) {
                        throw new TerryException(TerryException.invalidDeadlineMessage());
                    }
                    String[] partDeadline = description.split("/by ", 2);
                    taskManager.addTask(new Deadlines(partDeadline[0].trim(), partDeadline[1].trim()));
                    break;
                case "event":
                    if (!userInput.contains("/from") || !userInput.contains("/to")) {
                        throw new TerryException(TerryException.invalidEventMessage());
                    }
                    String[] partEvent = description.split("/from|/to");
                    taskManager.addTask(new Events(partEvent[0].trim(), partEvent[1].trim(), partEvent[2].trim()));
                    break;
                case "delete":
                    if (parts.length < 2 || !taskManager.taskExists(Integer.parseInt(parts[1]))) {
                        throw new TerryException(TerryException.invalidTaskNumberMessage(taskManager.taskNumber()));
                    }
                    taskManager.deleteTask(Integer.parseInt(parts[1]));
                    break;
                default:
                    System.out.println(TerryException.unknownCommand());
                    break;
                }
                System.out.println(dottedLine);
                taskManager.saveTasks();
            } catch (TerryException e) {
                System.out.println(e.getMessage() + dottedLineNextLine);
            } catch (Exception e) {
                System.out.println(TerryException.generalError() + "\n   " + e.getMessage() + dottedLineNextLine);
            }
        }
    }
}
