package org.example.view;

import org.example.elements.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
public class SearchMenu  {

	public void openSearchMenu(){
		BurgurFrame frame = new BurgurFrame("Burgur Shop");
		ImageIcon imageIcon = new ImageIcon("img/frontImage.jpg");
		JLabel imageLabel = new JLabel(imageIcon);
		frame.add(imageLabel);
		JPanel rightSideMenu = new JPanel();
		JPanel menuButtons = new JPanel();
		menuButtons.setLayout(new BoxLayout(menuButtons, BoxLayout.Y_AXIS));
		menuButtons.setBackground(CommonColors.commonBackgroundColor);

		CustomButton buttonPlaceOrder = new CustomButton("Best Customer","RED",200);
		CustomButton buttonSearch = new CustomButton("Search Order","RED",200);
		CustomButton buttonViewOrders = new CustomButton("Search Customer","RED",200);

		menuButtons.setBorder(new EmptyBorder(50, 10, 10, 10));
		menuButtons.add(Box.createRigidArea(new Dimension(0, 20)));  
		menuButtons.add(buttonPlaceOrder);
		menuButtons.add(Box.createRigidArea(new Dimension(0, 20)));  
		menuButtons.add(buttonSearch);
		menuButtons.add(Box.createRigidArea(new Dimension(0, 20)));  
		menuButtons.add(buttonViewOrders);
		menuButtons.add(Box.createRigidArea(new Dimension(0, 20))); 
		menuButtons.add(Box.createRigidArea(new Dimension(0, 20))); 
		rightSideMenu.setLayout(new BorderLayout());
		rightSideMenu.setBackground(CommonColors.commonBackgroundColor);
		
		//Top Title
		BurgurPanelCover topTitle = new BurgurPanelCover(Color.RED);
		BurgurTextLables title = new BurgurTextLables();
		title.setBorder(new EmptyBorder(10, 10, 10, 10));
		title.setText("Search Order");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("SansSerif", Font.BOLD, 40));
		title.setVerticalAlignment (SwingConstants.CENTER);
		title.setHorizontalAlignment (SwingConstants.CENTER);
		topTitle.add(title);
		
		rightSideMenu.add(topTitle, BorderLayout.NORTH);
		rightSideMenu.add(new BurgurPanelCover(), BorderLayout.WEST);
		rightSideMenu.add(new BurgurPanelCover(), BorderLayout.EAST);
		rightSideMenu.add(menuButtons, BorderLayout.CENTER);
		
		BurgurPanelCover backToHomePagePanel =new BurgurPanelCover();
		CustomButton backToHomePageS = new CustomButton("Back to Home Page","GREEN",200);
		backToHomePagePanel.setLayout(new BoxLayout(backToHomePagePanel, BoxLayout.Y_AXIS));
		backToHomePagePanel.add(Box.createRigidArea(new Dimension(20, 0)));
		backToHomePageS.setAlignmentX(Component.RIGHT_ALIGNMENT);
		backToHomePagePanel.add(backToHomePageS);
		rightSideMenu.add(backToHomePagePanel,BorderLayout.SOUTH);
		frame.add(rightSideMenu);
		frame.pack();
        frame.setVisible(true);
	}
}

