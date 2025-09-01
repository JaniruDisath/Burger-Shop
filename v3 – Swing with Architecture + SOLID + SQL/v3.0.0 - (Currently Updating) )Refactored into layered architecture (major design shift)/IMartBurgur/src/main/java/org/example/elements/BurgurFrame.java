package org.example.elements;

import javax.swing.*;
import java.awt.*;

public class BurgurFrame extends JFrame {
    private static int width;
    private static int height;

    public BurgurFrame(String title){
        width =1000;
        height=550;
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle(title);
        setLayout(new GridLayout(1, 2));
        setResizable(false);
        ImageIcon imageIcon = new ImageIcon("img/frontImage.jpg");
        setIconImage(imageIcon.getImage());
        getContentPane().setBackground(Color.WHITE);
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
    }
}
