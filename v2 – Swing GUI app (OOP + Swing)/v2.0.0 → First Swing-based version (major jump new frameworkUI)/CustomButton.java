import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//FYI - I used codes from " https://stackoverflow.com/questions/423950/rounded-swing-jbutton-using-java " , " https://www.tutorialspoint.com/how-can-we-draw-a-rounded-rectangle-using-the-graphics-object-in-java ", " https://docs.oracle.com/javase/tutorial/2d/text/measuringtext.html " and reffered videos " https://www.youtube.com/watch?v=Kmgo00avvEw ", " https://www.youtube.com/watch?v=nUs-IYfH2B4 " to create this. :|

class CustomButton extends JButton implements MouseListener {
    private boolean isHovered = false;
    private boolean isPressed = false;
    private String text;
    private String changingButtonColor;
    private String buttonLocation="";
    private int buttonWidth;
    private int buttonHeight;
    private int outIntValue=0;
    private static BurgerShopDatabase database = OurDataBase.SHARED_DB;
    private static UpdateDatabase currentUpdateOrder = OurDataBase.SHARED_UPDATE_DB;
    private static PlaceOrderWindow currentOrderWindow = null;
    private static SearchBestCustomer currentBestCustomer = null;
    private static SearchCustomerDetails currentCustomerOrder = null;
    public static SearchOrderDetails currentSearchOrder = OurDataBase.SEARCH_ORDER;
    public static MainWindow mainWindow = OurDataBase.MAIN_WINDOW;
  
