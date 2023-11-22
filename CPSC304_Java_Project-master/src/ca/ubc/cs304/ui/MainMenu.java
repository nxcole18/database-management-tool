package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    private static final int WIDTH = 580;
    private static final int HEIGHT = 400;
    private static final String IMG_FILE = "project_b7i8b_g3i0k_v7o6b\\CPSC304_Java_Project-master\\src\\tourneyCup.png";
    private JButton insertTourneyButton, insertMatchButton, insertTeamButton, insertTeamMemButton, deleteButton, updateButton, viewButton;

    public MainMenu() {
        super("Menu");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setBackground(Color.white);

        // set up panel for landing screen
        // inspiration for tabs https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/TabDemoProject/src/layout/TabDemo.java
        UIManager.put("TabbedPane.selected", new Color(214, 223, 229));
        JTabbedPane tabsPane = new JTabbedPane();
        JPanel tab1 = new JPanel();
        JPanel tab2 = new JPanel();
        JPanel tab3 = new JPanel();

        // add tabs to the pane
        tabsPane.addTab("Add Data", tab1);
        tabsPane.addTab("Modify Data", tab2);
        tabsPane.addTab("View Data", tab3);

        decorateTabPanes(tabsPane);

        // set up header
        JPanel header = new JPanel();
        initHeader(header);

        // set up buttons
        initButtons();

        // add everything to the panel
        tab1.add(insertTourneyButton);
        tab1.add(insertMatchButton);
        tab1.add(insertTeamButton);
        tab1.add(insertTeamMemButton);
        tab2.add(deleteButton);
        tab2.add(updateButton);
        tab3.add(viewButton);

        // add image
        JLabel img = new JLabel(new ImageIcon(IMG_FILE)); /// !!! not working

        // add the panel
        add(header, BorderLayout.PAGE_START);
        add(tabsPane, BorderLayout.CENTER);
        add(img, BorderLayout.PAGE_END);
        setVisible(true);

    }

    private void initHeader(JPanel header) {
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel pageTitle = new JLabel("Tournament Database");
        pageTitle.setFont(new Font("Sans serif", Font.BOLD, 19));

        JLabel pageSubHeading = new JLabel("— Main Menu");
        pageSubHeading.setFont(new Font("Sans serif", Font.BOLD, 13));
        pageSubHeading.setForeground(new Color(56, 133, 193));

        JLabel whitespace1 = new JLabel("1");
        whitespace1.setFont(new Font("Sans serif", Font.BOLD, 8));
        whitespace1.setForeground(Color.white);

        JLabel whitespace2 = new JLabel("1");
        whitespace2.setForeground(Color.white);
        whitespace2.setFont(new Font("Sans serif", Font.BOLD, 8));

        header.add(whitespace1);
        header.add(pageTitle);
        header.add(pageSubHeading);
        header.add(whitespace2);
    }

    private void decorateTabPanes(JTabbedPane tabsPane) {
        tabsPane.setBackground(Color.white);
        tabsPane.setFont(new Font("Sans serif", Font.BOLD, 13));
        tabsPane.setBackgroundAt(0,new Color(214, 223, 229));
        tabsPane.setBackgroundAt(1,new Color(214, 223, 229));
        tabsPane.setBackgroundAt(2,new Color(214, 223, 229));
        tabsPane.setForeground(new Color(73, 81, 88));
    }

    private void initButtons() {
        insertTourneyButton = new JButton("New Tournament");
        insertMatchButton = new JButton("New Match");
        insertTeamButton = new JButton("New Team");
        insertTeamMemButton = new JButton("New Team Member");
        deleteButton = new JButton("Delete Team Member");
        updateButton = new JButton("Update Match");
        viewButton = new JButton("Match");

        JButton[] buttonArr = {insertTourneyButton, insertMatchButton, insertTeamButton, insertTeamMemButton, deleteButton, updateButton, viewButton};

        for (JButton b:buttonArr){
            decorateButton(b);
        }

        initButtonListeners();
    }

    private void decorateButton(JButton b) {
        b.setFont(new Font("Sans serif", Font.PLAIN, 13));
        b.setBorderPainted(false);
        b.setForeground(new Color(219, 229, 237));
        b.setBackground(new Color(56, 133, 193));
    }

    private void initButtonListeners() {
        //ActionListener insertButtonListener = e -> new InsertTable();
        //insertButton.addActionListener(insertButtonListener);

        //ActionListener deleteButtonListener = e -> new OrderGUI();
        //enterButton.addActionListener(enterButtonListener);

        ActionListener updateButtonListener = e -> new UpdateTable();
        updateButton.addActionListener(updateButtonListener);

        ActionListener viewButtonListener = e -> new ViewTable();
        viewButton.addActionListener(viewButtonListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
