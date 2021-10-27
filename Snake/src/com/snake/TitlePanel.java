package com.snake;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitlePanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    boolean gameRunning = false;
    String colorChosen;
    Boolean wallCollisions;
    Boolean gridUp;

    GridLayout gridLayout;
    JPanel buttonPanel;
    JRadioButton basicColor;
    JRadioButton colorWheel;
    JRadioButton random;

    JRadioButton gridOn;
    JRadioButton gridOff;

    JRadioButton wallOn;
    JRadioButton wallOff;

    TitlePanel(){
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        setLayout(new BorderLayout());
        drawTitle();
        drawUniques();
        drawColorChosen();
        drawGridChoice();
        drawWallChoice();
        repaint();
    }

    /**
     * This method draws the titles
     */
    private void drawTitle(){
        JTextField titleText = new JTextField();
        titleText.setForeground(Color.RED);
        titleText.setBackground(Color.black);
        titleText.setText("Snake");
        titleText.setEditable(false);
        titleText.setFont(new Font("Ink Free", Font.BOLD, 100));
        titleText.setBorder(new LineBorder(Color.black, 0));
        titleText.setVisible(true);
        titleText.setHorizontalAlignment(JLabel.CENTER);
        add(titleText, BorderLayout.CENTER);
        repaint();

    }

    /**
     * This method sets up the panel for the radio buttons and the start button,
     * and the gridLayout
     */
    private void drawUniques(){
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        buttonPanel.setPreferredSize(new Dimension(200,200));
        gridLayout = new GridLayout(20,1);
        buttonPanel.setLayout(gridLayout);
        add(buttonPanel, BorderLayout.LINE_START);


        /**
         * This is the start button which takes all the choices and
         * passes the parameters to GamePanel
         */
        JButton startGame = new JButton("Start");
        startGame.addActionListener(this);
        add(startGame, BorderLayout.AFTER_LAST_LINE);

    }

    /**
     * This method draws the radio buttons for the choice of color
     */
    private void drawColorChosen(){
        JLabel colorStuff = new JLabel("Pick your color choice:");
        colorStuff.setBackground(Color.BLACK);
        colorStuff.setForeground(Color.WHITE);

        basicColor = new JRadioButton("Basic", true);
        colorWheel = new JRadioButton("Colorful");
        random = new JRadioButton("Madness");

        ButtonGroup colorSelect = new ButtonGroup();

        colorSelect.add(basicColor);
        colorSelect.add(colorWheel);
        colorSelect.add(random);

        buttonPanel.add(random, 0, 0);
        buttonPanel.add(colorWheel,0, 0);
        buttonPanel.add(basicColor, 0, 0);
        buttonPanel.add(colorStuff, 0, 0);

        basicColor.setBackground(Color.black);
        colorWheel.setBackground(Color.black);
        random.setBackground(Color.black);

        basicColor.setForeground(Color.WHITE);
        colorWheel.setForeground(Color.WHITE);
        random.setForeground(Color.WHITE);

        basicColor.setVisible(true);
        colorWheel.setVisible(true);
        random.setVisible(true);
        buttonPanel.setVisible(true);
    }

    /**
     * This method draws the radio buttons for the choice of grid
     */
    private void drawGridChoice(){
        JLabel gridStuff = new JLabel("Do you want to play with a grid?");
        gridStuff.setBackground(Color.BLACK);
        gridStuff.setForeground(Color.WHITE);

        gridOn = new JRadioButton("Grid On");
        gridOff = new JRadioButton("Grid Off", true);

        ButtonGroup gridSelect = new ButtonGroup();
        gridSelect.add(gridOff);
        gridSelect.add(gridOn);

        buttonPanel.add(gridOff,0, 0);
        buttonPanel.add(gridOn, 0, 0);
        buttonPanel.add(gridStuff,0, 0);

        gridOn.setBackground(Color.black);
        gridOff.setBackground(Color.black);

        gridOn.setForeground(Color.WHITE);
        gridOff.setForeground(Color.WHITE);


    }

    /**
     * This method draws the radio buttons for the choice of wall
     * collisions being on or off
     */
    private void drawWallChoice(){
        JLabel wallStuff = new JLabel("Die from wall collisions?");
        wallStuff.setBackground(Color.BLACK);
        wallStuff.setForeground(Color.WHITE);

        wallOn = new JRadioButton("Walls On", true);
        wallOff = new JRadioButton("Walls Off");

        ButtonGroup wallSelect = new ButtonGroup();
        wallSelect.add(wallOff);
        wallSelect.add(wallOn);

        buttonPanel.add(wallOff,0, 0);
        buttonPanel.add(wallOn, 0, 0);
        buttonPanel.add(wallStuff,0, 0);

        wallOn.setBackground(Color.black);
        wallOff.setBackground(Color.black);

        wallOn.setForeground(Color.WHITE);
        wallOff.setForeground(Color.WHITE);

    }

    public boolean isGameRunning() {
        return gameRunning;
    }


    public Boolean getGridUp() {
        return gridUp;
    }

    public String getColorChosen() {
        return colorChosen;
    }

    public Boolean getWallCollisions() {
        return wallCollisions;
    }

    /**
     * This method collects the input from your choices of radio button
     * once you click the start button, which is then used to initialize
     * your game of snake
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (basicColor.isSelected()) {
            colorChosen = "basic";
        } else if (colorWheel.isSelected()) {
            colorChosen = "colorful";
        } else if (random.isSelected()) {
            colorChosen = "random";
        }

        if(gridOn.isSelected()){
            gridUp = true;
        } else if(gridOff.isSelected()){
            gridUp = false;
        }

        if(wallOn.isSelected()){
            wallCollisions = true;
        } else if(wallOff.isSelected()){
            wallCollisions = false;
        }

        gameRunning = true;
    }

}
