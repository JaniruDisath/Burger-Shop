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
public class SearchOrderDetails{

	private BurgurTextField orderIDSearch= new BurgurTextField();
	private BurgurTextField developerOptions= new BurgurTextField();
	private BurgurTextLables warningText =null;
	private BurgurTextLables itemLable1 =null;
	private BurgurTextLables itemLable2 =null;
	private BurgurTextLables itemLable3 =null;
	private BurgurTextLables itemLable4 =null;
	private BurgurTextLables itemLable5 =null;
	private int got =0;
	private CustomButton backtoHomePageS = new CustomButton("Back to Home Page","GREEN",200);
	private BurgerShopDatabase database = OurDataBase.SHARED_DB;
	public void openSearchOrderDetials(){	
		BurgurFrame frame = new BurgurFrame("Burgur Shop");
		JPanel rightSideMenu = new JPanel();
		rightSideMenu.setLayout(new BorderLayout());
		rightSideMenu.setBackground(CommonColors.commonBackgroundColor);
		//Center
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		//Reset
		orderIDSearch.setText("");
		developerOptions.setText("");
		//
		GridBagConstraints centerPanelLayout = new GridBagConstraints();
		centerPanelLayout.anchor = GridBagConstraints.NORTH;
		centerPanelLayout.insets = new Insets(10, 10, 10, 10);
		
		JPanel orderIDPanel = new JPanel();	

		{
		orderIDPanel.setLayout(new GridBagLayout());

		GridBagConstraints GridLayout = new GridBagConstraints();
		GridLayout.insets = new Insets(0, 10, 0, 10);
		GridLayout.anchor = GridBagConstraints.EAST;

		BurgurTextLables enterOrderID = new BurgurTextLables();
		enterOrderID.setText("Enter Order ID : ");
		GridLayout.gridx = 0;
		GridLayout.gridy = 0;
		orderIDPanel.add(enterOrderID, GridLayout);

		GridLayout.gridx = 1; 

		GridLayout.anchor = GridBagConstraints.WEST;
		orderIDPanel.add(orderIDSearch, GridLayout);
		
		GridLayout.gridx = 2; 
		developerOptions.setVisible(false);
		orderIDPanel.add(developerOptions, GridLayout);
		
		JPanel fixedPanel = new JPanel();
		fixedPanel.setPreferredSize(new Dimension(300, 0));
		warningText = new BurgurTextLables();
		warningText.setText("         ");
		warningText.setFont(new Font("Monospaced", Font.PLAIN, 16));
		warningText.setForeground(new Color (255,0,0));
		warningText.setVerticalAlignment (JLabel.CENTER); 
		warningText.setHorizontalAlignment (JLabel.CENTER);
		GridLayout.gridx = 1;
		GridLayout.gridy = 1;
		GridLayout.ipadx = 100;
		GridLayout.ipady = 50;
		GridLayout.fill = GridBagConstraints.NONE;
		fixedPanel.add(warningText);
		orderIDPanel.add(fixedPanel, GridLayout);
		
		Document doc = orderIDSearch.getDocument();
            doc.addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
					resetShowedValues();
					developerOptions.setVisible(false);
					warningText.setForeground(new Color (255,0,0));
					getDetailsOrder();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
					resetShowedValues();
					developerOptions.setVisible(false);
					warningText.setForeground(new Color (255,0,0));
					getDetailsOrder();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
					resetShowedValues();
					developerOptions.setVisible(false);
					warningText.setForeground(new Color (255,0,0));
					getDetailsOrder();
                }
			});
		
		}
		
		centerPanelLayout.gridx = 0;
		centerPanelLayout.gridy = 0;
		centerPanel.add(orderIDPanel, centerPanelLayout); 
		

		
		JPanel cutomerIDPanel = new JPanel();
		{
		cutomerIDPanel.setLayout(new GridBagLayout());

		GridBagConstraints GridLayout = new GridBagConstraints();
		GridLayout.insets = new Insets(0, 10, 10, 10);
		GridLayout.anchor = GridBagConstraints.EAST;
		
		//Customer ID
		BurgurTextLables itemNameLable1 = new BurgurTextLables();
		itemNameLable1.setText("Customer ID : ");
		GridLayout.gridx = 0;
		GridLayout.gridy = 0;
		cutomerIDPanel.add(itemNameLable1, GridLayout);

		//Name
		BurgurTextLables itemNameLable2 = new BurgurTextLables();
		itemNameLable2.setText("Name : ");
		GridLayout.gridx = 0;
		GridLayout.gridy = 1;
		cutomerIDPanel.add(itemNameLable2, GridLayout);

		//QTY
		BurgurTextLables itemNameLable3 = new BurgurTextLables();
		itemNameLable3.setText("Burgur Quantity : ");
		GridLayout.gridx = 0;
		GridLayout.gridy = 2;
		cutomerIDPanel.add(itemNameLable3, GridLayout);
		
		//Total
		BurgurTextLables itemNameLable4 = new BurgurTextLables();
		itemNameLable4.setText("Total Price : ");
		GridLayout.gridx = 0;
		GridLayout.gridy = 3;
		cutomerIDPanel.add(itemNameLable4, GridLayout);
		
		//Order status
		BurgurTextLables itemNameLable5 = new BurgurTextLables();
		itemNameLable5.setText("Order Status : ");
		GridLayout.gridx = 0;
		GridLayout.gridy = 4;
		cutomerIDPanel.add(itemNameLable5, GridLayout);
				
		//set Text lables
		GridLayout.anchor = GridBagConstraints.WEST;
		
		itemLable1 = new BurgurTextLables();
		itemLable1.setText("");
		GridLayout.gridx = 1;
		GridLayout.gridy = 0;
		cutomerIDPanel.add(itemLable1, GridLayout);
		
		itemLable2 = new BurgurTextLables();
		itemLable2.setText("");
		GridLayout.gridx = 1;
		GridLayout.gridy = 1;
		cutomerIDPanel.add(itemLable2, GridLayout);
	
		 itemLable3 = new BurgurTextLables();
		itemLable3.setText("");
		GridLayout.gridx = 1;
		GridLayout.gridy = 2;
		cutomerIDPanel.add(itemLable3, GridLayout);
		
		itemLable4 = new BurgurTextLables();
		itemLable4.setText("0.0");
		GridLayout.gridx = 1;
		GridLayout.gridy = 3;
		cutomerIDPanel.add(itemLable4, GridLayout);
		
		itemLable5 = new BurgurTextLables();
		itemLable5.setText("");
		GridLayout.gridx = 1;
		GridLayout.gridy = 4;
		cutomerIDPanel.add(itemLable5, GridLayout);
		
		

		}
		centerPanelLayout.gridx = 0;
		centerPanelLayout.gridy = 1;
		centerPanel.add(cutomerIDPanel, centerPanelLayout); 
		
		
		centerPanelLayout.gridx = 0;
		centerPanelLayout.gridy = 1;
		centerPanel.add(cutomerIDPanel, centerPanelLayout); 
		
		
		//Top Title
		BurgurPanelCover topTitle = new BurgurPanelCover(Color.RED);
		BurgurTextLables title = new BurgurTextLables(); 
		title.setBorder(new EmptyBorder(10, 10, 10, 10));
		title.setText("Search Order");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("SansSerif", Font.BOLD, 40));
		title.setVerticalAlignment (BurgurTextLables.CENTER); 
		title.setHorizontalAlignment (BurgurTextLables.CENTER);
		topTitle.add(title);
		
		rightSideMenu.add(topTitle, BorderLayout.NORTH);
		rightSideMenu.add(centerPanel, BorderLayout.CENTER);
		//Bottom Panel
		BurgurPanelCover backtoHomePagePanel =new BurgurPanelCover();
		backtoHomePagePanel.setLayout(new GridBagLayout());
		GridBagConstraints GridBagLayout = new GridBagConstraints();
		GridBagLayout.anchor = GridBagConstraints.EAST;
		GridBagLayout.insets = new Insets(10, 10, 10, 10);
		//Back Home

		backtoHomePagePanel.add(Box.createRigidArea(new Dimension(20, 0)));
		//Back
		CustomButton backto = new CustomButton("Back","GREEN",200);
		GridBagLayout.gridx = 0; GridBagLayout.gridy = 0;
		backtoHomePagePanel.add(backto,GridBagLayout);
		GridBagLayout.gridx = 1; 
		backtoHomePagePanel.add(backtoHomePageS,GridBagLayout);
		rightSideMenu.add(backtoHomePagePanel,BorderLayout.SOUTH);

		frame.add(rightSideMenu);
		frame.pack();
        frame.setVisible(true);
        
        Document doc1 = developerOptions.getDocument();
            doc1.addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
					setDeveloperOptions();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
					setDeveloperOptions();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
					setDeveloperOptions();
                }
			});
	}
	public void getDetailsOrder(){
		String orderIDWhat = orderIDSearch.getText().trim().toUpperCase();
		System.out.println("Start :"+orderIDWhat);
		int id=2,gotit=0,profid =0;;
			if (orderIDWhat.isEmpty()) {
				warningText.setText("Empty Field or Empty Databse");
			} else {
				char char1 = orderIDWhat.charAt(0);
				if (char1 != 'B') {
					warningText.setText("Order ID must start with 'B'");
				}else if (orderIDWhat.matches("[B]{1}[-]{1}[D]{1}[0]{4}[U]{1}")){
					developerOptions.setVisible(true);
					warningText.setText("Please Enter Developer PassCode"); 
					System.out.println("Login : ADMIN");
					itemLable1.setText("Master Developer");
					itemLable2.setText("Login \\_");
					itemLable3.setText("");
					itemLable4.setText("");
					itemLable5.setText("Developer Access");
					setDeveloperOptions();
					
				} else if (orderIDWhat.length() < 5) {
					warningText.setText("ID length is shorter");
				} else if (orderIDWhat.length() > 5) {
					warningText.setText("ID length is longer");
				} else if (orderIDWhat.matches("[B]{1}[0]{4}")) {
					warningText.setText("Order ID Reseted"); 
					System.out.println("Loop :"+orderIDWhat);
					itemLable1.setText(""+0);
					itemLable2.setText(orderIDWhat);
					itemLable3.setText(""+0);
					itemLable4.setText(""+(FoodMenu.BURGERPRICE*0));
					itemLable5.setText("RESETED");
					got=0;
				}else if (orderIDWhat.matches("[B]{1}\\d{4}")) {
					warningText.setForeground(new Color (0,255,0));
					warningText.setText("Order ID Confirmed");
					char char2 = orderIDWhat.charAt(1);
					char char3 = orderIDWhat.charAt(2);
					char char4 = orderIDWhat.charAt(3);
					char char5 = orderIDWhat.charAt(4);
					String checkString = ""+char2+char3+char4+(char5);
					int checkInt = (Integer.parseInt(checkString)-1);
					if(checkInt<=database.getLatestOrder()-1){
						System.out.println(checkInt);
						if (database.getOrderIDStringSOD(checkInt).equals(orderIDWhat)) {
								id=checkInt;
								gotit=1;
								System.out.println("Found You :"+orderIDWhat);
						}else{  
							for (int i = 1; i <=database.getLatestOrder()-1; i++) {
								
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
					if(gotit==1 ){
						switch (database.getOrderStatusSOD(id)){
							case 0:
							orderStatusDetails="PREPARING";
							break;
							case 1:
							orderStatusDetails="DELIVERED";
							break;		
							case 2:
							orderStatusDetails="CANCEL";		
							break;
						}
					
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
						itemLable1.setText("0"+database.getProfCustIDSOD(profid));
						itemLable2.setText(database.getCustNameSOD(profid));
						itemLable3.setText(""+database.getBurgerNumberSOD(id));
						itemLable4.setText(""+(FoodMenu.BURGERPRICE*database.getBurgerNumberSOD(id)));
						itemLable5.setText(orderStatusDetails);
					}else{
						warningText.setForeground(new Color (255,0,0));
						warningText.setText("Entered Order Does not Exists");
					}
			} else {
				warningText.setText("Invalid format. Use B0000");
			}
		}
	}
	public void resetShowedValues(){
		itemLable1.setText(""+0);
		itemLable2.setText("B0000");
		itemLable3.setText(""+0);
		itemLable4.setText(""+(FoodMenu.BURGERPRICE*0));
		itemLable5.setText("");
	}
	public void setDeveloperOptions(){
		String devfinal = developerOptions.getText().trim().toUpperCase();
		System.out.println("We get "+devfinal);

		if(devfinal.matches("[D]{1}[E]{1}[V]{1}[0]{4}[U]{1}")) {
			got=1;
			System.out.println("Here We go");
		}
	}
	public int getDev(){
			System.out.println("We get "+got);
		return got;
	}
	
}
