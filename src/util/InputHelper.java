package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Utility class for reading and validating console user input.
 */
public class InputHelper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads a non-empty string from the user.
     *
     * @param prompt message displayed to the user
     * @return validated non-empty string
     */
    public String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    /**
     * Reads an optional string; blank input returns the default value.
     *
     * @param prompt       message displayed to the user
     * @param defaultValue value used when input is blank
     * @return user input or default
     */
    public String readOptionalString(String prompt, String defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? defaultValue : input;
    }

    /**
     * Reads a positive integer ID from the user.
     *
     * @param prompt message displayed to the user
     * @return validated positive integer
     */
    public int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                }
                System.out.println("Please enter a positive number.");
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Please enter a valid integer.");
            }
        }
    }

    /**
     * Reads a menu choice within the valid range.
     *
     * @param min minimum valid choice
     * @param max maximum valid choice
     * @return validated menu choice
     */
    public int readMenuChoice(int min, int max) {
        while (true) {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.println("Invalid choice. Please enter a number between "
                        + min + " and " + max + ".");
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Reads a date in yyyy-MM-dd format from the user.
     *
     * @param prompt message displayed to the user
     * @return validated LocalDate
     */
    public LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt + " (yyyy-MM-dd): ");
            String input = scanner.nextLine().trim();
            try {
                LocalDate date = LocalDate.parse(input, DATE_FORMATTER);
                if (date.isBefore(LocalDate.now().minusYears(10))) {
                    System.out.println("Date seems too far in the past. Please verify.");
                }
                return date;
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd (e.g., 2026-07-15).");
            }
        }
    }

    /**
     * Reads an optional date; blank input returns the default value.
     *
     * @param prompt       message displayed to the user
     * @param defaultValue value used when input is blank
     * @return parsed date or default
     */
    public LocalDate readOptionalDate(String prompt, LocalDate defaultValue) {
        System.out.print(prompt + " (yyyy-MM-dd, Enter to keep [" + defaultValue + "]): ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return defaultValue;
        }
        try {
            return LocalDate.parse(input, DATE_FORMATTER);
        } catch (DateTimeParseException ex) {
            System.out.println("Invalid date format. Keeping current value: " + defaultValue);
            return defaultValue;
        }
    }

    /**
     * Reads a yes/no confirmation from the user.
     *
     * @param prompt confirmation message
     * @return true if user confirms
     */
    public boolean readConfirmation(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("Please enter 'y' or 'n'.");
        }
    }

    /**
     * Pauses execution until the user presses Enter.
     */
    public void pause() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
