import java.util.ArrayList;
import java.util.List;

/*
 Manager for Course CRUD operations. Uses a List<Course> to hold courses in memory.
 Each Course internally manages its students using a singly linked list as required.
*/
public class CourseManager {
    private List<Course> courses;

    public CourseManager() {
        courses = new ArrayList<>();
    }

    // Create course. Returns true if created, false if id already exists or invalid credits.
    public boolean createCourse(String id, String name, int credits) {
        if (credits <= 0) return false;
        if (getCourseById(id) != null) return false;
        courses.add(new Course(id, name, credits));
        return true;
    }

    public Course getCourseById(String id) {
        for (Course c : courses) {
            if (c.id.equals(id)) return c;
        }
        return null;
    }

    // Update course fields
    public boolean updateCourse(String id, String newName, Integer newCredits) {
        Course c = getCourseById(id);
        if (c == null) return false;
        if (newName != null) c.name = newName;
        if (newCredits != null) {
            if (newCredits <= 0) return false;
            c.credits = newCredits;
        }
        return true;
    }

    // Delete course and free its students
    public boolean deleteCourse(String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).id.equals(id)) {
                Course c = courses.get(i);
                c.freeStudents();
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Course> listCourses() {
        return new ArrayList<>(courses);
    }
}
