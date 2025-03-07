package Terry.Storage;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import Terry.Exception.TerryException;
import Terry.Task.*;

/**
 * Handles storage-related operations such as saving and loading tasks to a file.
 */
public class Storage {
    private static String FILE_PATH;

    /**
     * Constructs a Storage instance and ensures the file exists.
     *
     * @param filePath The path to the file used for storage.
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
        ensureFileExists(filePath);
    }

    /**
     * Ensures that the specified file exists, creating it if necessary.
     *
     * @param path The file path to check or create.
     */
    private void ensureFileExists(String path) {
        try {
            Path filePath = Paths.get(path);
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Saves the list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat()); // Custom method to format each task
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     * @throws TerryException If an error occurs while parsing the tasks.
     */
    public static ArrayList<Task> loadTasks() throws TerryException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    tasks.add(parseTask(line));
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
        } catch (TerryException e) {
            throw e;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a task from a line of text.
     *
     * @param line The line containing task data.
     * @return The parsed Task object.
     * @throws TerryException If the format is invalid.
     */
    private static Task parseTask(String line) throws TerryException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new TerryException(TerryException.unknownCommand());
        }
        boolean isDone = parts[1].equals("1");
        switch (parts[0]) {
        case "T":
            return new ToDos(parts[2], isDone);
        case "D":
            if (parts.length < 4) {
                throw new TerryException(TerryException.invalidDeadlineMessage());
            }
            return new Deadlines(parts[2], isDone, parts[3]);
        case "E":
            if (parts.length < 5) {
                throw new TerryException(TerryException.invalidEventMessage());
            }
            return new Events(parts[2], isDone, parts[3], parts[4]);
        default:
            throw new TerryException(TerryException.showLoadingError());
        }
    }
}