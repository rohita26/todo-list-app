package util;

import model.Task;

import java.util.List;

/**
 * Formats task data for console table display.
 */
public final class TableFormatter {

    private static final String SEPARATOR = "-".repeat(130);
    private static final String HEADER = String.format(
            "%-5s | %-25s | %-30s | %-12s | %-12s | %-10s | %-10s",
            "ID", "Title", "Description", "Created", "Due Date", "Priority", "Status");

    private TableFormatter() {
        // Utility class
    }

    /**
     * Prints a formatted table of tasks to the console.
     *
     * @param tasks   list of tasks to display
     * @param heading table heading
     */
    public static void printTaskTable(List<Task> tasks, String heading) {
        System.out.println("\n" + heading);
        System.out.println(SEPARATOR);

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            System.out.println(SEPARATOR);
            return;
        }

        System.out.println(HEADER);
        System.out.println(SEPARATOR);

        for (Task task : tasks) {
            System.out.println(formatRow(task));
        }

        System.out.println(SEPARATOR);
        System.out.println("Total: " + tasks.size() + " task(s)");
    }

    /**
     * Prints a single task in a detailed format.
     *
     * @param task task to display
     */
    public static void printTaskDetails(Task task) {
        System.out.println("\n--- Task Details ---");
        System.out.println("ID          : " + task.getId());
        System.out.println("Title       : " + task.getTitle());
        System.out.println("Description : " + task.getDescription());
        System.out.println("Created     : " + task.getCreatedDate());
        System.out.println("Due Date    : " + task.getDueDate());
        System.out.println("Priority    : " + task.getPriority().getDisplayName());
        System.out.println("Status      : " + (task.isCompleted() ? "Completed" : "Pending"));
        System.out.println("--------------------");
    }

    private static String formatRow(Task task) {
        return String.format(
                "%-5d | %-25s | %-30s | %-12s | %-12s | %-10s | %-10s",
                task.getId(),
                truncate(task.getTitle(), 25),
                truncate(task.getDescription(), 30),
                task.getCreatedDate(),
                task.getDueDate(),
                task.getPriority().getDisplayName(),
                task.isCompleted() ? "Completed" : "Pending"
        );
    }

    private static String truncate(String value, int maxLength) {
        if (value.length() <= maxLength) {
            return value;
        }
        return value.substring(0, maxLength - 3) + "...";
    }
}
