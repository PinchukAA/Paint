import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    JButton  btCLear, btRed, btOrange, btYellow, btGreen, btBlue,btMagenta, btGray, btBlack, btChColor ,btErase;
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
        JMenu hintSizeMenu = new JMenu("Размер кисти");
        hintMenu.add(hintSizeMenu);

        JMenu eraserMenu = new JMenu("Ластик");
        toolsMenu.add(eraserMenu);
        JMenu eraserSizeMenu= new JMenu("Размер ластика");
        eraserMenu.add(eraserSizeMenu);

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
        frame.setSize(1920, 1080);
      //  frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
