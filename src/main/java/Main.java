
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
    }
}
