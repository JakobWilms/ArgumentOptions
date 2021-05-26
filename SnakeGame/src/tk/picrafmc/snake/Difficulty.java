package tk.picrafmc.snake;

public enum Difficulty {
    NOOB(500),
    EASY(200),
    MEDIUM(100),
    DIFFICULT(20),
    PRO(10),
    ;

    private final int delay;

    Difficulty(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }
}
