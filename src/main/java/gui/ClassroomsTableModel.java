package gui;

import model.Classroom;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Map;

public class ClassroomsTableModel extends AbstractTableModel {

    private final String[] columns = {"Nazwa", "Maksymalna liczba studentów", "obecna ilość studentów", "% wypełnienia"};
    private final ArrayList<Classroom> classrooms;

    public ClassroomsTableModel(ArrayList<Classroom> classrooms) {
        super();
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
            case 0:
                return classroom.getName();
            case 1:
                return classroom.getMaxStudentsCount();
            case 2:
                return classroom.getStudentsCount();
            case 3:
                return classroom.getPercentageFilling();
        }
        return new String();
    }

    public String getColumnName(int i) {
        return this.columns[i];
    }
}
