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
        g = (Graphics2D) img.getGraphics();
        g.setColor(new Color(89, 172, 124));

        screenHeight = screenSize.height;
        screenWidth = screenSize.width;

        g.fillRect(0,0,screenWidth,screenHeight);

        setPreferredSize(new Dimension(screenWidth,screenHeight));
        setFocusable(true);

        requestFocus();
    }

}
