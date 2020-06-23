import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class MainWindow extends JFrame {
    CoordAxis coordAxis;

    JLabel textWithLogin;

    JPanel windowXY;
    JPanel panelAbove;
    JPanel buttonMap;

    JButton plus;
    JButton minus;
    JButton info;
    JButton help;

    JTable table;
    JScrollPane pane;

    JPanel allOtherButton;
    JPanel forTable;

    JButton addHouse;
    JButton removeAll;
    JButton update;
    JButton removeByKey;
    JButton replaceIfGreater;
    JButton replaceIfLower;
    JButton removeLower;
    JButton maxTransport;
    JButton sumOfRooms;
    JPanel panelForSum;
    JLabel sum;
    JButton selectLocale;
    JButton buttonForFilter;

    JTextField filerText;

    MoveListener ml;

    TableRowSorter<TableModel> sorter;

    static int CurrentX = -700;
    static int CurrentY = -400;

    public static LinkedHashMap<Integer, Flat> memoryMemoryFlats = new LinkedHashMap<>();

    MainWindow(){
        super(MainClient.stats.getString("Main window"));
        setLayout(null);
        int X = (Toolkit.getDefaultToolkit().getScreenSize().width - 1000)/2;
        int Y = (Toolkit.getDefaultToolkit().getScreenSize().height - 700)/2;
        //setSize(new Dimension(1000, 700));
        setBounds(X,Y,1000,700);
        setResizable(false);

        windowXY = new JPanel();
        panelAbove = new JPanel();
        buttonMap = new JPanel();
        plus = new JButton(new ImageIcon(this.getClass().getResource("plus.png")));
        minus = new JButton(new ImageIcon(this.getClass().getResource("minus.png")));
        info = new JButton(new ImageIcon(this.getClass().getResource("info.png")));
        help = new JButton(new ImageIcon(this.getClass().getResource("help.png")));

        info.setSize(40,40);
        help.setSize(40,40);
        plus.setSize(40,40);
        minus.setSize(40,40);

        buttonMap.setSize(280,40);
        buttonMap.setLayout(new GridLayout());
        buttonMap.setLocation(660,5);
        buttonMap.add(info);
        buttonMap.add(Box.createRigidArea(new Dimension(40,0)));
        buttonMap.add(help);
        buttonMap.add(Box.createRigidArea(new Dimension(40,0)));
        buttonMap.add(plus);
        buttonMap.add(Box.createRigidArea(new Dimension(40,0)));
        buttonMap.add(minus);

        allOtherButton = new JPanel();
        allOtherButton.setLayout(null);
        allOtherButton.setSize(145,650);
        allOtherButton.setLocation(15,10);
        addHouse =new JButton(MainClient.stats.getString("Add"));
        addHouse.setBounds(0,0,145,30);
        removeAll = new JButton(MainClient.stats.getString("Remove all"));
        removeAll.setBounds(0,40,145,30);
        update = new JButton(MainClient.stats.getString("Update"));
        update.setBounds(0,80,145,30);
        removeByKey = new JButton(MainClient.stats.getString("Remove by key"));
        removeByKey.setBounds(0,120,145,30);
        removeByKey.setMargin(new Insets(0,0,0,0));
        replaceIfGreater = new JButton(MainClient.stats.getString("Replace if greater"));
        replaceIfGreater.setBounds(0,160,145,30);
        replaceIfGreater.setMargin(new Insets(0,0,0,0));
        replaceIfLower = new JButton(MainClient.stats.getString("Replace if lower"));
        replaceIfLower.setBounds(0,200,145,30);
        replaceIfLower.setMargin(new Insets(0,0,0,0));
        removeLower = new JButton(MainClient.stats.getString("Remove lower"));
        removeLower.setBounds(0,240,145,30);
        maxTransport = new JButton(MainClient.stats.getString("Max by transport"));
        maxTransport.setBounds(0, 300, 145, 30);
        maxTransport.setMargin(new Insets(0,0,0,0));
        sumOfRooms = new JButton(MainClient.stats.getString("Sum of rooms"));
        sumOfRooms.setBounds(0,340, 145,30);
        panelForSum = new JPanel();
        panelForSum.setBounds(0, 370, 145,30);
        sum = new JLabel("", SwingConstants.CENTER);
        sum.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        selectLocale = new JButton();
        selectLocale.setBounds(35,540,75,75);
        selectLocale.setIcon(new ImageIcon(MainClient.stats.getString("Flag")));
        panelForSum.add(sum);

        allOtherButton.add(addHouse);
        allOtherButton.add(removeAll);
        allOtherButton.add(update);
        allOtherButton.add(removeByKey);
        allOtherButton.add(replaceIfGreater);
        allOtherButton.add(replaceIfLower);
        allOtherButton.add(removeLower);
        allOtherButton.add(maxTransport);
        allOtherButton.add(sumOfRooms);
        allOtherButton.add(panelForSum);
        allOtherButton.add(selectLocale);

        windowXY.setSize(new Dimension(800,400));
        panelAbove.setSize(new Dimension(800,400));

        windowXY.setBackground(Color.white);

        ml = new MoveListener();
        windowXY.setLocation(175,50);
        panelAbove.setLocation(175,50);
        coordAxis = new CoordAxis();
        coordAxis.setOpaque(true);
        coordAxis.addMouseListener(ml);
        coordAxis.addMouseMotionListener(ml);
        windowXY.add(coordAxis);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(windowXY);

        forTable = new JPanel();
        forTable.setLayout(null);
        forTable.setSize(800,170);
        forTable.setLocation(175,490);

        memoryMemoryFlats = (LinkedHashMap<Integer, Flat>) MainClient.memoryFlats.clone();
        Object[][] rows = {

        };
        String columns[] = {MainClient.stats.getString("Key"), "id", MainClient.stats.getString("Name of Flat"), "x", "y", MainClient.stats.getString("Creation date"),MainClient.stats.getString("Area"),MainClient.stats.getString("Number of rooms"),MainClient.stats.getString("New?"),MainClient.stats.getString("Furnish"),MainClient.stats.getString("Transport"), MainClient.stats.getString("Creation date"), MainClient.stats.getString("House name"), MainClient.stats.getString("House year"),MainClient.stats.getString("Number of floors"), MainClient.stats.getString("Owner")};
        TableModel model = new DefaultTableModel(rows, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            public Class getColumnClass(int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };
        table = new JTable(model);
        table.setBounds(0,0,800,195);

        TableColumn column = null;
        for (int i = 0; i < 15; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(50);
        }
        sorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        table.setDragEnabled(false);
        pane = new JScrollPane(table);
        pane.setSize(800,195);
        forTable.add(pane);
        table.getTableHeader().setReorderingAllowed(false);

        JPanel forLogin = new JPanel();
        forLogin.setLayout(new BorderLayout());
        forLogin.setBounds(175,10,800,30);
        textWithLogin = new JLabel(MainClient.stats.getString("Login")+": " + MainClient.username);
        textWithLogin.setBackground(MainClient.colorize.get(MainClient.username));
        textWithLogin.setOpaque(true);
        textWithLogin.setForeground(new Color(
                255-MainClient.colorize.get(MainClient.username).getRed(),
                255-MainClient.colorize.get(MainClient.username).getGreen(),
                255-MainClient.colorize.get(MainClient.username).getBlue())
        );
        textWithLogin.setFont(new Font("TimesNewRoman", Font.ITALIC, 30));
        forLogin.add(textWithLogin, BorderLayout.WEST);
        //textWithLogin.setBounds(175,10,lngth,30);

        JPanel forFilterTextField = new JPanel();
        forFilterTextField.setLayout(new BoxLayout(forFilterTextField, BoxLayout.X_AXIS));
        forFilterTextField.setBounds(175,455,200,30);
        buttonForFilter = new JButton(MainClient.stats.getString("Filter")+": ");
        filerText = new JTextField("");
        forFilterTextField.add(buttonForFilter);
        forFilterTextField.add(Box.createRigidArea(new Dimension(20,0)));
        forFilterTextField.add(filerText);
        buttonForFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filerText.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter
                                .setRowFilter(RowFilter
                                        .regexFilter(text));
                    } catch (PatternSyntaxException pse) {
                        System.err.println("Bad regex pattern");
                    }
                }
            }
        });

        add(panelAbove);
        add(buttonMap);
        add(allOtherButton);
        add(forTable);
        add(forLogin);
        add(forFilterTextField);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initListeners();
            }
        });
        //coordAxis.setLocation(-700,-400);



        //System.out.println("main "+ coordAxis.getX() + " " + coordAxis.getY() );
    }

    class CoordAxis extends JPanel {


        double scale=1.0;
        int dX;
        int dY;

        //ArrayList<HouseMoving> allHouses = new ArrayList<>();
        LinkedHashMap<Integer, HouseMoving> allHouses = new LinkedHashMap<>(); //key - JComponent
        LinkedHashMap<Integer, ForHousesListener> allListeners = new LinkedHashMap<>(); //key - listener

        CoordAxis(){
            setLayout(null);
            dX=0;
            dY=0;
            setPreferredSize(new Dimension(2200,1200));
            setLocation(-700,-400);
            //System.out.println(getX()+ " "+getY());

        }

        @Override
        protected void paintComponent(Graphics g){

            Graphics2D g2d = (Graphics2D) g;
            BasicStroke c = new BasicStroke(2);
            g2d.setColor(Color.white);
            g2d.fillRect(0,0,2200,1200);
            g2d.setColor(Color.black);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setFont(new Font("TimesNewRoman", Font.BOLD, 15));


            g2d.setStroke(c);
            for (int i = 0; i <= 60; i++) {
                g2d.setColor(Color.lightGray);
                if(i%5 == 0){
                    if(i==30){
                        g2d.setColor(Color.red);
                        c = new BasicStroke(3);
                    } else{
                        double str = ((30-i)*scale);
                        g2d.setColor(Color.black);
                        int lngth = String.valueOf(str).split("").length*10;
                        if(getX()>=(-300-lngth)){
                            g2d.drawString(String.valueOf(str), Math.abs(getX())+800-lngth,dY+i*20-5);
                        } else if(getX()<=-1105){
                            g2d.drawString(String.valueOf(str), Math.abs(getX()),dY+i*20-5);
                        } else{
                            g2d.drawString(String.valueOf(str), 1105,dY+i*20-5);
                        }
                        g2d.setColor(Color.lightGray);
                        c = new BasicStroke(2);
                    }

                } else {
                    c = new BasicStroke(1);
                }
                g2d.setStroke(c);
                g2d.drawLine(dX,dY+i*20, dX+2200, dY+i*20);
            }
            for (int i = 0; i <= 110 ; i++) {
                g2d.setColor(Color.lightGray);
                if(i%5 == 0){
                    if(i==55){
                        g2d.setColor(Color.red);
                        c = new BasicStroke(3);
                    } else{
                        double str = ((-55+i)*scale);
                        g2d.setColor(Color.black);
                        if(getY()>=-200){
                            g2d.drawString(String.valueOf(str), dY+i*20+5,Math.abs(getY())+395);
                        } else if(getY()<=-580){
                            g2d.drawString(String.valueOf(str), dY+i*20+5,Math.abs(getY())+15);
                        } else{
                            g2d.drawString(String.valueOf(str), dY+i*20+5,595);
                        }
                        g2d.setColor(Color.lightGray);
                        c = new BasicStroke(2);
                    }

                } else {
                    c = new BasicStroke(1);
                }

                g2d.setStroke(c);
                g2d.drawLine(dX+i*20,dY, dX+i*20, dY+1200);
            }

            g2d.setColor(Color.black);
            allHouses.values().stream().forEach(x -> {
                        x.setLocation(
                                (int)(((1/scale)*x.XCoord*20+1100)-x.getWidth()/2),
                                (int)(((1/scale)*(-x.YCoord)*20+600)-x.getHeight()/2)
                        );
                    }
            );
            LinkedHashMap<Integer, HouseMoving> others = new LinkedHashMap<>() ;
            allHouses.entrySet().stream().filter(x -> x.getValue().scale>0.0).forEach(x-> others.put(x.getKey(),x.getValue()));
            if(others.size()<allHouses.size()){
                removeMouseMotionListener(ml);
                removeMouseListener(ml);
                ml = new MoveListener();
                addMouseListener(ml);
                addMouseMotionListener(ml);
                allHouses.entrySet().stream().filter(x -> x.getValue().scale<=0.0).forEach(x -> {
                    x.getValue().removeMouseListener(allListeners.remove(x.getKey()));
                });
            }
            allHouses = others;

            // g2d.fillOval((int)(((1/scale)*xShar*20+1100)-10),(int)(((1/scale)*(-yShar)*20+600)-10),20,20);

            g2d.setColor(Color.green);
            //g2d.fillOval(1090,590,20,20);
            g2d.drawString("0",1105,595);
            if((getX()==-700)&&(getY()==5)){
                setLocation(CurrentX,CurrentY);
            }

            //System.out.println(getX()+" " + getY());
        }

    }

    private void deleting(){
        memoryMemoryFlats.entrySet()
                .stream()
                .filter(x ->{
                    try {
                        int comp = x.getValue().compareTo(MainClient.memoryFlats.get(x.getKey()));
                        return comp!=0;
                    } catch (NullPointerException e){
                        //System.out.println();
                        return true;
                    }
                })
                .forEach(x ->{
                    coordAxis.allHouses.get(x.getKey()).clear();
                });
    }
    private void adding(){
        MainClient.memoryFlats
                .entrySet()
                .stream()
                .filter(x ->{
                    try {
                        int comp = memoryMemoryFlats.get(x.getKey()).compareTo(x.getValue());
                        return comp!=0;
                    } catch (NullPointerException e){
                        //System.out.println();
                        return true;
                    }
                })
                .forEach(x -> {
                    memoryMemoryFlats.put(x.getKey(), x.getValue());
                    HouseMoving houseMoving = new HouseMoving(MainClient.colorize.get(x.getValue().getUser()), 18, x.getValue().getNumberOfRooms());
                    houseMoving.setBounds(-1,-1, (int)houseMoving.width, (int)houseMoving.height );
                    houseMoving.setCoord(x.getValue().getCoordinates().getX(),x.getValue().getCoordinates().getY());
                    coordAxis.allHouses.put(x.getKey(),houseMoving);
                    coordAxis.add(houseMoving);
                    houseMoving.restart();
                });
    }

    public void refresh(){
        coordAxis.allHouses.entrySet().stream().forEach(x ->{
            x.getValue().removeMouseListener(coordAxis.allListeners.get(x.getKey()));
        });
        coordAxis.allListeners.clear();
        AtomicInteger i = new AtomicInteger(0);
        MainClient.memoryFlats.entrySet()
                .stream()
                .forEach(x -> {
                    try {
                        int comp = memoryMemoryFlats.get(x.getKey()).compareTo(x.getValue());
                        if(comp == 0){
                            i.incrementAndGet();
                        }
                    } catch (NullPointerException e){
                        return;
                    }
                });
        int i1 = i.get();
        //System.out.println(i1);
        if(i1==memoryMemoryFlats.size()){
            if(i1<MainClient.memoryFlats.size()){
                adding();
                coordAxis.repaint();
            }
        } else if(i1< memoryMemoryFlats.size()){
            if(i1 == MainClient.memoryFlats.size()){
                // удаление
                deleting();
                memoryMemoryFlats = MainClient.memoryFlats;
                coordAxis.repaint();
            }else{
                if(MainClient.memoryFlats.size() == memoryMemoryFlats.size()){
                    //Замена
                    deleting();
                    adding();
                    memoryMemoryFlats = MainClient.memoryFlats;
                    coordAxis.repaint();
                }
            }

        }
        refreshTable();


        coordAxis.allHouses.entrySet().stream().forEach(x ->{
            ForHousesListener opa = new ForHousesListener(x);
            x.getValue().addMouseListener(opa);
            coordAxis.allListeners.put(x.getKey(), opa);
        });
    }

    private void refreshTable(){
        String columns[] = {MainClient.stats.getString("Key"), "id", MainClient.stats.getString("Name of Flat"), "x", "y", MainClient.stats.getString("Creation date"),MainClient.stats.getString("Area"),MainClient.stats.getString("Number of rooms"),MainClient.stats.getString("New?"),MainClient.stats.getString("Furnish"),MainClient.stats.getString("Transport"), MainClient.stats.getString("House name"), MainClient.stats.getString("House year"),MainClient.stats.getString("Number of floors"), MainClient.stats.getString("Owner")};
        ArrayList<Object[]> allOfThem = new ArrayList<>();
        memoryMemoryFlats.entrySet().stream().forEach( x -> {
            allOfThem.add(new Object[]{
                    x.getKey(),
                    x.getValue().getId(),
                    x.getValue().getName(),
                    x.getValue().getCoordinates().getX(),
                    x.getValue().getCoordinates().getY(),
                    x.getValue().getCreationDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(MainClient.currentLocale)),
                    x.getValue().getArea(),
                    x.getValue().getNumberOfRooms(),
                    x.getValue().getNewOrNot(),
                    x.getValue().getFurnish(),
                    x.getValue().getTransport(),
                    x.getValue().getHouse().getName(),
                    x.getValue().getHouse().getYear(),
                    x.getValue().getHouse().getNumberOfFloors(),
                    x.getValue().getUser(),

            });
        });
        Object[][]rows = new Object[allOfThem.size()][14];
        rows = allOfThem.toArray(rows);
        forTable.remove(pane);
        TableModel model = new DefaultTableModel(rows, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            public Class getColumnClass(int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };

        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setBounds(0,0,800,195);

        TableColumn column = null;
        for (int i2 = 0; i2 < 15; i2++) {
            column = table.getColumnModel().getColumn(i2);
            column.setPreferredWidth(50);
        }
        sorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        table.setDragEnabled(false);
        pane = new JScrollPane(table);
        pane.setSize(800,195);

        forTable.add(pane);
    }

    private void initListeners(){
        addHouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        FlatFrame flatFrame = new FlatFrame();
                        flatFrame.setVisible(true);
                    }
                });

            }
        });
        removeAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ClearCommandUnex clearCommandUnex = new ClearCommandUnex();
                        clearCommandUnex.checkCom(new String[]{});
                        clearCommandUnex.user = MainClient.username;
                        try {
                            InputMessage.send(Serializer.serialize(clearCommandUnex));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int i;
                        for (i = 0; i < memoryMemoryFlats.size(); i++) {
                            if(table.isRowSelected(i)){
                                break;
                            }
                        }
                        if(i<memoryMemoryFlats.size()){
                            Integer x = (Integer) table.getValueAt(i,0);
                            if(MainClient.username.equals(memoryMemoryFlats.get(x).getUser())){
                                FlatFrame flatFrame = new FlatFrame(
                                        x.toString(),
                                        memoryMemoryFlats.get(x).getName(),
                                        memoryMemoryFlats.get(x).getCoordinates().getX().toString(),
                                        memoryMemoryFlats.get(x).getCoordinates().getY().toString(),
                                        memoryMemoryFlats.get(x).getArea().toString(),
                                        memoryMemoryFlats.get(x).getNumberOfRooms().toString(),
                                        memoryMemoryFlats.get(x).getNewOrNot(),
                                        memoryMemoryFlats.get(x).getFurnish(),
                                        memoryMemoryFlats.get(x).getTransport(),
                                        memoryMemoryFlats.get(x).getHouse().getName(),
                                        memoryMemoryFlats.get(x).getHouse().getYear().toString(),
                                        memoryMemoryFlats.get(x).getHouse().getNumberOfFloors().toString()
                                );
                                flatFrame.updateIdMode(MainClient.memoryFlats.get(x).getId(), x);
                                flatFrame.setVisible(true);
                            }

                        }else {
                            InsertOneNumber insertOneNumber = new InsertOneNumber(false);
                            insertOneNumber.setVisible(true);
                        }
                    }
                });
            }
        });
        removeByKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        InsertOneNumber insertOneNumber = new InsertOneNumber(true);
                        insertOneNumber.setVisible(true);
                    }
                });
            }
        });
        replaceIfGreater.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        FlatFrame flatFrame =new FlatFrame();
                        flatFrame.replaceMode(true);
                        flatFrame.setVisible(true);
                    }
                });
            }
        });
        replaceIfLower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        FlatFrame flatFrame =new FlatFrame();
                        flatFrame.replaceMode(false);
                        flatFrame.setVisible(true);
                    }
                });
            }
        });
        removeLower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        FlatFrame flatFrame = new FlatFrame();
                        flatFrame.removeLowerMode();
                        flatFrame.setVisible(true);
                    }
                });
            }
        });
        maxTransport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Map.Entry<Integer, Flat>[] max = new Map.Entry[1];
                        memoryMemoryFlats.entrySet()
                                .stream()
                                .max(((o1, o2) -> Integer.compare(o1.getValue().getTransport().compareTo(o2.getValue().getTransport()), 0)))
                                .ifPresent(x -> max[0] = x);
                        FlatFrame flatFrame = new FlatFrame(
                                max[0].getKey().toString(),
                                max[0].getValue().getName(),
                                max[0].getValue().getCoordinates().getX().toString(),
                                max[0].getValue().getCoordinates().getY().toString(),
                                max[0].getValue().getArea().toString(),
                                max[0].getValue().getNumberOfRooms().toString(),
                                max[0].getValue().getNewOrNot(),
                                max[0].getValue().getFurnish(),
                                max[0].getValue().getTransport(),
                                max[0].getValue().getHouse().getName(),
                                max[0].getValue().getHouse().getYear().toString(),
                                max[0].getValue().getHouse().getNumberOfFloors().toString()
                        );
                        flatFrame.Uneditble();
                        flatFrame.setVisible(true);
                    }
                });
            }
        });
        sumOfRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        CurrentX = coordAxis.getX();
                        CurrentY = coordAxis.getY();
                        AtomicLong atSum = new AtomicLong(0);
                        memoryMemoryFlats.values().stream().forEach(x -> atSum.addAndGet(x.getNumberOfRooms()));
                        Long Sum = atSum.get();
                        sum.setText(Sum.toString());
                        sum.setVisible(true);
                    }
                });
            }
        });
        selectLocale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        CurrentX = coordAxis.getX();
                        CurrentY = coordAxis.getY();
                        MainClient.nextLocale();
                        selectLocale.setIcon(new ImageIcon(MainClient.stats.getString("Flag")));
                        setTitle(MainClient.stats.getString("Main window"));
                        addHouse.setText(MainClient.stats.getString("Add"));
                        removeAll.setText(MainClient.stats.getString("Remove all"));
                        update.setText(MainClient.stats.getString("Update"));
                        removeByKey.setText(MainClient.stats.getString("Remove by key"));
                        replaceIfGreater.setText(MainClient.stats.getString("Replace if greater"));
                        replaceIfLower.setText(MainClient.stats.getString("Replace if lower"));
                        removeLower.setText(MainClient.stats.getString("Remove lower"));
                        maxTransport.setText(MainClient.stats.getString("Max by transport"));
                        sumOfRooms.setText(MainClient.stats.getString("Sum of rooms"));
                        buttonForFilter.setText(MainClient.stats.getString("Filter")+": ");
                        textWithLogin.setText(MainClient.stats.getString("Login")+": "+MainClient.username);
                        refreshTable();
                    }
                });
            }
        });
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coordAxis.scale/=2.0;
                coordAxis.repaint();
            }
        });
        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coordAxis.scale*=2.0;
                coordAxis.repaint();
            }
        });
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(new Frame(), MainClient.stats.getString("info"), MainClient.stats.getString("Information"), JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            }
        });
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(new Frame(), MainClient.stats.getString("help"), MainClient.stats.getString("Help"), JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            }
        });
        textWithLogin.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                CurrentX = coordAxis.getX();
                CurrentY = coordAxis.getY();
                textWithLogin.setText(MainClient.stats.getString("Color of background is yours"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                textWithLogin.setText(MainClient.stats.getString("Login")+": " + MainClient.username);
            }
        });
    }

    class ForHousesListener implements MouseListener {
        Map.Entry<Integer, HouseMoving> x;
        ForHousesListener(Map.Entry<Integer, HouseMoving> x){
            this.x = x;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println(x);
            sum.setVisible(false);
            table.clearSelection();
            FlatFrame flatFrame = new FlatFrame(
                    x.getKey().toString(),
                    memoryMemoryFlats.get(x.getKey()).getName(),
                    memoryMemoryFlats.get(x.getKey()).getCoordinates().getX().toString(),
                    memoryMemoryFlats.get(x.getKey()).getCoordinates().getY().toString(),
                    memoryMemoryFlats.get(x.getKey()).getArea().toString(),
                    memoryMemoryFlats.get(x.getKey()).getNumberOfRooms().toString(),
                    memoryMemoryFlats.get(x.getKey()).getNewOrNot(),
                    memoryMemoryFlats.get(x.getKey()).getFurnish(),
                    memoryMemoryFlats.get(x.getKey()).getTransport(),
                    memoryMemoryFlats.get(x.getKey()).getHouse().getName(),
                    memoryMemoryFlats.get(x.getKey()).getHouse().getYear().toString(),
                    memoryMemoryFlats.get(x.getKey()).getHouse().getNumberOfFloors().toString()
            );
            flatFrame.updateIdMode(MainClient.memoryFlats.get(x.getKey()).getId(), x.getKey());
            //System.out.println(MainClient.username + " " + memoryMemoryFlats.get(x.getKey()).getUser());
            if(!MainClient.username.equals(memoryMemoryFlats.get(x.getKey()).getUser())) flatFrame.Uneditble();
            flatFrame.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class MoveListener extends MouseAdapter {

        private Point old;

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            sum.setVisible(false);
            table.clearSelection();
            old = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            try {
                super.mouseDragged(e);
                int X = coordAxis.getX() + e.getX() - (int)old.getX();
                int Y = coordAxis.getY() + e.getY() - (int)old.getY();
                if ((X>=0)||(X<=-1400)) X = coordAxis.getX();
                if ((Y>=0)||(Y<=-800)) Y = coordAxis.getY();
                coordAxis.setLocation(X, Y);
            } catch (NullPointerException e1){

            }

        }
    }
}
