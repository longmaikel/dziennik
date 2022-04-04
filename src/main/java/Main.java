import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        try {

            final String CLASS_NAME_1 = "klasa1";
            final String CLASS_NAME_2 = "klasa2";
            ClassContainer classContainer = new ClassContainer();
            classContainer.addClassroom(CLASS_NAME_1, 2);
            classContainer.addClassroom(CLASS_NAME_2, 20);

            classContainer.summary();
            classContainer.removeClassroom(CLASS_NAME_1);
            classContainer.summary();

            Student s1 = new Student("Michał", "Nowak", 1999, 0, StudentCondition.OK);
            Student s2 = new Student("Jacek", "Windak", 1999, 0, StudentCondition.OK);
            Student s3 = new Student("Anna", "Brzózka", 1999, 0, StudentCondition.OK);
            Student s4 = new Student("Anna", "Nowakowska", 1999, 0, StudentCondition.OK);

            classContainer.addClassroom(CLASS_NAME_2, 20);

            List<Classroom> emptyClassrooms = classContainer.findEmpty();
            System.out.println("---------------------");
            System.out.println("puste klasy: ");
            emptyClassrooms.forEach(Classroom::summary);
            System.out.println("---------------------");


            Classroom classroom1 = classContainer.getClassroom(CLASS_NAME_2);
            classroom1.addStudent(s1);
            classroom1.addStudent(s2);
            classroom1.addStudent(s3);
            classroom1.addStudent(s4);

            classroom1.summary();

            classroom1.addPoints(s1, 10);
            classroom1.addPoints(s2, 5);
            classroom1.removePoints(s3, 2);

            classroom1.summary();

            classroom1.changeCondition(s1, StudentCondition.ABSENT);

            classroom1.summary();

            Optional<Student> optionalStudent = classroom1.search("Nowak");

            if (optionalStudent.isEmpty()) {
                System.out.println("W klasie ma nie studenta o podanym nazwisku");
            } else {
                System.out.println("---------------------");
                System.out.println("Znaleziony uczniowie: ");
                optionalStudent.get().print();
                System.out.println("---------------------");
            }

            ArrayList<Student> matchedStudents = classroom1.searchPartial("Nowa");
            System.out.println("---------------------");
            System.out.println("Znalezieni uczniowie: ");
            matchedStudents.forEach(Student::print);
            System.out.println("---------------------");

            System.out.println("Liczba studentow o statusie " + StudentCondition.ABSENT + ":" + classroom1.countByCondition(StudentCondition.ABSENT));
            System.out.println("Liczba studentow o statusie " + StudentCondition.OK + ":" + classroom1.countByCondition(StudentCondition.OK));

            ArrayList<Student> sortedStudents = classroom1.sortByName();
            System.out.println("---------------------");
            System.out.println("posortowani uczniowie po nazwisku: ");
            sortedStudents.forEach(Student::print);
            System.out.println("---------------------");

            sortedStudents = classroom1.sortByPoints();
            System.out.println("---------------------");
            System.out.println("posortowani uczniowie po pkt malejaco: ");
            sortedStudents.forEach(Student::print);
            System.out.println("---------------------");

        } catch (Throwable e) {
            System.out.println("Błąd aplikacji");
            System.out.println(e.getMessage());
        }



    }
}
