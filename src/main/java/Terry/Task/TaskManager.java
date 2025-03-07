package Terry.Task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Terry.Storage.Storage;
import Terry.UI.UI;
import Terry.Exception.TerryException;

/**
 * TaskManager manages the list of tasks, providing methods to add, list, mark tasks as done or undone,
 * delete tasks, and search tasks by time or keyword.
 */
public class TaskManager {
    private ArrayList<Task> tasks;
    private UI ui;

    /**
     * Constructor for TaskManager with an empty task list.
     *
     * @param ui The UI instance to interact with the user interface.
     */
    public TaskManager(UI ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Constructor for TaskManager with an initial list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskManager with.
     * @param ui The UI instance to interact with the user interface.
     */
    public TaskManager(ArrayList<Task> tasks, UI ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Adds a new task to the task list and updates the storage.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) throws TerryException {
        if (task == null) {
            throw new TerryException(TerryException.generalError());
        }
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        Storage.saveTasks(tasks);
    }

    /**
     * Lists all the tasks in the task list.
     */
    public void listTasks() {
        ui.showTaskList(tasks);
    }

    /**
     * Marks a task as done by its index in the list.
     *
     * @param index The index of the task to mark as done (1-based index).
     * @throws TerryException If the task is invalid or already marked as done.
     */
    public void markTaskAsDone(int index) throws TerryException {
        if (index < 1 || index > tasks.size()) {
            throw new TerryException(TerryException.invalidTaskNumberMessage(tasks.size()));
        }
        if (tasks.get(index - 1).isDone) {
            throw new TerryException(TerryException.taskAlreadyMarked());
        }
        Task task = tasks.get(index - 1).markAsDone();
        ui.showTaskMarkedAsDone(task);
        Storage.saveTasks(tasks);
    }

    /**
     * Marks a task as undone by its index in the list.
     *
     * @param index The index of the task to mark as undone (1-based index).
     * @throws TerryException If the task is invalid or not marked as done.
     */
    public void markTaskAsUndone(int index) throws TerryException {
        if (index < 1 || index > tasks.size()) {
            throw new TerryException(TerryException.invalidTaskNumberMessage(tasks.size()));
        }
        if (!tasks.get(index - 1).isDone) {
            throw new TerryException(TerryException.taskNotMarked());
        }
        Task task = tasks.get(index - 1).markAsUndone();
        ui.showTaskMarkedAsUndone(task);
        Storage.saveTasks(tasks);
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index The index of the task to delete (1-based index).
     * @throws TerryException If the task is invalid.
     */
    public void deleteTask(int index) throws TerryException {
        if (index < 1 || index > tasks.size()) {
            throw new TerryException(TerryException.invalidTaskNumberMessage(tasks.size()));
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        ui.showTaskDeleted(task, tasks.size());
        Storage.saveTasks(tasks);
    }

    /**
     * Finds tasks that are within a specified time range.
     *
     * @param startTime The start time in "d/M/yyyy HHmm" format.
     * @param endTime The end time in "d/M/yyyy HHmm" format.
     * @throws TerryException If no tasks are found within the time range or if the time format is incorrect.
     */
    public void findTaskInTimeRange(String startTime, String endTime) throws TerryException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime startDate;
        LocalDateTime endDate;

        // Parse time inputs and handle exceptions
        try {
            startDate = LocalDateTime.parse(startTime, formatter);
            endDate = LocalDateTime.parse(endTime, formatter);

            // Ensure start time is before end time
            if (startDate.isAfter(endDate)) {
                throw new TerryException("Start time must be before end time.");
            }
        } catch (DateTimeException e) {
            throw new TerryException(TerryException.noTasksInTimeRange());
        }

        ArrayList<Task> foundTasks = new ArrayList<>();
        // Loop through tasks to find matching ones
        for (Task task : tasks) {
            if (task instanceof Terry.Task.Deadlines) {
                Terry.Task.Deadlines deadline = (Terry.Task.Deadlines) task;
                if (!deadline.getDeadline().isBefore(startDate) && !deadline.getDeadline().isAfter(endDate)) {
                    foundTasks.add(deadline);
                }
            } else if (task instanceof Terry.Task.Events) {
                Terry.Task.Events event = (Terry.Task.Events) task;
                if ((event.getStartTime().isEqual(startDate) || event.getStartTime().isAfter(startDate)) &&
                        (event.getEndTime().isEqual(endDate) || event.getEndTime().isBefore(endDate))) {
                    foundTasks.add(event);
                }
            }
        }
        ui.showTaskList(foundTasks);
    }

    /**
     * Returns the total number of tasks in the list.
     */
    public int taskNumber() {
        return this.tasks.size();
    }

    /**
     * Finds tasks in the list that match a given keyword in their description.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public void findTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        ui.showFindResult(matchingTasks);
    }

    /**
     * Checks if a task exists by its index.
     *
     * @param number The index of the task to check (1-based index).
     * @return true if the task exists, false otherwise.
     */
    public boolean taskExists(int number) {
        return number > 0 && number <= this.tasks.size();
    }
}
