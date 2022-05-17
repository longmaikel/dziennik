package gui;

import model.ClassContainer;
import model.Classroom;
import model.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClassroomsTable {
    private final JTable table;
    private final ClassroomsTableModel model;
    private final ClassContainer classContainer;
    private ListSelectionListener listener;


    public ClassroomsTable(ClassContainer classContainer, ListSelectionListener listener) {
        this.listener = listener;
        this.classContainer = classContainer;
        this.model = new ClassroomsTableModel(this.classContainer.getAll());

        this.table = new JTable(this.model);
        this.table.setBounds(30, 40, 200, 300);
        this.table.getSelectionModel().addListSelectionListener(this.listener);
    }

    public JTable getTable() {
        return this.table;
    }
    
    public void addClassroom(String name, int volume) {
        this.classContainer.addClassroom(name, volume);
        this.fireTableDataChanged();
    }

    public void editClassroom(int volume) {
        int selectedRow = this.table.getSelectedRow();
        if (-1 < selectedRow){
            String classroomName = (String)this.table.getValueAt(selectedRow, ClassroomsTableModel.COLUMN_NAME);
            this.classContainer.editClassroom(classroomName, volume);
            this.fireTableDataChanged();
        }
    }
    
    public void deleteClassroom() {
        int selectedRow = this.table.getSelectedRow();
        if (-1 < selectedRow){
            String classroomName = (String)this.table.getValueAt(selectedRow, ClassroomsTableModel.COLUMN_NAME);
            this.classContainer.removeClassroom(classroomName);
            this.fireTableDataChanged();
        }
    }

    public void fireTableDataChanged() {
        this.model.setClassrooms(this.classContainer.getAll());
        this.model.fireTableDataChanged();
    }

    public List<Student> getClassroomStudents() {
        int selectedRow = this.table.getSelectedRow();
        if (-1 < selectedRow) {
            String classroomName = (String)this.table.getValueAt(selectedRow, ClassroomsTableModel.COLUMN_NAME);
            Classroom classroom = this.classContainer.getClassroom(classroomName);
            return classroom.getStudents();
        }
        return null;
    }

    public Classroom getSelectedClassroom() {
        int selectedRow = this.table.getSelectedRow();
        if (-1 < selectedRow) {
            String classroomName = (String)this.table.getValueAt(selectedRow, ClassroomsTableModel.COLUMN_NAME);
            return this.classContainer.getClassroom(classroomName);

        }
        return null;
    }

    public List<Student> sortStudentsByPoints() {
        int selectedRow = this.table.getSelectedRow();
        if (-1 < selectedRow) {
            String classroomName = (String)this.table.getValueAt(selectedRow, ClassroomsTableModel.COLUMN_NAME);
            Classroom classroom = this.classContainer.getClassroom(classroomName);
            return classroom.sortByPoints();
        }

        return null;
    }

    public List<Student> sortStudentsAlphabet() {
        int selectedRow = this.table.getSelectedRow();
        if (-1 < selectedRow) {
            String classroomName = (String)this.table.getValueAt(selectedRow, ClassroomsTableModel.COLUMN_NAME);
            Classroom classroom = this.classContainer.getClassroom(classroomName);
            return classroom.sortByName();
        }
        return null;
    }
}
