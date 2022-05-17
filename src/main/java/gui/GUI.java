package gui;

import model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GUI implements ActionListener, ListSelectionListener {

    private final GridBagLayout btnGrid;
    private final GridBagConstraints btnGridSettings;
    
    private final JFrame frame;
    private final JPanel mainPanel;
    private final JPanel bottomPanel;

    private final JButton addClassBtn;
    private final JButton editClassBtn;
    private final JButton deleteClassBtn;
    private final JButton addStudentBtn;
    private final JButton editStudentBtn;
    private final JButton deleteStudentBtn;
    private final JButton sortStudentBtn;
    private final JButton sortStudentByPointsBtn;

    private final ClassroomsTable classroomsTable;
    private final JScrollPane classroomTablePanel;
    private final StudentsTable studentsTable;
    private final JScrollPane studentTablePanel;


    public GUI() {

        btnGrid = new GridBagLayout();
        btnGridSettings = new GridBagConstraints();

        //Creating the Frame
        frame = new JFrame("Dziennik");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);

        //Creating the panel at bottom and adding components
        bottomPanel = new JPanel();
        bottomPanel.setLayout(btnGrid);

        this.addClassBtn = new JButton();
        this.editClassBtn = new JButton();
        this.deleteClassBtn = new JButton();
        this.addStudentBtn = new JButton();
        this.editStudentBtn = new JButton();
        this.deleteStudentBtn = new JButton();
        this.sortStudentBtn = new JButton();
        this.sortStudentByPointsBtn = new JButton();

        this.setBtn(this.addClassBtn, "Dodaj klasę", Actions.ADD_CLASSROOM, 0 ,0)
                .setBtn(this.editClassBtn,"Edytuj klasę", Actions.EDIT_CLASSROOM, 1,0)
                .setBtn(this.deleteClassBtn,"Usuń klasę", Actions.DELETE_CLASSROOM, 2,0)
                .setBtn(this.addStudentBtn,"Dodaj studenta", Actions.ADD_STUDENT, 3,0)
                .setBtn(this.editStudentBtn,"Edytuj studenta", Actions.EDIT_STUDENT, 4,0)
                .setBtn(this.deleteStudentBtn,"Usuń studenta", Actions.DELETE_STUDENT, 5,0)
                .setBtn(this.sortStudentBtn,"Sortuj studentów alfabetycznie", Actions.STUDENT_SORT_ALPHABET, 0,1)
                .setBtn(this.sortStudentByPointsBtn,"Sortuj studentów po punktach", Actions.STUDENT_SORT_POINTS, 4,1);

        // working panel
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        ClassContainer classContainer = new ClassContainer();
        this.classroomsTable = new ClassroomsTable(classContainer, this);
        this.classroomTablePanel = new JScrollPane(this.classroomsTable.getTable());

        ArrayList<Student> students = new ArrayList<>();
        this.studentsTable = new StudentsTable(students);
        this.studentTablePanel = new JScrollPane(this.studentsTable.getTable());

        this.mainPanel.add(this.classroomTablePanel);
        this.mainPanel.add(this.studentTablePanel);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, this.bottomPanel);
        frame.getContentPane().add(BorderLayout.CENTER, this.mainPanel);
        frame.setVisible(true);
    }

    private GUI setBtn(JButton btn, String text, String action, int posX, int posY) {
        btn.setText(text);
        btn.setActionCommand(action);
        btn.addActionListener(this);
        this.setBtnGridSetting(posX,posY);
        this.bottomPanel.add(btn, this.btnGridSettings);
        return this;
    }

    private void setBtnGridSetting(int x, int y) {
        this.btnGridSettings.fill = GridBagConstraints.HORIZONTAL;
        this.btnGridSettings.gridx = x;
        this.btnGridSettings.gridy = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case Actions.ADD_CLASSROOM -> this.addClassroom();
            case Actions.EDIT_CLASSROOM -> this.editClassroom();
            case Actions.DELETE_CLASSROOM -> this.classroomsTable.deleteClassroom();
            case Actions.ADD_STUDENT -> this.addStudent();
            case Actions.EDIT_STUDENT -> this.editStudent();
            case Actions.DELETE_STUDENT -> this.deleteStudent();
            case Actions.STUDENT_SORT_ALPHABET -> this.sortStudentsAlphabet();
            case Actions.STUDENT_SORT_POINTS -> this.sortStudentsByPoints();
        }


    }

    private void showStudents() {
        List<Student> studentList = this.classroomsTable.getClassroomStudents();
        this.studentsTable.setStudents(studentList);
    }

    private void sortStudentsByPoints() {
        List<Student> students = this.classroomsTable.sortStudentsByPoints();
        this.studentsTable.setStudents(students);
    }

    private void sortStudentsAlphabet() {
        List<Student> students = this.classroomsTable.sortStudentsAlphabet();
        this.studentsTable.setStudents(students);
    }

    private void deleteStudent() {
        String studentLastname = this.studentsTable.getSelectedStudent();
        if (studentLastname == null) {
            JOptionPane.showMessageDialog(null, "Zaznacz ucznia.");
            return;
        }

        Classroom classroom = this.classroomsTable.getSelectedClassroom();
        if (classroom == null) {
            JOptionPane.showMessageDialog(null, "Zaznacz klasę.");
            return;
        }

        Optional<Student> optionalStudent = classroom.search(studentLastname);
        if (optionalStudent.isEmpty()) {
            JOptionPane.showMessageDialog(null, "W klasie nie ma ucznia.");
            return;
        }

        Student student = optionalStudent.get();
        classroom.getStudent(student);
        this.classroomsTable.fireTableDataChanged();
        this.studentsTable.fireTableDataChanged();

    }

    private void editStudent() {
        double points;
        Classroom classroom = this.classroomsTable.getSelectedClassroom();
        if (classroom == null) {
            JOptionPane.showMessageDialog(null, "Zaznacz klasę.");
            return;
        }

        String studentLastname = this.studentsTable.getSelectedStudent();
        Optional<Student> optionalStudent = classroom.search(studentLastname);
        if (optionalStudent.isEmpty()){
            JOptionPane.showMessageDialog(null, "Zaznacz ucznia.");
            return;
        }
        Student student = optionalStudent.get();

        String sName = (String) JOptionPane.showInputDialog(this.frame, "Imię:", null, JOptionPane.INFORMATION_MESSAGE, null, null, student.getName());
        String sLastname = (String) JOptionPane.showInputDialog(this.frame, "Nazwisko:",null, JOptionPane.INFORMATION_MESSAGE, null, null, student.getLastname());
        int birthYear;
        try {
            birthYear = Integer.parseInt((String) JOptionPane.showInputDialog(this.frame, "Rok urodzenia:",null, JOptionPane.INFORMATION_MESSAGE, null, null, student.getBirthYear()));
            //@todo walidacja mnijesze niz obecny rok
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Niepoprawny rok urodzenia");
            return;
        }
        try {
            points = Double.parseDouble((String) JOptionPane.showInputDialog(this.frame, "Punkty:",null, JOptionPane.INFORMATION_MESSAGE, null, null, student.getPoints()));
            //@todo validacja nieujemne
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Niepoprawna ilość punktów");
            return;
        }

        StudentCondition[] choices = StudentCondition.values();
        StudentCondition sStatus = StudentCondition.valueOf(
                JOptionPane.showInputDialog(
                        this.frame,
                        "Wybierz status",
                        null,
                        JOptionPane.QUESTION_MESSAGE,
                        null, choices, student.getStatus()
                ).toString()
        );
        student.setName(sName).setLastname(sLastname).setBirthYear(birthYear).setPoints(points).setStatus(sStatus);
        this.classroomsTable.fireTableDataChanged();
        this.studentsTable.fireTableDataChanged();
    }

    private void editClassroom() {
        try {
            int volume = Integer.parseInt(JOptionPane.showInputDialog(this.frame, "Pojemność:"));
            this.classroomsTable.editClassroom(volume);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Niepoprawna pojemność");
        }
    }

    private void addClassroom() {
        String name = JOptionPane.showInputDialog(this.frame, "Nazwa");
        try {
            int volume = Integer.parseInt(JOptionPane.showInputDialog(this.frame, "Pojemność:"));
            this.classroomsTable.addClassroom(name, volume);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Niepoprawna pojemność");
        }
    }

    private void addStudent() {
        Classroom classroom = this.classroomsTable.getSelectedClassroom();
        if (classroom == null) {
            JOptionPane.showMessageDialog(null, "Zaznacz klasę.");
            return;
        }
        String sName = JOptionPane.showInputDialog(this.frame, "Imię:");
        String sLastname = JOptionPane.showInputDialog(this.frame, "Nazwisko:");
        int birthYear;
        double points;
        try {
            birthYear = Integer.parseInt(JOptionPane.showInputDialog(this.frame, "Rok urodzenia:"));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Niepoprawny rok urodzenia");
            return;
        }
        try {
            points = Double.parseDouble(JOptionPane.showInputDialog(this.frame, "Punkty:"));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Niepoprawna ilość punktów");
            return;
        }
        StudentCondition[] choices = StudentCondition.values();
        StudentCondition sCondition = StudentCondition.valueOf(
                JOptionPane.showInputDialog(
                        this.frame,
                        "Wybierz status",
                        null,
                        JOptionPane.QUESTION_MESSAGE,
                        null, choices, choices[1]
                ).toString()
        );
        Student student = new Student(sName, sLastname, birthYear, points, sCondition);
        try {
            classroom.addStudent(student);
        } catch (FulfilledClassroomException ex) {
            JOptionPane.showMessageDialog(null, "Klasa jest już pełna");
            return;
        } catch (StudentAlreadyAddedToClassroomException ex){
            JOptionPane.showMessageDialog(null, "Student został już dodany do klasy");
            return;
        }
        this.classroomsTable.fireTableDataChanged();
        this.studentsTable.setStudents(classroom.getStudents());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.showStudents();
    }
}
