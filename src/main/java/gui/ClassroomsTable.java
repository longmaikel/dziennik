package gui;

import model.ClassContainer;
import model.Classroom;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassroomsTable {
    private final JTable table;
    private final ClassroomsTableModel model;
    private final ClassContainer classContainer;


    public ClassroomsTable() {
        this.classContainer = new ClassContainer();
        this.model = new ClassroomsTableModel(classContainer.getAll());

        this.table = new JTable(this.model);
        this.table.setBounds(30, 40, 200, 300);
    }

    public JTable getTable() {
        return this.table;
    }
    
    public void addClassroom(String name, int volume) {
        this.classContainer.addClassroom(name, volume);
    }

}
