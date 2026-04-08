import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentRepository {
    private final List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
    }

    public void add(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("student must not be null");
        }
        students.add(s);
    }

    public List<Student> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(students));
    }
}
