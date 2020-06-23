import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLanguageStart extends JFrame {
    private JButton russia;
    private JButton ireland;
    private JButton poland;
    private JButton slovakia;
    public SelectLanguageStart(){
        super("");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int X = (Toolkit.getDefaultToolkit().getScreenSize().width - 385)/2;
        int Y = (Toolkit.getDefaultToolkit().getScreenSize().height - 165)/2;
        setBounds(X,Y,385,165);
        setLayout(null);
        JPanel all = new JPanel();
        all.setBounds(25,25,315,75);
        all.setLayout(new GridLayout(1,4,5,0));
        russia = new JButton(new ImageIcon(this.getClass().getResource("Russia.png")));
        russia.setSize(75,75);
        russia.setAlignmentX(Component.CENTER_ALIGNMENT);
        russia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action1();
            }
        });
        ireland = new JButton(new ImageIcon(this.getClass().getResource("Ireland.png")));
        ireland.setSize(75,75);
        ireland.setAlignmentX(Component.CENTER_ALIGNMENT);
        ireland.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action2();
            }
        });
        poland = new JButton(new ImageIcon(this.getClass().getResource("Poland.png")));
        poland.setSize(75,75);
        poland.setAlignmentX(Component.CENTER_ALIGNMENT);
        poland.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action3();
            }
        });
        slovakia = new JButton(new ImageIcon(this.getClass().getResource("Slovakia.png")));
        slovakia.setSize(75,75);
        slovakia.setAlignmentX(Component.CENTER_ALIGNMENT);
        slovakia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action4();
            }
        });
        all.add(russia);
        all.add(ireland);
        all.add(poland);
        all.add(slovakia);
        add(all);
    }
    private void action1(){
        InsertPortFrame app = new InsertPortFrame();
        app.setVisible(true);
        setVisible(false);
        dispose();
    }
    private void action2(){
        MainClient.nextLocale();
        action1();
    }
    private void action3(){
        MainClient.nextLocale();
        action2();
    }
    private void action4(){
        MainClient.nextLocale();
        action3();
    }
}
