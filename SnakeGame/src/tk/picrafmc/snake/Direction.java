package tk.picrafmc.snake;

public enum Direction {
    LEFT(0),
    RIGHT(1),
    UP(2),
    DOWN(3);

    private final int direction;

    Direction(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
