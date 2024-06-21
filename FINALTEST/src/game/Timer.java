package game;

public class Timer {
    private int updatesSinceStart;

    public Timer() {
        this.updatesSinceStart = 0;
    }

    public int getUpdatesFromSeconds(int seconds) {
        return seconds * GameLoop.UPDATE_PER_SECOND;
    }
}
