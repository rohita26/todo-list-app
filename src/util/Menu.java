package util;

import exception.TaskNotFoundException;
import model.Priority;
import model.Task;
import service.TaskService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Handles menu display and routes user actions to the task service.
 */
public class Menu {

    private static final int EXIT_CHOICE = 16;

    private final TaskService taskService;
    private final InputHelper inputHelper;
    private final Scanner scanner;
    private boolean running;

    public Menu(TaskService taskService, Scanner scanner) {
        this.taskService = taskService;
        this.scanner = scanner;
        this.inputHelper = new InputHelper(scanner);
        this.running = true;
    }

    /**
     * Starts the main application loop.
     */
    public void start() {
        printWelcomeBanner();
        while (running) {
            printMenu();
            int choice = inputHelper.readMenuChoice(1, EXIT_CHOICE);
            processChoice(choice);
        }
        System.out.println("\nThank you for using To-Do List App. Goodbye!");
    }

    private void printWelcomeBanner() {
        System.out.println("=".repeat(50));
        System.out.println("       Welcome to To-Do List Application");
        System.out.println("=".repeat(50));
    }

    private void printMenu() {
        System.out.println("\n+------------------------ MAIN MENU ------------------------+");
        System.out.println("|  1.  Add Task                                             |");
        System.out.println("|  2.  View All Tasks                                       |");
        System.out.println("|  3.  Search Task by ID                                    |");
        System.out.println("|  4.  Search Task by Title                                 |");
        System.out.println("|  5.  Update Task                                          |");
        System.out.println("|  6.  Mark Task as Completed                               |");
        System.out.println("|  7.  Delete Task                                          |");
        System.out.println("|  8.  View Pending Tasks                                   |");
        System.out.println("|  9.  View Completed Tasks                                 |");
        System.out.println("| 10.  Filter by Priority                                   |");
        System.out.println("| 11.  Sort by Due Date                                     |");
        System.out.println("| 12.  Sort by Priority                                     |");
        System.out.println("| 13.  Show Total Tasks                                     |");
        System.out.println("| 14.  Show Pending Count                                   |");
        System.out.println("| 15.  Show Completed Count                                 |");
        System.out.println("| 16.  Exit                                                 |");
        System.out.println("+-----------------------------------------------------------+");
    }

