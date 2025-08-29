import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
public class ViewOrderMenu  {
	public static void viewOrderMenuMenu(){	
		BurgurFrame frame = new BurgurFrame("Burgur Shop");
		ImageIcon imageIcon = new ImageIcon("img/frontImage.jpg");
		JLabel imageLabel = new JLabel(imageIcon);
		frame.add(imageLabel);
		JPanel rightSideMenu = new JPanel();
		JPanel menuButtons = new JPanel();
		menuButtons.setLayout(new BoxLayout(menuButtons, BoxLayout.Y_AXIS));
		menuButtons.setBackground(CommonColors.commonBackgroundColor);
		CustomButton buttonPlaceOrder = new CustomButton("Delivered Orders","RED","View");
		CustomButton buttonSearch = new CustomButton("Preparing Orders","RED","View");
		CustomButton buttonViewOrders = new CustomButton("Cancelled Orders","RED","View");
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
		title.setText("View Orders");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("SansSerif", Font.BOLD, 40));
		title.setVerticalAlignment (BurgurTextLables.CENTER); 
		title.setHorizontalAlignment (BurgurTextLables.CENTER);
		topTitle.add(title);
		
		rightSideMenu.add(topTitle, BorderLayout.NORTH);
		rightSideMenu.add(new BurgurPanelCover(), BorderLayout.WEST);
		rightSideMenu.add(new BurgurPanelCover(), BorderLayout.EAST);
		rightSideMenu.add(menuButtons, BorderLayout.CENTER);
		
		BurgurPanelCover backtoHomePagePanel =new BurgurPanelCover();
		CustomButton backtoHomePageS = new CustomButton("Back to Home Page","GREEN",200,"Search"); 
		backtoHomePagePanel.setLayout(new BoxLayout(backtoHomePagePanel, BoxLayout.Y_AXIS));
		backtoHomePagePanel.add(Box.createRigidArea(new Dimension(20, 0)));
		backtoHomePageS.setAlignmentX(Component.RIGHT_ALIGNMENT);
		backtoHomePagePanel.add(backtoHomePageS);
		rightSideMenu.add(backtoHomePagePanel,BorderLayout.SOUTH);
		frame.add(rightSideMenu);
		frame.pack();
        frame.setVisible(true);
	}

	 public static void main(String[] args) {
        viewOrderMenuMenu();	
    }
}

