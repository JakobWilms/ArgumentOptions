package tk.picrafmc.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent event) {
        int keyId = event.getKeyCode();

        if (keyId == KeyEvent.VK_LEFT && Game.direction != Direction.RIGHT) {
            Game.direction = Direction.LEFT;
        }
        if (keyId == KeyEvent.VK_RIGHT && Game.direction != Direction.LEFT) {
            Game.direction = Direction.RIGHT;
        }
        if (keyId == KeyEvent.VK_DOWN && Game.direction != Direction.UP) {
            Game.direction = Direction.DOWN;
        }
        if (keyId == KeyEvent.VK_UP && Game.direction != Direction.DOWN) {
            Game.direction = Direction.UP;
        }
    }
}
