
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {
    private BufferedImage image;
    private Graphics g;

    Canvas(){
        draw(1);
        repaint();
    }
    public Graphics2D g2;
    private int curX, curY, exX, exY;
    public boolean drHint = false;
    public boolean drLine = false;
    public boolean drRect = false;
    public boolean drOval = false;

    public void draw(int a){
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY();
            }
        });

        switch(a) {
            case 1:
                drHint = true;
                drLine = false;
                drRect = false;
                drOval = false;

                addMouseMotionListener(new MouseMotionAdapter() {
                    public void mouseDragged(MouseEvent e) {

                        if(drHint){
                            curX = e.getX();
                            curY = e.getY();

                            if (g2 != null) {
                                g2.drawLine(exX, exY, curX, curY);
                                repaint();

                                exX = curX;
                                exY = curY;
                            }
                        }
                    }
                });
                break;
            case 2:
                black();
                drHint = false;
                drLine = true;
                drRect = false;
                drOval = false;

                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (drLine){
                            curX = e.getX();
                            curY = e.getY();
                            if (g2 != null) {
                                g2.drawLine(exX, exY, curX, curY);
                                repaint();

                                exX = curX;
                                exY = curY;
                            }
                        }

                    }
                });
                break;
            case 3:
                black();
                drHint = false;
                drLine = false;
                drRect = true;
                drOval = false;

                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (drRect) {
                            curX = e.getX();
                            curY = e.getY();
                            if (curX < exX) {
                                int tmp = curX;
                                curX = exX;
                                exX = tmp;
                            }
                            if (curY < exY) {
                                int tmp = curY;
                                curY = exY;
                                exY = tmp;
                            }

                            if (g2 != null) {
                                g2.drawRect(exX, exY, curX - exX, curY - exY);
                                repaint();

                                exX = curX;
                                exY = curY;
                            }
                        }
                    }
                });
                break;
            case 4:
                black();
                drHint = false;
                drLine = false;
                drRect = false;
                drOval = true;

                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (drOval) {
                            curX = e.getX();
                            curY = e.getY();
                            if (curX < exX) {
                                int tmp = curX;
                                curX = exX;
                                exX = tmp;
                            }
                            if (curY < exY) {
                                int tmp = curY;
                                curY = exY;
                                exY = tmp;
                            }

                            if (g2 != null) {
                                g2.drawOval(exX, exY, curX - exX, curY - exY);
                                repaint();

                                exX = curX;
                                exY = curY;
                            }
                        }
                    }
                });
                break;
        }
    }

    protected void paintComponent(Graphics g){
        if (image == null) {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    public void setImage(BufferedImage image){
        this.image = image;
        g2 = (Graphics2D) image.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.black);
        getGraphics().drawImage(image, 0, 0, null);
        repaint();
    }

    public void clear() {
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, 1920, 1080);
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
        draw(1);
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
