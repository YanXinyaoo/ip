package Terry.Task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Terry.Storage.Storage;
import Terry.UI.UI;
import Terry.Exception.TerryException;

public class TaskManager {
    private ArrayList<Task> tasks;
    private UI ui;

    public TaskManager(UI ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    public TaskManager(ArrayList<Task> tasks, UI ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void addTask(Task task) throws TerryException {
        if (task == null) {
            throw new TerryException(TerryException.generalError());
        }
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        Storage.saveTasks(tasks);
    }

    public void listTasks() {
        ui.showTaskList(tasks);
    }

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

    public void deleteTask(int index) throws TerryException {
        if (index < 1 || index > tasks.size()) {
            throw new TerryException(TerryException.invalidTaskNumberMessage(tasks.size()));
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        ui.showTaskDeleted(task, tasks.size());
        Storage.saveTasks(tasks);
    }

    public void findTaskInTimeRange(String startTime, String endTime) throws TerryException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime startDate;
        LocalDateTime endDate;
        System.out.println(startTime + endTime);
        try {
            startDate = LocalDateTime.parse(startTime, formatter);
            endDate = LocalDateTime.parse(endTime, formatter);
        } catch (DateTimeException e) {
            throw new TerryException(TerryException.noTasksInTimeRange());
        }

        ArrayList<Task> foundTasks = new ArrayList<>();
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

    public int taskNumber() {
        return this.tasks.size();
    }

    public void findTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        ui.showFindResult(matchingTasks);
    }

    public boolean taskExists(int number) {
        return number > 0 && number <= this.tasks.size();
    }
}
