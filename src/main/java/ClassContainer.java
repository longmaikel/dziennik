import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class ClassContainer {

    private final Map<String, Classroom> classrooms;
    private final PrintStream output;

    public ClassContainer() {
        this.classrooms = new HashMap<String, Classroom>();
        this.output = System.out;
    }

    public void addClassroom(String name, int maxStudentsCount) {
        this.classrooms.put(name, new Classroom(name, maxStudentsCount));
    }

    public void  removeClassroom(String name) {
        this.classrooms.remove(name);
    }

    public Classroom getClassroom(String name) {
        return this.classrooms.get(name);
    }

    public void  summary() {
        this.output.println("----- Pojemnik na klasy: -----");
        this.classrooms.forEach((key, classroom) -> {
            this.output.println("Klasa: " + key + ", wypełnienie: " + classroom.getPercentageFilling() + "%");
        } );
        this.output.println("------------------------------");
    }

}
