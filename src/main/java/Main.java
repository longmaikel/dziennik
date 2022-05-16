import gui.ClassroomsTable;
import gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        try {

            new GUI();

//            final String CLASS_NAME_1 = "klasa1";
//            final String CLASS_NAME_2 = "klasa2";
//            model.ClassContainer classContainer = new model.ClassContainer();
//            classContainer.addClassroom(CLASS_NAME_1, 2);
//            classContainer.addClassroom(CLASS_NAME_2, 20);
//
//            classContainer.summary();
//            classContainer.removeClassroom(CLASS_NAME_1);
//            classContainer.summary();
//
//            model.Student s1 = new model.Student("Michał", "Nowak", 1999, 0, model.StudentCondition.OK);
//            model.Student s2 = new model.Student("Jacek", "Windak", 1999, 0, model.StudentCondition.OK);
//            model.Student s3 = new model.Student("Anna", "Brzózka", 1999, 0, model.StudentCondition.OK);
//            model.Student s4 = new model.Student("Anna", "Nowakowska", 1999, 0, model.StudentCondition.OK);
//
//            classContainer.addClassroom(CLASS_NAME_2, 20);
//
//            List<model.Classroom> emptyClassrooms = classContainer.findEmpty();
//            System.out.println("---------------------");
//            System.out.println("puste klasy: ");
//            emptyClassrooms.forEach(model.Classroom::summary);
//            System.out.println("---------------------");
//
//
//            model.Classroom classroom1 = classContainer.getClassroom(CLASS_NAME_2);
//            classroom1.addStudent(s1);
//            classroom1.addStudent(s2);
//            classroom1.addStudent(s3);
//            classroom1.addStudent(s4);
//
//            classroom1.summary();
//
//            classroom1.addPoints(s1, 10);
//            classroom1.addPoints(s2, 5);
//            classroom1.removePoints(s3, 2);
//
//            classroom1.summary();
//
//            classroom1.changeCondition(s1, model.StudentCondition.ABSENT);
//
//            classroom1.summary();
//
//            Optional<model.Student> optionalStudent = classroom1.search("Nowak");
//
//            if (optionalStudent.isEmpty()) {
//                System.out.println("W klasie ma nie studenta o podanym nazwisku");
//            } else {
//                System.out.println("---------------------");
//                System.out.println("Znaleziony uczniowie: ");
//                optionalStudent.get().print();
//                System.out.println("---------------------");
//            }
//
//            ArrayList<model.Student> matchedStudents = classroom1.searchPartial("Nowa");
//            System.out.println("---------------------");
//            System.out.println("Znalezieni uczniowie: ");
//            matchedStudents.forEach(model.Student::print);
//            System.out.println("---------------------");
//
//            System.out.println("Liczba studentow o statusie " + model.StudentCondition.ABSENT + ":" + classroom1.countByCondition(model.StudentCondition.ABSENT));
//            System.out.println("Liczba studentow o statusie " + model.StudentCondition.OK + ":" + classroom1.countByCondition(model.StudentCondition.OK));
//
//            ArrayList<model.Student> sortedStudents = classroom1.sortByName();
//            System.out.println("---------------------");
//            System.out.println("posortowani uczniowie po nazwisku: ");
//            sortedStudents.forEach(model.Student::print);
//            System.out.println("---------------------");
//
//            sortedStudents = classroom1.sortByPoints();
//            System.out.println("---------------------");
//            System.out.println("posortowani uczniowie po pkt malejaco: ");
//            sortedStudents.forEach(model.Student::print);
//            System.out.println("---------------------");

        } catch (Throwable e) {
            System.out.println("Błąd aplikacji");
            System.out.println(e.getMessage());
        }



    }
}
