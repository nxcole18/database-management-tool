package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.TournieDBHandler;
import ca.ubc.cs304.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;


public class UpdatePlayer extends JFrame implements ActionListener {
    private JTextField playerF, firstF, lastF, countryF, joinF, rankingF, teamF;
    private JButton submit;
    TournieDBHandler database;

    public UpdatePlayer(TournieDBHandler db) {
        super("Update Player");
        database = db;

        setSize(370, 500);
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
        JLabel first = new JLabel("    First name");
        JLabel last = new JLabel("    Last name");
        JLabel country = new JLabel("    Country");
        JLabel join = new JLabel("    Join date (YYYY-MM-DD)");
        JLabel ranking = new JLabel("    Ranking");
        JLabel team = new JLabel("    Team ID");
        JLabel ws = new JLabel("  ");
        ws.setForeground(Color.white);

        submit = new JButton("Update");
        submit.setFont(new Font("Sans serif", Font.PLAIN, 13));
        submit.setBorderPainted(false);
        submit.setForeground(new Color(219, 229, 237));
        submit.setBackground(new Color(56, 133, 193));
        submit.addActionListener(this);

        playerF = new JTextField();
        firstF = new JTextField();
        lastF = new JTextField();
        countryF = new JTextField();
        joinF = new JTextField();
        rankingF = new JTextField();
        teamF = new JTextField();

        //guard fields

        JLabel[] labels = {player, first, last, country, join, ranking, team};
        for (JLabel l : labels) {
            l.setFont(new Font("Sans serif", Font.PLAIN, 13));
        }

        content.add(player);
        content.add(playerF);
        content.add(first);
        content.add(firstF);
        content.add(last);
        content.add(lastF);
        content.add(country);
        content.add(countryF);
        content.add(join);
        content.add(joinF);
        content.add(ranking);
        content.add(rankingF);
        content.add(team);
        content.add(teamF);
        content.add(ws);
        content.add(submit);
    }

    private void initHeader(JPanel header) {
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel pageTitle = new JLabel(" Update Player");
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
                if (playerF.getText() == null || playerF.getText().isEmpty()) {
                    throw new NullPointerException();
                }

                int playerS = Integer.parseInt(playerF.getText());

                if (firstF.getText() != null && !(firstF.getText().isEmpty())) {
                    String firstS = firstF.getText();
                    database.updatePlayerFirstName(playerS, firstS);
                }

                if (lastF.getText() != null && !(lastF.getText().isEmpty())) {
                    String lastS = lastF.getText();
                    database.updatePlayerLastName(playerS, lastS);
                }

                if (countryF.getText() != null && !(countryF.getText().isEmpty())) {
                    String countryS = countryF.getText();
                    database.updatePlayerCountry(playerS, countryS);
                }

                if (joinF.getText() != null && !(joinF.getText().isEmpty())) {
                    String joinPre = joinF.getText();
                    Date joinS = Date.valueOf(joinPre);
                    database.updatePlayerJoinDate(playerS, joinS);
                }

                if (rankingF.getText() != null && !(rankingF.getText().isEmpty())) {
                    int rankingS = Integer.parseInt(rankingF.getText());
                    database.updatePlayerRanking(playerS, rankingS);
                }

                if (teamF.getText() != null && !(teamF.getText().isEmpty())) {
                    int teamS = Integer.parseInt(teamF.getText());
                    database.updatePlayerTeamId(playerS, teamS);
                }

                JOptionPane.showMessageDialog(null,
                        "Player was successfully updated.",
                        "Success",
                        JOptionPane.PLAIN_MESSAGE);

                this.dispose();
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(null,
                        exception.getMessage(),
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
                this.dispose();
            } catch (NullPointerException exception) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a Player ID.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
                this.dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null,
                        "Please ensure that you have valid inputs.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
                this.dispose();
            }
        }
    }
}
