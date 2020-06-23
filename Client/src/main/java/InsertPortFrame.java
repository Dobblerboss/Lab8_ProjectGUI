import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertPortFrame extends JFrame {
    String realProt;
    private JLabel massage;
    private JLabel massagePort;
    private JTextField port;
    private JButton ok;
    InsertPortFrame(){
        super(MainClient.stats.getString("Insert")+" "+MainClient.stats.getString("Port"));
        int X = (Toolkit.getDefaultToolkit().getScreenSize().width - 250)/2;
        int Y = (Toolkit.getDefaultToolkit().getScreenSize().height - 170)/2;
        setBounds(X,Y,250,170);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        massage = new JLabel(MainClient.stats.getString("Insert")+" "+MainClient.stats.getString("Port"));
        massage.setFont(new Font("TimesNewRoman", Font.BOLD, 25));
        massage.setAlignmentX(CENTER_ALIGNMENT);
        port = new JTextField();

        PlainDocument doc = new PlainDocument(){
            String chars = "0123456789";
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null)
                    return;
                if ((getLength() + str.length()) <= 4) {
                    if(chars.indexOf(str)!=-1){
                        super.insertString(offset, str, attr);
                    }
                }
            }
        };
        //doc.setDocumentFilter(filter);
        port.setDocument(doc);
        massagePort = new JLabel(MainClient.stats.getString("Port")+": ");
        massagePort.setAlignmentX(CENTER_ALIGNMENT);
        ok = new JButton("ОК");
        ok.setAlignmentX(CENTER_ALIGNMENT);
        JPanel allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));
        allPanel.add(massage);
        allPanel.add(Box.createRigidArea(new Dimension(0,25)));
        JPanel portable = new JPanel();
        portable.setLayout(new BoxLayout(portable, BoxLayout.X_AXIS));
        portable.add(Box.createRigidArea(new Dimension(80,0)));
        portable.add(massagePort);
        portable.add(port);
        portable.add(Box.createRigidArea(new Dimension(80,0)));
        allPanel.add(portable);
        allPanel.add(Box.createRigidArea(new Dimension(0,25)));
        allPanel.add(ok);
        allPanel.add(Box.createRigidArea(new Dimension(0,10)));
        add(allPanel);
        initListeners();

    }
    private void initListeners() {
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realProt = port.getText();
                setVisible(false);
                Console.port = Integer.parseInt(realProt);
                Authoriation ap = new Authoriation();
                ap.setVisible(true);
                dispose();
            }
        });
    }
}
