import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FlatFrame extends JFrame {
    JLabel begin = new JLabel(MainClient.stats.getString("Passport of object"));
    JPanel panel1 = new JPanel();
    JTextField keyTF = new JTextField();
    JPanel panel2 = new JPanel();
    JTextField nameTF = new JTextField();
    JPanel panel3 = new JPanel();
    JTextField xTF = new JTextField();
    JTextField yTF = new JTextField();
    JPanel panel35 = new JPanel();
    JTextField areaTF = new JTextField();
    JPanel panel4 = new JPanel();
    JTextField numberOfRoomsTF = new JTextField();
    JPanel panel5 = new JPanel();
    JCheckBox newOrNotCB = new JCheckBox();
    JPanel panel6 = new JPanel();
    JComboBox furnishCB;
    JPanel panel7 = new JPanel();
    JComboBox transportCB;
    JPanel panel8 = new JPanel();
    JTextField houseNameTF = new JTextField();
    JPanel panel9 = new JPanel();
    JTextField yearHouseTF = new JTextField();
    JPanel panel10 = new JPanel();
    JTextField numberOfFloorsTF = new JTextField();
    JButton ok = new JButton("OK");


    boolean updateKey = false;

    Integer oldKey = 0;
    long id = 0;
    boolean updateId = false;
    boolean replace = false;
    boolean greater = false;
    boolean remove_lower = false;

    FlatFrame(String a1, String a2, String a3, String a4, String a5, String a6, boolean a7, Furnish a8, Transport a9, String a10, String a11, String a12){
        super("Новый Flat");
        set();
        keyTF.setText(a1);
        nameTF.setText(a2);
        xTF.setText(a3);
        yTF.setText(a4);
        areaTF.setText(a5);
        numberOfRoomsTF.setText(a6);
        newOrNotCB.setSelected(a7);
        furnishCB.setSelectedItem(a8);
        transportCB.setSelectedItem(a9);
        houseNameTF.setText(a10);
        yearHouseTF.setText(a11);
        numberOfFloorsTF.setText(a12);

    }
    private void set(){

        int X = (Toolkit.getDefaultToolkit().getScreenSize().width - 250)/2;
        int Y = (Toolkit.getDefaultToolkit().getScreenSize().height - 400)/2;
        setBounds(X,Y,250,400);
        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
        setResizable(false);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addPanel(panel1, MainClient.stats.getString("Key"), 85, keyTF);
        keyTF.setDocument(new PlusNumb());
        addPanel(panel2, MainClient.stats.getString("Name of Flat"), 10, nameTF);
        nameTF.setDocument(newPlain());

        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        panel3.add(Box.createRigidArea(new Dimension(25,0)));
        panel3.add(new JLabel("X"));
        panel3.add(Box.createRigidArea(new Dimension(25,0)));
        panel3.add(xTF);
        xTF.setDocument(new JustNumb());
        panel3.add(Box.createRigidArea(new Dimension(25,0)));
        panel3.add(new JLabel("Y"));
        panel3.add(Box.createRigidArea(new Dimension(25,0)));
        panel3.add(yTF);
        yTF.setDocument(new JustNumb());
        panel3.add(Box.createRigidArea(new Dimension(25,0)));

        addPanel(panel35,MainClient.stats.getString("Area"), 62, areaTF);
        areaTF.setDocument(new PlusNumb());
        addPanel(panel4, MainClient.stats.getString("Number of rooms"), 70, numberOfRoomsTF);
        numberOfRoomsTF.setDocument(new OneNumber());
        addPanel(panel5,MainClient.stats.getString("New?"), 83, newOrNotCB);

        Furnish[] items1 = {
                Furnish.NONE,
                Furnish.LITTLE,
                Furnish.FINE
        };
        furnishCB = new JComboBox(items1);

        Transport[] items2 = {
                Transport.NONE,
                Transport.FEW,
                Transport.NORMAL,
                Transport.ENOUGH
        };
        transportCB = new JComboBox(items2);

        addPanel(panel6, MainClient.stats.getString("Furnish"), 25, furnishCB);
        addPanel(panel7, MainClient.stats.getString("Transport"), 10, transportCB);
        addPanel(panel8, MainClient.stats.getString("House name"), 10, houseNameTF);
        houseNameTF.setDocument(newPlain());
        addPanel(panel9, MainClient.stats.getString("House year"), 10, yearHouseTF);
        yearHouseTF.setDocument(new PlusNumb());

        addPanel(panel10, MainClient.stats.getString("Number of floors"), 10, numberOfFloorsTF);
        numberOfFloorsTF.setDocument(new PlusNumb());
        begin.setFont(new Font("TimesNewRoman", Font.BOLD, 25));
        begin.setAlignmentX(CENTER_ALIGNMENT);
        ok.setAlignmentX(CENTER_ALIGNMENT);
        ok.setSize(100,30);

        all.add(begin);
        all.add(Box.createRigidArea(new Dimension(0,5)));
        all.add(panel1);
        all.add(Box.createRigidArea(new Dimension(0,5)));
        all.add(panel2);
        all.add(Box.createRigidArea(new Dimension(0,5)));
        all.add(panel3);
        all.add(Box.createRigidArea(new Dimension(0,5)));
        all.add(panel35);
        all.add(Box.createRigidArea(new Dimension(0,5)));
        all.add(panel4);
        all.add(Box.createRigidArea(new Dimension(0,5)));
        all.add(panel5);
        all.add(Box.createRigidArea(new Dimension(0,5)));
        all.add(panel6);
        all.add(Box.createRigidArea(new Dimension(0,5)));
        all.add(panel7);
        all.add(Box.createRigidArea(new Dimension(0,5)));
        all.add(panel8);
        all.add(Box.createRigidArea(new Dimension(0,5)));
        all.add(panel9);
        all.add(Box.createRigidArea(new Dimension(0,5)));
        all.add(panel10);
        all.add(Box.createRigidArea(new Dimension(0,5)));
        all.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyStr = keyTF.getText();
                String name = nameTF.getText();
                String xStr = xTF.getText();
                String yStr = yTF.getText();
                String areaStr = areaTF.getText();
                String numberOfRoomsStr = numberOfRoomsTF.getText();
                boolean newOrNot = newOrNotCB.isSelected();
                Furnish furnish = (Furnish) furnishCB.getSelectedItem();
                Transport transport = (Transport) transportCB.getSelectedItem();
                String houseName = houseNameTF.getText();
                String yearHouseStr = yearHouseTF.getText();
                String numberOfFloorsStr = numberOfFloorsTF.getText();
                String msg = "";
                Integer key = 0;
                Float x = 0f;
                Float y =0f;
                Integer area = 0;
                Long numberOfRooms = 0l;
                Long yearHouse = 0l;
                Long numberOfFloors = 0l;
                try{
                    key = Integer.parseInt(keyStr);
                    if(name.trim().equals("")) msg+=MainClient.stats.getString("Name of Flat")+": "+ MainClient.stats.getString("field is empty")+". ";
                    if(houseName.trim().equals("")) msg+=MainClient.stats.getString("House name") +": "+ MainClient.stats.getString("field is empty")+". ";
                    x = Float.parseFloat(xStr);
                    y = Float.parseFloat(yStr);
                    area = Integer.parseInt(areaStr);
                    if(area == 0) msg += MainClient.stats.getString("Area")+" = 0. ";
                    numberOfRooms = Long.parseLong(numberOfRoomsStr);
                    yearHouse = Long.parseLong(yearHouseStr);
                    if(yearHouse==0) msg+=MainClient.stats.getString("House year")+" = 0. ";
                    if (yearHouse>155) msg+=MainClient.stats.getString("House year")+" > 155. ";
                    numberOfFloors = Long.parseLong(numberOfFloorsStr);
                    if(numberOfFloors == 0) msg+=MainClient.stats.getString("Number of floors")+" = 0. ";

                } catch (NumberFormatException e1){
                    msg+=MainClient.stats.getString("Check each parameter. Maybe it's empty or you use excess signs. ");
                }
                if(MainClient.memoryFlats.get(key)!=null){
                    if(!remove_lower){
                        if(updateId && !oldKey.equals(key)){
                            msg+=MainClient.stats.getString("This key already exists")+".";
                        } else if(!MainClient.memoryFlats.get(key).getUser().equals(MainClient.username)){
                            msg+=MainClient.stats.getString("This key already exists")+", "+MainClient.stats.getString("and it doesn't belong to you");
                        }
                    }
                }
                if(!msg.equals("")){
                    ErrorInsertFrame errorInsertFrame = new ErrorInsertFrame(msg);
                    errorInsertFrame.preSet(keyStr,name,xStr,yStr,areaStr,numberOfRoomsStr,newOrNot,furnish,transport,houseName,yearHouseStr,numberOfFloorsStr);
                    errorInsertFrame.id = id;
                    errorInsertFrame.oldkey = oldKey;
                    errorInsertFrame.setVisible(true);
                } else{
                    Flat newFlat = new Flat(name, new Coordinates(x,y),area, numberOfRooms, newOrNot, furnish, transport, new House(houseName, yearHouse, numberOfFloors));
                    newFlat.setUser(MainClient.username);
                    if(updateId){
                        newFlat.setId(id);
                        UpdateIdCommandUnex updateIdCommandUnex = new UpdateIdCommandUnex();
                        updateIdCommandUnex.set(id, newFlat, key);
                        updateIdCommandUnex.user = MainClient.username;
                        try {
                            InputMessage.send(Serializer.serialize(updateIdCommandUnex));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } else if(replace){
                        if(greater){
                            if(MainClient.memoryFlats.get(key)!=null){
                                long id = MainClient.memoryFlats.get(key).getId();
                                newFlat.setId(id);
                                ReplaceIfGreaterCommandUnex replaceIfGreaterCommandUnex = new ReplaceIfGreaterCommandUnex();
                                replaceIfGreaterCommandUnex.user =MainClient.username;
                                replaceIfGreaterCommandUnex.setNewFlat(newFlat);
                                replaceIfGreaterCommandUnex.setKey(key);
                                try {
                                    InputMessage.send(Serializer.serialize(replaceIfGreaterCommandUnex));
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                } catch (ClassNotFoundException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            if(MainClient.memoryFlats.get(key)!=null){
                                long id = MainClient.memoryFlats.get(key).getId();
                                newFlat.setId(id);
                                ReplaceIfLowerCommandUnex replaceIfLowerCommandUnex = new ReplaceIfLowerCommandUnex();
                                replaceIfLowerCommandUnex.user =MainClient.username;
                                replaceIfLowerCommandUnex.setNewFlat(newFlat);
                                replaceIfLowerCommandUnex.setKey(key);
                                try {
                                    InputMessage.send(Serializer.serialize(replaceIfLowerCommandUnex));
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                } catch (ClassNotFoundException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    } else if(remove_lower){
                        RemoveLowerCommandUnex removeLowerCommandUnex = new RemoveLowerCommandUnex();
                        removeLowerCommandUnex.user = MainClient.username;
                        removeLowerCommandUnex.newFlat = newFlat;
                        try {
                            InputMessage.send(Serializer.serialize(removeLowerCommandUnex));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        InsertElementCommandUnex insertElementCommandUnex = new InsertElementCommandUnex();
                        insertElementCommandUnex.user = MainClient.username;
                        insertElementCommandUnex.checkCom(new String[]{keyStr});
                        insertElementCommandUnex.setNewFlat(newFlat);
                        try {
                            InputMessage.send(Serializer.serialize(insertElementCommandUnex));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }

                }
                setVisible(false);
                dispose();
            }
        });
        add(all);

    }
    FlatFrame(){
        super("Flat");
        set();
    }

    public void updateIdMode(long id, Integer oldKey){
        setTitle(MainClient.stats.getString("Changing"));
        this.oldKey = oldKey;
        updateId = true;
        this.id = id;
    }

    public void replaceMode(boolean greater){
        setTitle(MainClient.stats.getString("Replacement"));
        replace = true;
        this.greater = greater;
    }

    public void removeLowerMode(){
        setTitle(MainClient.stats.getString("Clearing"));
        remove_lower = true;
        keyTF.setText("1");
        panel1.setVisible(false);
    }

    public void Uneditble(){
        setTitle(MainClient.stats.getString("Unchanged"));
        keyTF.setEditable(false);
        nameTF.setEditable(false);
        xTF.setEditable(false);
        yTF.setEditable(false);
        areaTF.setEditable(false);
        numberOfRoomsTF.setEditable(false);
        newOrNotCB.setEnabled(false);
        furnishCB.setEditable(false);
        furnishCB.setEnabled(false);
        transportCB.setEditable(false);
        transportCB.setEnabled(false);
        houseNameTF.setEditable(false);
        yearHouseTF.setEditable(false);
        numberOfFloorsTF.setEditable(false);
        ok.setVisible(false);
    }

    private void addPanel(JPanel panel, String title, int width, JComponent textField){
        panel.add(Box.createRigidArea(new Dimension(25,0)));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel(title));
        panel.add(Box.createRigidArea(new Dimension(width,0)));
        panel.add(textField);
        panel.add(Box.createRigidArea(new Dimension(25,0)));
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

    class PlusNumb extends PlainDocument{
        String chars = "0123456789";
        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;
            int a = -1;
            for(char q: str.toCharArray()){
                a = chars.indexOf(q);
                if(a==-1){
                    break;
                }
            }
            if((a!=-1)){
                super.insertString(offset, str, attr);
            }
        }
    };
    class JustNumb extends PlainDocument{
        String chars = ".-0123456789";
        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;
            int a = -1;
            for(char q: str.toCharArray()){
                a = chars.indexOf(q);
                if(a==-1){
                    break;
                }
            }
            if((a!=-1)){
                super.insertString(offset, str, attr);
            }
        }

    }
    class OneNumber extends PlainDocument{
        String chars = "123456789";
        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;
            if ((getLength() + str.length()) <= 1) {
                if(chars.indexOf(str)!=-1){
                    super.insertString(offset, str, attr);
                }
            }
        }
    }
}