    private static ViewOrdersAll currentViewOrder = null;
    private static UpdateOrder currentNewUpdateOrder = null;
	public CustomButton(String text,String changingButtonColor, String buttonLocation) {
		this.text = text;
		addMouseListener(this);
		buttonWidth =200;
		buttonHeight=35;
		setPreferredSize (new Dimension(buttonWidth, buttonHeight));
		setSize(new Dimension(buttonWidth, buttonHeight));
		setMinimumSize(new Dimension(buttonWidth, buttonHeight));
		setMaximumSize(new Dimension(buttonWidth, buttonHeight));
		setFont(new Font("SansSerif", Font.BOLD, 14));
		this.changingButtonColor=changingButtonColor;
		this.buttonLocation=buttonLocation;
		setBorder(null);
		setBackground(new Color (191, 191, 191));
	}
	public CustomButton(String text,String changingButtonColor,int buttonWidth, String buttonLocation) {
		this.text = text;
		addMouseListener(this);
		this.buttonWidth=buttonWidth;
		buttonHeight=35;
		setPreferredSize (new Dimension(buttonWidth, 35));
		setSize(new Dimension(buttonWidth, buttonHeight));
		setMinimumSize(new Dimension(buttonWidth, buttonHeight));
		setMaximumSize(new Dimension(buttonWidth, buttonHeight));
		setFont(new Font("SansSerif", Font.BOLD, 14));
		this.changingButtonColor=changingButtonColor;
		this.buttonLocation=buttonLocation;
		setBorder(null);
		setBackground(new Color (191, 191, 191));
	}
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setBackground(new Color (191, 191, 191));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color (191, 191, 191));
		g2d.fillRect(0, 0, getWidth(), getHeight());
        Color butonColor = new Color(0, 0, 0);
        switch (changingButtonColor){
			case "RED" :
				butonColor = isPressed ? new Color(180, 0, 0) : (isHovered ? new Color(220, 0, 0) : Color.RED);
				
			break;
			case "GREEN" :
				butonColor = isPressed ? new Color(0, 180, 0) : (isHovered ? new Color(0, 220, 0) : Color.GREEN);
			break;
			default:
				butonColor = isPressed ? new Color(0, 0, 180) : (isHovered ? new Color(0, 0, 220) : Color.BLUE);
		}
        g2d.setColor(butonColor);
        g2d.fillRoundRect(0, 0, buttonWidth, buttonHeight, 25, 25);
        g2d.setColor(Color.WHITE);
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (buttonWidth - metrics.stringWidth(text))/ 2;
        int y = (buttonHeight + metrics.getHeight()/2)/2 ;
        g2d.drawString(text, x, y);    
    }
    @Override
    public void mouseClicked(MouseEvent e) {
		switch(text){
			case "Back to Home Page":
					{
					JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
					topFrame.dispose();
					mainWindow.openMainWindow();
					}
					System.out.println("They gave us : "+text);
				break;
			case "Cancel":
			{
				JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
				topFrame.dispose();
			}
			break;
			}

		switch (buttonLocation){
			case "Menu":
				switch (text){
					case "Place Order":
					{
						JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
						topFrame.dispose();

						currentOrderWindow = new PlaceOrderWindow();
						currentOrderWindow.openPlaceOrderWindow();
						System.out.println("They gave us : " + text);
					}
					break;
					case "Search":
					{
						SearchMenu.openSearchMenu();
						System.out.println("They gave us : "+text);
						JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
						topFrame.dispose();
					}
					break;
					case "View Orders":
						{
						ViewOrderMenu.viewOrderMenuMenu();
						System.out.println("They gave us : "+text);
						JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
						topFrame.dispose();
						}
					break;
					case "Update Order Detail":
						{
							currentNewUpdateOrder = new UpdateOrder();
							currentNewUpdateOrder.openUpdateOrderWindow();
							System.out.println("They gave us : "+text);
							JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
							topFrame.dispose();
						}
					break;
					case "Add Customers":
						mainWindow.testData(150);
						System.out.println("They gave us : "+text);
					break;
					case "Exit" :
						InCodeTools.exit();
						System.out.println("They gave us : "+text);
					break;		
				}
			break;
			case "Place Order":
				switch (text){
				case "Confirm Order":
					if (currentOrderWindow != null) {
						long CustID = currentOrderWindow.getCustIDOP();
						String name = "", OrderID = "";
						int BurgurQTY = 0;
						if (CustID != -1) {
							name = currentOrderWindow.getcustNamePO();
							if (!name.equals("-1")) {
								BurgurQTY = currentOrderWindow.getButgurQTY();
								OrderID = currentOrderWindow.getOrderID();
								currentUpdateOrder.placeNewOrder(CustID, name, BurgurQTY, OrderID);
								JOptionPane.showMessageDialog(null, "Order Added Successfuly");
								mainWindow.openMainWindow();
								JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
								topFrame.dispose();
							}
						}
						System.out.println("They gave us : " + CustID + " : " + name + " : " + BurgurQTY + " : " + OrderID);
					} else {
						System.err.println("Error: Order window is not open.");
					}
				break;
				case "Cancel" :
					 {
					JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
					topFrame.dispose();
					mainWindow.openMainWindow();
					}
				break;		
			}
			break;
			case "Search":
				switch(text){
					case "Best Customer":
					{
						System.out.println("They gave us : "+text);
						JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
						topFrame.dispose();
						currentBestCustomer = new SearchBestCustomer();
						currentBestCustomer.openSearchBestCustomer();
					}
					break;
					case "Search Order":
					{
						currentSearchOrder.openSearchOrderDetials();
						System.out.println("They gave us : "+text);
						JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
						topFrame.dispose();
					}
					break;
					case "Search Customer":
						{
						currentCustomerOrder = new SearchCustomerDetails();
						currentCustomerOrder.openSearchCustomerDetials();
						System.out.println("They gave us : "+text);
						JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
						topFrame.dispose();
					}
					break;
				}
			break;
			case "Best Customer":
				switch (text){
					case "Back":
					{
						SearchMenu.openSearchMenu();
						System.out.println("They gave us : "+text);
						JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
						topFrame.dispose();
					}
					break;	
				}
			break;
			case "View":
				switch (text){
					case "Delivered Orders":
						{	
							currentViewOrder =new ViewOrdersAll();
							currentViewOrder.setViewOrderSelection(1);
							currentViewOrder.openViewOrdersAll();
							System.out.println("They gave us : "+text);
							JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
							topFrame.dispose();
						}
					break;
					case "Preparing Orders":
						{	
							currentViewOrder =new ViewOrdersAll();
							currentViewOrder.setViewOrderSelection(0);
							currentViewOrder.openViewOrdersAll();
							System.out.println("They gave us : "+text);
							JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
							topFrame.dispose();
						}
					break;
					case "Cancelled Orders":
						{	
							currentViewOrder =new ViewOrdersAll();
							currentViewOrder.setViewOrderSelection(2);
							currentViewOrder.openViewOrdersAll();
							System.out.println("They gave us : "+text);
							JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
							topFrame.dispose();
						}
						break;
					case "Back":
						{
							ViewOrderMenu.viewOrderMenuMenu();
							System.out.println("They gave us : "+text);
							JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
							topFrame.dispose();
						}
					break;
					}
				break;
				case "Update Order":
					switch(text){
					case "Update Order":
					{
						System.out.println("They gave us : "+text);

						int idArrayNumber = currentNewUpdateOrder.getArrayID();
						
						if(currentNewUpdateOrder.getStatus()!=-1){
							database.setOrderStatusSOD(currentNewUpdateOrder.getStatus(),idArrayNumber);	
						}
						if(currentNewUpdateOrder.getButgurQTY()!=-1){
							database.setBurgerNumberSOD(currentNewUpdateOrder.getButgurQTY(),idArrayNumber);
						}
						System.out.println("They gave us : " + idArrayNumber + " : " + currentNewUpdateOrder.getStatus() + " : " + currentNewUpdateOrder.getButgurQTY());
						JOptionPane.showMessageDialog(null, "Order Updated Successfuly");
						mainWindow.openMainWindow();
						JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
						topFrame.dispose();
					}
					break;	
				}
			}
    }
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
}
