package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.TournieDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class JoinPlayerTeam extends JFrame implements ActionListener {
    private JTextField playerF, firstF, lastF, countryF, joinF, rankingF, teamF, teamNameF, country2F, organizationF;
    private JButton submit;
    private JComboBox cond;

    TournieDBHandler database;

    public JoinPlayerTeam(TournieDBHandler db) {
        super("");
        database = db;

        setSize(370, 780);
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

        JLabel condition = new JLabel("    Condition");
        JLabel player = new JLabel("    Player ID");
        JLabel first = new JLabel("    First name");
        JLabel last = new JLabel("    Last name");
        JLabel country = new JLabel("    Country");
        JLabel join = new JLabel("    Join date (YYYY-MM-DD)");
        JLabel ranking = new JLabel("    Ranking");
        JLabel team = new JLabel("    Team ID");
        JLabel teamName = new JLabel("    Team name");
        JLabel organization = new JLabel("    Team organization");
        JLabel country2 = new JLabel("    Team country");
        JLabel ws = new JLabel("  ");
        ws.setForeground(Color.white);

        submit = new JButton("Join");
        submit.setFont(new Font("Sans serif", Font.PLAIN, 13));
        submit.setBorderPainted(false);
        submit.setForeground(new Color(219, 229, 237));
        submit.setBackground(new Color(56, 133, 193));
        submit.addActionListener(this);

        String[] choices = {"AND"};
        cond = new JComboBox(choices);
        playerF = new JTextField();
        firstF = new JTextField();
        lastF = new JTextField();
        countryF = new JTextField();
        joinF = new JTextField();
        rankingF = new JTextField();
        teamF = new JTextField();
        teamNameF = new JTextField();
        organizationF = new JTextField();
        country2F = new JTextField();

        //guard fields

        JLabel[] labels = {condition, player, first, last, country, join, ranking, team, teamName, organization, country2};
        for (JLabel l : labels) {
            l.setFont(new Font("Sans serif", Font.PLAIN, 13));
        }

        content.add(condition);
        content.add(cond);
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
        content.add(teamName);
        content.add(teamNameF);
        content.add(organization);
        content.add(organizationF);
        content.add(country2);
        content.add(country2F);
        content.add(ws);
        content.add(submit);
    }

    private void initHeader(JPanel header) {
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel pageTitle = new JLabel(" Join Team and Player Table");
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
            int playerS = Integer.parseInt(playerF.getText());
            String firstS = firstF.getText();
            String lastS = lastF.getText();
            String countryS = countryF.getText();
            LocalDate joinS = LocalDate.parse(joinF.getText()); // not sure if this is allowed
            int rankingS = Integer.parseInt(rankingF.getText());
            int teamS = Integer.parseInt(teamF.getText());
        }
    }
}
