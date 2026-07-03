package model;

/**
 * Represents the priority level of a task.
 */
public enum Priority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String displayName;

    Priority(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns a user-friendly display name for the priority.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Parses a priority from a string input (case-insensitive).
     *
     * @param input the user input
     * @return the matching Priority
     * @throws IllegalArgumentException if the input does not match any priority
     */
    public static Priority fromString(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Priority cannot be empty.");
        }
        try {
            return Priority.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(
                    "Invalid priority. Please enter LOW, MEDIUM, or HIGH.");
        }
    }
}
