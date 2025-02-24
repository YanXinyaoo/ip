package Terry.Task;

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
