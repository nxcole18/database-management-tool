package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.TournieDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class Projection extends JFrame implements ActionListener {
    private JComboBox tableSelection;
    private JButton submit;
    private TournieDBHandler database;

    public Projection(TournieDBHandler db) {
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

        submit = new JButton("Show attributes");
        submit.setFont(new Font("Sans serif", Font.PLAIN, 13));
        submit.setBorderPainted(false);
        submit.setForeground(new Color(219, 229, 237));
        submit.setBackground(new Color(56, 133, 193));
        submit.addActionListener(this);

        JLabel tableOptionLabel = new JLabel("    Select table");
        tableOptionLabel.setFont(new Font("Sans serif", Font.PLAIN, 13));
        JLabel ws = new JLabel("  ");
        ws.setForeground(Color.white);

        String[] tableOptions = {"PLAYER", "VENUE", "SPONSOR", "TEAM1", "TEAM2", "TOURNAMENT", "MATCH1", "MATCH2", "COMMENTATOR", "COMMENTATES",
        "BROADCASTER", "BROADCASTS", "COACH", "BUSINESSMEMBER", "NEW TABLE"};
        tableSelection = new JComboBox(tableOptions);
        tableSelection.addActionListener(this);

        content.add(tableOptionLabel);
        content.add(tableSelection);
        content.add(ws);
        content.add(submit);
    }

    private void initHeader(JPanel header) {
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel pageTitle = new JLabel(" Select Table");
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
            String table = (String) tableSelection.getSelectedItem();
            new Projection2(database, table);
            this.dispose();
        }
    }

}
