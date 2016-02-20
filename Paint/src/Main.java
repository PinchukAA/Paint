import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    JButton btCLear, btBlack, btBlue, btGreen, btRed;
    Paper paper;
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btCLear){
                paper.clear();
            }
            else if (e.getSource() == btBlack){
                paper.black();
            }
            else if (e.getSource() == btBlue){
                paper.blue();
            }
            else if (e.getSource() == btRed){
                paper.red();
            }
            else if (e.getSource() == btGreen){
                paper.green();
            }
        }
    };

    public static void main(String[] args){
        new Main().show();
    }

    public void show() {
        JFrame frame = new JFrame("Paint");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        paper = new Paper();

        content.add(paper, BorderLayout.CENTER);
        JPanel tools = new JPanel();

        btCLear = new JButton("Clear");
        btCLear.addActionListener(actionListener);
        btBlack = new JButton("Black");
        btBlack.addActionListener(actionListener);
        btBlue = new JButton("Blue");
        btBlue.addActionListener(actionListener);
        btRed = new JButton("Red");
        btRed.addActionListener(actionListener);
        btGreen = new JButton("Green");
        btGreen.addActionListener(actionListener);

        tools.add(btCLear);
        tools.add(btBlack);
        tools.add(btBlue);
        tools.add(btRed);
        tools.add(btGreen);


        content.add(tools, BorderLayout.NORTH);
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
