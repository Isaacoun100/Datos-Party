package com.okcomputer.datosparty.boardWindow;

import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BoardPanel extends JPanel {

    private Image background;
    private Graphics2D g;

    private BufferedImage img;
    int screenHeight;
    int screenWidth;

    public void init(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = screenSize.height;
        screenWidth = screenSize.width;
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        setBackground("res/bg/BoardTemplate.png");

    }

    public void setBackground(String imagePath) {

        this.setOpaque(false);
        this.background = new ImageIcon(imagePath).getImage();
        repaint();
    }

    public BoardPanel(){

        init();


        setFocusable(true);
        requestFocus();
    }
}
