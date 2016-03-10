
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {
    public BufferedImage image;
    private Graphics g;

    Canvas(){
        draw();
        repaint();
    }
    public Graphics2D g2;
    private int curX, curY, exX, exY;

    public void draw(){
        setDoubleBuffered(false);
        removeListeners();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY();
                requestFocus();
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

    public void drLine(){
        setDoubleBuffered(false);
        removeListeners();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY();
                requestFocus();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
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

    public void drRectangle(){
        setDoubleBuffered(false);
        removeListeners();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY();
                requestFocus();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
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
        });
    }

    public void drOval(){
        setDoubleBuffered(false);
        removeListeners();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY();
                requestFocus();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
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
        });
    }

    public void drText(){
        setDoubleBuffered(false);
        removeListeners();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY();
                requestFocus();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                requestFocus();
                String text = new String("");
                text+=e.getKeyChar();
                g2.setFont(new Font("Arial", 0, 15));
                g2.drawString(text, exX, exY);
                exX += 10;

                repaint();
            }
        });
    }


    public void removeListeners(){
        MouseListener[] l1= getMouseListeners();
        for(MouseListener i: l1){
            removeMouseListener(i);
        }
        MouseMotionListener[] l2 = getMouseMotionListeners();
        for(MouseMotionListener i: l2){
            removeMouseMotionListener(i);
        }
        KeyListener[] l3 = getKeyListeners();
        for(KeyListener i: l3){
            removeKeyListener(i);
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

    public BufferedImage getImage(){
        return image;
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
        draw();
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
