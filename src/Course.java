/*
 Course (Class) that internally manages students with a singly linked list.
*/
public class Course {
    public String id;
    public String name;
    public int credits; // must be positive
    public Student head; // head of singly linked list

    public Course(String id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.head = null;
    }

    // justification: insert students ordered by lastName (ascending). If lastName equal, order by idNumber.
    // This keeps a readable sorted roster and makes some operations (like alphabetical listing) consistent.

    // Check duplicate idNumber
    public boolean existsById(String idNumber) {
        Student cur = head;
        while (cur != null) {
            if (cur.idNumber.equals(idNumber)) return true;
            cur = cur.next;
        }
        return false;
    }

    // Insert keeping order by lastName, then idNumber.
    // Returns true if inserted, false if duplicate idNumber or invalid credits (handled externally)
    public boolean insertStudent(Student s) {
        if (s == null) return false;
        if (existsById(s.idNumber)) return false;

        // empty list -> new head
        if (head == null) {
            head = s;
            return true;
        }

        // compare function
        java.util.Comparator<Student> cmp = (a, b) -> {
            int c = a.lastName.compareToIgnoreCase(b.lastName);
            if (c != 0) return c;
            return a.idNumber.compareTo(b.idNumber);
        };

        // if new student should be new head
        if (cmp.compare(s, head) <= 0) {
            s.next = head;
            head = s;
            return true;
        }

        // find insertion point
        Student prev = head;
        Student cur = head.next;
        while (cur != null && cmp.compare(s, cur) > 0) {
            prev = cur;
            cur = cur.next;
        }
        // insert between prev and cur
        prev.next = s;
        s.next = cur;
        return true;
    }

    // Update student by idNumber. Returns true if found and updated.
    public boolean updateStudent(String idNumber, String newFirstName, String newLastName, Integer newSemester, String newProgram) {
        Student cur = head;
        while (cur != null) {
            if (cur.idNumber.equals(idNumber)) {
                if (newFirstName != null) cur.firstName = newFirstName;
                if (newLastName != null) cur.lastName = newLastName;
                if (newSemester != null) cur.semester = newSemester;
                if (newProgram != null) cur.program = newProgram;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // Find student by idNumber
    public Student findById(String idNumber) {
        Student cur = head;
        while (cur != null) {
            if (cur.idNumber.equals(idNumber)) return cur;
            cur = cur.next;
        }
        return null;
    }

    // List all students as String lines
    public String listAllStudents() {
        StringBuilder sb = new StringBuilder();
        Student cur = head;
        if (cur == null) {
            sb.append("(lista vacía)\n");
            return sb.toString();
        }
        int idx = 1;
        while (cur != null) {
            sb.append(String.format("%d. %s\n", idx++, cur.toString()));
            cur = cur.next;
        }
        return sb.toString();
    }

    // Remove student by idNumber. Returns true if removed.
    public boolean removeById(String idNumber) {
        if (head == null) return false;
        // special case: head
        if (head.idNumber.equals(idNumber)) {
            Student toDel = head;
            head = head.next;
            toDel.next = null; // help GC
            return true;
        }
        Student prev = head;
        Student cur = head.next;
        while (cur != null) {
            if (cur.idNumber.equals(idNumber)) {
                prev.next = cur.next;
                cur.next = null;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    // Free the entire list (used when deleting course)
    public void freeStudents() {
        Student cur = head;
        while (cur != null) {
            Student next = cur.next;
            cur.next = null;
            cur = next;
        }
        head = null;
    }

    @Override
    public String toString() {
        return String.format("Course %s - %s (credits: %d)", id, name, credits);
    }
}
