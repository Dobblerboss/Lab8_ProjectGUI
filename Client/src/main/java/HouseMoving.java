import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class HouseMoving extends JComponent implements ActionListener {
    public double scale;
    private Color color;
    public Timer timer;
    int delay;
    Long floor;
    public int x =10;
    public int  y =90;
    public float XCoord;
    public float YCoord;
    boolean clear = false;
    boolean otrisovkaTimer = false;
    boolean mojnoRisovatDrugim = true;
    double width;
    double height;
    double divfloor;

    public HouseMoving(Color color, int delay, Long floor) {
        this.delay = delay;
        this.floor = floor;
        scale = 1.0;
        timer = new Timer(delay, this);
        this.color = color;
        divfloor = floor;
        width = 60*(1+divfloor/10);
        height = 70*(1+divfloor/10);
        setPreferredSize(new Dimension((int)width, (int)height));
    }


    public void setCoord(float XCoord, float YCoord){
        this.XCoord = XCoord;
        this.YCoord = YCoord;
    }
    public void restart(){
        x = 10;
        y = 90;
        scale = 1.0;
        clear = false;
        delay = 18;
        start();
    }
    public void start() {

        mojnoRisovatDrugim = false;
        timer.start();
    }

    public void stop() {
        mojnoRisovatDrugim = true;
        timer.stop();
    }
    public void clear(){
        x = 10;
        y = 25;
        clear = true;
        scale = 1.0;
        timer.setDelay(15);
        start();
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if((y <= 55)&&(y>40)&&(!clear)){
            delay+=1;
            timer.setDelay(delay);
        }
        if((y <= 40)&&(y>25)&&(!clear)){
            delay+=2;
            timer.setDelay(delay);
        }
        if((y <= 25)&&(timer.isRunning())&&(!clear)){
            //System.out.println("Всё");
            clear = true;
            timer.setDelay(15);
            stop();
        }
        if(scale<=0.0){
            stop();
        }
        otrisovkaTimer = true;
        this.repaint();
        //System.out.println("Fkkf");
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        BasicStroke с = new BasicStroke(2); //толщина линии 3  многоугольника
        g2d.setStroke(с);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(!otrisovkaTimer) {
            if (!mojnoRisovatDrugim) {
                //System.out.println("Разве?");
            }
        } else {
            if ((clear) && (timer.isRunning())) {
                x += 2;
                y += 2;
                scale -= 0.02;
            }
            if(!clear){
                y--;
            }
        }
        otrisovkaTimer = false;


        if ((scale >= 0.05)&&(clear)&&(timer.isRunning())) {
            g2d.setColor(Color.black);
            g2d.fillOval(0, 0, 20, 20);
        }


        g2d.setColor(color);
        g2d.scale(scale*(1+(divfloor/10)),scale*(1+(divfloor/10)));
        g2d.drawLine( x, y, x+20, y-20);
        g2d.drawLine(x+20, y-20, x+40, y);
        g2d.fillRect(x, y, 40, 40);
        g2d.setColor(Color.white);
        g2d.drawRoundRect(x+10,y+10,20,20,10,10);
        g2d.drawLine(x+20,y+10,x+20,y+30);
        g2d.drawLine(x+10,y+20,x+30,y+20);

    }
}
