package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Objects;

import ca.ubc.cs304.database.TournieDBHandler;

public class Projection2 extends JFrame implements ActionListener {
    private JButton submit;
    String tableChosen;
    TournieDBHandler database;
    ArrayList<String> columnsChosen = new ArrayList<>();

    ArrayList<JComboBox> cSelections = new ArrayList<>();


    public Projection2(TournieDBHandler db, String table) {
        super("");
        setSize(370, 500);
        setResizable(true);
        setBackground(Color.white);

        database = db;
        tableChosen = table;

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

        submit = new JButton("Display");
        submit.setFont(new Font("Sans serif", Font.PLAIN, 13));
        submit.setBorderPainted(false);
        submit.setForeground(new Color(219, 229, 237));
        submit.setBackground(new Color(56, 133, 193));
        submit.addActionListener(this);

        JLabel ws = new JLabel("  ");
        ws.setForeground(Color.white);

        try {
            ArrayList<String> columns = database.getTableColumns(tableChosen);
            for (String c: columns) {
                JLabel cLabel = new JLabel("    " + c);
                cLabel.setFont(new Font("Sans serif", Font.PLAIN, 13));

                String[] options = {c, "Exclude "+ c};
                JComboBox cSelection = new JComboBox(options);

                cSelections.add(cSelection);

                content.add(cLabel);
                content.add(cSelection);
            }
            content.add(ws);
            content.add(submit);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null,
                    "Table selected does not exist.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        for (JComboBox cSelection: cSelections) {
            cSelection.addActionListener(this);
        }
    }

    private void initHeader(JPanel header) {
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel pageTitle = new JLabel(" Select attributes");
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
            for (JComboBox cSelection: cSelections) {
                String selectedItem = (String) cSelection.getSelectedItem();
                assert selectedItem != null;
                if (!selectedItem.contains("Exclude ")) {
                    columnsChosen.add(selectedItem);
                }
            }
            System.out.println(columnsChosen);
            new ViewTable(database, "PROJECTION", tableChosen, columnsChosen);
        }
    }

}
