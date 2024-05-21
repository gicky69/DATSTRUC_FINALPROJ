public class GameLoop implements Runnable {
    private boolean running;
    private final double updateRate = 1.0d / 60.0d;

    private long nextStatTime;
    private int fps, ups;

    // Count in milliseconds
    @Override
    public void run() {
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while (running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            while (accumulator >= updateRate) {
                update();
                accumulator -= updateRate;
            }
            render();
            printStats();
        }
    }

    // Printing the stats
    private void printStats() {
        if (System.currentTimeMillis() > nextStatTime) {
            System.out.printf("FPS: %d, UPS: %d\n", fps, ups);
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    private void update() {
        ups++;
    }

    private void render() {
        fps++;
    }
}
