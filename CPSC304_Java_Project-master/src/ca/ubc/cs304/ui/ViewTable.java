package ca.ubc.cs304.ui;

import ca.ubc.cs304.model.BranchModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ViewTable extends JFrame implements ActionListener {

    public ViewTable() {
        super("View Table");
        setResizable(false);
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        Field[] fields = BranchModel.class.getDeclaredFields();
        for (Field f:fields) {
            ArrayList<String> columnNames = new ArrayList<String>();
            columnNames.add(f.getName());
        }

        table.setModel(model);
        add(table);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
