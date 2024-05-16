import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestPanel extends JPanel {
    private List<Point> cars;
    private Timer timer;
    Random rand;

    public TestPanel() {
        rand = new Random();
        cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int x = rand.nextInt(1920); // random x-coordinate between 0 and 1920
            int y = rand.nextInt(1080); // random y-coordinate between 0 and 1080
            cars.add(new Point(x, y));
        }

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCars();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        for (Point car : cars) {
            g.fillRect(car.x, car.y, 50, 50);
        }
    }

    public void moveCars() {
        for (Point car : cars) {
            car.x = (int) (Math.sin(Math.toRadians(car.y)) * 50 + 300);
            car.y += 5;

            if (car.y > 1080) {
                car.x = rand.nextInt(1920);
                car.y = 0;
            }
        }

        repaint();
    }
}