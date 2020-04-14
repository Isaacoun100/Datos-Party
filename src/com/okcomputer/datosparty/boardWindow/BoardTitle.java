package com.okcomputer.datosparty.boardWindow;

import javax.swing.*;

public class BoardTitle extends JFrame {

    public BoardTitle(){

        setTitle("Datos Party");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(new BoardPanel());
        setResizable(false);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);


    }
}
