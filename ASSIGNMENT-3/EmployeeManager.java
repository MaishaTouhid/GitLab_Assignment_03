import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static String readFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("employees.txt")));
        String line = bufferedReader.readLine();
        bufferedReader.close();
        return line;
    }

    public static void writeFile(String content, boolean append) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("employees.txt", append));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please give exactly one argument to run the program.");
            System.out.println("Example: java EmployeeManager l");
            return; // Stop the program if the argument is missing or too many
        }
        String argument = args[0];

        if (argument.equals("l") || argument.equals("line")) {
            System.out.println("Loading data ...");
            try {
                String line = readFile();
                String[] employees = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception ex) {
            System.out.println("Data Loaded.");
            }

        } else if (argument.equals("s")) {
            System.out.println("Loading data ...");
            try {
                String line = readFile();
                System.out.println(line);
                String[] employees = line.split(",");
                Random rand = new Random();
                int idx = rand.nextInt(employees.length);
                System.out.println(employees[idx]);
            } catch (Exception ex) {}
            System.out.println("Data Loaded.");

        } else if (argument.startsWith("+")) {
            System.out.println("Loading data ...");
            try {
                String newEmployee = argument.substring(1);
                writeFile(", " + newEmployee, true);
            } catch (Exception ex) {
            System.out.println("Data Loaded.");
            }

        } else if (argument.startsWith("?")) {
            System.out.println("Loading data ...");
            try {
                String line = readFile();
                String[] employees = line.split(",");
                boolean found = false;
                String searchName = argument.substring(1);
                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            } catch (Exception ex) {
            System.out.println("Data Loaded.");
            }

        } else if (argument.contains("c")) {
            System.out.println("Loading data ...");
            try {
                String line = readFile();
                char[] chars = line.toCharArray();
                boolean inWord = false;
                int count = 0;
                for (char c : chars) {
                    if (c == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        }
                    } else {
                        inWord = false;
                    }
                }
                System.out.println(count + " word(s) found " + chars.length);
            } catch (Exception ex) {}
            System.out.println("Data Loaded.");

        } else if (argument.startsWith("u")) {
            System.out.println("Loading data ...");
            try {
                String line = readFile();
                String[] employees = line.split(",");
                String nameToUpdate = argument.substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }
                writeFile(String.join(",", employees), false);
            } catch (Exception ex) {
            System.out.println("Data Updated.");
            }

        } else if (argument.startsWith("d")) {
            System.out.println("Loading data ...");
            try {
                String line = readFile();
                String[] employees = line.split(",");
                String employeeToDelete = argument.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeToDelete);
                writeFile(String.join(",", employeeList), false);
            } catch (Exception ex) {
            System.out.println("Data Deleted.");
            }
        }
    }
}
