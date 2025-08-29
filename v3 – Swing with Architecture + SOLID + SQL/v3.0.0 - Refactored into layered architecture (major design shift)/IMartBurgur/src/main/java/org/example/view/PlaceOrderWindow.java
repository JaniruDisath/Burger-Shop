package org.example.view;

import org.example.controller.PlaceOrderController;
import org.example.elements.*;
import org.example.service.order.GenerateOrderID;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlaceOrderWindow {

    private final BurgurTextField customerIDEntered = new BurgurTextField();
    private final BurgurTextField customerNamePO = new BurgurTextField();
    private final BurgurTextField textFieldBurgerNumber = new BurgurTextField();
    
    private CustomButton confirmOrder;
    private CustomButton backToHomePage;
    private CustomButton cancelOrder;
    private BurgurFrame frame;
    private BurgurTextLables burgerTotalPO;

    public void openPlaceOrderWindow() {
        frame = new BurgurFrame("Burger Shop");
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
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
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
        gbc.gridx = 0;
        gbc.gridy = row;
        BurgurTextLables orderIDLabel = new BurgurTextLables();
        orderIDLabel.setText("Order ID");
        centerPanel.add(orderIDLabel, gbc);

        gbc.gridx = 1;
        BurgurTextLables orderIDGenerated = new BurgurTextLables();
        orderIDGenerated.setText(": " + new GenerateOrderID().generateId());
        centerPanel.add(orderIDGenerated, gbc);
        row++;

        //Customer ID
        gbc.gridx = 0;
        gbc.gridy = row;
        BurgurTextLables customerIDLabel = new BurgurTextLables();
        customerIDLabel.setText("Customer ID");
        centerPanel.add(customerIDLabel, gbc);

        gbc.gridx = 1;
        centerPanel.add(customerIDEntered, gbc);
        row++;

        //Customer Name
        gbc.gridx = 0;
        gbc.gridy = row;
        BurgurTextLables customerNameIDLabel = new BurgurTextLables();
        customerNameIDLabel.setText("Customer Name");
        centerPanel.add(customerNameIDLabel, gbc);

        gbc.gridx = 1;
        centerPanel.add(customerNamePO, gbc);
        row++;

        //line
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        centerPanel.add(new DrawLines(), gbc);
        gbc.gridwidth = 1;
        row++;

        //Burger QTY
        gbc.gridx = 0;
        gbc.gridy = row;
        BurgurTextLables burgerQTYLabel = new BurgurTextLables();
        burgerQTYLabel.setText("Burger Quantity");
        centerPanel.add(burgerQTYLabel, gbc);

        gbc.gridx = 1;
        centerPanel.add(textFieldBurgerNumber, gbc);
        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        BurgurTextLables orderStatusLabel = new BurgurTextLables();
        orderStatusLabel.setText("Order Status");
        centerPanel.add(orderStatusLabel, gbc);

        gbc.gridx = 1;
        BurgurTextField orderStatus = new BurgurTextField();
        orderStatus.setText("     PREPARING");
        orderStatus.setEditable(false);
        centerPanel.add(orderStatus, gbc);


        // Right side buttons
        BurgurPanelCover rightSide = new BurgurPanelCover(CommonColors.commonBackgroundColor, 200, 0);
        rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
        rightSide.setBorder(new EmptyBorder(10, 5, 5, 5));

        //Buttons
        confirmOrder = new CustomButton("Confirm Order", "GREEN", 175);
        backToHomePage = new CustomButton("Back to Home Page", "RED", 175);
        cancelOrder = new CustomButton("Cancel", "RED", 175);


        rightSide.add(Box.createRigidArea(new Dimension(10, 100)));
        rightSide.add(confirmOrder);
        rightSide.add(Box.createRigidArea(new Dimension(10, 10)));
        rightSide.add(backToHomePage);
        rightSide.add(Box.createRigidArea(new Dimension(10, 10)));
        rightSide.add(cancelOrder);

        // Bottom panel
        BurgurPanelCover bottomTotal = new BurgurPanelCover(CommonColors.commonBackgroundColor, 0, 50);
        bottomTotal.setBorder(new EmptyBorder(5, 5, 5, 5));
        burgerTotalPO = new BurgurTextLables();
        burgerTotalPO.setText("NET TOTAL : 0.0");

        bottomTotal.add(burgerTotalPO);

        rightSideMenu.add(centerPanel, BorderLayout.CENTER);
        rightSideMenu.add(rightSide, BorderLayout.EAST);
        rightSideMenu.add(bottomTotal, BorderLayout.SOUTH);
        frame.add(rightSideMenu);
        frame.pack();
        frame.setVisible(true);

        new PlaceOrderController(this).init();
    }

    //Setting Listeners
    public void onConfirmOrder(ActionListener l) {
        confirmOrder.addActionListener(l);
    }

    public void onBackToHomePage(ActionListener l) {
        backToHomePage.addActionListener(l);
        cancelOrder.addActionListener(l);
    }

    //Doc Listeners
    public void onBurgerNumberChange(DocumentListener l) {
        textFieldBurgerNumber.getDocument().addDocumentListener(l);
    }

    public void onCustomerIDChange(DocumentListener l) {
        customerIDEntered.getDocument().addDocumentListener(l);
    }

    //Changing UI from the controller class
    public void setTotalValue(Double totalValue) {
        burgerTotalPO.setText("NET TOTAL : " + totalValue);
    }

    public void setName(String name) {
        customerNamePO.setText(name);
    }

    public void setNameFieldEditable(boolean bool) {
        customerNamePO.setEditable(bool);
    }

    //Getting Values from the UI
    public String getEnteredBurgerNumberString() {
        return textFieldBurgerNumber.getText();
    }

    public String getCustomerIDString() {
        return customerIDEntered.getText();
    }

    public String getCustomerName() {
        return customerNamePO.getText().trim().isEmpty() ? "" : customerNamePO.getText().trim();
    }

    public String getBurgerNumberString() {
        return textFieldBurgerNumber.getText().trim().isEmpty() ? "" : textFieldBurgerNumber.getText().trim();
    }


    //Send an Option Pane message
    public void sendOptionPaneMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    //Close the Window
    public void close() {
        if (frame != null) frame.dispose();
    }


}
