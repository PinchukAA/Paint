
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Paper extends JComponent {

    private Image image;
    private Graphics2D g2;
    private int curX, curY, exX, exY;
    public Paper(){
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                curX = e.getX();
                curY = e.getY();

                if (g2 != null) {
                    g2.drawLine(exX, exY, curX, curY);
                    repaint();

                    exX = curX;
                    exY = curY;
                }
            }
        });
    }

    protected void paintComponent(Graphics g){
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }

    public void red() {
        g2.setPaint(Color.red);
    }

    public void orange() {
        g2.setPaint(Color.orange);
    }

    public void yellow() {
        g2.setPaint(Color.yellow);
    }

    public void green() {
        g2.setPaint(Color.green);
    }

    public void blue() {
        g2.setPaint(Color.blue);
    }

    public void magenta() {
        g2.setPaint(Color.magenta);
    }

    public void gray() {
        g2.setPaint(Color.gray);
    }

    public void black() {
        g2.setPaint(Color.black);
    }

    public void chooseColor(Color c) {
        g2.setPaint(c);
    }

    public void erase(){
        g2.setPaint(Color.white);
    }

    public void x1(){
        g2.setStroke(new BasicStroke(1));
    }

    public void x2(){
        g2.setStroke(new BasicStroke(2));
    }

    public void x4(){
        g2.setStroke(new BasicStroke(4));
    }

    public void x8(){
        g2.setStroke(new BasicStroke(8));
    }

}
