package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePlayer extends JFrame implements ActionListener {
    private JTextField playerF, rankingF;
    private JButton submit;

    public UpdatePlayer() {
        super("Update Player");
        setSize(370, 245);
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
        JLabel ranking = new JLabel("    Ranking");
        JLabel ws = new JLabel("  ");
        ws.setForeground(Color.white);

        submit = new JButton("Add");
        submit.setFont(new Font("Sans serif", Font.PLAIN, 13));
        submit.setBorderPainted(false);
        submit.setForeground(new Color(219, 229, 237));
        submit.setBackground(new Color(56, 133, 193));

        playerF = new JTextField();
        rankingF = new JTextField();

        //guard fields

        JLabel[] labels = {player, ranking};
        for (JLabel l : labels) {
            l.setFont(new Font("Sans serif", Font.PLAIN, 13));
        }

        content.add(player);
        content.add(playerF);
        content.add(ranking);
        content.add(rankingF);
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
            int playerS = Integer.parseInt(playerF.getText());
            int rankingS = Integer.parseInt(rankingF.getText());
        }
    }
}
