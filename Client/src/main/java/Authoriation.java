import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class Authoriation extends JFrame{

    private JLabel labelBegin;
    private JLabel loginLabel;
    private JTextField loginInsert;
    private JLabel passLabel;
    private JPasswordField passInsert;
    private JLabel newUserLabel;
    private JCheckBox newUserSet;
    private JButton ok;
    private String serverAnswer;
    public Authoriation(){
        super(MainClient.stats.getString("Authorization"));
        int X = (Toolkit.getDefaultToolkit().getScreenSize().width - 260)/2;
        int Y = (Toolkit.getDefaultToolkit().getScreenSize().height - 200)/2;
        setBounds(X,Y,260,200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        labelBegin = new JLabel(MainClient.stats.getString("Authorization"));
        labelBegin.setAlignmentX(CENTER_ALIGNMENT);
        labelBegin.setFont(new Font("TimesNewRoman", Font.BOLD, 25));
        loginInsert = new JTextField();
        loginInsert.setAlignmentX(CENTER_ALIGNMENT);
        passInsert = new JPasswordField();
        passInsert.setAlignmentX(CENTER_ALIGNMENT);
        PlainDocument forInsertLog = newPlain();
        PlainDocument forInsertPass = newPlain();
        loginInsert.setDocument(forInsertLog);
        passInsert.setDocument(forInsertPass);
        loginLabel = new JLabel(MainClient.stats.getString("Login")+": ");
        loginLabel.setAlignmentX(CENTER_ALIGNMENT);
        passLabel = new JLabel(MainClient.stats.getString("Password")+": ");
        passLabel.setAlignmentX(CENTER_ALIGNMENT);
        newUserLabel = new JLabel(MainClient.stats.getString("Enter like new user?")+" ");
        newUserLabel.setAlignmentX(CENTER_ALIGNMENT);
        newUserSet = new JCheckBox();
        newUserSet.setAlignmentX(CENTER_ALIGNMENT);
        newUserSet.setSelected(true);
        ok = new JButton("OK");
        ok.setAlignmentX(CENTER_ALIGNMENT);
        //===
        JPanel allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));

        allPanel.add(labelBegin);

        allPanel.add(Box.createRigidArea(new Dimension(0,15)));

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.X_AXIS));
        loginPanel.add(Box.createRigidArea(new Dimension(10,0)));
        loginPanel.add(loginLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(20,0)));
        loginPanel.add(loginInsert);
        loginPanel.add(Box.createRigidArea(new Dimension(10,0)));
        allPanel.add(loginPanel);

        allPanel.add(Box.createRigidArea(new Dimension(0,5)));

        JPanel passPanel = new JPanel();
        passPanel.setLayout(new BoxLayout(passPanel, BoxLayout.X_AXIS));
        passPanel.add(Box.createRigidArea(new Dimension(10,0)));
        passPanel.add(passLabel);
        passPanel.add(Box.createRigidArea(new Dimension(9,0)));
        passPanel.add(passInsert);
        passPanel.add(Box.createRigidArea(new Dimension(10,0)));
        allPanel.add(passPanel);

        allPanel.add(Box.createRigidArea(new Dimension(0,5)));

        JPanel newUserPanel = new JPanel();
        newUserPanel.setLayout(new BoxLayout(newUserPanel, BoxLayout.X_AXIS));
        newUserPanel.add(newUserLabel);
        newUserPanel.add(newUserSet);
        allPanel.add(newUserPanel);

        allPanel.add(Box.createRigidArea(new Dimension(0,5)));

        allPanel.add(ok);

        allPanel.add(Box.createRigidArea(new Dimension(0,25)));

        add(allPanel);
        initListeners();
    }
    private void initListeners() {
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginInsert.getText();
                String password = passInsert.getText();
                boolean newUser = newUserSet.isSelected();
                setVisible(false);
                dispose();
                try {
                    check(login,password,newUser);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private PlainDocument newPlain(){
        return new PlainDocument(){
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                String chars = " !\"#$%&\'()*+,-./:;<=>?@[\\]^_{|}`~";
                if(str == null){
                    return;
                }
                if(chars.indexOf(str)==-1){
                    super.insertString(offset, str, attr);
                }
            }
        };
    }
    private void check(String login, String password, boolean newUser) throws IOException, ClassNotFoundException {
        LoginPassSendCommandUnex loginPassSendCommandUnex = new LoginPassSendCommandUnex();
        String[] all = Stream.concat(Arrays.stream(login.split(" ")), Arrays.stream(password.split(" ")))
                .toArray(String[]::new);
        if (loginPassSendCommandUnex.checkCom(all)) {
            loginPassSendCommandUnex.newOrNot = newUser;
            MainClient.username = login;
            MainClient.colorize.put(login,  new Color((int)(Math.random()*256), (int) (Math.random() * 256), (int)(Math.random()*256)));
            InputMessage.send(Serializer.serialize(loginPassSendCommandUnex));

        }

    }
}


