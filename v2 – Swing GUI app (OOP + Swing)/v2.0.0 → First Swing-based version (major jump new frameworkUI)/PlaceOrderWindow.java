import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public class PlaceOrderWindow {
	private BurgerShopDatabase database = OurDataBase.SHARED_DB;
	private String orderIDPO;
	private long custIDPO=0;
	private String custNamePO="";
	private int burgerQTYPO=0;
	private BurgurTextField customerIDEntered= new BurgurTextField();
	private BurgurTextField customerNamePO= new BurgurTextField();
	private BurgurTextField BurgurQTY= new BurgurTextField();
	private int LatestOrder=database.getLatestOrder();
	private int LatestProfile=database.getLatestProfile();

	private String generateOrderId(){
			String zz ="";
			if(LatestOrder>99){
				zz+="0";
			}else if(LatestOrder>9){
				zz+="00";
			}else if(LatestOrder>=0){
				zz+="000";
			}
			orderIDPO = "B"+zz+(LatestOrder+1);
			return orderIDPO;
	}
public void openPlaceOrderWindow() {
    BurgurFrame frame = new BurgurFrame("Burgur Shop");
    frame.invalidate();
    frame.validate();
    frame.repaint();

    JPanel rightSideMenu = new JPanel(new BorderLayout());

    BurgurPanelCover topTitle = new BurgurPanelCover(Color.RED);
    BurgurTextLables title = new BurgurTextLables();
    title.setBorder(new EmptyBorder(5, 10, 10, 10));
    title.setText("Place Order");
    title.setForeground(Color.WHITE);
    title.setFont(new Font("Serif", Font.PLAIN, 45));
    title.setVerticalAlignment(JLabel.CENTER);
    title.setHorizontalAlignment(JLabel.CENTER);
    topTitle.add(title);
    rightSideMenu.add(topTitle, BorderLayout.NORTH);

    JPanel centerPanel = new JPanel(new GridBagLayout());
    centerPanel.setBorder(new EmptyBorder(30, 60, 30, 0));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    int row = 0;

    //Order ID
    gbc.gridx = 0; gbc.gridy = row;
    BurgurTextLables orderIDLable = new BurgurTextLables();
    orderIDLable.setText("Order ID");
    centerPanel.add(orderIDLable, gbc);

    gbc.gridx = 1;
    BurgurTextLables orderIDGenerated = new BurgurTextLables();
    orderIDGenerated.setText(": " + generateOrderId());
    centerPanel.add(orderIDGenerated, gbc);
    row++;

    //Cust ID
    gbc.gridx = 0; gbc.gridy = row;
    BurgurTextLables customerIDLable = new BurgurTextLables();
    customerIDLable.setText("Customer ID");
    centerPanel.add(customerIDLable, gbc);

    gbc.gridx = 1;
    centerPanel.add(customerIDEntered, gbc); 
    row++;

    //Cust Name
    gbc.gridx = 0; gbc.gridy = row;
    BurgurTextLables customerNameIDLable = new BurgurTextLables();
    customerNameIDLable.setText("Customer Name");
    centerPanel.add(customerNameIDLable, gbc);

    gbc.gridx = 1;
    centerPanel.add(customerNamePO, gbc); 
    row++;

    //line
    gbc.gridx = 0; gbc.gridy = row;
    gbc.gridwidth = 2;
    centerPanel.add(new DrawLines(), gbc);
    gbc.gridwidth = 1;
    row++;

    //Burger QTY
    gbc.gridx = 0; gbc.gridy = row;
    BurgurTextLables burgerQTYLabel = new BurgurTextLables();
    burgerQTYLabel.setText("Burger Quantity");
    centerPanel.add(burgerQTYLabel, gbc);

    gbc.gridx = 1;
    centerPanel.add(BurgurQTY, gbc); 
    row++;

    gbc.gridx = 0; gbc.gridy = row;
    BurgurTextLables orderStatusLabel = new BurgurTextLables();
    orderStatusLabel.setText("Order Status");
    centerPanel.add(orderStatusLabel, gbc);

    gbc.gridx = 1;
    BurgurTextField orderStatus = new BurgurTextField();
    orderStatus.setText("     PREPARING");
    orderStatus.setEditable(false);
    centerPanel.add(orderStatus, gbc);
    row++;

    // Rightside buttons
    BurgurPanelCover rightSide = new BurgurPanelCover(CommonColors.commonBackgroundColor, 200, 0);
    rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
    rightSide.setBorder(new EmptyBorder(10, 5, 5, 5));

    CustomButton confirmOrder = new CustomButton("Confirm Order", "GREEN", 175, "Place Order");
    CustomButton backToHomePagePO = new CustomButton("Back to Home Page", "RED", 175, "Place Order");
    CustomButton cancelPO = new CustomButton("Cancel", "RED", 175, "Place Order");

    rightSide.add(Box.createRigidArea(new Dimension(10, 100)));
    rightSide.add(confirmOrder);
    rightSide.add(Box.createRigidArea(new Dimension(10, 10)));
    rightSide.add(backToHomePagePO);
    rightSide.add(Box.createRigidArea(new Dimension(10, 10)));
    rightSide.add(cancelPO);

    // Bottom panel
    BurgurPanelCover bottomTotal = new BurgurPanelCover(CommonColors.commonBackgroundColor, 0, 50);
    bottomTotal.setBorder(new EmptyBorder(5, 5, 5, 5));
    BurgurTextLables burgerTotalPO = new BurgurTextLables();
    burgerTotalPO.setText("NET TOTAL : 0.0");

    bottomTotal.add(burgerTotalPO);

    Document doc = BurgurQTY.getDocument();
    doc.addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) {
            burgerTotalPO.setText("NET TOTAL : " + liveTotal());
        }

        public void removeUpdate(DocumentEvent e) {
            burgerTotalPO.setText("NET TOTAL : " + liveTotal());
        }

        public void changedUpdate(DocumentEvent e) {
            burgerTotalPO.setText("NET TOTAL : " + liveTotal());
        }
    });


    rightSideMenu.add(centerPanel, BorderLayout.CENTER);
    rightSideMenu.add(rightSide, BorderLayout.EAST);
    rightSideMenu.add(bottomTotal, BorderLayout.SOUTH);
    frame.add(rightSideMenu);
    frame.pack();
    frame.setVisible(true);
    
     Document docID = customerIDEntered.getDocument();
        docID.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
				customerNamePO.setText("");
				customerNamePO.setEditable(true);
				getCustIDCD();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
				customerNamePO.setText("");
				customerNamePO.setEditable(true);
                getCustIDCD();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
				customerNamePO.setText("");
				customerNamePO.setEditable(true);
                getCustIDCD();
            }
        });
}
public void getCustIDFound(long customerID) {
    int approvedID = 1;
    System.out.println("We Got the Cust ID : " + customerID);
    if (approvedID == 1) {
        int n = LatestOrder, nn, gh = 0, prof = -1, gotit = 0;
        int[] values = new int[n]; // selected order numbers
        for (int i = 0; i < n; i++) {
            if (customerID == database.getOrderCustIDSOD(i)) {
                values[gh] = i;
                gh++;
            }
        }
        for (int i = 0; i < LatestProfile; i++) {
            if (database.getProfCustIDSOD(i) == customerID) {
                gotit = 1;
                prof = i;
                break;
            }
        }

        if (gotit == 1) {
            System.out.println("\n\n  Customer ID found");
            InCodeTools.printExtra(2, 3);
            InCodeTools.printExtra(62, 1);
            System.out.println("\n   Customer ID - " + "0" + customerID);
            System.out.println("   Name        - " + database.getCustNameSOD(prof));
            customerNamePO.setText(database.getCustNameSOD(prof));
            customerNamePO.setEditable(false);
            
            System.out.println("\n\nCustomer Order List");
            System.out.println("=====================\n");
        } 
    }
}
public void getCustIDCD() {
		long custIDPO;
		String customerIDText = customerIDEntered.getText().trim();

		if (customerIDText == null || customerIDText.isEmpty()) {
			return; 
		}
		if (customerIDText.matches("[0]{10}")) {
			System.out.println("Reset Code :" + customerIDText);
			return; 
		}
		if (customerIDText.length() < 1) {
			return;
		}
		char firstNumber = customerIDText.charAt(0);
		if (firstNumber == '0') {
			if (customerIDText.length() != 10) {
				return;
			}
			try {
				custIDPO = Long.parseLong(customerIDText);
				getCustIDFound(custIDPO);
			} catch (NumberFormatException e) {
			}
		} else {
		}
	}

	private double liveTotal(){
		int count =0;
		String testQYT = BurgurQTY.getText().trim();
		try {
			count = Integer.parseInt(testQYT);
		} catch (NumberFormatException e) {
		}
		double total = count*FoodMenu.BURGERPRICE;
		return total;
	}
	public long getCustIDOP() {
    String customerIDText = customerIDEntered.getText();
    if (customerIDText == null || customerIDText.trim().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Customer ID is Empty");
			return -1;
		}
		char firstNumber = customerIDText.trim().charAt(0);
		if (firstNumber == '0') {
			if (customerIDText.length() != 10) {
				JOptionPane.showMessageDialog(null, "Customer ID Invalid Length");
				return -1;
			}
			try {
				custIDPO = Long.parseLong(customerIDText.trim());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Customer ID must be a number.");
				return -1;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Customer ID must start with '0'");
			return -1;
		}

		return custIDPO;
	}
	public String getcustNamePO(){
		if (customerNamePO == null || customerNamePO.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please Enter Customer Name");
			return "-1";
		}
		custNamePO = customerNamePO.getText();
		return custNamePO;
	}
	public int getButgurQTY() {
    if (BurgurQTY == null || BurgurQTY.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please Enter Burgur Number");
			return -1;
		}
		String testQYT = BurgurQTY.getText().trim();
		int BurgerQTYPO;
		try {
			BurgerQTYPO = Integer.parseInt(testQYT);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please enter a valid number for Burger Quantity");
			return -1;
		}
		if (BurgerQTYPO <= 0) {
			JOptionPane.showMessageDialog(null, "Please enter a positive quantity");
			return -1;
		}
		return BurgerQTYPO;
	}
	public String getOrderID() {
		return orderIDPO;
	}

}
