import java.util.*;

public class Student {
    protected String studentID;
    protected String name;
    protected String degree;
    protected String faculty;
    protected double outstandingFees;
    protected double GPA;
    protected int duration;

    public Student(String id, String name, String degreeCode, String faculty, double outstanding, int duration, double GPA)
    {
        this.studentID = id;
        this.name = name;
        this.degree = degreeCode;
        this.faculty = faculty;
        this.outstandingFees = outstanding;
        this.duration = duration;
        this.GPA = GPA;
    }

    public Student(String name, String degreeCode, String faculty, int duration)
    {
        this.name = name;
        this.studentID = generateID(name);
        this.degree = degreeCode;
        this.faculty = faculty;
        this.duration = duration;
        this.outstandingFees = 0;
        this.GPA = 0;
    }

    public Student(String lineOfText)
    {
        String[] splitAttributes = lineOfText.split("_", 8);
        this.studentID = splitAttributes[0];
        this.name = splitAttributes[1];
        this.degree = splitAttributes[2];
        this.faculty = splitAttributes[3];
        this.outstandingFees = Double.valueOf(splitAttributes[4]);
        this.duration = Integer.parseInt(splitAttributes[5]);
        this.GPA = Double.parseDouble(splitAttributes[6]);
    }

    public String generateID(String name)
    {
        String[] nameSplit = name.split(" ", 2);
        String firstThree = nameSplit[0].substring(0,3);
        String secondThree = nameSplit[1].substring(0, 3);

        Random r = new Random();

        int number = r.nextInt(900) + 100;

        return firstThree.toUpperCase() + secondThree.toUpperCase() + Integer.toString(number);
    }

    public String toString()
    {
        return "ID: " + this.studentID + "\nName: " + this.name + "\nDegree: " + this.degree + "\nFaculty: " + this.faculty + "\nDuration: " + this.duration;
    }

    public String getOutstandingFees()
    {
        return "Outstanding Fees: R" + String.format("%.2f", this.outstandingFees);
    }

    public String getGPA()
    {
        return "GPA: " + this.GPA + "%";
    }

    public boolean equals(Student other)
    {
        if (this.name.equals(other.name) && (this.studentID.equals(other.studentID)))
        {
            return true;
        } else {
            return false;
        }
    }

    public int compareTo(Student other)
    {
        // return -1 if other before this
        // return 0 if keys are same
        // return 1 if other after this
        return this.studentID.compareTo(other.studentID); // lexicographically compare id's
    }
}
