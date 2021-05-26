package tk.picrafmc.snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {
    private final ClassLoader loader = Game.class.getClassLoader();
    private final int width = 600;
    private final int height = 600;
    private final Random random = new Random();

    private boolean running;

    private Image apple;

    private Image head;
    private Image body;

    private int body_amount = 3;
    private final int snake_width = 10;
    private final int[] snake_x = new int[width * height / (snake_width * snake_width)];
    private final int[] snake_y = new int[width * height / (snake_width * snake_width)];

    private int apple_x;
    private int apple_y;

    public static Direction direction;
    private final Timer timer;

    public Game(Difficulty difficulty) {
        running = true;
        addKeyListener(new SnakeListener());
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setBackground(Color.DARK_GRAY);

        InputStream apple_in = loader.getResourceAsStream("tk/picraftmc/snake/assets/apple.png");
        InputStream head_in = loader.getResourceAsStream("tk/picraftmc/snake/assets/head.png");
        InputStream body_in = loader.getResourceAsStream("tk/picraftmc/snake/assets/body.png");

        apple = null;
        head = null;
        body = null;

        direction = Direction.RIGHT;
        if (apple_in != null && head_in != null && body_in != null) {
            try {
                this.apple = ImageIO.read(apple_in);
                this.head = ImageIO.read(head_in);
                this.body = ImageIO.read(body_in);
            } catch (IOException e) {
                e.printStackTrace();
                escape();
            } finally {
                try {
                    apple_in.close();
                    head_in.close();
                    body_in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    escape();
                }
            }
        } else {
            System.err.println("Couldn't find images");
            this.apple = new ImageIcon(Game.class.getResource("assets/apple.png")).getImage();
            this.head = new ImageIcon(Game.class.getResource("assets/head.png")).getImage();
            this.body = new ImageIcon(Game.class.getResource("assets/body.png")).getImage();
        }

        if (isRunning()) {
            for (int i = 0; i < body_amount; i++) {
                snake_x[i] = 100 - (i * 10);
                snake_y[i] = 50;
            }
        }

        timer = new Timer(difficulty.getDelay(), this);
        timer.start();

        startGame();

    }

    private void startGame() {
        spawnApple();
    }

    private void spawnApple() {
        int random = this.random.nextInt(59);
        apple_x = random * snake_width;
        random = this.random.nextInt(59);
        apple_y = random * snake_width;
    }

    private void escape() {
        setRunning(false);
    }

    public Image getBody() {
        return body;
    }

    public Image getHead() {
        return head;
    }

    public Image getApple() {
        return apple;
    }

    public int getBody_amount() {
        return body_amount;
    }

    public void setBody_amount(int body_amount) {
        this.body_amount = body_amount;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (running) {
            checkApple();
            checkDeath();
            moveSnake();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (running) {
            graphics.drawImage(apple, apple_x, apple_y, this);

            for (int i = 0; i < body_amount; i++) {
                graphics.drawImage(body, snake_x[i], snake_y[i], this);
            }

            graphics.drawImage(head, snake_x[0], snake_y[0], this);

            Toolkit.getDefaultToolkit().sync();
        } else {
            Font font = new Font("Calibri", Font.BOLD, 24);
            FontMetrics metrics = getFontMetrics(font);

            graphics.setColor(Color.YELLOW);
            graphics.setFont(font);
            graphics.drawString("Game Over", width - metrics.stringWidth("Game Over"), height);
        }
    }

    private void moveSnake() {
        for (int i = body_amount; i > 0; i--) {
            snake_x[i] = snake_x[i - 1];
            snake_y[i] = snake_y[i - 1];
        }
        switch (direction) {
            case LEFT:
                snake_x[0] -= snake_width;
                break;
            case RIGHT:
                snake_x[0] += snake_width;
                break;
            case UP:
                snake_y[0] -= snake_width;
                break;
            case DOWN:
                snake_y[0] += snake_width;
                break;
            default:
                break;
        }
    }

    private void checkDeath() {
        for (int i = body_amount; i > 0; i--) {
            if (snake_x[0] == snake_x[i] && snake_y[0] == snake_y[i]) {
                running = false;
                break;
            }
        }
        if (snake_y[0] >= height || snake_y[0] <= 0 || snake_x[0] >= width || snake_x[0] < 0) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    private void checkApple() {
        if (snake_x[0] == apple_x && snake_y[0] == apple_y) {
            body_amount++;
            spawnApple();
        }
    }
}
