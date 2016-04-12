
import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel implements Scrollable{
    private BufferedImage image, croppedImage, tempImage, tempCroppedImage;
    private Shape curShape;
    private Graphics2D g2;
    private Image cursorImage;
    private Toolkit toolkit;
    private Cursor cursor;
    private Point point;
    private int curX, curY, exX, exY;
    private Color color;
    private GeneralPath path;

    Canvas(){
        setPreferredSize(new Dimension(1920, 1080));
        point = new Point(0, 0);

        toolkit = Toolkit.getDefaultToolkit();

        setCursor(Cursor.getDefaultCursor());
        color = Color.black;

        draw(false);

        repaint();
    }

    public void draw(boolean b) {
            if (g2 != null) {
                g2.setPaint(color);
                cursorImage = toolkit.getImage("cursorPencil.png");
                cursor = toolkit.createCustomCursor(cursorImage, point, "cursorPencil");

            if (b) {
                cursorImage = toolkit.getImage("cursorEraser.png");
                cursor = toolkit.createCustomCursor(cursorImage, point, "cursorEraser");
                g2.setPaint(Color.white);
            }
        }

        setCursor(cursor);
        curShape = null;
        repaint();

        setDoubleBuffered(false);
        removeListeners();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY() + 30;
                requestFocus();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                curX = e.getX();
                curY = e.getY() + 30;

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

        g2.setPaint(color);
        cursorImage = toolkit.getImage("cursorPencil.png");
        cursor = toolkit.createCustomCursor(cursorImage, point, "cursorPencil");
        setCursor(cursor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY() + 30;
                requestFocus();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                curX = e.getX();
                curY = e.getY() + 30;
                if (g2 != null) {

                    g2.drawLine(exX, exY, curX, curY);
                    curShape = null;
                    repaint();
                }

            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                curX = e.getX();
                curY = e.getY() + 30;

                curShape = new Line2D.Double(exX, exY, curX, curY);
                repaint();
            }
        });

    }

    public void drRectangle(){
        setDoubleBuffered(false);
        removeListeners();

        g2.setPaint(color);
        cursorImage = toolkit.getImage("cursorPencil.png");
        cursor = toolkit.createCustomCursor(cursorImage, point, "cursorPencil");
        setCursor(cursor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY() + 30;
                requestFocus();
            }
        });


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                curX = e.getX();
                curY = e.getY() + 30;

                if (g2 != null) {
                    g2.drawRect(Math.min(exX, curX), Math.min(exY, curY), Math.abs(curX - exX), Math.abs(curY - exY));
                    curShape = null;
                    repaint();
                }

            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                curX = e.getX();
                curY = e.getY() + 30;

                curShape = new Rectangle2D.Double(Math.min(exX, curX), Math.min(exY, curY), Math.abs(curX - exX), Math.abs(curY - exY));
                repaint();
            }
        });
    }

    public void drOval(){
        setDoubleBuffered(false);
        removeListeners();

        g2.setPaint(color);
        cursorImage = toolkit.getImage("cursorPencil.png");
        cursor = toolkit.createCustomCursor(cursorImage, point, "cursorPencil");
        setCursor(cursor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY() + 30;
                requestFocus();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                curX = e.getX();
                curY = e.getY() + 30;

                if (g2 != null) {
                    g2.drawOval(Math.min(exX, curX), Math.min(exY, curY), Math.abs(curX - exX), Math.abs(curY - exY));
                    curShape = null;

                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                curX = e.getX();
                curY = e.getY() + 30;

                curShape = new Ellipse2D.Double(Math.min(exX, curX), Math.min(exY, curY), Math.abs(curX - exX), Math.abs(curY - exY));
                repaint();
            }
        });
    }

    public void drText(){
        setDoubleBuffered(false);
        removeListeners();

        g2.setPaint(color);
        cursorImage = toolkit.getImage("cursorBold.png");
        cursor = toolkit.createCustomCursor(cursorImage, point, "cursorBold");
        setCursor(cursor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY();
                requestFocus();

                curShape = null;
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                curX = e.getX();
                curY = e.getY();

                curShape = new Rectangle2D.Double(exX, exY, curX - exX, 35);
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (exX < curX - 30) {
                    requestFocus();
                    String text = new String();

                    char ch = e.getKeyChar();
                    int chWidth = g2.getFontMetrics().charWidth(ch);
                    text += ch;

                    g2.setFont(new Font("Arial", 0, 30));
                    g2.drawString(text, exX + 10, exY + 30);
                    exX += (chWidth + 5);

                    repaint();
                }
            }
        });
    }

    public void selectImage(){
        setDoubleBuffered(false);
        removeListeners();

        cursorImage = toolkit.getImage("cursorSelect.png");
        cursor = toolkit.createCustomCursor(cursorImage, point, "cursorSelect");
        setCursor(cursor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY() + 30;
                requestFocus();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                curX = e.getX();
                curY = e.getY() + 30;

                curShape = new Rectangle2D.Double(Math.min(exX, curX), Math.min(exY, curY), Math.abs(curX - exX), Math.abs(curY - exY));
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                curX = e.getX();
                curY = e.getY() + 30;

                tempImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                tempImage.setData(image.getRaster());
                tempCroppedImage = tempImage.getSubimage(Math.min(exX, curX), Math.min(exY, curY), Math.abs(curX - exX), Math.abs(curY - exY));
            }
        });
    }



    public void selectFreeImage(){
        setDoubleBuffered(false);
        removeListeners();

        cursorImage = toolkit.getImage("cursorSelect.png");
        cursor = toolkit.createCustomCursor(cursorImage, point, "cursorSelect");
        setCursor(cursor);
        g2.setPaint(color);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exX = e.getX();
                exY = e.getY() + 30;

                path = new GeneralPath();
                path.moveTo(exX, exY);
                curShape = path;

                requestFocus();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                curX = e.getX();
                curY = e.getY() + 30;

                path.lineTo(curX, curY);

                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                path.closePath();

                tempImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
                tempImage.setData(image.getRaster());

                Graphics2D gTemp = tempImage.createGraphics();

                Area pathArea = new Area(path);
                Area appendArea = new Area(path.getBounds());
                Area resultArea = new Area();
                resultArea.add(appendArea);
                resultArea.subtract(pathArea);
                gTemp.setComposite(AlphaComposite.Clear);
                gTemp.fill(resultArea);

                tempCroppedImage = tempImage.getSubimage((int) path.getBounds().getX(),
                        (int) path.getBounds().getY(),
                        (int) path.getBounds().getWidth(),
                        (int) path.getBounds().getHeight());

                repaint();
            }
        });

    }

    public void cutImage(){
        setDoubleBuffered(false);
        removeListeners();
        setCursor(Cursor.getDefaultCursor());

        color = g2.getColor();

        if(croppedImage == null || croppedImage != tempCroppedImage) {

            croppedImage = tempCroppedImage;
            g2.setColor(Color.white);

            if (path != null) {
                g2.fill(path);
                path = null;
            } else g2.fillRect(Math.min(exX, curX), Math.min(exY, curY), Math.abs(curX - exX), Math.abs(curY - exY));
            curShape = null;
        }
        repaint();
        g2.setColor(color);
    }



    public void copyImage(){
        setDoubleBuffered(false);
        removeListeners();
        setCursor(Cursor.getDefaultCursor());

        croppedImage = tempCroppedImage;
        path = null;
        curShape = null;

        repaint();
    }

    public void pasteImage(){
        setDoubleBuffered(false);
        removeListeners();

        cursorImage = toolkit.getImage("cursorPaste.png");
        cursor = toolkit.createCustomCursor(cursorImage, point, "cursorPaste");
        setCursor(cursor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocus();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                curX = e.getX();
                curY = e.getY();
                if(croppedImage != null) {
                    g2.drawImage(croppedImage, curX, curY, null);
                    repaint();
                }
            }
        });
    }

    public void zoomInImage() {
        removeListeners();
        curShape = null;
        setDoubleBuffered(false);
        setCursor(Cursor.getDefaultCursor());
        color = g2.getColor();

        setPreferredSize(new Dimension(image.getWidth() *2, image.getHeight() * 2));
        Image scaledImage = image.getScaledInstance(image.getWidth()*2, image.getHeight()*2, Image.SCALE_AREA_AVERAGING);

        image = new BufferedImage(getWidth() * 2 , getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.drawImage(scaledImage, 0, 0, null);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);

        repaint();
    }

    public void zoomOutImage() {
        removeListeners();
        curShape = null;
        setDoubleBuffered(false);
        setCursor(Cursor.getDefaultCursor());
        color = g2.getColor();

        setPreferredSize(new Dimension(image.getWidth() / 2, image.getHeight() / 2));
        Image scaledImage = image.getScaledInstance(image.getWidth() / 2, image.getHeight() / 2, Image.SCALE_AREA_AVERAGING);

        image = new BufferedImage(getWidth() / 2 , getHeight() / 2, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.drawImage(scaledImage, 0, 0, null);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(color);

        repaint();
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
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, this);

        if(curShape!=null){
            g.setColor(Color.blue);
            ((Graphics2D)(g)).draw(curShape);
        }
    }

    public void setImage(BufferedImage setImage){
        g2.drawImage(setImage, 0, 0, null);
        repaint();
    }

    public BufferedImage getImage(){
        return image;
    }

    public void clear() {
        removeListeners();
        setCursor(Cursor.getDefaultCursor());
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setPaint(color);
        curShape = null;
        repaint();
    }

    public void red() {
        g2.setPaint(Color.red);
        color = g2.getColor();
    }

    public void orange() {
        g2.setPaint(Color.orange);
        color = g2.getColor();
    }

    public void yellow() {
        g2.setPaint(Color.yellow);
        color = g2.getColor();
    }

    public void green() {
        g2.setPaint(Color.green);
        color = g2.getColor();
    }

    public void blue() {
        g2.setPaint(Color.blue);
        color = g2.getColor();
    }

    public void magenta() {
        g2.setPaint(Color.magenta);
        color = g2.getColor();
    }

    public void gray() {
        g2.setPaint(Color.gray);
        color = g2.getColor();
    }

    public void black() {
        g2.setPaint(Color.black);
        color = g2.getColor();
    }

    public void chooseColor(Color c) {
        g2.setPaint(c);
        color = g2.getColor();
    }

    public void erase(){
        color = g2.getColor();
        draw(true);
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

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 5;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 5;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}
