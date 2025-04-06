import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static String readFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)));
        String line = bufferedReader.readLine();
        bufferedReader.close();
        return line;
    }

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

        if (args[0].equals("l") || args[0].equals("line")) {
            System.out.println("Loading data ...");
            try {
                for (String employee : readFile().split(",")) {
                    System.out.println(employee);
                }
            } catch (Exception ex) {
                System.out.println("Data Loaded.");
            }

        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readFile().split(",");
                System.out.println(String.join(",", employees));
                System.out.println(employees[new Random().nextInt(employees.length)]);
            } catch (Exception ex) {}
            System.out.println("Data Loaded.");

        } else if (args[0].startsWith("+")) {
            System.out.println("Loading data ...");
            try {
                writeFile(", " + args[0].substring(1), true);
            } catch (Exception ex) {
                System.out.println("Data Loaded.");
            }

        } else if (args[0].startsWith("?")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readFile().split(",");
                boolean found = false;
                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(args[0].substring(1))) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Data Loaded.");
            }

        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                char[] chars = readFile().toCharArray();
                int count = 0;
                boolean inWord = false;
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

        } else if (args[0].startsWith("u")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readFile().split(",");
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(args[0].substring(1))) {
                        employees[i] = "Updated";
                    }
                }
                writeFile(String.join(",", employees), false);
            } catch (Exception ex) {
                System.out.println("Data Updated.");
            }

        } else if (args[0].startsWith("d")) {
            System.out.println("Loading data ...");
            try {
                List<String> employeeList = new ArrayList<>(Arrays.asList(readFile().split(",")));
                employeeList.remove(args[0].substring(1));
                writeFile(String.join(",", employeeList), false);
            } catch (Exception ex) {
                System.out.println("Data Deleted.");
            }
        }
    }
}