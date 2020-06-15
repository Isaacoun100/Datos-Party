package com.itcr.ce.datosparty.gfx;

import java.awt.*;
import java.io.*;

/**
 * Loads fonts to customize strings displayed onscreen
 */
public class FontLoader {

    /**
     * Font loader, it simply needs to be provided a file path, and it will return a Font object
     * @param path String of a file's current location
     * @return Font Obj
     */
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
