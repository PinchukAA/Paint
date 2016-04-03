import javax.swing.*;

public class Main {
    Main(){
        Frame newFrame = new Frame();
        newFrame.show();
    }
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace();
        }
        new Main();
    }
}
