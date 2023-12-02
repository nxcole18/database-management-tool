package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.TournieDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Aggregation extends JFrame implements ActionListener {
    private JComboBox aggQueries;
    private JButton submit;

    TournieDBHandler database;

    public Aggregation(TournieDBHandler db) {
        super("");
        database = db;

        setSize(370, 185);
        setResizable(false);
        setBackground(Color.white);


        JPanel header = new JPanel();
        initHeader(header);

        JPanel content = new JPanel();
        initContent(content);

        add(header, BorderLayout.PAGE_START);
        add(content, BorderLayout.CENTER);

        setVisible(true);
    }

    private void initContent(JPanel content) {
        content.setLayout(new GridLayout(0, 2, 0, 2));

        JLabel team = new JLabel("    Options");
        JLabel ws = new JLabel("  ");
        ws.setForeground(Color.white);

        submit = new JButton("Display");
        submit.setFont(new Font("Sans serif", Font.PLAIN, 13));
        submit.setBorderPainted(false);
        submit.setForeground(new Color(219, 229, 237));
        submit.setBackground(new Color(56, 133, 193));
        submit.addActionListener(this);

        String[] choices = {"GROUP BY", "HAVING", "NESTED GROUP BY"};
        aggQueries = new JComboBox(choices);
        aggQueries.addActionListener(this);

        JLabel[] labels = {team};
        for (JLabel l : labels) {
            l.setFont(new Font("Sans serif", Font.PLAIN, 13));
        }

        content.add(team);
        content.add(aggQueries);
        content.add(ws);
        content.add(submit);
    }

    private void initHeader(JPanel header) {
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel pageTitle = new JLabel(" Run Aggregation Queries");
        pageTitle.setFont(new Font("Sans serif", Font.BOLD, 19));

        JLabel ws1 = new JLabel("  ");
        ws1.setFont(new Font("Sans serif", Font.BOLD, 8));
        ws1.setForeground(Color.white);

        JLabel ws2 = new JLabel("  ");
        ws2.setForeground(Color.white);
        ws2.setFont(new Font("Sans serif", Font.BOLD, 8));

        header.add(ws1);
        header.add(pageTitle);
        header.add(ws2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submit) {
            String selectedQuery = (String) aggQueries.getSelectedItem();
            if (Objects.equals(selectedQuery, "GROUP BY")) {
                try {
                    ArrayList<ArrayList<String>> results = database.aggGroupBy();
                    new ViewTable2(database, results);
                    this.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error producing query.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (Objects.equals(selectedQuery, "HAVING")) {
                try {
                    ArrayList<ArrayList<String>> results = database.aggGroupByHaving();
                    new ViewTable2(database, results);
                    this.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error producing query.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
                // new ViewTable(database, table, columns);
            } else if (Objects.equals(selectedQuery, "NESTED GROUP BY")) {
                try {
                    ArrayList<ArrayList<String>> results = database.nested();
                    new ViewTable2(database, results);
                    this.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error producing query.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
