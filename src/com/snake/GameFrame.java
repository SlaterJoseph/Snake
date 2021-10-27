package com.snake;

import javax.swing.*;

public class GameFrame extends JFrame {
    boolean gameRun = false;
    GameFrame() {
        TitlePanel titlePanel = new TitlePanel();
        add(titlePanel);
        setTitle("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        createGamePanel(titlePanel);
    }

    private void createGamePanel(TitlePanel titlePanel){
        while(!gameRun){
            gameRun = titlePanel.gameRunning;
            System.out.println();
        }

        GamePanel gamePanel = new GamePanel(titlePanel.getColorChosen(), titlePanel.getGridUp(), titlePanel.getWallCollisions());
        titlePanel.resetKeyboardActions();
        titlePanel.setVisible(false);
        titlePanel.setFocusable(false);
        gamePanel.resetKeyboardActions();
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        add(gamePanel);
        pack();
        repaint();
    }

}
