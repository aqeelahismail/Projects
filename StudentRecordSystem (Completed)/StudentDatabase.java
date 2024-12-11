import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StudentDatabase {
    //THE BST TREE
    public StudentNode root;
    public ArrayList<Student> top;

    public StudentDatabase()
    {
        this.root = null;
    }

    // METHODS FOR CREATING BST BASED ON ID
    public void readFile(String filename)
    {
        try {
            File file = new File(filename);
            Scanner linesOfFile = new Scanner(file);
            while (linesOfFile.hasNextLine())
            {
                Student newStudent = new Student(linesOfFile.nextLine());
                insert(newStudent);
            }

            linesOfFile.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void insert(Student newStudent)
    {
        this.root = insert(newStudent, root);
    }

    public StudentNode insert(Student newStudent, StudentNode root)
    {
        if (root == null)
        {
            root = new StudentNode(newStudent);
        } else if (newStudent.compareTo(root.studentData) < 0)
        {
            root.left = insert(newStudent, root.left);
        } else if (newStudent.compareTo(root.studentData) > 0)
        {
            root.right = insert(newStudent, root.right);
        }
        return root;
    }

    // METHODS FOR CREATING BST BASED ON GPA
    public void readFileGPA(String filename)
    {
        try {
            File file = new File(filename);
            Scanner linesOfFile = new Scanner(file);
            while (linesOfFile.hasNextLine())
            {
                Student newStudent = new Student(linesOfFile.nextLine());
                insertGPA(newStudent);
            }

            linesOfFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertGPA(Student newStudent)
    {
        this.root = insertGPA(newStudent, root);
    }

    public StudentNode insertGPA(Student newStudent, StudentNode root)
    {
        if (root == null)
        {
            root = new StudentNode(newStudent);
        } else if (newStudent.GPA < root.studentData.GPA)
        {
            root.left = insertGPA(newStudent, root.left);
        } else if (newStudent.GPA > root.studentData.GPA)
        {
            root.right = insertGPA(newStudent, root.right);
        } else if (newStudent.GPA == root.studentData.GPA)
        {
            root.right = insertGPA(newStudent, root.right);
        }
        return root;
    }

    // FIND METHODS WHEN GIVEN STUDENT INPUT
    public StudentNode find(Student findInfo)
    {
        return findStudent(findInfo, this.root);
    }

    public StudentNode findStudent(Student studentInfo, StudentNode node)
    {
        while (node != null)
        {
            if (studentInfo.compareTo(node.studentData) < 0)
            {
                node = node.left;
            } else if (studentInfo.compareTo(node.studentData) > 0)
            {
                node = node.right;
            } else if (studentInfo.compareTo(node.studentData) == 0)
            {
                return node;
            }
        }
        return null; // not found
    }

    // FIND METHODS WHEN GIVEN STRING ID
    public StudentNode findByID(String findID)
    {
        return findByID(findID, this.root);
    }

    public StudentNode findByID(String findID, StudentNode node)
    {
        while (node != null)
        {
            if (findID.compareTo(node.studentData.studentID) < 0)
            {
                node = node.left;
            } else if (findID.compareTo(node.studentData.studentID) > 0)
            {
                node = node.right;
            } else if (findID.compareTo(node.studentData.studentID) == 0)
            {
                return node;
            }
        }
        return null;
    }

    // METHOD FOR DELETING A STUDENT
    public void remove(Student removeStudent)
    {
        this.root = remove(removeStudent, root);
    }

    public StudentNode remove(Student removeStudent, StudentNode node)
    {
        if (node == null)
        {
            return null;
        } else if (removeStudent.compareTo(node.studentData) < 0)
        {
            node.left = remove(removeStudent, node.left);
        } else if (removeStudent.compareTo(node.studentData) > 0)
        {
            node.right = remove(removeStudent, node.right);
        } else {
            if (node.left != null)
            {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    // CHECK IF EMPTY
    public boolean isEmpty()
    {
        if (this.root == null)
        {
            return true;
        } else {
            return false;
        }
    }

    public void makeEmpty()
    {
        this.root = null;
    }

    // METHODS FOR GETTING ALPHABETICAL RANGE FROM BST
    public ArrayList<String> getAlphabetical(char start, char end)
    {
        ArrayList<String> alphabeticalRange = new ArrayList<String>();
        sortThroughTree(this.root, start, end, alphabeticalRange);
        return alphabeticalRange;
    }

    public void sortThroughTree(StudentNode node, char start, char end, ArrayList<String> container)
    {
        if (node == null)
        {
            return;
            // do nothing
        }

        sortThroughTree(node.left, start, end, container);

        char firstLetter = node.studentData.studentID.charAt(0);

        if (firstLetter >= start && firstLetter <= end)
        {
            container.add(node.studentData.studentID);
        }

        sortThroughTree(node.right, start, end, container);
    }

    // METHODS FOR GETTING TOP 10 STUDENTS BASED ON GPA
    public void printTop()
    {
        getTop();
        System.out.println("Results:");
        for (Student student : top)
        {
            System.out.println("Name: " + student.name + "\nGPA: " + student.GPA);
        }
    }

    public void getTop()
    {
        top = new ArrayList<Student>();
        traverseForTop(this.root);
    }

    public void traverseForTop(StudentNode node)
    {
        if (node == null || top.size() >=25)
        {
            return;
        }

        traverseForTop(node.right);

        if (top.size() < 10)
        {
            top.add(node.studentData);
        }

        traverseForTop(node.left);
    }
}
