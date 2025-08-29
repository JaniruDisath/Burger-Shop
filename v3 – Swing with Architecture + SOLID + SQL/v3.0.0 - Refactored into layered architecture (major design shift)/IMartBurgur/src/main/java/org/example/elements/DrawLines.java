package org.example.elements;

import javax.swing.*;
import java.awt.*;

public class DrawLines extends JPanel {
    private int a;
    private int b;
    private int c;
    private int d;
    public DrawLines(){
        a=0;
        b=0;
        c=600;
        d=0;
    }
    DrawLines(int addition){
        a=0;
        b=0;
        c=10;
        d=0;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(0, 0, 600, 00);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(100));
    }
}