import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorInsertFrame extends JFrame {
    String a1;
    String a2;
    String a3;
    String a4;
    String a5;
    String a6;
    boolean a7;
    Furnish a8;
    Transport a9;
    String a10;
    String a11;
    String a12;
    long id = 0;
    Integer oldkey = 0;
    ErrorInsertFrame(String msg){
        super(MainClient.stats.getString("Incorrect"));
        int X = (Toolkit.getDefaultToolkit().getScreenSize().width - 250)/2;
        int Y = (Toolkit.getDefaultToolkit().getScreenSize().height - 170)/2;
        setBounds(X,Y,250,170);
        setResizable(false);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
        JLabel begin = new JLabel(MainClient.stats.getString("Incorrect enter"));
        begin.setAlignmentX(CENTER_ALIGNMENT);
        begin.setFont(new Font("TimesNewRoman", Font.BOLD, 25));
        all.add(begin);
        all.add(Box.createRigidArea(new Dimension(0,10)));
        JLabel message = new JLabel(msg);
        message.setFont(new Font("TimesNewRoman",Font.PLAIN,15));
        JScrollPane pane = new JScrollPane(message);
        pane.setAlignmentX(CENTER_ALIGNMENT);
        all.add(pane);
        all.add(Box.createRigidArea(new Dimension(0,10)));
        JButton ok = new JButton("OK");
        ok.setSize(100,30);
        ok.setAlignmentX(CENTER_ALIGNMENT);
        all.add(ok);
        all.add(Box.createRigidArea(new Dimension(0,15)));
        add(all);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlatFrame flatFrame = new FlatFrame(a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12);
                if(id!=0){
                    flatFrame.updateIdMode(id, oldkey);
                }
                flatFrame.setVisible(true);
                setVisible(false);
                dispose();
            }
        });
    }
    public void preSet(String a1, String a2, String a3, String a4, String a5, String a6, boolean a7, Furnish a8, Transport a9, String a10, String a11, String a12){
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.a5 = a5;
        this.a6 = a6;
        this.a7 = a7;
        this.a8 = a8;
        this.a9 = a9;
        this.a10 = a10;
        this.a11 = a11;
        this.a12 = a12;
    }
}
