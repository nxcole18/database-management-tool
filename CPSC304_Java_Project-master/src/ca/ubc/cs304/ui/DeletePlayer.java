package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.TournieDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeletePlayer extends JFrame implements ActionListener {
    private JTextField playerF;
    private JButton submit;
    TournieDBHandler database;

    public DeletePlayer(TournieDBHandler db) {
        super("Delete Player");
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

        JLabel player = new JLabel("    Player ID");
        JLabel ws = new JLabel("  ");
        ws.setForeground(Color.white);

        submit = new JButton("Delete");
        submit.setFont(new Font("Sans serif", Font.PLAIN, 13));
        submit.setBorderPainted(false);
        submit.setForeground(new Color(219, 229, 237));
        submit.setBackground(new Color(56, 133, 193));
        submit.addActionListener(this);

        playerF = new JTextField();

        JLabel[] labels = {player};
        for (JLabel l : labels) {
            l.setFont(new Font("Sans serif", Font.PLAIN, 13));
        }

        content.add(player);
        content.add(playerF);
        content.add(ws);
        content.add(submit);
    }

    private void initHeader(JPanel header) {
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel pageTitle = new JLabel(" Delete Player");
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
                int playerS = Integer.parseInt(playerF.getText());
                database.deletePlayer(playerS);
                JOptionPane.showMessageDialog(null,
                        "Player " + playerS + " was successfully deleted.",
                        "Success",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null,
                        "Please ensure that you have input a number.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error deleting from the database.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }

        }
    }
}
