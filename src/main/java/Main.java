
public class Main {
    public static void main(String[] args) {
        final String CLASS_NAME_1 = "klasa1";
        final String CLASS_NAME_2 = "klasa2";
        ClassContainer classContainer = new ClassContainer();
        classContainer.addClassroom(CLASS_NAME_1, 10);
        classContainer.addClassroom(CLASS_NAME_2, 20);

        classContainer.summary();
        classContainer.removeClassroom(CLASS_NAME_1);
        classContainer.summary();

        Student s1 = new Student("Michał", "Nowak", 1999, 0, StudentCondition.OK);
        Student s2 = new Student("Jacek", "Windak", 1999, 0, StudentCondition.OK);
        Student s3 = new Student("Anna", "Brzózka", 1999, 0, StudentCondition.OK);
        Classroom classroom1 = classContainer.getClassroom(CLASS_NAME_2);
        classroom1.addStudent(s1);
        classroom1.addStudent(s2);
        classroom1.addStudent(s3);

        classroom1.summary();


    }
}
