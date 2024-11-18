
public class StudentNode {
    Student studentData;
    StudentNode left;
    StudentNode right;

    public StudentNode(Student studentData)
    {
        this.studentData = studentData;
        this.left = null;
        this.right = null;
    }

    public StudentNode getLeft()
    {
        return this.left;
    }

    public StudentNode getRight()
    {
        return this.right;
    }
}
