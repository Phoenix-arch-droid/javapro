import java.util.*;

abstract class Task {
    String description;
    boolean isCompleted;

    Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    abstract void displayTask();
}

class SimpleTask extends Task {
    SimpleTask(String description) {
        super(description);
    }

    @Override
    void displayTask() {
        String status = isCompleted ? "✔ Done" : "✘ Pending";
        System.out.println(description + " [" + status + "]");
    }
}

public class pro {
    private static final String PIN = "1234";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        if (!authenticateUser()) return;
        int choice;
        do {
            showMenu();
            choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) {
                case 1 -> viewTasks();
                case 2 -> addTask();
                case 3 -> markTaskComplete();
                case 4 -> deleteTask();
                case 5 -> System.out.println("Exiting To-Do App. Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    static boolean authenticateUser() {
        int attempts = 3;
        while (attempts-- > 0) {
            System.out.print("Enter your PIN: ");
            String enteredPin = sc.nextLine();
            if (enteredPin.equals(PIN)) {
                System.out.println("Login successful.");
                return true;
            } else {
                System.out.println("Incorrect PIN. Attempts left: " + attempts);
            }
        }
        System.out.println("Too many failed attempts. Exiting...");
        return false;
    }

    static void showMenu() {
        System.out.println("\n--- To-Do List Menu ---");
        System.out.println("1. View Tasks");
        System.out.println("2. Add Task");
        System.out.println("3. Mark Task as Completed");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("\nYour Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ". ");
            tasks.get(i).displayTask();
        }
    }

    static void addTask() {
        System.out.print("Enter task description: ");
        String desc = sc.nextLine();
        tasks.add(new SimpleTask(desc));
        System.out.println("Task added successfully.");
    }

    static void markTaskComplete() {
        viewTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Enter task number to mark complete: ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).isCompleted = true;
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    static void deleteTask() {
        viewTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Enter task number to delete: ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task deleted.");
        } else {
            System.out.println("Invalid task number.");
        }
    }
}
