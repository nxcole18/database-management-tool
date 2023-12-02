package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.TournieDBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ViewTable2 extends JFrame {
    TournieDBHandler database;

    public ViewTable2(TournieDBHandler db, ArrayList<ArrayList<String>> results) {
        super("View Table");
        setSize(500, 400);
        database = db;

        try {
            ArrayList<String> columns = results.get(0);

            DefaultTableModel model = new DefaultTableModel();

            for (String c: columns) {
                model.addColumn(c);
            }

            for (ArrayList<String> r: results) {
                model.addRow(r.toArray());
            }

            JTable resultsTable = new JTable(model);

            add(new JScrollPane(resultsTable));
            add(resultsTable);

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null,
                    exception.getMessage(),
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            this.dispose();
        }

        setVisible(true);
    }

}
