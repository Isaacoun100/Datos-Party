package com.okcomputer.datosparty.boardWindow;

import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BoardPanel extends JPanel {

    private Graphics2D g;
    private BufferedImage img;
    int screenHeight;
    int screenWidth;

    public BoardPanel(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        screenHeight = screenSize.height;
        screenWidth = screenSize.width;

        BufferedImage img = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        g.setColor(new Color(89, 172, 124));

        g.fillRect(0,0,screenWidth,screenHeight);

        setPreferredSize(new Dimension(screenWidth,screenHeight));
        setFocusable(true);

        requestFocus();
    }

}
