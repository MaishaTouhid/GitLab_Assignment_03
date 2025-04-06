import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Utility to read the employee file
    public static String readFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)));
        String line = bufferedReader.readLine();
        bufferedReader.close();
        return line;
    }

    // Utility to write to the employee file
    public static void writeFile(String content, boolean append) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE, append));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please give exactly one argument to run the program.");
            System.out.println("Example: java EmployeeManager l");
            return;
        }

        String command = args[0];
        System.out.println("Loading data ...");

        try {
            if (command.equals("l") || command.equals("line")) {
                for (String employee : readFile().split(",")) {
                    System.out.println(employee);
                }

            } else if (command.equals("s")) {
                String[] employees = readFile().split(",");
                System.out.println("All Employees: " + String.join(", ", employees));
                System.out.println("Random Employee: " + employees[new Random().nextInt(employees.length)]);

            } else if (command.startsWith("+")) {
                writeFile(", " + command.substring(1), true);
                System.out.println("New employee added.");

            } else if (command.startsWith("?")) {
                String[] employees = readFile().split(",");
                String searchName = command.substring(1);

                // Direct search using stream (or basic loop)
                boolean exists = Arrays.asList(employees).contains(searchName);

                if (exists) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found.");
                }

            } else if (command.equals("c")) {
                char[] chars = readFile().toCharArray();
                int wordCount = 0;
                boolean inWord = false;
                for (char c : chars) {
                    if (c == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        }
                    } else {
                        inWord = false;
                    }
                }
                System.out.println(wordCount + " word(s) found. Total characters: " + chars.length);

            } else if (command.startsWith("u")) {
                String[] employees = readFile().split(",");
                String nameToUpdate = command.substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }
                writeFile(String.join(",", employees), false);
                System.out.println("Employee updated.");

            } else if (command.startsWith("d")) {
                List<String> employeeList = new ArrayList<>(Arrays.asList(readFile().split(",")));
                String employeeToDelete = command.substring(1);
                if (employeeList.remove(employeeToDelete)) {
                    writeFile(String.join(",", employeeList), false);
                    System.out.println("Employee deleted.");
                } else {
                    System.out.println("Employee not found for deletion.");
                }

            } else {
                System.out.println("Unknown command. Please check usage.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        System.out.println("Data Loaded.");
    }
}
