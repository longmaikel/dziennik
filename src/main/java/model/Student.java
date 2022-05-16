package model;

import java.io.PrintStream;

public class Student implements Printable, Comparable<Student> {

    private final String name;
    private final String lastname;
    private final int birthYear;
    private double points;
    private StudentCondition state;
    private final PrintStream output;

    public Student(String name, String lastname, int birthYear, double points, StudentCondition state) {
        this.name = name;
        this.lastname = lastname;
        this.birthYear = birthYear;
        this.points = points;
        this.state = state;
        this.output = System.out;
    }

    public void addPoints(double points) {
        this.points += points;
    }

    public double getPoints() {
        return this.points;
    }

    @Override
    public void print() {
        this.output.println("model.Student: " + this.name + " " + this.lastname + " " + this.birthYear + ", points: " + this.points + ", state: " + this.state);
    }

    @Override
    public int compareTo(Student o) {
        if (o.lastname.equals(this.lastname)) {
            return 0;
        }

        return 1;
    }

    public void setStatus(StudentCondition status) {
        this.state = status;
    }

    public String getLastname() {
        return this.lastname;
    }

    public StudentCondition getStatus() {
        return this.state;
    }
}
