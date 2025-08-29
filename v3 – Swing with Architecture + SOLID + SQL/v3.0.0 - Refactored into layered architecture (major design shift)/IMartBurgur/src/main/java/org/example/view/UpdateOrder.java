package org.example.view;

import org.example.repository.BurgerShopDatabase;
import org.example.config.FoodMenu;
import org.example.repository.OurDataBase;
import org.example.elements.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class UpdateOrder {
	private BurgerShopDatabase database = OurDataBase.SHARED_DB;
	private BurgurTextField customerIDEntered= new BurgurTextField();
	private BurgurTextField customerNameIDGenerated= new BurgurTextField();
	private BurgurTextField burgurQTYField= new BurgurTextField();
	private BurgurTextField orderIDSearch= new BurgurTextField();
	private BurgurTextField burgerTotalOrder = new BurgurTextField();
	private JCheckBox checkOrderSatus = new JCheckBox("Updte Order Status");
	private JCheckBox checkOrderQTY = new JCheckBox("Updte Order Quantity");
	private String[] fruits = { "PREPARING", "DELIVERED", "CANCEL" };
    private JComboBox<String>  orderStatus = new JComboBox<>(fruits);
	private BurgurTextLables warningText = new BurgurTextLables();
	private BurgurTextLables warningTextBurgurNumber = new BurgurTextLables();
	private static int orderPositionX =-1;

public void openUpdateOrderWindow() {
    BurgurFrame frame = new BurgurFrame("Burgur Shop");
    setDisableInputs();
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
    centerPanel.setBorder(new EmptyBorder(5, 60, 5, 0));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 5, 10, 5);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    int row = 0;

    //Order ID
    gbc.gridx = 0; gbc.gridy = row;
    BurgurTextLables orderIDLable = new BurgurTextLables();
    orderIDLable.setText("Order ID");
    centerPanel.add(orderIDLable, gbc);

    gbc.gridx = 1;
    centerPanel.add(orderIDSearch, gbc);
    
    JPanel fixedPanel = new JPanel();
	fixedPanel.setPreferredSize(new Dimension(280, 50));
	warningText.setText(" ");
	warningText.setFont(new Font("Monospaced", Font.PLAIN, 14));
	warningText.setForeground(new Color (255,0,0));
	warningText.setVerticalAlignment (JLabel.CENTER); 
	warningText.setHorizontalAlignment (JLabel.CENTER);
	gbc.gridx = 2;
	fixedPanel.add(warningText);
	centerPanel.add(fixedPanel, gbc);
    row++;
    
    gbc.gridx = 0; gbc.gridy = row;
    
    //Order status
    BurgurTextLables orderStatusLabel = new BurgurTextLables();
    orderStatusLabel.setText("Order Status");
    centerPanel.add(orderStatusLabel, gbc);

    gbc.gridx = 1;
        
	orderStatus.setPreferredSize(new Dimension(200, 40));
	orderStatus.setFont(new Font("Serif", Font.BOLD, 18));
	orderStatus.setForeground(new Color (0,0,0));
	orderStatus.setBackground(new Color (255,255,255));

    centerPanel.add(orderStatus, gbc);
    gbc.gridx = 2;
    checkOrderSatus.setVisible(false);
    centerPanel.add( checkOrderSatus, gbc);
   
    row++;

    //Customer ID
    gbc.gridx = 0; gbc.gridy = row;
    BurgurTextLables customerIDLable = new BurgurTextLables();
    customerIDLable.setText("Customer ID");
    centerPanel.add(customerIDLable, gbc);

    gbc.gridx = 1;
    customerIDEntered.setEditable(false);
    centerPanel.add(customerIDEntered, gbc);  // Assuming `customerIDEntered` is a BurgurTextField
    row++;

    //Customer Name
    gbc.gridx = 0; gbc.gridy = row;
    BurgurTextLables customerNameIDLable = new BurgurTextLables();
    customerNameIDLable.setText("Customer Name");
    centerPanel.add(customerNameIDLable, gbc);

    gbc.gridx = 1;
    customerNameIDGenerated.setEditable(false);
    centerPanel.add(customerNameIDGenerated, gbc);  
    row++;

    //Line
    gbc.gridx = 0; gbc.gridy = row;
    gbc.gridwidth = 2;
    centerPanel.add(new DrawLines(), gbc);
    gbc.gridwidth = 1;
    row++;

    gbc.gridx = 0; gbc.gridy = row;
    BurgurTextLables burgerQTYLabel = new BurgurTextLables();
    burgerQTYLabel.setText("Burger Quantity");
    centerPanel.add(burgerQTYLabel, gbc);

    gbc.gridx = 1;
    centerPanel.add(burgurQTYField, gbc); 
    
    gbc.gridx = 2;
    checkOrderQTY.setVisible(false);
    centerPanel.add(checkOrderQTY, gbc);
    
    JPanel fixedPanel2 = new JPanel();
	fixedPanel.setPreferredSize(new Dimension(280, 50));
	warningTextBurgurNumber.setText(" ");
	warningTextBurgurNumber.setFont(new Font("Monospaced", Font.PLAIN, 16));
	warningTextBurgurNumber.setForeground(new Color (255,0,0));
	warningTextBurgurNumber.setVerticalAlignment (JLabel.CENTER); 
	warningTextBurgurNumber.setHorizontalAlignment (JLabel.CENTER);
	
	gbc.gridx = 3;
	fixedPanel.add(warningTextBurgurNumber);
	centerPanel.add(fixedPanel2, gbc);
    row++;

   

    // Rightside buttons
    BurgurPanelCover rightSide = new BurgurPanelCover(CommonColors.commonBackgroundColor, 200, 0);
    rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
    rightSide.setBorder(new EmptyBorder(10, 5, 5, 5));

    CustomButton confirmOrder = new CustomButton("Update Order", "GREEN", 175);
    CustomButton backToHomePagePO = new CustomButton("Back to Home Page", "RED", 175);
    CustomButton cancelPO = new CustomButton("Cancel", "RED", 175);

    rightSide.add(Box.createRigidArea(new Dimension(10, 100)));
    rightSide.add(confirmOrder);
    rightSide.add(Box.createRigidArea(new Dimension(10, 10)));
    rightSide.add(backToHomePagePO);
    rightSide.add(Box.createRigidArea(new Dimension(10, 10)));
    rightSide.add(cancelPO);


	gbc.gridx = 0; gbc.gridy = row;
    BurgurTextLables burgerTotalLable = new BurgurTextLables();
    burgerTotalLable.setText("NET TOTAL : ");
    centerPanel.add(burgerTotalLable, gbc);

    gbc.gridx = 1;
    burgerTotalOrder.setText("0.00");
    burgerTotalOrder.setEditable(false);
    centerPanel.add(burgerTotalOrder, gbc); 
   
    rightSideMenu.add(centerPanel, BorderLayout.CENTER);
    rightSideMenu.add(rightSide, BorderLayout.EAST);

    frame.add(rightSideMenu);
    frame.pack();
    frame.setVisible(true);
    
    Document docTotal = (burgurQTYField.getDocument());
    docTotal.addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) {
			burgerTotalOrder.setText(""+liveTotal());
        }

        public void removeUpdate(DocumentEvent e) {
            burgerTotalOrder.setText(""+liveTotal());
        }

        public void changedUpdate(DocumentEvent e) {
            burgerTotalOrder.setText(""+liveTotal());
        }
    });
    Document doc = orderIDSearch.getDocument();
		doc.addDocumentListener(new DocumentListener() {
			@Override
                public void insertUpdate(DocumentEvent e) {
					resetValuesShowed();
					warningText.setForeground(new Color (255,0,0));
					getDetailsOrder();
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
					resetValuesShowed();
					warningText.setForeground(new Color (255,0,0));
					getDetailsOrder();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
					resetValuesShowed();
					warningText.setForeground(new Color (255,0,0));
					getDetailsOrder();
                }
			});

}
public void getDetailsOrder(){
		String orderIDWhat = orderIDSearch.getText().trim().toUpperCase();
		System.out.println("Start :"+orderIDWhat);
		int id=2,gotit=0,profid =0;
		if(database.getLatestOrder()>0){
			if (orderIDWhat.isEmpty()) {
				warningText.setText("Empty Field or Empty Databse");

			} else {
				char char1 = orderIDWhat.charAt(0);
				if (char1 != 'B') {
					warningText.setText("Order ID must start with 'B'");

				} else if (orderIDWhat.length() < 5) {
					warningText.setText("ID length is shorter");

				} else if (orderIDWhat.length() > 5) {
					warningText.setText("ID length is longer");

				} else if (orderIDWhat.matches("[B]{1}[0]{4}")) {
					warningText.setText("Order ID Reseted"); 
					System.out.println("Loop :"+orderIDWhat);

					customerIDEntered.setText("");
					customerNameIDGenerated.setText("");
					burgurQTYField.setText("");
					burgerTotalOrder.setText("0.00");
				}else if (orderIDWhat.matches("[B]{1}\\d{4}")) {
					warningText.setForeground(new Color (0,255,0));
					warningText.setText("Order ID Confirmed");
					char char2 = orderIDWhat.charAt(1);
					char char3 = orderIDWhat.charAt(2);
					char char4 = orderIDWhat.charAt(3);
					char char5 = orderIDWhat.charAt(4);
					String checkString = ""+char2+char3+char4+(char5);
					int checkInt = (Integer.parseInt(checkString)-1);
					if(checkInt<=database.getLatestOrder()){
						System.out.println(checkInt);
						if (database.getOrderIDStringSOD(checkInt).equals(orderIDWhat)) {
								id=checkInt;
								gotit=1;
								System.out.println("Found You :"+orderIDWhat);
						}else{  
							for (int i = 1; i <=database.getLatestOrder(); i++) {
								
								System.out.println("Comparing with DB ID: " + database.getOrderStatusSOD(i));
								if (database.getOrderIDStringSOD(i).equals(orderIDWhat)) {
										id=i;
										gotit=1;
										break;
									}	
								}
							}
					}
				String orderStatusDetails = "";
					if(gotit==1){
						int goodToGo =0;
						switch (database.getOrderStatusSOD(id)){
							case 0:
								orderStatusDetails="PREPARING";
								orderStatus.setSelectedItem("PREPARING");
								goodToGo = 1;
							break;
							case 1:
								orderStatusDetails="DELIVERED";
								checkBoxVisibility();
								orderStatus.setSelectedItem("DELIVERED");
								setDisableInputs();
								warningText.setForeground(new Color (255,0,0));
								warningText.setText("Order Cannot Be Updated"); 
							break;		
							case 2:
								orderStatusDetails="CANCEL";
								checkBoxVisibility();	
								orderStatus.setSelectedItem("CANCEL");
								setDisableInputs();	
								warningText.setForeground(new Color (255,0,0));
								warningText.setText("Order Cannot Be Updated"); 
							break;
						}
						if(goodToGo==1){
							checkUpdatateSelection();
							setArrayID(id);
						for(int i=0;i<database.getLatestProfile();i++){
							if(database.getProfCustIDSOD(i)==database.getOrderCustIDSOD(id)){
								profid=i;
							}
						}
						InCodeTools.printExtra(3,2);
						InCodeTools.printExtra(3,3);
						InCodeTools.printExtra(92,1);
						System.out.printf(" %-1s | %-10s   %-15s  %-15s %-10s  %-15s %-15s|\n", "", "Order ID", "Customer ID", "Name","Quantity","Order Value","Order Statue");
						InCodeTools.printExtra(3,3);
						InCodeTools.printExtra(92,1);
						System.out.printf(" %-1s | %-10s   %-15s  %-15s %-10s  %-15s %-15s|\n", "",database.getOrderIDStringSOD(id),"0"+database.getProfCustIDSOD(profid), database.getCustNameSOD(profid),database.getBurgerNumberSOD(id),(FoodMenu.BURGERPRICE*database.getBurgerNumberSOD(id)),orderStatusDetails);
						InCodeTools.printExtra(3,3);
						InCodeTools.printExtra(92,1);
						//orderIDSearch.setText(""+database.getProfCustIDSOD(profid));
						customerIDEntered.setText(""+database.getProfCustIDSOD(profid));
						customerNameIDGenerated.setText(database.getCustNameSOD(profid));
						burgurQTYField.setText(""+database.getBurgerNumberSOD(id));
						burgerTotalOrder.setText(""+(FoodMenu.BURGERPRICE*database.getBurgerNumberSOD(id)));
						//itemLable1.setText(""+database.getProfCustIDSOD(profid));
						//itemLable2.setText(database.getCustNameSOD(profid));
						//itemLable3.setText(""+database.getBurgerNumberSOD(id));
						//itemLable4.setText(""+(FoodMenu.BURGERPRICE*database.getBurgerNumberSOD(id)));
						//itemLable5.setText(orderStatusDetails);
					}
					}else{
						warningText.setForeground(new Color (255,0,0));
						warningText.setText("Entered Order Does not Exists");
					}
			} else {
				warningText.setForeground(new Color (255,0,0));
				warningText.setText("Invalid format. Use B0000");
			}
		}
	}else{
			warningText.setForeground(new Color (255,0,0));
			warningText.setText("Empty Database");
	}
}
	private void setDisableInputs(){
		orderStatus.setEnabled(false);
		burgurQTYField.setEditable(false);
	}
	private void checkUpdatateSelection(){
		checkOrderQTY.setVisible(true);
		checkOrderQTY.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkOrderQTY.isSelected()){
					burgurQTYField.setEnabled(true);
					burgurQTYField.setEditable(true);
				}else{
					burgurQTYField.setEnabled(false);
					burgurQTYField.setEditable(false);
				}
			}
		});
		checkOrderSatus.setVisible(true);
		checkOrderSatus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkOrderSatus.isSelected()){
					orderStatus.setEnabled(true);
				}else{
					orderStatus.setEnabled(false);
				}
			}
		});
	}
	private double liveTotal(){
		int count =0;
		String testQYT = burgurQTYField.getText().trim();
		try {
			count = Integer.parseInt(testQYT);
		} catch (NumberFormatException e) {
		}
		double total = count*FoodMenu.BURGERPRICE;
		return total;
	}

	private void resetValuesShowed(){
		customerIDEntered.setText("");
		customerNameIDGenerated.setText("");
		burgurQTYField.setText("");
		burgerTotalOrder.setText("0.00");	
		checkBoxVisibility();
	}
	private void checkBoxVisibility(){
		checkOrderQTY.setVisible(false);
		checkOrderQTY.setSelected(false);
		checkOrderSatus.setVisible(false);
		checkOrderSatus.setSelected(false);	
	}
	private void setArrayID(int id){
		orderPositionX = id;
	}
	public int getArrayID(){
		return orderPositionX;
	}
	public int getStatus(){
		int statusInt= -1;
		String selected = (String) orderStatus.getSelectedItem();
		System.out.println("Selected: " + selected);
		switch (selected){
				case "PREPARING":
					statusInt =0;
				break;
				case "DELIVERED":
					statusInt =1;
				break;
				case "CANCEL":
					statusInt =2;
				break;
			}
		return statusInt;
	}
	public int getButgurQTY() {
		int BurgerQTYPO=-1;
		if (burgurQTYField == null || burgurQTYField.getText().trim().isEmpty()) {
				warningTextBurgurNumber.setForeground(new Color (255,0,0));
				warningTextBurgurNumber.setText( "Please Enter Burgur Number");
		}else{
			String testQYT = burgurQTYField.getText().trim();
			
			try {
				BurgerQTYPO = Integer.parseInt(testQYT);
			} catch (NumberFormatException e) {
				warningTextBurgurNumber.setForeground(new Color (255,0,0));
				warningTextBurgurNumber.setText( "Please Enter Valid Burgur Number");
			}
			if (BurgerQTYPO <= 0) {
				warningTextBurgurNumber.setForeground(new Color (255,0,0));
				warningTextBurgurNumber.setText( "Please Enter a Postive Number");
			}
		}
		return BurgerQTYPO;
	}
	
}
