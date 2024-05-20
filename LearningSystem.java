import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LearningSystem {
    public static ArrayList<Instructor> instructors = new ArrayList<Instructor>();
    public static ArrayList<Student> students = new ArrayList<Student>();
    public static ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    public static User activeUser;

    public LearningSystem(){
        readInstructors();
        readStudents();
        readAssignments();
    }

    public static void readInstructors(){
        try {
            File myObj = new File("static/Instructors.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfData = data.split(",", -1);
                int id = Integer.parseInt(arrOfData[0]);
                String name = arrOfData[1];
                String email = arrOfData[2];
                String hashPass = arrOfData[3];
                Instructor i1 = new Instructor(id, name, email, hashPass);
                instructors.add(i1);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readStudents() {
        try {
            File myObj = new File("static/Students.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfData = data.split(",", -1);
                int id = Integer.parseInt(arrOfData[0]);
                String name = arrOfData[1];
                String email = arrOfData[2];
                String hashPass = arrOfData[3];
                int presents = Integer.parseInt(arrOfData[4]);
                int absents = Integer.parseInt(arrOfData[5]);
                Student s1 = new Student(id, name, email, hashPass, presents, absents);
                students.add(s1);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readAssignments() {
        try {
            File myObj = new File("static/Assignments.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfData = data.split(",", -1);
                int sID = Integer.parseInt(arrOfData[0]);
                String comment = arrOfData[1];
                String path = arrOfData[2];
                Assignment a1 = new Assignment(sID, comment, path);
                assignments.add(a1);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
