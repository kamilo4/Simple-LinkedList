/*
 Student node for singly linked list
*/
public class Student {
    public String firstName;
    public String lastName;
    public String idNumber;
    public int semester;
    public String program;
    public Student next;

    public Student(String firstName, String lastName, String idNumber, int semester, String program) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.semester = semester;
        this.program = program;
        this.next = null;
    }

    @Override
    public String toString() {
        return String.format("%s %s (ID: %s) - Sem: %d - Prog: %s", firstName, lastName, idNumber, semester, program);
    }
}
