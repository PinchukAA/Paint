import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame {
    JButton btRed, btOrange, btYellow, btGreen, btBlue,btMagenta, btGray, btBlack;
    JButton btCLear, btChColor ,btErase, btLine, btRectangle, btOval, btHint;

    JMenuItem x1Item, x2Item, x4Item, x8Item, x1ErItem, x2ErItem, x4ErItem, x8ErItem;
    Canvas canvas;
    Color c;

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
                canvas.draw(1);
            } else if (e.getSource() == btLine) {
                canvas.draw(2);
            } else if (e.getSource() == btRectangle) {
                canvas.draw(3);
            } else if (e.getSource() == btOval) {
                canvas.draw(4);
            } else if (e.getSource() == btErase) {
                canvas.erase();
            } else if (e.getSource() == btCLear) {
                canvas.clear();
            }
        }
    };

    private class colorButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            c = JColorChooser.showDialog(((Component) e.getSource()).getParent(), "Выберите цвет!", c);
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
            } else if (e.getSource() == x1ErItem){
                canvas.x1();
            } else if (e.getSource() == x2ErItem) {
                canvas.x2();
            } else if (e.getSource() == x4ErItem) {
                canvas.x4();
            } else if (e.getSource() == x8ErItem) {
                canvas.x8();
            }
        }
    };

    public void show() {
        JFrame frame = new JFrame("Рисовалка");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        canvas = new Canvas();
        canvas.draw(1);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        menuBar.setBounds(0, 0, 350, 30);

        JMenu menu = new JMenu("Меню");
        menuBar.add(menu);

        JMenu fileMenu = new JMenu("Файл");
        menu.add(fileMenu);
        JMenuItem fileOpenItem = new JMenuItem("Открыть");
        fileMenu.add(fileOpenItem);
        JMenuItem fileSaveItem = new JMenuItem("Сохранить как");
        fileMenu.add(fileSaveItem);



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

        JMenu hintMenu = new JMenu("Кисть");
        toolsMenu.add(hintMenu);
        JMenuItem colorItem = new JMenuItem("Цвет кисти");
        hintMenu.add(colorItem);
        colorItem.addActionListener(new colorButtonActionListener());
        JMenu hintSizeMenu = new JMenu("Размер кисти");
        hintMenu.add(hintSizeMenu);


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

        JMenu eraserMenu = new JMenu("Ластик");
        toolsMenu.add(eraserMenu);
        JMenu eraserSizeMenu= new JMenu("Размер ластика");
        eraserMenu.add(eraserSizeMenu);


        x1ErItem = new JMenuItem("x1");
        eraserSizeMenu.add(x1ErItem);
        x1ErItem.addActionListener(sizeHintActionListener);
        x2ErItem = new JMenuItem("x2");
        eraserSizeMenu.add(x2ErItem);
        x2ErItem.addActionListener(sizeHintActionListener);
        x4ErItem = new JMenuItem("x4");
        eraserSizeMenu.add(x4ErItem);
        x4ErItem.addActionListener(sizeHintActionListener);
        x8ErItem = new JMenuItem("x8");
        eraserSizeMenu.add(x8ErItem);
        x8Item.addActionListener(sizeHintActionListener);


        JMenu textMenu = new JMenu("Текст");
        toolsMenu.add(textMenu);
        JMenu textSizeMenu = new JMenu("Размер текста");
        textMenu.add(textSizeMenu);
        JMenu viewMenu = new JMenu("Вид");
        editMenu.add(viewMenu);
        JMenu increaseMenu = new JMenu("Увеличить изображение");
        viewMenu.add(increaseMenu);
        JMenu decreaseMenu = new JMenu("Уменьшить изображение");
        viewMenu.add(decreaseMenu);

        editMenu.addSeparator();

        JMenuItem cleanItem = new JMenuItem("Очистить поле");
        editMenu.add(cleanItem);

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
        toolsPanel.setLayout(new GridLayout(6, 1));
//        JToolBar tools = new JToolBar("Инструменты", JToolBar.VERTICAL);

        btCLear = new JButton("Очистить");
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

        btChColor = new JButton("Цвет");
        btChColor.setBackground(c);
        btChColor.addActionListener(new colorButtonActionListener());

        btHint = new JButton("Кисть");
        btHint.addActionListener(toolsActionListener);

        btErase = new JButton("Ластик");
        btErase.addActionListener(toolsActionListener);

        btLine= new JButton("Прямая");
        btLine.addActionListener(toolsActionListener);

        btRectangle= new JButton("Прямоугольник");
        btRectangle.addActionListener(toolsActionListener);

        btOval = new JButton("Овал");
        btOval.addActionListener(toolsActionListener);

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
        toolsPanel.add(btErase);
        toolsPanel.add(btCLear);


        content.add(colorPanel, BorderLayout.NORTH);
        content.add(toolsPanel, BorderLayout.WEST);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}