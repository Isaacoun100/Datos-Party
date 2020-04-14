package com.okcomputer.datosparty.boardWindow;

import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BoardPanel extends JPanel {

    private Graphics2D g;
    private BufferedImage img;
    public static int width;
    public static int height;
    //int screenHeight;
    //int screenWidth;

    public BoardPanel(int width, int height){

        this.width = width;
        this.height = height;

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        g = (Graphics2D) img.getGraphics();
        g.setColor(new Color(89, 172, 124));


        //screenHeight = screenSize.height;
        //screenWidth = screenSize.width;

        g.fillRect(0,0,width,height);

        setPreferredSize(new Dimension(width,height));
        setFocusable(true);

        requestFocus();
    }

}
