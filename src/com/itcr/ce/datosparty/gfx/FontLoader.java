package com.itcr.ce.datosparty.gfx;

import java.awt.*;
import java.io.*;

public class FontLoader {

    public static Font loadFont(String path){
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT,new File(path)).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File(path)));
            return font;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
