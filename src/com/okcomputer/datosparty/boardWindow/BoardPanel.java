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

        setBackground(new Color(108, 172, 155));

        setPreferredSize(new Dimension(screenWidth,screenHeight));
        setFocusable(true);

        requestFocus();
    }

}
