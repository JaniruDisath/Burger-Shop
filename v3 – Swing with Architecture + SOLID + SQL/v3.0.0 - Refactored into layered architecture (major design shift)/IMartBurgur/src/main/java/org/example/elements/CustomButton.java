package org.example.elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    private boolean isHovered = false;
    private boolean isPressed = false;
    private final String text;
    private final String changingButtonColor;
    private final int buttonWidth;
    private final int buttonHeight;

	public CustomButton(String text,String changingButtonColor,int buttonWidth) {
		this.text = text;
		this.buttonWidth= buttonWidth;
		buttonHeight=35;
		setPreferredSize (new Dimension(buttonWidth, 35));
		setSize(new Dimension(buttonWidth, buttonHeight));
		setMinimumSize(new Dimension(buttonWidth, buttonHeight));
		setMaximumSize(new Dimension(buttonWidth, buttonHeight));
		setFont(new Font("SansSerif", Font.BOLD, 14));
		this.changingButtonColor=changingButtonColor;
		setBorder(null);
		setBackground(new Color (191, 191, 191));

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				isHovered = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				isHovered = false;
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				isPressed = true;
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				isPressed = false;
				repaint();
			}
		});
	}

	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setBackground(new Color (191, 191, 191));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color (191, 191, 191));
		g2d.fillRect(0, 0, getWidth(), getHeight());

        Color butonColor = switch (changingButtonColor) {
            case "RED" -> getRedButtonColor();
            case "GREEN" -> getGreenButtonColor();
            default -> getBlueButtonColor();
        };

        g2d.setColor(butonColor);
        g2d.fillRoundRect(0, 0, buttonWidth, buttonHeight, 25, 25);
        g2d.setColor(Color.WHITE);
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (buttonWidth - metrics.stringWidth(text))/ 2;
        int y = (buttonHeight + metrics.getHeight()/2)/2 ;
        g2d.drawString(text, x, y);    
    }

	private Color getRedButtonColor(){
		if (isPressed){
			return new Color(180, 0, 0);
		}
		if (isHovered){
			return  new Color(220, 0, 0);
		}
		return Color.RED;
	}

	private Color getGreenButtonColor(){
		if (isPressed){
			return new Color(0, 180, 0);
		}
		if (isHovered){
			return  new Color(0, 220, 0);
		}
		return Color.GREEN;
	}

	private Color getBlueButtonColor(){
		if (isPressed){
			return new Color(0, 0, 180);
		}
		if (isHovered){
			return  new Color(0, 0, 220);
		}
		return Color.BLUE;
	}
}
