import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public class SearchCustomerDetails {

    private BurgurTextField customerIDEntered = new BurgurTextField();
    private BurgurTextLables warningText = null;
    private BurgurTextLables itemLable1 = null;
    private BurgurTextLables itemLable2 = null;
	private BurgerShopDatabase database = OurDataBase.SHARED_DB;
    private int LatestOrder = database.getLatestOrder();
    private int LatestProfile = database.getLatestProfile();
    private String[] columns = {"No","Orde ID","Burger Quantity","Total ","Order Status"};
    private DefaultTableModel model = new DefaultTableModel(columns, 0);
    private JTable table = new JTable(model);
    private int n = database.getLatestProfile();
    public void openSearchCustomerDetials() {
        BurgurFrame frame = new BurgurFrame("Burgur Shop");
        JPanel rightSideMenu = new JPanel();
        rightSideMenu.setLayout(new BorderLayout());
        rightSideMenu.setBackground(CommonColors.commonBackgroundColor);

        // Top Title
        BurgurPanelCover topTitle = new BurgurPanelCover(Color.RED);
        BurgurTextLables title = new BurgurTextLables();
        title.setBorder(new EmptyBorder(0, 10, 0, 10));
        title.setText("Search Customer");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 40));
        title.setVerticalAlignment(BurgurTextLables.CENTER);
        title.setHorizontalAlignment(BurgurTextLables.CENTER);
        topTitle.add(title);
        rightSideMenu.add(topTitle, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 0, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel orderIDPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gCon = new GridBagConstraints();
        gCon.insets = new Insets(5, 5, 0, 5);

        BurgurTextLables enterOrderID = new BurgurTextLables();
        enterOrderID.setText("Enter Order ID : ");
        gCon.gridx = 0;
        gCon.gridy = 0;
        gCon.anchor = GridBagConstraints.EAST;
        orderIDPanel.add(enterOrderID, gCon);

        gCon.gridx = 1;
        gCon.anchor = GridBagConstraints.WEST;
        orderIDPanel.add(customerIDEntered, gCon);

        JPanel fixedPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fixedPanel.setPreferredSize(new Dimension(250, 20));

        warningText = new BurgurTextLables();
        warningText.setText("         ");
        warningText.setFont(new Font("Monospaced", Font.PLAIN, 16));
        warningText.setForeground(new Color(255, 0, 0));
        warningText.setVerticalAlignment(JLabel.CENTER);
        warningText.setHorizontalAlignment(JLabel.LEFT);
        fixedPanel.add(warningText);

        gCon.gridx = 2;
        gCon.ipadx = 100;
        gCon.ipady = 20;
        gCon.insets = new Insets(0, 10, 0, 10);
        orderIDPanel.add(fixedPanel, gCon);


        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(orderIDPanel, gbc);

        // Name Field Panel
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemLable1 = new BurgurTextLables();
        itemLable2 = new BurgurTextLables();
        itemLable1.setText("Name : ");
        itemLable2.setText("Jack");
        namePanel.add(itemLable1);
        namePanel.add(itemLable2);

        gbc.gridy = 1;
        centerPanel.add(namePanel, gbc);

        BurgurPanelCover orderTitle = new BurgurPanelCover(Color.RED, 1000, 40);
        BurgurTextLables orderLabel = new BurgurTextLables();
        orderLabel.setText("Order Details");
        orderLabel.setForeground(Color.WHITE);
        orderLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        orderLabel.setHorizontalAlignment(JLabel.CENTER);
        orderTitle.add(orderLabel);

        gbc.gridy = 2;
        centerPanel.add(orderTitle, gbc);

        // Table Placeholder
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        model.setRowCount(0);
        table.setRowHeight(30);
        table.setFont(new Font("Serif", Font.BOLD, 15));
        int tableHight = 200, col1Width = 100, col2Width = 300, col3Width = 100;
        int tableWidth = col1Width + col2Width + col3Width;
        table.setEnabled(false);

        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollTable = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(tableWidth, tableHight));
        table.setFillsViewportHeight(true);
        tablePanel.add(scrollTable);
        gbc.gridy = 3;
        centerPanel.add(tablePanel, gbc);

        rightSideMenu.add(centerPanel, BorderLayout.CENTER);


        BurgurPanelCover backPanel = new BurgurPanelCover();
        backPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        CustomButton back = new CustomButton("Back", "GREEN", 200, "Best Customer");
        backPanel.add(back);
        rightSideMenu.add(backPanel, BorderLayout.SOUTH);


        frame.add(rightSideMenu);
        frame.pack();
        frame.setVisible(true);

        Document doc = customerIDEntered.getDocument();
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                warningText.setForeground(new Color(255, 0, 0));
                model.setRowCount(0);
                getCustIDCD();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                warningText.setForeground(new Color(255, 0, 0));
                model.setRowCount(0);
                getCustIDCD();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                warningText.setForeground(new Color(255, 0, 0));
                model.setRowCount(0);
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
            warningText.setForeground(new Color(0, 255, 0));
            warningText.setText("Order ID Confirmed");
            System.out.println("\n\n  Customer ID found");
            InCodeTools.printExtra(2, 3);
            InCodeTools.printExtra(62, 1);
            System.out.println("\n   Customer ID - " + "0" + customerID);
            System.out.println("   Name        - " + database.getCustNameSOD(prof));
            System.out.println("\n\nCustomer Order List");
            System.out.println("=====================\n");
            InCodeTools.printExtra(3, 3);
            InCodeTools.printExtra(62, 1);
            System.out.printf(" %-1s | %-10s   %-1s  %-20s %-1s  %-20s %-1s  %-10s|\n","", "Order ID", "", "Order Quantity", "", "Total Value", "", "Status");
            InCodeTools.printExtra(3, 3);
            InCodeTools.printExtra(62, 1);
            for (int i = 0; i < gh; i++) {
                String orderStatusDetails = "";
                nn = values[i]; 
                switch (database.getOrderStatusSOD(nn)) {
                    case 0:
                        orderStatusDetails = "PREPARING";
                        break;
                    case 1:
                        orderStatusDetails = "DELIVERED";
                        break;
                    case 2:
                        orderStatusDetails = "CANCEL";
                        break;
                }
                System.out.printf(" %-1s | %-10s   %-1s  %-20s %-1s  %-20s %-1s  %-10s|\n", "", database.getOrderIDStringSOD(nn), "",database.getBurgerNumberSOD(nn), "", (FoodMenu.BURGERPRICE * database.getBurgerNumberSOD(nn)), "", orderStatusDetails);
                model.addRow(new Object[]{(i + 1),"0" + database.getOrderIDStringSOD(nn),database.getBurgerNumberSOD(nn),(FoodMenu.BURGERPRICE * database.getBurgerNumberSOD(nn)),orderStatusDetails});
            }
            InCodeTools.printExtra(3, 3);
            InCodeTools.printExtra(62, 1);
        } else {
            warningText.setText("Customer ID does not Exist");
        }
    }
}
	public void getCustIDCD() {
		long custIDPO;
		String customerIDText = customerIDEntered.getText().trim();

		if (customerIDText == null || customerIDText.isEmpty()) {
			warningText.setText("Empty Field");
			return; 
		}
		if (customerIDText.matches("[0]{10}")) {
			warningText.setText("Order ID Reseted");
			System.out.println("Reset Code :" + customerIDText);
			itemLable1.setText("" + 0);
			itemLable2.setText(customerIDText);
			return; 
		}
		if (customerIDText.length() < 1) {
			warningText.setText("Customer ID cannot be Empty");
			return;
		}
		char firstNumber = customerIDText.charAt(0);
		if (firstNumber == '0') {
			if (customerIDText.length() != 10) {
				warningText.setText("Customer ID Invalid Length");
				return;
			}
			try {
				custIDPO = Long.parseLong(customerIDText);
				getCustIDFound(custIDPO);
			} catch (NumberFormatException e) {
				warningText.setText("Customer ID must be a number.");
			}
		} else {
			warningText.setText("Customer ID must start with '0'");
		}
	}
}
