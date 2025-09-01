package org.example.view;

import org.example.repository.BurgerShopDatabase;
import org.example.config.FoodMenu;
import org.example.repository.OurDataBase;
import org.example.elements.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
public class ViewOrdersAll{
	private int viewOrderSelection;	
	private BurgerShopDatabase database = OurDataBase.SHARED_DB;
	private int LatestOrder=database.getLatestOrder();
	private int LatestProfile=database.getLatestProfile();
	private int[] values = new int[database.getLatestOrder()];  
	private int[] valuesp = new int[database.getLatestOrder()];
	private String[] columns = {"No","Order ID","Customer ID", "Name","Order Quantity", "Total "};
	private DefaultTableModel model = new DefaultTableModel(columns, 0);
	private JTable table = new JTable(model);
	private int n = database.getLatestProfile();
	public void openViewOrdersAll(){	
		BurgurFrame frame = new BurgurFrame("Burgur Shop");
		JPanel rightSideMenu = new JPanel();
		rightSideMenu.setLayout(new BorderLayout());
		rightSideMenu.setBackground(CommonColors.commonBackgroundColor);
		//Center
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
        model.setRowCount(0);
        table.setRowHeight(30);
        table.setFont(new Font("Serif", Font.BOLD, 15));
        int tableHight =400,col1Width = 100,col2Width = 300,col3Width = 100;
        int tableWidth=col1Width+col2Width+col3Width;
        table.setEnabled(false);

		int ordertypex = getViewOrderSelection();
		InCodeTools.dashesBox ("View Orders");
			int n = LatestOrder,gh=0,prof=0,gotit,nn,np;
			for (int i = 0; i<n; i++) {
				if(ordertypex==database.getOrderStatusSOD(i)){
					values[gh] = i;
					gh++;
				}
			}	
			for(int i=0;i<n;i++){
				gotit=values[i];
				for(int j=0;j<LatestProfile;j++){
					if (database.getOrderCustIDSOD(gotit)== database.getProfCustIDSOD(j)){
						valuesp[i]=j;
					}
				}
			}
			InCodeTools.printExtra(2,2);
			InCodeTools.printExtra(3,3);
			InCodeTools.printExtra(87,1);
			System.out.printf(" %-1s | %-10s %-15s %-1s  %-20s %-12s %-20s|\n", "", "Order ID", "Customer ID","","Name","Quantity","Order Value");
			InCodeTools.printExtra(3,3);
			InCodeTools.printExtra(87,1);
			if(gh>0){
				for(int i =0;i<gh;i++){
					nn=values[i];
					np=valuesp[i];
					System.out.printf(" %-1s | %-10s %-15s %-1s  %-20s %-12s %-20s|\n", "",database.getOrderIDStringSOD(nn),"0"+database.getOrderCustIDSOD(nn),"",database.getCustNameSOD(np),database.getBurgerNumberSOD(nn),(FoodMenu.BURGERPRICE*database.getBurgerNumberSOD(nn)));
					InCodeTools.printExtra(3,3);
					InCodeTools.printExtra(87,1);
					model.addRow(new Object[]{(i+1),database.getOrderIDStringSOD(nn),"0"+database.getOrderCustIDSOD(nn),database.getCustNameSOD(np),database.getBurgerNumberSOD(nn),(FoodMenu.BURGERPRICE*database.getBurgerNumberSOD(nn))});
				}
			}else if(gh==0){
				InCodeTools.noOrdersFound("View Orders");
			}else{
				InCodeTools.noOrdersFound("View Orders");
			}
		
        table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);


		JScrollPane scrollTable = new  JScrollPane ( table);
		table.setPreferredScrollableViewportSize(new Dimension(tableWidth,tableHight));
		table.setFillsViewportHeight(true);
        tablePanel.add(scrollTable);

		

		//Top Title
		BurgurPanelCover topTitle = new BurgurPanelCover(Color.RED);
		BurgurTextLables title = new BurgurTextLables();
		title.setBorder(new EmptyBorder(10, 10, 10, 10));
		title.setText("Best Customer");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("SansSerif", Font.BOLD, 40));
		title.setVerticalAlignment (BurgurTextLables.CENTER); 
		title.setHorizontalAlignment (BurgurTextLables.CENTER);
		topTitle.add(title);
		
		rightSideMenu.add(topTitle, BorderLayout.NORTH);
		rightSideMenu.add(tablePanel, BorderLayout.CENTER);
		//Bottom Panel
		BurgurPanelCover backtoHomePagePanel =new BurgurPanelCover();
		backtoHomePagePanel.setLayout(new GridBagLayout());
		GridBagConstraints GridBagLayout = new GridBagConstraints();
		GridBagLayout.anchor = GridBagConstraints.EAST;
		GridBagLayout.insets = new Insets(10, 10, 10, 10);
		//Back Home
		CustomButton backtoHomePageS = new CustomButton("Back to Home Page","GREEN",200);
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
	}
	public int getViewOrderSelection(){
		return viewOrderSelection;
	}
	public void setViewOrderSelection(int viewOrderSelection){
		this.viewOrderSelection=viewOrderSelection;
	}
	
}
