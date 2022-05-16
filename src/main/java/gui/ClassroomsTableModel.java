package gui;

import model.Classroom;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Map;

public class ClassroomsTableModel extends AbstractTableModel {
    public static final int COLUMN_NAME = 0;
    public static final int COLUMN_MAX_STUDENT_COUNT = 1;
    public static final int COLUMN_STUDENTS_COUNT = 2;
    public static final int COLUMN_PERCENTAGE_FILLING = 3;
    private final String[] columns = {"Nazwa", "Maksymalna liczba studentów", "obecna ilość studentów", "% wypełnienia"};

    private ArrayList<Classroom> classrooms;

    public ClassroomsTableModel(ArrayList<Classroom> classrooms) {
        super();
        this.setClassrooms(classrooms);
    }

    public void setClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    @Override
    public int getRowCount() {
        return this.classrooms.size();
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Classroom classroom = this.classrooms.get(rowIndex);
        switch (columnIndex) {
            case ClassroomsTableModel.COLUMN_NAME:
                return classroom.getName();
            case ClassroomsTableModel.COLUMN_MAX_STUDENT_COUNT:
                return classroom.getMaxStudentsCount();
            case ClassroomsTableModel.COLUMN_STUDENTS_COUNT:
                return classroom.getStudentsCount();
            case ClassroomsTableModel.COLUMN_PERCENTAGE_FILLING:
                return classroom.getPercentageFilling();
        }
        return new String();
    }

    public String getColumnName(int i) {
        return this.columns[i];
    }
}
