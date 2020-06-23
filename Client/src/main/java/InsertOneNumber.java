import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class InsertOneNumber extends JFrame {
    String keyOrIdStr;

    private JLabel massage;
    private JLabel massagePort;
    private JTextField number;
    private JButton ok;
    InsertOneNumber(boolean keyOrId){
        super("");
        if(keyOrId){
            setTitle(MainClient.stats.getString("Key"));
        } else {
            setTitle("Id");
        }
        int X = (Toolkit.getDefaultToolkit().getScreenSize().width - 250)/2;
        int Y = (Toolkit.getDefaultToolkit().getScreenSize().height - 170)/2;
        setBounds(X,Y,250,170);
        setResizable(false);
        massage = new JLabel(" ");
        if(keyOrId){
            massage.setText(MainClient.stats.getString("Insert")+" "+MainClient.stats.getString("Key"));
        } else {
            massage.setText(MainClient.stats.getString("Insert")+" id");
        }
        massage.setFont(new Font("TimesNewRoman", Font.BOLD, 25));
        massage.setAlignmentX(CENTER_ALIGNMENT);
        number = new JTextField();

        PlainDocument doc = new PlainDocument(){
            String chars = "0123456789";
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null)
                    return;
                if(chars.indexOf(str)!=-1){
                    super.insertString(offset, str, attr);
                }
            }
        };
        //doc.setDocumentFilter(filter);
        number.setDocument(doc);
        massagePort = new JLabel(" ");
        if(keyOrId){
            massagePort.setText(MainClient.stats.getString("Key")+": ");
        } else{
            massagePort.setText("Id: ");
        }
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
        portable.add(number);
        portable.add(Box.createRigidArea(new Dimension(80,0)));
        allPanel.add(portable);
        allPanel.add(Box.createRigidArea(new Dimension(0,25)));
        allPanel.add(ok);
        allPanel.add(Box.createRigidArea(new Dimension(0,10)));
        add(allPanel);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyOrIdStr = number.getText();
                setVisible(false);
                if(keyOrId){
                    Integer key = Integer.parseInt(keyOrIdStr);
                    RemoveKeyCommandUnex removeKeyCommandUnex = new RemoveKeyCommandUnex();
                    removeKeyCommandUnex.key = key;
                    removeKeyCommandUnex.user = MainClient.username;
                    try {
                        InputMessage.send(Serializer.serialize(removeKeyCommandUnex));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else{

                    long id = Long.parseLong(keyOrIdStr);
                    LinkedHashMap<Integer, Flat> oneElement = new LinkedHashMap<>();
                    MainClient.memoryFlats.entrySet().stream().filter(x -> x.getValue().getId() == id).findFirst().ifPresent(x -> oneElement.put(x.getKey(),x.getValue()));
                    if(oneElement.size() != 0){
                        Map.Entry<Integer, Flat> x = oneElement.entrySet().stream().findFirst().get();
                        if(x.getValue().getUser().equals(MainClient.username)){
                            FlatFrame flatFrame = new FlatFrame(
                                    x.getKey().toString(),
                                    x.getValue().getName(),
                                    x.getValue().getCoordinates().getX().toString(),
                                    x.getValue().getCoordinates().getY().toString(),
                                    x.getValue().getArea().toString(),
                                    x.getValue().getNumberOfRooms().toString(),
                                    x.getValue().getNewOrNot(),
                                    x.getValue().getFurnish(),
                                    x.getValue().getTransport(),
                                    x.getValue().getHouse().getName(),
                                    x.getValue().getHouse().getYear().toString(),
                                    x.getValue().getHouse().getNumberOfFloors().toString()
                            );
                            flatFrame.updateIdMode(MainClient.memoryFlats.get(x.getKey()).getId(), x.getKey());
                            flatFrame.setVisible(true);
                        }
                    }
                }
                dispose();
            }
        });

    }
}
