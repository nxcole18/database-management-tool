package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingScreen extends JFrame implements ActionListener {
    private static final int WIDTH = 580;
    private static final int HEIGHT = 770;
    private JButton insertButton, deleteButton, updateButton;

    public LandingScreen() {
        super("Landing Screen");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setBackground(Color.white);

        // set up panel for landing screen
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // set up panel contents
        JLabel pageTitle = new JLabel("Tournament Database");
        // set up buttons
        initButtons();

        // add everything to the panel
        content.add(pageTitle);
        content.add(insertButton);
        content.add(insertButton);
        content.add(deleteButton);
        content.add(updateButton);

        // add the panel
        add(content);
        setVisible(true);

    }

    private void initButtons() {
        insertButton = new JButton("Insert");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");
        initButtonListeners();
    }

    private void initButtonListeners() {
        ActionListener insertButtonListener = e -> new InsertTable();
        insertButton.addActionListener(insertButtonListener);

        //ActionListener deleteButtonListener = e -> new OrderGUI();
        //enterButton.addActionListener(enterButtonListener);

        ActionListener updateButtonListener = e -> new UpdateTable();
        updateButton.addActionListener(updateButtonListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
