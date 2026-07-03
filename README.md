# Console-Based To-Do List Application

A production-quality, console-based task management application built with **Core Java 17+**. This project demonstrates Object-Oriented Programming, SOLID principles, clean architecture, and recruiter-friendly code organization.

## Project Description

This application allows users to manage their daily tasks through an interactive command-line interface. Tasks are stored in memory using `ArrayList` and support rich metadata including title, description, priority, due dates, and completion status. The codebase is modular, well-documented, and designed for easy extension.

## Features

- Add, view, update, and delete tasks
- Search tasks by ID or title keyword
- Mark tasks as completed
- View pending and completed tasks separately
- Filter tasks by priority (LOW, MEDIUM, HIGH)
- Sort tasks by due date or priority
- Display task statistics (total, pending, completed counts)
- Formatted table output for readability
- Input validation and graceful error handling
- Pre-loaded sample data for immediate testing

## Technologies Used

- **Java 17+** (Core Java only — no external frameworks)
- `ArrayList` for in-memory storage
- `LocalDate` for date handling
- `Enum` for priority levels
- `Scanner` for console I/O
- Java Streams API for filtering and sorting

## Folder Structure

```
ToDoListApp/
│
├── src/
│   ├── model/
│   │   ├── Task.java
│   │   └── Priority.java
│   ├── service/
│   │   └── TaskService.java
│   ├── util/
│   │   ├── InputHelper.java
│   │   ├── Menu.java
│   │   ├── TableFormatter.java
│   │   └── SampleDataLoader.java
│   ├── exception/
│   │   ├── TaskNotFoundException.java
│   │   └── DuplicateTaskException.java
│   └── Main.java
│
├── screenshots/
├── docs/
│   └── sample-output.txt
├── README.md
├── LICENSE
└── .gitignore
```

## Installation

### Prerequisites

- [JDK 17](https://adoptium.net/) or higher installed
- `java` and `javac` available in your system PATH

Verify installation:

```bash
java -version
javac -version
```

### Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/todo-list-app.git
cd todo-list-app
```

## How to Run

### Option 1: Command Line

**Windows (PowerShell):**

```powershell
cd src
javac -encoding UTF-8 model/*.java exception/*.java service/*.java util/*.java Main.java
java Main
```

**Linux / macOS:**

```bash
cd src
javac -encoding UTF-8 model/*.java exception/*.java service/*.java util/*.java Main.java
java Main
```

### Option 2: IntelliJ IDEA

1. Open IntelliJ IDEA → **File** → **Open** → select the project folder
2. Mark `src` as Sources Root (right-click `src` → **Mark Directory as** → **Sources Root**)
3. Run `Main.java`

### Option 3: VS Code / Cursor

1. Install the **Extension Pack for Java**
2. Open the project folder
3. Open `src/Main.java` and click **Run**

## Sample Output

```
==================================================
       Welcome to To-Do List Application
==================================================

+------------------------ MAIN MENU ------------------------+
|  1.  Add Task                                             |
|  2.  View All Tasks                                       |
|  3.  Search Task by ID                                    |
|  4.  Search Task by Title                                 |
|  5.  Update Task                                          |
|  6.  Mark Task as Completed                               |
|  7.  Delete Task                                          |
|  8.  View Pending Tasks                                   |
|  9.  View Completed Tasks                                 |
| 10.  Filter by Priority                                   |
| 11.  Sort by Due Date                                     |
| 12.  Sort by Priority                                     |
| 13.  Show Total Tasks                                     |
| 14.  Show Pending Count                                   |
| 15.  Show Completed Count                                 |
| 16.  Exit                                                 |
+-----------------------------------------------------------+
Enter your choice (1-16): 2

All Tasks
----------------------------------------------------------------------------------------------------------------------------------
ID    | Title                     | Description                    | Created      | Due Date     | Priority   | Status
----------------------------------------------------------------------------------------------------------------------------------
1     | Complete Java Assignment  | Finish the OOP console appl... | 2026-06-28   | 2026-07-06   | High       | Pending
2     | Grocery Shopping          | Buy milk, eggs, bread, and ... | 2026-07-01   | 2026-07-04   | Medium     | Pending
...
----------------------------------------------------------------------------------------------------------------------------------
Total: 10 task(s)

Press Enter to continue...
```

See [docs/sample-output.txt](docs/sample-output.txt) for the full sample session.

## Sample Tasks (Pre-loaded)

| ID | Title | Priority | Status |
|----|-------|----------|--------|
| 1 | Complete Java Assignment | HIGH | Pending |
| 2 | Grocery Shopping | MEDIUM | Pending |
| 3 | Schedule Dentist Appointment | LOW | Completed |
| 4 | Prepare Presentation | HIGH | Pending |
| 5 | Pay Electricity Bill | MEDIUM | Pending |
| 6 | Morning Workout | MEDIUM | Completed |
| 7 | Read Design Patterns Book | LOW | Pending |
| 8 | Submit Tax Documents | HIGH | Pending |
| 9 | Call Mom | LOW | Pending |
| 10 | Refactor Legacy Module | HIGH | Completed |

## Screenshots

Add screenshots of the application running in your terminal to the `screenshots/` folder:

- `screenshots/main-menu.png` — Main menu display
- `screenshots/view-all-tasks.png` — Task table view
- `screenshots/search-by-id.png` — Task detail view

## Future Improvements

- Persist tasks to a file or database (SQLite, PostgreSQL)
- Add unit tests with JUnit 5
- Support task categories and tags
- Add due date reminders and overdue notifications
- Export tasks to CSV or JSON
- Implement user authentication for multi-user support
- Build a REST API and web/mobile frontend
- Add recurring tasks and subtasks

## Author

**Rohita**

- GitHub: [@YOUR_USERNAME](https://github.com/YOUR_USERNAME)

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
