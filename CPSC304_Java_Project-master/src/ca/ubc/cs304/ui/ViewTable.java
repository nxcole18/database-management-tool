package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.TournieDBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ViewTable extends JFrame {
    TournieDBHandler database;

    public ViewTable(TournieDBHandler db, String source, String table, ArrayList<String> columns) {
        super("View Table");
        setSize(500, 500);
        database = db;

        try {
            ArrayList<ArrayList<String>> results = database.project(table, columns);
            DefaultTableModel model = new DefaultTableModel();

            for (String c: columns) {
                model.addColumn(c);
            }

            for (ArrayList<String> r: results) {
                model.addRow(r.toArray());
            }

            JTable resultsTable = new JTable(model);

            add(new JScrollPane(resultsTable)); // ???
            add(resultsTable);

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null,
                    "Error projecting table.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }

        setVisible(true);
    }

}
