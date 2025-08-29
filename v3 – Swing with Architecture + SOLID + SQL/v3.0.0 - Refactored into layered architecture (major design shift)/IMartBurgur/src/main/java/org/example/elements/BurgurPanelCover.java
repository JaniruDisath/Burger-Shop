package org.example.elements;

import javax.swing.*;
import java.awt.*;

public class BurgurPanelCover extends JPanel {
    private int widthPanel = 0;
    private int heightPanel = 0;
    public BurgurPanelCover(){
        setBackground(CommonColors.commonBackgroundColor);
        setPreferredSize(new Dimension(140, 80));
    }
    BurgurPanelCover(int i){
        setPreferredSize(new Dimension(1000, 60));
    }
    public BurgurPanelCover(Color color){
        setBackground(color);
        setPreferredSize(new Dimension(200, 80));
    }
    public BurgurPanelCover(Color color, int widthPanel, int heightPanel){
        this. widthPanel= widthPanel;
        this. heightPanel= heightPanel;
        setBackground(color);
        setPreferredSize(new Dimension(widthPanel, heightPanel));
    }
}