    private void processChoice(int choice) {
        try {
            switch (choice) {
                case 1 -> handleAddTask();
                case 2 -> handleViewAllTasks();
                case 3 -> handleSearchById();
                case 4 -> handleSearchByTitle();
                case 5 -> handleUpdateTask();
                case 6 -> handleMarkCompleted();
                case 7 -> handleDeleteTask();
                case 8 -> handleViewPendingTasks();
                case 9 -> handleViewCompletedTasks();
                case 10 -> handleFilterByPriority();
                case 11 -> handleSortByDueDate();
                case 12 -> handleSortByPriority();
                case 13 -> handleShowTotalTasks();
                case 14 -> handleShowPendingCount();
                case 15 -> handleShowCompletedCount();
                case 16 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } catch (TaskNotFoundException ex) {
            System.out.println("\nError: " + ex.getMessage());
        } catch (IllegalStateException | IllegalArgumentException ex) {
            System.out.println("\nError: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("\nUnexpected error: " + ex.getMessage());
        }

        if (running && choice != EXIT_CHOICE) {
            inputHelper.pause();
        }
    }

    private void handleAddTask() {
        System.out.println("\n--- Add New Task ---");
        String title = inputHelper.readNonEmptyString("Enter title: ");
        String description = inputHelper.readNonEmptyString("Enter description: ");
        LocalDate dueDate = inputHelper.readDate("Enter due date");
        Priority priority = readPriority();

        Task task = taskService.addTask(title, description, dueDate, priority);
        System.out.println("\nTask added successfully with ID: " + task.getId());
        TableFormatter.printTaskDetails(task);
    }

    private void handleViewAllTasks() {
        TableFormatter.printTaskTable(taskService.getAllTasks(), "All Tasks");
    }

    private void handleSearchById() {
        System.out.println("\n--- Search Task by ID ---");
        int id = inputHelper.readPositiveInt("Enter task ID: ");
        Task task = taskService.getTaskById(id);
        TableFormatter.printTaskDetails(task);
    }

    private void handleSearchByTitle() {
        System.out.println("\n--- Search Task by Title ---");
        String keyword = inputHelper.readNonEmptyString("Enter title keyword: ");
        List<Task> results = taskService.searchByTitle(keyword);
        TableFormatter.printTaskTable(results, "Search Results for \"" + keyword + "\"");
    }

    private void handleUpdateTask() {
        System.out.println("\n--- Update Task ---");
        int id = inputHelper.readPositiveInt("Enter task ID to update: ");
        Task existing = taskService.getTaskById(id);

        System.out.println("\nLeave blank to keep current value.");
        String title = inputHelper.readOptionalString(
                "Title [" + existing.getTitle() + "]: ", existing.getTitle());
        String description = inputHelper.readOptionalString(
                "Description [" + existing.getDescription() + "]: ", existing.getDescription());
        LocalDate dueDate = inputHelper.readOptionalDate(
                "Due date", existing.getDueDate());
        Priority priority = readOptionalPriority(existing.getPriority());

        Task updated = taskService.updateTask(id, title, description, dueDate, priority);
        System.out.println("\nTask updated successfully.");
        TableFormatter.printTaskDetails(updated);
    }

    private void handleMarkCompleted() {
        System.out.println("\n--- Mark Task as Completed ---");
        int id = inputHelper.readPositiveInt("Enter task ID: ");
        Task task = taskService.markAsCompleted(id);
        System.out.println("\nTask marked as completed.");
        TableFormatter.printTaskDetails(task);
    }

    private void handleDeleteTask() {
        System.out.println("\n--- Delete Task ---");
        int id = inputHelper.readPositiveInt("Enter task ID to delete: ");
        Task task = taskService.getTaskById(id);
        TableFormatter.printTaskDetails(task);

        if (inputHelper.readConfirmation("Are you sure you want to delete this task?")) {
            taskService.deleteTask(id);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private void handleViewPendingTasks() {
        TableFormatter.printTaskTable(taskService.getPendingTasks(), "Pending Tasks");
    }

    private void handleViewCompletedTasks() {
        TableFormatter.printTaskTable(taskService.getCompletedTasks(), "Completed Tasks");
    }

    private void handleFilterByPriority() {
        System.out.println("\n--- Filter by Priority ---");
        Priority priority = readPriority();
        List<Task> filtered = taskService.filterByPriority(priority);
        TableFormatter.printTaskTable(filtered, priority.getDisplayName() + " Priority Tasks");
    }

    private void handleSortByDueDate() {
        TableFormatter.printTaskTable(taskService.sortByDueDate(), "Tasks Sorted by Due Date");
    }

    private void handleSortByPriority() {
        TableFormatter.printTaskTable(taskService.sortByPriority(), "Tasks Sorted by Priority");
    }

    private void handleShowTotalTasks() {
        System.out.println("\nTotal tasks: " + taskService.getTotalTaskCount());
    }

    private void handleShowPendingCount() {
        System.out.println("\nPending tasks: " + taskService.getPendingCount());
    }

    private void handleShowCompletedCount() {
        System.out.println("\nCompleted tasks: " + taskService.getCompletedCount());
    }

    private Priority readPriority() {
        while (true) {
            String input = inputHelper.readNonEmptyString(
                    "Enter priority (LOW, MEDIUM, HIGH): ");
            try {
                return Priority.fromString(input);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private Priority readOptionalPriority(Priority current) {
        System.out.print("Priority (LOW, MEDIUM, HIGH, Enter to keep ["
                + current.name() + "]): ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return current;
        }
        try {
            return Priority.fromString(input);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage() + " Keeping current: " + current.name());
            return current;
        }
    }
}
