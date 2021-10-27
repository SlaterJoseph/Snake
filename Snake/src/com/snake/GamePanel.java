package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_HEIGHT * SCREEN_WIDTH)/UNIT_SIZE;
    static final int TIMER_DELAY = 75;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 5;
    int applesEaten, appleX, appleY;

    String colorChoice;
    boolean gridChoice;
    boolean wallsOn;

    /**
     * R is for right
     * L is for left
     * D is for down
     * U is for up
     */
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;


    /**
     * This constructor initializes the random class
     * As well as sets up the game
     */
    GamePanel(String colorChoice, boolean gridChoice, boolean wallsOn){
        System.out.println("Test 1");
        random = new Random();
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        startGame();
        this.colorChoice = colorChoice;
        this.gridChoice = gridChoice;
        this.wallsOn = wallsOn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();

            if(wallsOn) {
                checkCollisionsWalls();
            } else {
                noWallCollisions();
            }

            checkCollisionsBody();
        }

        repaint();
    }

    private void startGame(){
        System.out.println("test 2");
        newApple();
        running = true;
        timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        draw(graphics);
    }

    private void draw(Graphics graphics){

        /**
         * This loop is for developmental purposes
         * I may make a option to keep it visible in game
         */
        if(running) {

            if(gridChoice) {
                for (int i = 0; i < (SCREEN_HEIGHT / UNIT_SIZE); i++) {
                    graphics.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                    graphics.drawLine(i * 0, i * UNIT_SIZE, i * SCREEN_WIDTH, i * UNIT_SIZE);
                }
            }

            graphics.setColor(Color.RED);
            graphics.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            if(colorChoice.equals("basic")){
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    graphics.setColor(Color.GREEN);
                    graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    graphics.setColor(new Color(45, 180, 0));
                    graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    }
                }
            } else if(colorChoice.equals("random")) {
                for(int i = 0; i < bodyParts; i++){
                    graphics.setColor(new Color(
                            random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            } else if(colorChoice.equals("colorful")){
                int j = 0;
                for(int i = 0; i < bodyParts; i++){

                    if(j == 12){
                        j = 0;
                    }

                    switch(j){
                        case 0:
                            graphics.setColor(new Color(208, 234, 43));
                            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                            break;
                        case 1:
                            graphics.setColor(new Color(102, 176, 50));
                            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                            break;
                        case 2:
                            graphics.setColor(new Color(3, 145, 206));
                            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                            break;
                        case 3:
                            graphics.setColor(new Color(2, 71, 254));
                            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                            break;
                        case 4:
                            graphics.setColor(new Color(61, 1, 164));
                            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                            break;
                        case 5:
                            graphics.setColor(new Color(134, 1, 175));
                            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                            break;
                        case 6:
                            graphics.setColor(new Color(167, 25, 75));
                            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                            break;
                        case 7:
                            graphics.setColor(new Color(254, 39, 18));
                            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                            break;
                        case 8:
                            graphics.setColor(new Color(253, 83, 8));
                            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                            break;
                        case 9:
                            graphics.setColor(new Color(251, 153, 2));
                            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                            break;
                        case 10:
                            graphics.setColor(new Color(250, 188, 2));
                            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                            break;
                        case 11:
                            graphics.setColor(new Color(254, 254, 51));
                            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                            break;
                    }
                    j++;
                }

            }

            graphics.setColor(Color.RED);
            graphics.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics fontMetrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score:" + applesEaten * 10, 225, graphics.getFont().getSize());

        } else {
            gameOver(graphics);
        }
    }

    private void newApple(){
        appleX = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
        appleY = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;

    }

    private void move(){
        /**
         * This loop shift the body parts of the snake around
         */
        for(int i = bodyParts; i > 0; i--){
            x[i] = x[i - 1];
            y[i] = y[i-1];
        }

        switch(direction){
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
        }
    }

    /**
     * This method checks if the snake connects with a apple
     * and creates a new apple when it does
     */
    private void checkApple(){
        if((x[0] == appleX) && (y[0] == appleY)){
            bodyParts+= 1;
            applesEaten+= 1;
            newApple();
        }
    }

    /**
     * This method will check for collisions with the wall
     * Which can be turned on or off in difficulty settings
     */
    private void checkCollisionsWalls(){
        if(x[0] < 0){
            running = false;
        }

        if(x[0] > SCREEN_WIDTH - UNIT_SIZE){
            running = false;
        }

        if(y[0] < 0){
            running = false;
        }

        if(y[0] > SCREEN_HEIGHT - UNIT_SIZE){
            running = false;
        }

        if(!running){
            timer.stop();
        }
    }

    private void noWallCollisions(){
        if(x[0] == 0){
            x[0] = SCREEN_WIDTH;
        }

        if(x[0] == SCREEN_WIDTH){
            x[0] = 0;
        }

        if(y[0] == 0){
            y[0] = SCREEN_HEIGHT;
        }

        if(y[0] == SCREEN_HEIGHT){
            y[0] = 0;
        }
    }

    /**
     * This method checks if the head collides with the body
     * Which will always result in a game over
     */
    private void checkCollisionsBody(){
        for(int i = bodyParts; i > 0; i--){
            if((x[0] == x[i]) && (y[0]) == y[i]){
                running = false;
            }
        }

        if(!running){
            timer.stop();
        }
    }

    /**
     * This method is activated when you collide with either
     * a wall or yourself
     * @param graphics
     */
    private void gameOver(Graphics graphics){
        /**
         * The first group is to print the Game Over string
         * The second is to print the score
         */
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics fontMetrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over", 112, SCREEN_HEIGHT/2);

        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics fontMetrics2 = getFontMetrics(graphics.getFont());
        graphics.drawString("Score:" + applesEaten * 10, 225,
                graphics.getFont().getSize());
    }

    private class MyKeyAdapter extends KeyAdapter{
        @Override
        /**
         * Both WASD and the arrow keys will be viable control
         * options
         * The option to turn 180 degrees is also removed,
         * as that would cause an instant game over
         */
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT, KeyEvent.VK_A:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;

                case KeyEvent.VK_RIGHT, KeyEvent.VK_D:
                    System.out.println("test 3");
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;

                case KeyEvent.VK_DOWN, KeyEvent.VK_S:
                    System.out.println("test 3");
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;

                case KeyEvent.VK_UP, KeyEvent.VK_W:
                    if(direction != 'D'){
                        direction = 'U';
                    }
            }
        }
    }

}
