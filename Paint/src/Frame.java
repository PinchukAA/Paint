import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Frame {
    JButton btRed, btOrange, btYellow, btGreen, btBlue,btMagenta, btGray, btBlack;
    JButton btCLear, btChColor ,btErase, btLine, btRectangle, btOval, btHint, btText;

    JMenuItem x1Item, x2Item, x4Item, x8Item, cleanItem;
    Canvas canvas;
    Color c;
    String fileName;
    BufferedImage image;
    boolean packFrame = false;


    ActionListener colorActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btCLear){
                canvas.clear();
            } else if (e.getSource() == btRed){
                canvas.red();
            } else if (e.getSource() == btOrange){
                canvas.orange();
            } else if (e.getSource() == btYellow){
                canvas.yellow();
            } else if (e.getSource() == btGreen){
                canvas.green();
            } else if (e.getSource() == btBlue){
                canvas.blue();
            } else if (e.getSource() == btMagenta){
                canvas.magenta();
            } else if (e.getSource() == btGray){
                canvas.gray();
            } else if (e.getSource() == btBlack){
                canvas.black();
            } else if (e.getSource() == btErase){
                canvas.erase();
            }
        }
    };

    ActionListener toolsActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btHint){
                canvas.draw(false);
            } else if (e.getSource() == btLine) {
                canvas.drLine();
            } else if (e.getSource() == btRectangle) {
                canvas.drRectangle();
            } else if (e.getSource() == btOval) {
                canvas.drOval();
            }else if (e.getSource() == btText) {
                canvas.drText();
            } else if (e.getSource() == btErase) {
                canvas.erase();
            } else if (e.getSource() == btCLear || e.getSource() == cleanItem) {
                canvas.clear();
            }
        }
    };

    private class colorButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            c = JColorChooser.showDialog(((Component) e.getSource()).getParent(), "Выберите цвет!", c);
            btChColor.setBackground(c);
            canvas.chooseColor(c);
        }
    }

    ActionListener sizeHintActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == x1Item){
                canvas.x1();
            } else if (e.getSource() == x2Item) {
                canvas.x2();
            } else if (e.getSource() == x4Item) {
                canvas.x4();
            } else if (e.getSource() == x8Item) {
                canvas.x8();
            }
        }
    };

    public void show() {
        JFrame frame = new JFrame("Рисовалка");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        menuBar.setBounds(0, 0, 350, 30);

        JMenu menu = new JMenu("Меню");
        menuBar.add(menu);

        JMenu fileMenu = new JMenu("Файл");
        menu.add(fileMenu);
        JMenuItem fileOpenItem = new JMenuItem("Открыть");
        fileMenu.add(fileOpenItem);

        fileOpenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser openFileChooser = new JFileChooser();
                openFileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg"));
                int result = openFileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        fileName = openFileChooser.getSelectedFile().getAbsolutePath();
                        File file = new File(fileName);
                        image = ImageIO.read(file);
                        frame.setSize(image.getWidth() + 40, image.getHeight() + 80);
                        canvas.setImage(image);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Такого файла не существует!");
                    }
                }
            }
        });

        canvas = new Canvas();

        JMenuItem fileSaveItem = new JMenuItem("Сохранить как");
        fileMenu.add(fileSaveItem);

        fileSaveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveFileChooser = new JFileChooser();
                saveFileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg"));
                int result = saveFileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        fileName = saveFileChooser.getSelectedFile().getAbsolutePath();
                        ImageIO.write(canvas.getImage(), "jpeg", new File(fileName + ".jpg"));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Такого файла не существует!");
                    }
                }

            }
        });

        JMenu editMenu = new JMenu("Правка");
        menu.add(editMenu);

        JMenu copyMenu = new JMenu("Буфер обмена");
        editMenu.add(copyMenu);
        JMenuItem selectItem = new JMenuItem("Выделить");
        copyMenu.add(selectItem);
        JMenuItem copyItem = new JMenuItem("Копировать");
        copyMenu.add(copyItem);
        JMenuItem pasteItem = new JMenuItem("Вставить");
        copyMenu.add(pasteItem);

        JMenu toolsMenu = new JMenu("Инструменты");
        editMenu.add(toolsMenu);

        JMenu sizeMenu = new JMenu("Размер");
        toolsMenu.add(sizeMenu);
        JMenuItem colorItem = new JMenuItem("Цвет");
        toolsMenu.add(colorItem);
        colorItem.addActionListener(new colorButtonActionListener());
        JMenu hintSizeMenu = new JMenu("Кисть/ластик");
        sizeMenu.add(hintSizeMenu);


        x1Item = new JMenuItem("x1");
        hintSizeMenu.add(x1Item);
        x1Item.addActionListener(sizeHintActionListener);
        x2Item = new JMenuItem("x2");
        hintSizeMenu.add(x2Item);
        x2Item.addActionListener(sizeHintActionListener);
        x4Item = new JMenuItem("x4");
        hintSizeMenu.add(x4Item);
        x4Item.addActionListener(sizeHintActionListener);
        x8Item = new JMenuItem("x8");
        hintSizeMenu.add(x8Item);
        x8Item.addActionListener(sizeHintActionListener);

        JMenu textSizeMenu = new JMenu("Текст");
        sizeMenu.add(textSizeMenu);
        JMenu viewMenu = new JMenu("Вид");
        editMenu.add(viewMenu);
        JMenu increaseMenu = new JMenu("Увеличить изображение");
        viewMenu.add(increaseMenu);
        JMenu decreaseMenu = new JMenu("Уменьшить изображение");
        viewMenu.add(decreaseMenu);

        editMenu.addSeparator();

        cleanItem = new JMenuItem("Очистить поле");
        editMenu.add(cleanItem);
        cleanItem.addActionListener(toolsActionListener);

        menu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Выход");
        menu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        content.add(canvas, BorderLayout.CENTER);
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(1, 8));
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new GridLayout(7, 1));
//        JToolBar tools = new JToolBar("Инструменты", JToolBar.VERTICAL);

        btCLear = new JButton(new ImageIcon("clear.png"));
        btCLear.addActionListener(colorActionListener);

        btRed = new JButton();
        btRed.setBackground(Color.red);
        btRed.addActionListener(colorActionListener);

        btOrange = new JButton();
        btOrange.setBackground(Color.orange);
        btOrange.addActionListener(colorActionListener);

        btYellow = new JButton();
        btYellow.setBackground(Color.YELLOW);
        btYellow.addActionListener(colorActionListener);

        btGreen = new JButton();
        btGreen.setBackground(Color.green);
        btGreen.addActionListener(colorActionListener);

        btBlue = new JButton();
        btBlue.setBackground(Color.blue);
        btBlue.addActionListener(colorActionListener);

        btMagenta = new JButton();
        btMagenta.setBackground(Color.magenta);
        btMagenta.addActionListener(colorActionListener);

        btGray= new JButton();
        btGray.setBackground(Color.gray);
        btGray.addActionListener(colorActionListener);

        btBlack = new JButton();
        btBlack.setBackground(Color.black);
        btBlack.addActionListener(colorActionListener);

        btChColor = new JButton(new ImageIcon("color.png"));
        btChColor.setBackground(c);
        btChColor.addActionListener(new colorButtonActionListener());

        btHint = new JButton(new ImageIcon("pencil.png"));
        btHint.addActionListener(toolsActionListener);

        btErase = new JButton(new ImageIcon("eraser.png"));
        btErase.addActionListener(toolsActionListener);

        btLine= new JButton(new ImageIcon("line.png"));
        btLine.addActionListener(toolsActionListener);

        btRectangle= new JButton(new ImageIcon("rectangle.png"));
        btRectangle.addActionListener(toolsActionListener);

        btOval = new JButton(new ImageIcon("oval.png"));
        btOval.addActionListener(toolsActionListener);

        btText = new JButton(new ImageIcon("textBold.png"));
        btText.addActionListener(toolsActionListener);

        colorPanel.add(btChColor);
        colorPanel.add(btRed);
        colorPanel.add(btOrange);
        colorPanel.add(btYellow);
        colorPanel.add(btGreen);
        colorPanel.add(btBlue);
        colorPanel.add(btMagenta);
        colorPanel.add(btGray);
        colorPanel.add(btBlack);

        toolsPanel.add(btHint);
        toolsPanel.add(btLine);
        toolsPanel.add(btRectangle);
        toolsPanel.add(btOval);
        toolsPanel.add(btText);
        toolsPanel.add(btErase);
        toolsPanel.add(btCLear);



        content.add(colorPanel, BorderLayout.NORTH);
        content.add(toolsPanel, BorderLayout.WEST);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
