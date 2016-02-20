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
        frame.setSize(600, 600);
      //  frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
