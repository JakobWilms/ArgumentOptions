package tk.picrafmc.snake;

import javax.swing.*;
import java.awt.*;

public class StartWindow {
    private JFrame frame;

    private JTextArea difficultyTextArea;
    private JButton EASYButton;
    private JButton DIFFICULTButton;
    private JButton MEDIUMButton;
    private JPanel panel;
    private JButton NOOBButton;
    private JButton PROButton;

    public StartWindow() {
        frame = new JFrame();
        addListeners();
        difficultyTextArea.setEditable(false);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
    private void addListeners() {
        EASYButton.addActionListener(actionEvent -> EventQueue.invokeLater(() -> {
            frame.dispose();
            JFrame snake = new Snake(Difficulty.EASY);
            snake.setVisible(true);
        }));
        MEDIUMButton.addActionListener(actionEvent -> EventQueue.invokeLater(() -> {
            frame.dispose();
            JFrame snake = new Snake(Difficulty.MEDIUM);
            snake.setVisible(true);
        }));
        DIFFICULTButton.addActionListener(actionEvent -> EventQueue.invokeLater(() -> {
            frame.dispose();
            JFrame snake = new Snake(Difficulty.DIFFICULT);
            snake.setVisible(true);
        }));
        NOOBButton.addActionListener(actionEvent -> EventQueue.invokeLater(() -> {
           frame.dispose();
           JFrame snake = new Snake(Difficulty.NOOB);
           snake.setVisible(true);
        }));
        PROButton.addActionListener(actionEvent -> EventQueue.invokeLater(() -> {
            frame.dispose();
            JFrame snake = new Snake(Difficulty.PRO);
            snake.setVisible(true);
        }));
    }
}
