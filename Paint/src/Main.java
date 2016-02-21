import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Main {
    JButton  btCLear, btRed, btOrange, btYellow, btGreen, btBlue,btMagenta, btGray, btBlack, btChColor ,btErase;
    JMenuItem x1Item, x2Item, x4Item, x8Item, x1ErItem, x2ErItem, x4ErItem, x8ErItem;
    Paper paper;
    Color c;
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btCLear){
                paper.clear();
            } else if (e.getSource() == btRed){
                paper.red();
            } else if (e.getSource() == btOrange){
                paper.orange();
            } else if (e.getSource() == btYellow){
                paper.yellow();
            } else if (e.getSource() == btGreen){
                paper.green();
            } else if (e.getSource() == btBlue){
                paper.blue();
            } else if (e.getSource() == btMagenta){
                paper.magenta();
            } else if (e.getSource() == btGray){
                paper.gray();
            } else if (e.getSource() == btBlack){
                paper.black();
            } else if (e.getSource() == btErase){
                paper.erase();
            }
        }
    };

    private class colorButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            c = JColorChooser.showDialog(((Component) e.getSource()).getParent(), "Выберите цвет!", c);
            paper.chooseColor(c);
        }
    }

    ActionListener sizeHintActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == x1Item){
                paper.x1();
            } else if (e.getSource() == x2Item) {
                paper.x2();
            } else if (e.getSource() == x4Item) {
                paper.x4();
            } else if (e.getSource() == x8Item) {
                paper.x8();
            } else if (e.getSource() == x1ErItem){
                paper.x1();
            } else if (e.getSource() == x2ErItem) {
                paper.x2();
            } else if (e.getSource() == x4ErItem) {
                paper.x4();
            } else if (e.getSource() == x8ErItem) {
                paper.x8();
            }
        }
    };

    public static void main(String[] args){
        new Main().show();
    }

    public void show() {
        JFrame frame = new JFrame("Рисовалка");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        paper = new Paper();

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

        content.add(paper, BorderLayout.CENTER);
        JPanel tools = new JPanel();

        btCLear = new JButton("Очистить");
        btCLear.addActionListener(actionListener);

        btRed = new JButton();
        btRed.setBackground(Color.red);
        btRed.addActionListener(actionListener);

        btOrange = new JButton();
        btOrange.setBackground(Color.orange);
        btOrange.addActionListener(actionListener);

        btYellow = new JButton();
        btYellow.setBackground(Color.YELLOW);
        btYellow.addActionListener(actionListener);

        btGreen = new JButton();
        btGreen.setBackground(Color.green);
        btGreen.addActionListener(actionListener);

        btBlue = new JButton();
        btBlue.setBackground(Color.blue);
        btBlue.addActionListener(actionListener);

        btMagenta = new JButton();
        btMagenta.setBackground(Color.magenta);
        btMagenta.addActionListener(actionListener);

        btGray= new JButton();
        btGray.setBackground(Color.gray);
        btGray.addActionListener(actionListener);

        btBlack = new JButton();
        btBlack.setBackground(Color.black);
        btBlack.addActionListener(actionListener);

        btChColor = new JButton("Цвет");
        btChColor.setBackground(Color.cyan);
        btChColor.addActionListener(new colorButtonActionListener());

        btErase = new JButton("Ластик");
        btErase.addActionListener(actionListener);

        tools.add(btCLear);
        tools.add(btRed);
        tools.add(btOrange);
        tools.add(btYellow);
        tools.add(btGreen);
        tools.add(btBlue);
        tools.add(btMagenta);
        tools.add(btGray);
        tools.add(btBlack);
        tools.add(btChColor);
        tools.add(btErase);

        content.add(tools, BorderLayout.NORTH);
        frame.setSize(800, 600);
      //  frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
