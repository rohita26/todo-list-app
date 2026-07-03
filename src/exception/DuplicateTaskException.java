package exception;

/**
 * Thrown when attempting to create or update a task with a duplicate ID.
 */
public class DuplicateTaskException extends RuntimeException {

    public DuplicateTaskException(int id) {
        super("A task with ID " + id + " already exists.");
    }
}
