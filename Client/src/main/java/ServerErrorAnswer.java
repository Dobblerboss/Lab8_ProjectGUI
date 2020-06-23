import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerErrorAnswer extends JFrame {
    JLabel beginLabel;
    JLabel answer;
    JButton tryAgain;

    public ServerErrorAnswer(String answer){
        super(MainClient.stats.getString("Error"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        beginLabel = new JLabel(MainClient.stats.getString("Server answer"));
        beginLabel.setAlignmentX(CENTER_ALIGNMENT);
        beginLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 25));
        this.answer = new JLabel(answer);
        this.answer.setAlignmentX(CENTER_ALIGNMENT);
        tryAgain = new JButton(MainClient.stats.getString("Enter again"));
        tryAgain.setAlignmentX(CENTER_ALIGNMENT);
        int X = (Toolkit.getDefaultToolkit().getScreenSize().width - 250)/2;
        int Y = (Toolkit.getDefaultToolkit().getScreenSize().height - 170)/2;
        setBounds(X,Y,250,170);
        setResizable(false);
        JPanel allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));

        allPanel.add(beginLabel);
        allPanel.add(Box.createRigidArea(new Dimension(0,20)));
        allPanel.add(this.answer);
        allPanel.add(Box.createRigidArea(new Dimension(0,30)));
        allPanel.add(tryAgain);

        add(allPanel);
        initListeners();
    }
    private void initListeners(){
        tryAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                if(answer.equals(MainClient.stats.getString("Connection error"))){
                    System.exit(0);
                }
                Authoriation ap = new Authoriation();
                ap.setVisible(true);

            }
        });
    }
}
