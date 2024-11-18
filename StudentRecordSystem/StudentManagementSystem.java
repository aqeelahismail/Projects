import java.util.*;

public class StudentManagementSystem {
    public static void main(String[] args)
    {
        System.out.println("Welcome to the Student Management System");
        System.out.println("Please enter the Student Database filename below:");
        Scanner keyboard = new Scanner(System.in);

        StudentDatabase database = new StudentDatabase();
        String fileName = keyboard.nextLine();
        database.readFile(fileName);

        if (!(database.isEmpty()))
        {
            System.out.println("Database Uploaded.");
    
            System.out.println("What would you like to do next?");
            System.out.println("1. Search for a Student\n2. Add a new Student to the Database\n3. View Top 10 Students\n4. Select Students Alphabetically\n5. Delete a Student Record\n6. Exit");
            String answer = keyboard.nextLine();
        
            while (!(answer.equals("6")))
            {
                switch (answer)
                {
                    // SEARCH FOR STUDENT
                    case "1":
                        System.out.println("Student ID:");
                        StudentNode foundNode = database.findByID(keyboard.nextLine());

                        if (foundNode == null)
                        {
                            System.out.println("Student not found.");
                            break;
                        }

                        System.out.println(foundNode.studentData.toString());
                        System.out.println("What would you like to do next?");
                        System.out.println("1. See " + foundNode.studentData.studentID + "'s GPA\n2. View Outstanding Fees\n3. Back");
                        String subAnswer = keyboard.nextLine();

                        while (!(subAnswer.equals("3")))
                        {
                            if (subAnswer.equals("1"))
                            {
                                System.out.println(foundNode.studentData.getGPA());
                            } else if (subAnswer.equals("2"))
                            {
                                System.out.println(foundNode.studentData.getOutstandingFees());
                            }
                                System.out.println("What would you like to do next?");
                                System.out.println("1. See " + foundNode.studentData.studentID + "'s GPA\n2. View Outstanding Fees\n3. Back");
                                subAnswer = keyboard.nextLine();
                        }
                    break;

                    // ADD NEW STUDENT
                    case "2":
                        System.out.println("Student Name:");
                        String newName = keyboard.nextLine();
                        System.out.println("Degree Code:");
                        String newDeg = keyboard.nextLine();
                        System.out.println("Faculty:");
                        String newFac = keyboard.nextLine();
                        System.out.println("Duration:");
                        int newDur = Integer.parseInt(keyboard.nextLine());

                        Student toAdd = new Student(newName, newDeg, newFac, newDur);
                        database.insert(toAdd);

                        System.out.println("Student Added: " + toAdd.studentID);
                    break;

                    // TOP 10 STUDENTS
                    case "3":
                        StudentDatabase databaseGPA = new StudentDatabase();
                        databaseGPA.readFileGPA(fileName);
                        databaseGPA.printTop();
                    break;

                    // ALPHABETICAL RANGE
                    case "4":
                        System.out.println("Enter starting letter:");
                        char start = keyboard.nextLine().toUpperCase().charAt(0);

                        System.out.println("Enter ending letter:");
                        char end = keyboard.nextLine().toUpperCase().charAt(0);

                        ArrayList<String> range = new ArrayList<String>();
                        range = database.getAlphabetical(start, end);

                        for (String alphaID : range)
                        {
                            System.out.println(alphaID);
                        }
                    break;

                    // DELETE STUDENT RECORD
                    case "5":
                        System.out.println("Student ID:");
                        String deleteID = keyboard.nextLine();

                        StudentNode nodeRemove = database.findByID(deleteID);
                        database.remove(nodeRemove.studentData);

                        if (database.find(nodeRemove.studentData) == null)
                        {
                            System.out.println("Student successfully deleted.");
                        } else {
                            System.out.println("Error: Student could not be deleted.");
                        }
                        
                    break;
                }
                System.out.println("What would you like to do next?");
                System.out.println("1. Search for a Student\n2. Add a new Student to the Database\n3. View Top 10 Students\n4. Select Students Alphabetically\n5. Delete a Student Record\n6. Exit");
                answer = keyboard.nextLine();
            }
        } else {
            System.out.println("Error: Database cannot be uploaded.");
        }
    }
}
