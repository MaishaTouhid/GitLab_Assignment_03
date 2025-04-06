import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Reads the employee file and returns its content as a single string
    public static String readEmployeeData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(Constants.EMPLOYEE_FILE)));
        String line = reader.readLine();
        reader.close();
        return line;
    }

    // Writes content to the employee file (append = true for adding, false for replacing)
    public static void writeEmployeeData(String content, boolean append) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, append));
        writer.write(content);
        writer.close();
    }

    // Displays usage instructions for the program
    public static void printUsage() {
        System.out.println("Please give exactly one argument to run the program.");
        System.out.println("Example: java EmployeeManager l");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid number of arguments.");
            printUsage();
            return;
        }

        String command = args[0];
        System.out.println("Loading data ...");

        try {
            // 1. List all employees
            if (command.equals("l") || command.equals("line")) {
                String[] employees = readEmployeeData().split(",");
                for (String employee : employees) {
                    System.out.println(employee.trim());
                }

            // 2. Show a random employee
            } else if (command.equals("s")) {
                String[] employees = readEmployeeData().split(",");
                System.out.println("All Employees: " + String.join(", ", employees));
                System.out.println("Random Employee: " + employees[new Random().nextInt(employees.length)]);

            // 3. Add new employee
            } else if (command.startsWith("+")) {
                String newEmployee = command.substring(1);
                writeEmployeeData(", " + newEmployee, true);
                System.out.println("New employee added.");

            // 4. Search for an employee
            } else if (command.startsWith("?")) {
                String[] employees = readEmployeeData().split(",");
                String searchName = command.substring(1);
                if (Arrays.asList(employees).contains(searchName)) {
                    System.out.println("Employee found.");
                } else {
                    System.out.println("Employee not found.");
                }

            // 5. Count words and characters
            } else if (command.equals("c")) {
                String employeeData = readEmployeeData();
                String[] words = employeeData.trim().split("\\s+");
                System.out.println(words.length + " word(s) found. Total characters: " + employeeData.length());

            // 6. Update an employee by name
            } else if (command.startsWith("u")) {
                String[] employees = readEmployeeData().split(",");
                String nameToUpdate = command.substring(1);
                boolean updated = false;
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(nameToUpdate)) {
                        employees[i] = "Updated";
                        updated = true;
                    }
                }
                if (updated) {
                    writeEmployeeData(String.join(",", employees), false);
                    System.out.println("Employee updated.");
                } else {
                    System.out.println("Employee not found to update.");
                }

            // 7. Delete an employee by name
            } else if (command.startsWith("d")) {
                List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeeData().split(",")));
                String employeeToDelete = command.substring(1);
                if (employeeList.removeIf(name -> name.trim().equals(employeeToDelete))) {
                    writeEmployeeData(String.join(",", employeeList), false);
                    System.out.println("Employee deleted.");
                } else {
                    System.out.println("Employee not found for deletion.");
                }

            // 8. Unknown command fallback
            } else {
                System.out.println("Unknown command: " + command);
                printUsage();
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        System.out.println("Data Loaded.");
    }
}
