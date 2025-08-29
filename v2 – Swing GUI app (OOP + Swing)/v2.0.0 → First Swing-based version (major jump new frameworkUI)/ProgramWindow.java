import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import java.io.OutputStream;
import java.io.PrintStream;
class CommonColors {
	public static Color commonBackgroundColor = new Color (191, 191, 191);
}
class BurgurFrame extends JFrame{
	private static int width;
	private static int height;

	BurgurFrame(String title){
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
class BurgurPanelCover extends JPanel{
	private int widthPanel = 0;
	private int heightPanel = 0;
	BurgurPanelCover(){
		setBackground(CommonColors.commonBackgroundColor);
		setPreferredSize(new Dimension(140, 80));
	}
	BurgurPanelCover(int i){
		setPreferredSize(new Dimension(1000, 60));
	}
	BurgurPanelCover(Color color){
		setBackground(color);
		setPreferredSize(new Dimension(200, 80));
	}
	BurgurPanelCover(Color color,int widthPanel,int heightPanel){
		this. widthPanel= widthPanel;
		this. heightPanel= heightPanel;
		setBackground(color);
		setPreferredSize(new Dimension(widthPanel, heightPanel));
	}
}
class BurgurTextField extends JTextField{
	BurgurTextField(){
		setPreferredSize(new Dimension(200, 40));
		setFont(new Font("Serif", Font.BOLD, 18));
		setMargin(new Insets(10, 10, 10, 10));
	}
	
}
class BurgurTextLables extends JLabel{
	BurgurTextLables(){
		setHorizontalTextPosition(JLabel.CENTER);
		setFont(new Font("Serif", Font.BOLD, 20));

		}
}
class DrawLines extends JPanel {
	private int a;
	private int b;
	private int c;
	private int d;
	DrawLines(){
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
class MainWindow  {	
	private static int go=5;
	private static CustomButton addCustomers = new CustomButton("Add Customers","GREEN",200,"Menu"); 
	private static CustomButton backto = new CustomButton("TEst","GREEN",200,"Best Customer"); 
	private static PrintStream originalOut = System.out;
	public  void openMainWindow(){	
		BurgurFrame frame = new BurgurFrame("Burgur Shop");

        PrintStream originalOut = System.out;
        OutputStream dummyOut = new OutputStream() {
            @Override
            public void write(int b) {
            }
        };
		System.setOut(new PrintStream(dummyOut));
        initiateDeveloperOptions();
		ImageIcon imageIcon = new ImageIcon("img/frontImage.jpg");
		JLabel imageLabel = new JLabel(imageIcon);
		frame.add(imageLabel);
		JPanel rightSideMenu = new JPanel();
		JPanel menuButtons = new JPanel();
		menuButtons.setLayout(new BoxLayout(menuButtons, BoxLayout.Y_AXIS));
		menuButtons.setBackground(CommonColors.commonBackgroundColor);
		CustomButton buttonPlaceOrder = new CustomButton("Place Order","RED","Menu");
		CustomButton buttonSearch = new CustomButton("Search","RED","Menu");
		CustomButton buttonViewOrders = new CustomButton("View Orders","RED","Menu");
		CustomButton buttonUpdateOrderDetail = new CustomButton("Update Order Detail","RED","Menu");
		CustomButton exitButton = new CustomButton("Exit","GREEN", "Menu");
		menuButtons.add(Box.createRigidArea(new Dimension(0, 20)));  
		menuButtons.add(buttonPlaceOrder);
		menuButtons.add(Box.createRigidArea(new Dimension(0, 20)));  
		menuButtons.add(buttonSearch);
		menuButtons.add(Box.createRigidArea(new Dimension(0, 20)));  
		menuButtons.add(buttonViewOrders);
		menuButtons.add(Box.createRigidArea(new Dimension(0, 20))); 
		menuButtons.add(buttonUpdateOrderDetail);
		menuButtons.add(Box.createRigidArea(new Dimension(0, 20))); 
		rightSideMenu.setLayout(new BorderLayout());
		rightSideMenu.setBackground(CommonColors.commonBackgroundColor);
		
		BurgurPanelCover backtoHomePagePanel =new BurgurPanelCover();
		backtoHomePagePanel.setLayout(new GridBagLayout());
		backtoHomePagePanel.setPreferredSize(new Dimension(200, 100));
		GridBagConstraints GridBagLayout = new GridBagConstraints();
		GridBagLayout.anchor = GridBagConstraints.EAST;
		GridBagLayout.insets = new Insets(30, 10, 10, 20);
		
		backtoHomePagePanel.add(Box.createRigidArea(new Dimension(20, 0)));
		
		GridBagLayout.gridx = 0; GridBagLayout.gridy = 0;
		backtoHomePagePanel.add(backto,GridBagLayout);
		GridBagLayout.gridx = 1; 
		backtoHomePagePanel.add(addCustomers,GridBagLayout);
		rightSideMenu.add(backtoHomePagePanel,BorderLayout.NORTH);
		
		
		rightSideMenu.add(new BurgurPanelCover(), BorderLayout.WEST);
		rightSideMenu.add(new BurgurPanelCover(), BorderLayout.EAST);
		rightSideMenu.add(menuButtons, BorderLayout.CENTER);
		BurgurPanelCover exitButtonPanel =new BurgurPanelCover();
		exitButtonPanel.setLayout(new BoxLayout(exitButtonPanel, BoxLayout.Y_AXIS));
		exitButtonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		exitButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		exitButtonPanel.add(exitButton);
		rightSideMenu.add(exitButtonPanel,BorderLayout.SOUTH);
		frame.add(rightSideMenu);
		frame.pack();
        frame.setVisible(true);

	} 
	public  void testData(int number) {
    BurgerShopDatabase db = OurDataBase.SHARED_DB;
    UpdateDatabase newUpdate = OurDataBase.SHARED_UPDATE_DB;
    for (int i = 0; i < number; i++) {
        String zz = "";
        int LatestOrder = db.getLatestOrder();

        if (LatestOrder >= 99) {
            zz += "0";
        } else if (LatestOrder >= 9) {
            zz += "00";
        } else if (LatestOrder >= 0) {
            zz += "000";
        }
        String orderIDPO = "B" + zz + (LatestOrder + 1);
        System.out.println(orderIDPO);
        Random r = new Random();
        int w = r.nextInt(100);
        newUpdate.placeNewOrder((713769480 + 1 * i * w + 20 * i * w + 300 * i * w + 4000 * i * w), "Janiru" + i,(i * 10), orderIDPO );
    }
}
	public void initiateDeveloperOptions(){
		go=getDev();
		System.out.println(go);
		if(go==1){
			backto.setVisible(true);
			addCustomers.setVisible(true);
			System.setOut(originalOut);
		}else{
			addCustomers.setVisible(false);
			backto.setVisible(false);
		}
	}
	public int getDev(){
		SearchOrderDetails currentSearchOrder = OurDataBase.SEARCH_ORDER;
		return currentSearchOrder.getDev();
	}
	
}

class ProgramWindow{
	 public static void main(String[] args) {
        CustomButton.mainWindow.openMainWindow();
    }
   }
