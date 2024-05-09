import javax.swing.*;

public class Main implements Runnable {
    JFrame Frame;
    TestPanel TestPanel;
    Thread GameThread;

    public Main() {
        Frame = new JFrame("DATSTRUC_FINALPROJ");
        Frame.setSize(1920,1080);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TestPanel = new TestPanel();

        Frame.setVisible(true);
        Frame.add(TestPanel);

        start();
    }

    public void start() {
        GameThread = new Thread(this);
        GameThread.start();
    }

    public void run() {
        while(true) {
            TestPanel.moveCars();
            try {
                Thread.sleep(1000/60);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}