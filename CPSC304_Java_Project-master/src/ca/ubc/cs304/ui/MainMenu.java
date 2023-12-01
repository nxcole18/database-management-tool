package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.TournieDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    private static final int WIDTH = 580;
    private static final int HEIGHT = 300;
    private static final String IMG_FILE = "src\\tourneyCup.png";
    private JButton insertPlayer, deleteButton, updateButton, viewButton, viewButton1, viewButton2, viewButton3, viewButton4;
    TournieDBHandler database;

    public MainMenu(TournieDBHandler db) {
        super("Menu");
        database = db;

        try {
            //database.login("ora_ethanz01", "a67073387");
            database.login("ora_nxcolel", "a71679872");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null,
                    "Can't login. Please restart the application.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }

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
        tab1.add(insertPlayer);
        tab2.add(deleteButton);
        tab2.add(updateButton);
        tab3.add(viewButton);
        tab3.add(viewButton1);
        tab3.add(viewButton2);
        tab3.add(viewButton3);
        tab3.add(viewButton4);

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

        JLabel pageTitle = new JLabel(" Tournament Database");
        pageTitle.setFont(new Font("Sans serif", Font.BOLD, 19));

        JLabel pageSubHeading = new JLabel("— Main Menu");
        pageSubHeading.setFont(new Font("Sans serif", Font.BOLD, 13));
        pageSubHeading.setForeground(new Color(56, 133, 193));

        JLabel whitespace1 = new JLabel("  ");
        whitespace1.setFont(new Font("Sans serif", Font.BOLD, 8));
        whitespace1.setForeground(Color.white);

        JLabel whitespace2 = new JLabel("  ");
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
        insertPlayer = new JButton("New Player");
        deleteButton = new JButton("Delete Team");
        updateButton = new JButton("Update Player");
        viewButton = new JButton("Selection");
        viewButton1 = new JButton("Projection");
        viewButton2 = new JButton("Join");
        viewButton3 = new JButton("Aggregation");
        viewButton4 = new JButton("Division");

        JButton[] buttonArr = {insertPlayer, deleteButton, updateButton, viewButton, viewButton1, viewButton2, viewButton3, viewButton4};

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
        ActionListener insertButtonListener = e -> new AddPlayer(database);

        insertPlayer.addActionListener(insertButtonListener);

        ActionListener deleteButtonListener = e -> new DeleteTeam(database);
        deleteButton.addActionListener(deleteButtonListener);

        ActionListener updateButtonListener = e -> new UpdatePlayer(database);
        updateButton.addActionListener(updateButtonListener);

        ActionListener viewButtonListener = e -> new Selection(database);
        viewButton.addActionListener(viewButtonListener);

        ActionListener viewButton1Listener = e -> new Projection(database);
        viewButton1.addActionListener(viewButton1Listener);

        ActionListener viewButton2Listener = e -> new JoinTeamTables(database);
        viewButton2.addActionListener(viewButton2Listener);

        ActionListener viewButton3Listener = e -> new Aggregation(database);
        viewButton3.addActionListener(viewButton3Listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
