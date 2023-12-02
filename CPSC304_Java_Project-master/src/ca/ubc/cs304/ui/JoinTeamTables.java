package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.TournieDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class JoinTeamTables extends JFrame implements ActionListener {
    private JTextField countryF;
    private JButton submit;

    TournieDBHandler database;

    public JoinTeamTables(TournieDBHandler db) {
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

        JLabel country = new JLabel("    Team country");
        JLabel ws = new JLabel("  ");
        ws.setForeground(Color.white);

        submit = new JButton("Join");
        submit.setFont(new Font("Sans serif", Font.PLAIN, 13));
        submit.setBorderPainted(false);
        submit.setForeground(new Color(219, 229, 237));
        submit.setBackground(new Color(56, 133, 193));
        submit.addActionListener(this);

        countryF = new JTextField();

        JLabel[] labels = {country};
        for (JLabel l : labels) {
            l.setFont(new Font("Sans serif", Font.PLAIN, 13));
        }

        content.add(country);
        content.add(countryF);
        content.add(ws);
        content.add(submit);
    }

    private void initHeader(JPanel header) {
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel pageTitle = new JLabel(" Join Team Tables by Organization");
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
            try {
                String sqlInjection = "\\b(SELECT|FROM|WHERE|HAVING|GROUP|INSERT|UPDATE|DELETE|AND|OR|DROP|ALTER|CREATE|UNION|JOIN)\\b";
                if (countryF.getText() != null && !(countryF.getText().isEmpty()) && !(countryF.getText().matches("[0-9]+")) && !(countryF.getText().matches(sqlInjection))) {
                    String countryS = countryF.getText();
                    ArrayList<ArrayList<String>> results = database.join(countryS);
                    new ViewTable2(database, results);

                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Please ensure you have an input and it is neither a number or SQL code.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    this.dispose();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Please ensure you have valid inputs.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
                this.dispose();
            }
        }
    }
}
