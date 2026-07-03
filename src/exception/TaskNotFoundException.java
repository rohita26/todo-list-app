package exception;

/**
 * Thrown when a task cannot be found by the given identifier.
 */
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(int id) {
        super("Task not found with ID: " + id);
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
