package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a to-do task with metadata such as priority, due date, and completion status.
 */
public class Task {

    private final int id;
    private String title;
    private String description;
    private final LocalDate createdDate;
    private LocalDate dueDate;
    private Priority priority;
    private boolean completed;

    /**
     * Creates a new task with the specified attributes.
     *
     * @param id          unique task identifier
     * @param title       short task title
     * @param description detailed task description
     * @param createdDate date the task was created
     * @param dueDate     date the task is due
     * @param priority    task priority level
     * @param completed   whether the task is completed
     */
    public Task(int id, String title, String description, LocalDate createdDate,
                LocalDate dueDate, Priority priority, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
                "Task{id=%d, title='%s', priority=%s, dueDate=%s, completed=%s}",
                id, title, priority, dueDate, completed);
    }
}
