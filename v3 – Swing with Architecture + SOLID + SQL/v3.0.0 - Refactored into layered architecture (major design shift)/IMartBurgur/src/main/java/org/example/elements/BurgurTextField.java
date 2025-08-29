package org.example.elements;

import javax.swing.*;
import java.awt.*;

public class BurgurTextField extends JTextField {
    public BurgurTextField() {
        setPreferredSize(new Dimension(200, 40));
        setFont(new Font("Serif", Font.BOLD, 18));
        setMargin(new Insets(10, 10, 10, 10));
    }

}