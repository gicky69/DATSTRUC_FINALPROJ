import javax.swing.*;
import java.awt.*;

public class Main implements Runnable {
    JFrame frame;
    Thread GameThread;
    GamePanel GamePanel;
    public Main() {
        frame = new JFrame("Final Test");
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        GamePanel = new GamePanel();
        GamePanel.setSize(1920, 1080);

        start();
    }

    public void start() {
        GameThread = new Thread(this);
        GameThread.start();
    }
    public void run() {
        while (true){

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}