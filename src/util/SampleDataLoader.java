package util;

import model.Priority;
import service.TaskService;

import java.time.LocalDate;

/**
 * Loads realistic sample tasks for demonstration and testing purposes.
 */
public final class SampleDataLoader {

    private SampleDataLoader() {
        // Utility class
    }

    /**
     * Populates the task service with 10 sample tasks.
     *
     * @param taskService the service to populate
     */
    public static void loadSampleTasks(TaskService taskService) {
        LocalDate today = LocalDate.now();

        taskService.addTaskWithId(1,
                "Complete Java Assignment",
                "Finish the OOP console application for CS course",
                today.minusDays(5),
                today.plusDays(3),
                Priority.HIGH,
                false);

        taskService.addTaskWithId(2,
                "Grocery Shopping",
                "Buy milk, eggs, bread, and vegetables",
                today.minusDays(2),
                today.plusDays(1),
                Priority.MEDIUM,
                false);

        taskService.addTaskWithId(3,
                "Schedule Dentist Appointment",
                "Call clinic to book annual checkup",
                today.minusDays(10),
                today.plusDays(7),
                Priority.LOW,
                true);

        taskService.addTaskWithId(4,
                "Prepare Presentation",
                "Create slides for team sprint review meeting",
                today.minusDays(3),
                today.plusDays(2),
                Priority.HIGH,
                false);

        taskService.addTaskWithId(5,
                "Pay Electricity Bill",
                "Pay online before due date to avoid late fee",
                today.minusDays(1),
                today.plusDays(5),
                Priority.MEDIUM,
                false);

        taskService.addTaskWithId(6,
                "Morning Workout",
                "30-minute cardio and strength training session",
                today.minusDays(7),
                today,
                Priority.MEDIUM,
                true);

        taskService.addTaskWithId(7,
                "Read Design Patterns Book",
                "Complete chapter on Factory and Singleton patterns",
                today.minusDays(4),
                today.plusDays(14),
                Priority.LOW,
                false);

        taskService.addTaskWithId(8,
                "Submit Tax Documents",
                "Gather W-2 forms and upload to tax portal",
                today.minusDays(6),
                today.plusDays(10),
                Priority.HIGH,
                false);

        taskService.addTaskWithId(9,
                "Call Mom",
                "Weekly catch-up call on Sunday evening",
                today.minusDays(1),
                today.plusDays(2),
                Priority.LOW,
                false);

        taskService.addTaskWithId(10,
                "Refactor Legacy Module",
                "Clean up authentication service and add unit tests",
                today.minusDays(8),
                today.plusDays(21),
                Priority.HIGH,
                true);
    }
}
