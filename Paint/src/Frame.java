
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

public class Frame {
    Canvas canvas;
    Color c;
    String fileName;
    BufferedImage image;
    JButton btChColor;
    JMenuItem increaseItem, decreaseItem;
    static JFrame frame;
    int count = 0;

    public void show() {
        frame = new JFrame("Рисовалка");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        menuBar.setBounds(0, 0, 350, 30);

        JMenu menu = new JMenu("Меню");
        menu.setBackground(Color.white);
        menuBar.add(menu);

        JMenu fileMenu = new JMenu("Файл");
        menu.add(fileMenu);
        JMenuItem fileOpenItem = new JMenuItem("Открыть");
        fileMenu.add(fileOpenItem);

        fileOpenItem.addActionListener(e -> {
            JFileChooser openFileChooser = new JFileChooser();
            openFileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg"));
            int result = openFileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    fileName = openFileChooser.getSelectedFile().getAbsolutePath();
                    File file = new File(fileName);
                    image = ImageIO.read(file);
                    frame.setSize(image.getWidth(), image.getHeight());
                    canvas.setImage(image);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Такого файла не существует!");
                }
            }
        });

        canvas = new Canvas();

        JMenuItem fileSaveItem = new JMenuItem("Сохранить как");
        fileMenu.add(fileSaveItem);

        fileSaveItem.addActionListener(e -> {
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
        });

        JMenu editMenu = new JMenu("Правка");
        menu.add(editMenu);

        JMenu copyMenu = new JMenu("Буфер обмена");
        editMenu.add(copyMenu);

        JMenuItem selectItem = new JMenuItem("Выделить");
        copyMenu.add(selectItem);
        selectItem.addActionListener(e -> canvas.selectImage());

        JMenuItem cutItem = new JMenuItem("Вырезать");
        copyMenu.add(cutItem);
        cutItem.addActionListener(e -> canvas.cutImage());

        JMenuItem copyItem = new JMenuItem("Копировать");
        copyMenu.add(copyItem);
        copyItem.addActionListener(e -> canvas.copyImage());

        JMenuItem pasteItem = new JMenuItem("Вставить");
        copyMenu.add(pasteItem);
        pasteItem.addActionListener(e -> canvas.pasteImage());

        JMenu toolsMenu = new JMenu("Инструменты");
        editMenu.add(toolsMenu);

        JMenu hintSizeMenu = new JMenu("Размер кисти/ластика");
        toolsMenu.add(hintSizeMenu);

        JMenuItem colorItem = new JMenuItem("Цвет кисти");
        toolsMenu.add(colorItem);
        colorItem.addActionListener(e -> {
            c = JColorChooser.showDialog(((Component) e.getSource()).getParent(), "Выберите цвет!", c);
            btChColor.setBackground(c);
            canvas.chooseColor(c);
        });

        JMenuItem x1Item = new JMenuItem("x1");
        hintSizeMenu.add(x1Item);
        x1Item.addActionListener(e -> canvas.x1());
        JMenuItem x2Item = new JMenuItem("x2");
        hintSizeMenu.add(x2Item);
        x2Item.addActionListener(e -> canvas.x2());
        JMenuItem x4Item = new JMenuItem("x4");
        hintSizeMenu.add(x4Item);
        x4Item.addActionListener(e -> canvas.x4());
        JMenuItem x8Item = new JMenuItem("x8");
        hintSizeMenu.add(x8Item);
        x8Item.addActionListener(e -> canvas.x8());

        JMenu viewMenu = new JMenu("Вид");
        editMenu.add(viewMenu);
        increaseItem = new JMenuItem("Увеличить изображение");
        viewMenu.add(increaseItem);
        increaseItem.addActionListener(e -> {
            canvas.zoomInImage();
            increaseItem.setVisible(false);
            decreaseItem.setVisible(true);
        });

        decreaseItem = new JMenuItem("Уменьшить изображение");
        viewMenu.add(decreaseItem);
        decreaseItem.addActionListener(e -> {
            canvas.zoomOutImage();
            decreaseItem.setVisible(false);
            increaseItem.setVisible(true);
        });
        decreaseItem.setVisible(false);


        editMenu.addSeparator();

        JMenuItem cleanItem = new JMenuItem("Очистить поле");
        editMenu.add(cleanItem);
        cleanItem.addActionListener(e -> canvas.clear());

        menu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Выход");
        menu.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));

        content.add(canvas, BorderLayout.CENTER);
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(1, 8));

        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new GridLayout(12, 1));

        JButton btCLear = new JButton(new ImageIcon("clear.png"));
        btCLear.setBackground(Color.white);
        btCLear.addActionListener(e -> canvas.clear());

        JButton btRed = new JButton();
        btRed.setBackground(Color.red);
        btRed.addActionListener(e -> canvas.red());

        JButton btOrange = new JButton();
        btOrange.setBackground(Color.orange);
        btOrange.addActionListener(e -> canvas.orange());

        JButton btYellow = new JButton();
        btYellow.setBackground(Color.YELLOW);
        btYellow.addActionListener(e -> canvas.yellow());

        JButton btGreen = new JButton();
        btGreen.setBackground(Color.green);
        btGreen.addActionListener(e -> canvas.green());

        JButton btBlue = new JButton();
        btBlue.setBackground(Color.blue);
        btBlue.addActionListener(e -> canvas.blue());

        JButton btMagenta = new JButton();
        btMagenta.setBackground(Color.magenta);
        btMagenta.addActionListener(e -> canvas.magenta());

        JButton btGray= new JButton();
        btGray.setBackground(Color.gray);
        btGray.addActionListener(ce -> canvas.gray());

        JButton btBlack = new JButton();
        btBlack.setBackground(Color.black);
        btBlack.addActionListener(e -> canvas.black());

        btChColor = new JButton(new ImageIcon("color.png"));
        btChColor.setBackground(Color.white);
        btChColor.addActionListener(e -> {
            c = JColorChooser.showDialog(((Component) e.getSource()).getParent(), "Выберите цвет!", c);
            btChColor.setBackground(c);
            canvas.chooseColor(c);
        });

        JButton btHint = new JButton(new ImageIcon("pencil.png"));
        btHint.setBackground(Color.white);
        btHint.addActionListener(e -> canvas.draw(false));

        JButton btErase = new JButton(new ImageIcon("eraser.png"));
        btErase.setBackground(Color.white);
        btErase.addActionListener(e -> canvas.erase());

        JButton btLine= new JButton(new ImageIcon("line.png"));
        btLine.setBackground(Color.white);
        btLine.addActionListener(e -> canvas.drLine());

        JButton btRectangle= new JButton(new ImageIcon("rectangle.png"));
        btRectangle.setBackground(Color.white);
        btRectangle.addActionListener(e -> canvas.drRectangle());

        JButton btOval = new JButton(new ImageIcon("oval.png"));
        btOval.setBackground(Color.white);
        btOval.addActionListener(e -> canvas.drOval());

        JButton btText = new JButton(new ImageIcon("bold.png"));
        btText.setBackground(Color.white);
        btText.addActionListener(e -> canvas.drText());

        JButton btZoom = new JButton(new ImageIcon("zoom.png"));
        btZoom.setBackground(Color.white);
        btZoom.addActionListener(e -> {
            if (count % 2 == 0) {
                canvas.zoomInImage();
                increaseItem.setVisible(false);
                decreaseItem.setVisible(true);
            }
            else {
                canvas.zoomOutImage();
                increaseItem.setVisible(true);
                decreaseItem.setVisible(false);
            }
            count++;
        });

        JButton btSelect = new JButton(new ImageIcon("select.png"));
        btSelect.setBackground(Color.white);
        btSelect.addActionListener(e -> canvas.selectImage());

        JButton btCut = new JButton(new ImageIcon("cut.png"));
        btCut.setBackground(Color.white);
        btCut.addActionListener(e -> canvas.cutImage());

        JButton btCopy = new JButton(new ImageIcon("copy.png"));
        btCopy.setBackground(Color.white);
        btCopy.addActionListener(e -> canvas.copyImage());

        JButton btPaste = new JButton(new ImageIcon("paste.png"));
        btPaste.setBackground(Color.white);
        btPaste.addActionListener(e -> canvas.pasteImage());


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
        toolsPanel.add(btZoom);
        toolsPanel.add(btSelect);
        toolsPanel.add(btCut);
        toolsPanel.add(btCopy);
        toolsPanel.add(btPaste);
        toolsPanel.add(btCLear);


        content.add(colorPanel, BorderLayout.NORTH);
        content.add(toolsPanel, BorderLayout.WEST);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JScrollPane canvasScrollPane = new JScrollPane(canvas);
        canvasScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        canvasScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(canvasScrollPane);
        frame.setSize(screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
