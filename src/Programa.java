import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class implements a simple student management system.
 */
public class Programa {
 
    public static void main(String[] args) {
        String fileName = "Progra.txt"; // Name of the file storing student data
        Scanner scanner = new Scanner(System.in);
        
        do {
            // Load the list of students from the file
            LinkedList<String> studentList = loadStudentList(fileName);

            // Display menu options
            System.out.println("\nStudent management operations:");
            System.out.println("1. Remove student");
            System.out.println("2. Edit student");
            System.out.println("3. Search student");
            System.out.println("4. Show current file status");
            System.out.println("5. Add new student at the end");
            System.out.println("6. Add new student at the beginning");
            System.out.println("7. Exit");
            System.out.print("Enter the number of the operation you want to perform: ");
            int operation = scanner.nextInt();
            scanner.nextLine();

            // Perform the selected operation based on user input
            switch (operation) {
                case 1:
                    removeStudent(studentList, fileName);
                    break;
                case 2:
                    editStudent(studentList, fileName);
                    break;
                case 3:
                    searchStudent(studentList);
                    break;
                case 4:
                    showFileStatus(fileName);
                    break;
                case 5:
                    addNewStudent(studentList, fileName, false);
                    break;
                case 6:
                    addNewStudent(studentList, fileName, true); 
                    break;
                case 7:
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid operation. Please try again.");
            }

        } while (true);
    }

    /**
     * Loads the list of students from the specified file.
     *
     * @param fileName The name of the file to load student data from.
     * @return A linked list containing the student data.
     */
    private static LinkedList<String> loadStudentList(String fileName) {
        LinkedList<String> studentList = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                studentList.add(line);
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error loading student list: " + e.getMessage());
        }
        return studentList;
    }

    /**
     * Saves the list of students to the specified file.
     *
     * @param studentList The list of students to save.
     * @param fileName    The name of the file to save student data to.
     */
    private static void saveStudentList(LinkedList<String> studentList, String fileName) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(fileName));
            for (String student : studentList) {
                pw.println(student);
            }
            pw.close();
        } catch (IOException e) {
            System.err.println("Error saving student list: " + e.getMessage());
        }
    }

    /**
     * Removes a student from the list based on the given full name.
     *
     * @param studentList The list of students to remove from.
     * @param fileName    The name of the file storing student data.
     */
    private static void removeStudent(LinkedList<String> studentList, String fileName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the full name of the student you want to remove: ");
        String fullName = scanner.nextLine();
        boolean removed = studentList.removeIf(student -> student.contains(fullName));
        if (removed) {
            saveStudentList(studentList, fileName);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found in the list.");
        }
    }

    /**
     * Edits a student's information based on the given full name.
     *
     * @param studentList The list of students to edit.
     * @param fileName    The name of the file storing student data.
     */
    private static void editStudent(LinkedList<String> studentList, String fileName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the full name of the student you want to edit: ");
        String fullName = scanner.nextLine();
        for (int i = 0; i < studentList.size(); i++) {
            String student = studentList.get(i);
            if (student.contains(fullName)) {
                System.out.println("Student found. Enter the new data:");
                System.out.print("New code: ");
                String code = scanner.nextLine();
                System.out.print("New group: ");
                String group = scanner.nextLine();
                System.out.print("New study mode: ");
                String studyMode = scanner.nextLine();
                System.out.print("New ID: ");
                String id = scanner.nextLine();
                System.out.print("New full name: ");
                String newFullName = scanner.nextLine();
                System.out.print("New institutional email address: ");
                String institutionalEmail = scanner.nextLine();
                System.out.print("New enrollment type: ");
                String enrollmentType = scanner.nextLine();
                String newStudent = code + "\t" + group + "\t" + studyMode + "\t" + id + "\t" +
                                     newFullName + "\t" + institutionalEmail + "\t" + enrollmentType;
                studentList.set(i, newStudent);
                saveStudentList(studentList, fileName);
                System.out.println("Student edited successfully.");
                return;
            }
        }
        System.out.println("Student not found in the list.");
    }

    /**
     * Searches for a student based on the given full name.
     *
     * @param studentList The list of students to search in.
     */
    private static void searchStudent(LinkedList<String> studentList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the full name of the student you want to search for: ");
        String fullName = scanner.nextLine();
        boolean found = false;
        for (String student : studentList) {
            if (student.contains(fullName)) {
                System.out.println("Student found:");
                System.out.println(student);
                found = true;
                break; 
            }
        }
        if (!found) {
            System.out.println("Student not found in the list.");
        }
    }

    /**
     * Displays the current status of the file storing student data.
     *
     * @param fileName The name of the file to display status for.
     */
    private static void showFileStatus(String fileName) {
        System.out.println("Current file status:");
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error showing current file status: " + e.getMessage());
        }
    }

    /**
     * Adds a new student to the list.
     *
     * @param studentList  The list of students
     * @param studentList  The list of students to add to.
     * @param fileName     The name of the file storing student data.
     * @param atBeginning  A flag indicating whether to add the new student at the beginning or end of the list.
     */
    private static void addNewStudent(LinkedList<String> studentList, String fileName, boolean atBeginning) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the details of the new student:");
        System.out.print("Code: ");
        String code = scanner.nextLine();
        System.out.print("Group: ");
        String group = scanner.nextLine();
        System.out.print("Study mode: ");
        String studyMode = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Institutional email address: ");
        String institutionalEmail = scanner.nextLine();
        System.out.print("Enrollment type: ");
        String enrollmentType = scanner.nextLine();

        String newStudent = code + "\t" + group + "\t" + studyMode + "\t" + id + "\t" +
                             fullName + "\t" + institutionalEmail + "\t" + enrollmentType;
        
        if (atBeginning) {
            studentList.addFirst(newStudent);
        } else {
            studentList.addLast(newStudent);
        }

        saveStudentList(studentList, fileName);
        System.out.println("New student added successfully.");
    }
}
