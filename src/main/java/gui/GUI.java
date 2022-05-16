package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {


    private final GridBagLayout btnGrid;
    private final GridBagConstraints btnGridSettings;
    
    private final JFrame frame;
    private final JPanel bottomPanel;
    
    private final JButton addClassBtn;
    private final JButton editClassBtn;
    private final JButton deleteClassBtn;
    private final JButton addStudentBtn;
    private final JButton editStudentBtn;
    private final JButton deleteStudentBtn;
    private final JButton sortStudentBtn;
    private final JButton sortStudentByPointsBtn;
    private final JPanel mainPanel;

    private final ClassroomsTable classroomsTable;
    private final JScrollPane classroomTablePanel;


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

        this.classroomsTable = new ClassroomsTable();
        this.classroomTablePanel = new JScrollPane(this.classroomsTable.getTable());

        ClassroomsTable studentsTable = new ClassroomsTable();
        JScrollPane st =  new JScrollPane(studentsTable.getTable());

        this.mainPanel.add(this.classroomTablePanel);
        this.mainPanel.add(st);

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
            case Actions.ADD_CLASSROOM:
                String name = JOptionPane.showInputDialog(this.frame, "Nazwa");
                try {
                    int volume = Integer.parseInt(JOptionPane.showInputDialog(this.frame, "Pojemność:"));
                    this.classroomsTable.addClassroom(name, volume);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Niepoprawna pojemność");
                }
                break;
        }


    }
}
