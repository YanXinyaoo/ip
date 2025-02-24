package Terry.Storage;

import java.io.*;
import java.nio.file.*;
import java.util.*;


import Terry.Exception.TerryException;
import Terry.Task.*;

public class Storage {
    private static String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
        ensureFileExists(filePath);
    }


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


    private static Task parseTask(String line) throws TerryException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) throw new IllegalArgumentException("Invalid format");

        boolean isDone = parts[1].equals("1");
        switch (parts[0]) {
        case "T": return new ToDos(parts[2], isDone);
        case "D": return new Deadlines(parts[2], parts[3], isDone);
        case "E": return new Events(parts[2], parts[3], parts[4], isDone);
        default: throw new TerryException(TerryException.showLoadingError());
        }
    }
}
