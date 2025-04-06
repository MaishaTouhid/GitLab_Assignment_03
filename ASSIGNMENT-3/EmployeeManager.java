import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please give exactly one argument to run the program.");
            System.out.println("Example: java EmployeeManager l");
            return;
        }
         // Check arguments
        if (args[0].equals(" line ")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader bufferReader  = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream("employees.txt")));
                String line = bufferReader .readLine();
                String employee[] = line.split(",");
                for (String employes : employee) {
                    System.out.println(employes);
                }
            } catch (Exception e) {
            System.out.println("Data Loaded.");
            }
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader bufferReader = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream("employees.txt")));
                String line = bufferReader.readLine();
                System.out.println(line);
                String e[] = line.split(",");
                Random rand = new Random();
                int idx = rand.nextInt(e.length);
                System.out.println(e[idx]);
            } catch (Exception exception) {
            System.out.println("Data Loaded.");
            }
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter w = new BufferedWriter(
                    new FileWriter("employees.txt", true));
                String n = args[0].substring(1);
                w.write(", " + n);
                w.close();
            } catch (Exception eexception) {
            System.out.println("Data Loaded.");
            }
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader bufferReader  = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream("employees.txt")));
                String line = bufferReader .readLine();
                String e[] = line.split(",");
                boolean found = false;
                String s = args[0].substring(1);
                for (int i = 0; i < e.length && !found; i++) {
                    if (e[i].equals(s)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            } catch (Exception exception) {
            System.out.println("Data Loaded.");
            }
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader bufferReader  = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = bufferReader .readLine();
                char[] chars = line.toCharArray();
                boolean inWord = false;
                int count = 0;
                for (char c : chars) {
                    if (c == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(count + " word(s) found " + chars.length);
            } catch (Exception exception) {
            System.out.println("Data Loaded.");
            }
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader bufferReader  = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = bufferReader .readLine();
                String e[] = line.split(",");
                String n = args[0].substring(1);
                for (int i = 0; i < e.length; i++) {
                    if (e[i].equals(n)) {
                        e[i] = "Updated";
                    }
                }
                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt"));
                w.write(String.join(",", e));
                w.close();
            } catch (Exception eexception) {
            System.out.println("Data Updated.");
            }
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader bufferReader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = bufferReader .readLine();
                String e[] = line.split(",");
                String n = args[0].substring(1);
                List<String> list = new ArrayList<>(Arrays.asList(e));
                list.remove(n);
                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt"));
                w.write(String.join(",", list));
                w.close();
            } catch (Exception exception) {
            System.out.println("Data Deleted.");
            }
        }
    }
}
