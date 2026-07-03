import service.TaskService;
import util.Menu;
import util.SampleDataLoader;

import java.util.Scanner;

/**
 * Entry point for the Console-Based To-Do List Application.
 */
public class Main {

    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        SampleDataLoader.loadSampleTasks(taskService);

        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(taskService, scanner);

        try {
            menu.start();
        } finally {
            scanner.close();
        }
    }
}
