package model;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Classroom {

    private final String name;
    private final List<Student> students = new ArrayList<>();
    private int maxStudentsCount;
    private final PrintStream output;

    public Classroom(String name, int maxStudentsCount) {
        this.name = name;
        this.setVolume(maxStudentsCount);
        this.output = System.out;
    }

    public Classroom setVolume(int volume) {
        if (this.isEmpty()) {
            this.maxStudentsCount = volume;
        }
        return this;
    }


    public void addStudent(Student student) throws FulfilledClassroomException, StudentAlreadyAddedToClassroomException {
        if (this.isFull()) {
            throw new FulfilledClassroomException();
        }

        if (this.containsStudent(student)) {
            throw new StudentAlreadyAddedToClassroomException();
        }

        this.students.add(student);
    }

    public void addPoints(Student student, double points) {

        Optional<Student> studentOnList = this.students.stream()
                .filter(el -> 0 == el.compareTo(student))
                .findFirst();

        if (studentOnList.isEmpty()) {
            return;
        }

        student.addPoints(points);
    }

    public void removePoints(Student student, double points) throws IllegalArgumentException {
        if (points <= 0) {
            throw new IllegalArgumentException("Punkty muszą być liczbą dodatnią");
        }

        this.addPoints(student, points);
    }

    public Optional<Student> getStudent(Student student) {

        Iterator<Student> iterator = this.students.iterator();

        while (iterator.hasNext()) {
            Student listStudent = iterator.next();

            if (0 == listStudent.compareTo(student) /*&& 0 == student.getPoints()*/) {
                iterator.remove();
                return Optional.of(student);
            }
        }

        return Optional.empty();

    }

    public void changeCondition(Student student, StudentCondition state) {

        Optional<Student> optionalStudent = this.students.stream()
                .filter(el -> 0 == el.compareTo(student))
                .findFirst();

        if (optionalStudent.isEmpty()) {
            return;
        }

        student.setStatus(state);
    }

    public Optional<Student> search(String lastname) {
        return this.students.stream()
                .filter(el -> lastname.equals(el.getLastname()))
                .findFirst();
    }

    public ArrayList<Student> searchPartial(String prefix) {
        return (ArrayList<Student>) this.students.stream()
                .filter(el -> el.getLastname().startsWith(prefix))
                .collect(Collectors.toList());
    }

    public int countByCondition(StudentCondition state) {
        ArrayList<Student> matchedStudents = (ArrayList<Student>) this.students.stream()
                .filter(el -> state == el.getStatus())
                .collect(Collectors.toList());
        return matchedStudents.size();
    }

    public ArrayList<Student> sortByName() {
        return (ArrayList<Student>) this.students.stream()
                .sorted(Comparator.comparing(Student::getLastname))
                .collect(Collectors.toList());
    }

    public ArrayList<Student> sortByPoints() {
        return (ArrayList<Student>) this.students.stream()
                .sorted((new StudentPointsComparator()).reversed())
                .collect(Collectors.toList());
    }

    public int max() {
        return this.getMaxStudentsCount();
    }

    public void summary() {
        this.output.println("----- Klasa: " + this.name + ": -----");
        String fillInfo = "Max liczba uczniow: " + this.getMaxStudentsCount() + ", wypełnienie: " + this.getPercentageFilling();
        this.output.println(fillInfo);
        this.students.forEach(Student::print);
        this.output.println("-------------------------------------");
    }

    public boolean isEmpty() {
        return this.students.isEmpty();
    }

    public double getPercentageFilling() {
        return (double) this.getStudentsCount() / (double) this.max() * 100.0;
    }

    private boolean isFull() {
        return this.students.size() >= this.getMaxStudentsCount();
    }

    private boolean hasPlace() {
        return !this.isFull();
    }

    private boolean containsStudent(Student suspicious) {
        return this.students.stream()
                .anyMatch(el -> 0 == el.compareTo(suspicious));
    }

    public int getStudentsCount() {
        return this.students.size();
    }
    public int getMaxStudentsCount() {return this.maxStudentsCount;}

    public String getName() {
        return  this.name;
    }

    public List<Student> getStudents() {
        return this.students;
    }
}
