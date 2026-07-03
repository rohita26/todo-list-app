package service;

import exception.DuplicateTaskException;
import exception.TaskNotFoundException;
import model.Priority;
import model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for managing in-memory task operations.
 * Encapsulates business logic and enforces data integrity rules.
 */
public class TaskService {

    private final List<Task> tasks;
    private int nextId;

    public TaskService() {
        this.tasks = new ArrayList<>();
        this.nextId = 1;
    }

    /**
     * Adds a new task with an auto-generated unique ID.
     *
     * @param title       task title
     * @param description task description
     * @param dueDate     due date
     * @param priority    priority level
     * @return the created task
     */
    public Task addTask(String title, String description, LocalDate dueDate, Priority priority) {
        Task task = new Task(
                nextId++,
                title,
                description,
                LocalDate.now(),
                dueDate,
                priority,
                false
        );
        tasks.add(task);
        return task;
    }

    /**
     * Adds a task with a specific ID. Used for loading sample data.
     *
     * @param id          task ID
     * @param title       task title
     * @param description task description
     * @param createdDate creation date
     * @param dueDate     due date
     * @param priority    priority level
     * @param completed   completion status
     * @return the created task
     * @throws DuplicateTaskException if a task with the same ID already exists
     */
    public Task addTaskWithId(int id, String title, String description, LocalDate createdDate,
                              LocalDate dueDate, Priority priority, boolean completed) {
        if (findById(id).isPresent()) {
            throw new DuplicateTaskException(id);
        }
        Task task = new Task(id, title, description, createdDate, dueDate, priority, completed);
        tasks.add(task);
        if (id >= nextId) {
            nextId = id + 1;
        }
        return task;
    }

    /**
     * Returns an unmodifiable view of all tasks.
     *
     * @return list of all tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Finds a task by its unique ID.
     *
     * @param id task ID
     * @return optional containing the task if found
     */
    public Optional<Task> findById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();
    }

    /**
     * Finds a task by ID or throws if not found.
     *
     * @param id task ID
     * @return the matching task
     * @throws TaskNotFoundException if no task exists with the given ID
     */
    public Task getTaskById(int id) {
        return findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    /**
     * Searches tasks whose title contains the given keyword (case-insensitive).
     *
     * @param titleKeyword keyword to search
     * @return matching tasks
     */
    public List<Task> searchByTitle(String titleKeyword) {
        String keyword = titleKeyword.trim().toLowerCase();
        return tasks.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing task's mutable fields.
     *
     * @param id          task ID
     * @param title       new title
     * @param description new description
     * @param dueDate     new due date
     * @param priority    new priority
     * @return the updated task
     * @throws TaskNotFoundException if the task does not exist
     */
    public Task updateTask(int id, String title, String description,
                           LocalDate dueDate, Priority priority) {
        Task task = getTaskById(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setPriority(priority);
        return task;
    }

    /**
     * Marks a task as completed.
     *
     * @param id task ID
     * @return the updated task
     * @throws TaskNotFoundException if the task does not exist
     */
    public Task markAsCompleted(int id) {
        Task task = getTaskById(id);
        if (task.isCompleted()) {
            throw new IllegalStateException("Task is already marked as completed.");
        }
        task.setCompleted(true);
        return task;
    }

    /**
     * Deletes a task by ID.
     *
     * @param id task ID
     * @return the removed task
     * @throws TaskNotFoundException if the task does not exist
     */
    public Task deleteTask(int id) {
        Task task = getTaskById(id);
        tasks.remove(task);
        return task;
    }

    /**
     * Returns all pending (incomplete) tasks.
     *
     * @return pending tasks
     */
    public List<Task> getPendingTasks() {
        return tasks.stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
    }

    /**
     * Returns all completed tasks.
     *
     * @return completed tasks
     */
    public List<Task> getCompletedTasks() {
        return tasks.stream()
                .filter(Task::isCompleted)
                .collect(Collectors.toList());
    }

    /**
     * Filters tasks by priority level.
     *
     * @param priority priority to filter by
     * @return tasks with the given priority
     */
    public List<Task> filterByPriority(Priority priority) {
        return tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    /**
     * Returns tasks sorted by due date (earliest first).
     *
     * @return sorted task list
     */
    public List<Task> sortByDueDate() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }

    /**
     * Returns tasks sorted by priority (HIGH first, then MEDIUM, then LOW).
     *
     * @return sorted task list
     */
    public List<Task> sortByPriority() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority).reversed())
                .collect(Collectors.toList());
    }

    public int getTotalTaskCount() {
        return tasks.size();
    }

    public int getPendingCount() {
        return (int) tasks.stream().filter(task -> !task.isCompleted()).count();
    }

    public int getCompletedCount() {
        return (int) tasks.stream().filter(Task::isCompleted).count();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
