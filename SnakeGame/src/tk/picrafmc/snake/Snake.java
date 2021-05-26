package tk.picrafmc.snake;

import javax.swing.*;

public class Snake extends JFrame {
    public Snake(Difficulty difficulty){
        add(new Game(difficulty));

        setResizable(false);
        pack();

        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
