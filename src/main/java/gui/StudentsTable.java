package gui;

import model.ClassContainer;
import model.Student;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsTable {

    private final JTable table;
    private final StudentsTableModel model;
    private List<Student> students;


    public StudentsTable(List<Student> students) {
        this.students = students;
        this.model = new StudentsTableModel(this.students);

        this.table = new JTable(this.model);
        this.table.setBounds(30, 40, 200, 300);
    }

    public JTable getTable() {
        return this.table;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        this.fireTableDataChanged();
    }
    
//    public void addStudent(String name, int volume) {
//        this.classContainer.addClassroom(name, volume);
//        this.fireTableDataChanged();
//    }
//
//    public void editClassroom(int volume) {
//        int selectedRow = this.table.getSelectedRow();
//        if (-1 < selectedRow){
//            String classroomName = (String)this.table.getValueAt(selectedRow, ClassroomsTableModel.COLUMN_NAME);
//            this.classContainer.editClassroom(classroomName, volume);
//            this.fireTableDataChanged();
//        }
//    }
//
//    public void deleteClassroom() {
//        int selectedRow = this.table.getSelectedRow();
//        if (-1 < selectedRow){
//            String classroomName = (String)this.table.getValueAt(selectedRow, ClassroomsTableModel.COLUMN_NAME);
//            this.classContainer.removeClassroom(classroomName);
//            this.fireTableDataChanged();
//        }
//    }

    public void fireTableDataChanged() {
        this.model.setStudents(this.students);
        this.model.fireTableDataChanged();
    }

    public String getSelectedStudent() {
        int selectedRow = this.table.getSelectedRow();
        if (-1 < selectedRow) {
            return (String)this.table.getValueAt(selectedRow, StudentsTableModel.COLUMN_LASTNAME);
        }
        return null;
    }
}
