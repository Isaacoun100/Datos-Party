package com.itcr.ce.datosparty.display;

import javax.swing.*;
import java.awt.*;

public class Display {

    /**
     * defined variables in order to be used by the methods bellow
     */
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int width, height;

    /**
     * Here we define the parameters needed in order to render a window
     * @param title String variable to set title
     * @param width width of the screen
     * @param height height of the screen
     */
    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    /**
     * This method creates the display using the swing logic
     */
    private void createDisplay(){

        frame = new JFrame(title);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    /**
     * canvas getter
     * @return returns canvas so it can be accessed by other classes
     */
    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }
}
