package org.example.view;

import org.example.repository.BurgerShopDatabase;
import org.example.repository.OurDataBase;
import org.example.elements.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
public class SearchBestCustomer{

	private CustomButton backToHomePage;


	private BurgerShopDatabase database = OurDataBase.SHARED_DB;
	private int LatestOrder=database.getLatestOrder();
	private int LatestProfile=database.getLatestProfile();
	private double[] totalValues = new double[database.getLatestProfile()];
	private int[] totalPositions = new int[database.getLatestProfile()];
	private String[] columns = {"No","Customer ID", "Name", "Total "};
	private DefaultTableModel model = new DefaultTableModel(columns, 0);
	private JTable table = new JTable(model);
	private int n = database.getLatestProfile();

	public void openSearchBestCustomer(){	
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

        for (int i = 0; i < n; i++) {
            totalValues[i] = database.getCustTotalSOD(i);
            totalPositions[i] = i; 
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (totalValues[i] < totalValues[j]) {
                    double temp = totalValues[i];
                    totalValues[i] = totalValues[j];
                    totalValues[j] = temp;
                    int tempPos = totalPositions[i];
                    totalPositions[i] = totalPositions[j];
                    totalPositions[j] = tempPos;
                }
            }
        }
        for (int i = 0; i < n; i++) {
			int r = totalPositions[i];
			model.addRow(new Object[]{(i+1),"0"+database.getProfCustIDSOD(r),database.getCustNameSOD(r),database.getCustTotalSOD(r)});
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
		CustomButton backToHomePage = new CustomButton("Back to Home Page","GREEN",200);

		backtoHomePagePanel.add(Box.createRigidArea(new Dimension(20, 0)));
		//Back
		CustomButton backto = new CustomButton("Back","GREEN",200);
		GridBagLayout.gridx = 0; GridBagLayout.gridy = 0;
		backtoHomePagePanel.add(backto,GridBagLayout);
		GridBagLayout.gridx = 1; 
		backtoHomePagePanel.add(backToHomePage,GridBagLayout);
		rightSideMenu.add(backtoHomePagePanel,BorderLayout.SOUTH);
		frame.add(rightSideMenu);
		frame.pack();
        frame.setVisible(true);
	}
}
