package model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContainer {

    private final Map<String, Classroom> classrooms;
    private final PrintStream output;

    public ClassContainer() {
        this.classrooms = new HashMap<>();
        this.output = System.out;
    }

    public void addClassroom(String name, int maxStudentsCount) {
        this.classrooms.put(name, new Classroom(name, maxStudentsCount));
    }

    public void editClassroom(String name, int volume) {
        Classroom classroom = this.getClassroom(name);
        classroom.setVolume(volume);
    }

    public ArrayList<Classroom> getAll() {
        return new ArrayList<Classroom>(this.classrooms.values());
    }

    public void removeClassroom(String name) {
        this.classrooms.remove(name);
    }

    public Classroom getClassroom(String name) {
        return this.classrooms.get(name);
    }

    public void summary() {
        this.output.println("----- Pojemnik na klasy: -----");
        this.classrooms.forEach((key, classroom) -> this.output.println("Klasa: " + key + ", wypełnienie: " + classroom.getPercentageFilling() + "%"));
        this.output.println("------------------------------");
    }

    public List<Classroom> findEmpty() {
        List<Classroom> emptyClassrooms = new ArrayList<>();
        this.classrooms.forEach((k, c) -> {
            if (c.isEmpty()) {
                emptyClassrooms.add(c);
            }
        });
        return emptyClassrooms;
    }

}
