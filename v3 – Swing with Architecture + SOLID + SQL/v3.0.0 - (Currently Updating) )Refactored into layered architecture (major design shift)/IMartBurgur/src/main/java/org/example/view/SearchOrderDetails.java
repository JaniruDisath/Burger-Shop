package org.example.view;

import org.example.controller.SearchOrderController;
import org.example.elements.*;
import org.example.model.OrderDetails;
import org.example.repository.BurgerShopDatabase;
import org.example.repository.OurDataBase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;

public class SearchOrderDetails {


    private BurgurTextField textFieldOrderIDEntered= new BurgurTextField();
    private BurgurTextField textFieldDevOptions = new BurgurTextField();

    private BurgurTextLables labelWarningText= new BurgurTextLables();
    private BurgurTextLables itemLable1 = null;
    private BurgurTextLables itemLable2 = null;
    private BurgurTextLables itemLable3 = null;
    private BurgurTextLables itemLable4 = null;
    private BurgurTextLables itemLable5 = null;

    private CustomButton backtoHomePageS = new CustomButton("Back to Home Page", "GREEN", 200);
    private BurgerShopDatabase database = OurDataBase.SHARED_DB;

    private Document doc = textFieldOrderIDEntered.getDocument();

    public void openSearchOrderDetails() {
        BurgurFrame frame = new BurgurFrame("Burger Shop");
        JPanel rightSideMenu = new JPanel();
        rightSideMenu.setLayout(new BorderLayout());
        rightSideMenu.setBackground(CommonColors.commonBackgroundColor);
        //Center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        //Reset
        textFieldOrderIDEntered.setText("");
        textFieldDevOptions.setText("");
        //
        GridBagConstraints centerPanelLayout = new GridBagConstraints();
        centerPanelLayout.anchor = GridBagConstraints.NORTH;
        centerPanelLayout.insets = new Insets(10, 10, 10, 10);

        JPanel orderIDPanel = new JPanel();

        {
            orderIDPanel.setLayout(new GridBagLayout());

            GridBagConstraints gridLayout = new GridBagConstraints();
            gridLayout.insets = new Insets(0, 10, 0, 10);
            gridLayout.anchor = GridBagConstraints.EAST;

            BurgurTextLables enterOrderID = new BurgurTextLables();
            enterOrderID.setText("Enter Order ID : ");
            gridLayout.gridx = 0;
            gridLayout.gridy = 0;
            orderIDPanel.add(enterOrderID, gridLayout);

            gridLayout.gridx = 1;

            gridLayout.anchor = GridBagConstraints.WEST;
            orderIDPanel.add(textFieldOrderIDEntered, gridLayout);

            gridLayout.gridx = 2;
            textFieldDevOptions.setVisible(false);
            orderIDPanel.add(textFieldDevOptions, gridLayout);

            JPanel fixedPanel = new JPanel();
            fixedPanel.setPreferredSize(new Dimension(300, 0));

            labelWarningText.setText("         ");
            labelWarningText.setFont(new Font("Monospaced", Font.PLAIN, 16));
            labelWarningText.setForeground(new Color(255, 0, 0));
            labelWarningText.setVerticalAlignment(SwingConstants.CENTER);
            labelWarningText.setHorizontalAlignment(SwingConstants.CENTER);
            gridLayout.gridx = 1;
            gridLayout.gridy = 1;
            gridLayout.ipadx = 100;
            gridLayout.ipady = 50;
            gridLayout.fill = GridBagConstraints.NONE;
            fixedPanel.add(labelWarningText);
            orderIDPanel.add(fixedPanel, gridLayout);


        }

        centerPanelLayout.gridx = 0;
        centerPanelLayout.gridy = 0;
        centerPanel.add(orderIDPanel, centerPanelLayout);


        JPanel customerIDPanel = new JPanel();
        {
            customerIDPanel.setLayout(new GridBagLayout());

            GridBagConstraints gridLayout = new GridBagConstraints();
            gridLayout.insets = new Insets(0, 10, 10, 10);
            gridLayout.anchor = GridBagConstraints.EAST;

            //Customer ID
            BurgurTextLables itemNameLabel1 = new BurgurTextLables();
            itemNameLabel1.setText("Customer ID : ");
            gridLayout.gridx = 0;
            gridLayout.gridy = 0;
            customerIDPanel.add(itemNameLabel1, gridLayout);

            //Name
            BurgurTextLables itemNameLabel2 = new BurgurTextLables();
            itemNameLabel2.setText("Name : ");
            gridLayout.gridx = 0;
            gridLayout.gridy = 1;
            customerIDPanel.add(itemNameLabel2, gridLayout);

            //QTY
            BurgurTextLables itemNameLabel3 = new BurgurTextLables();
            itemNameLabel3.setText("Burger Quantity : ");
            gridLayout.gridx = 0;
            gridLayout.gridy = 2;
            customerIDPanel.add(itemNameLabel3, gridLayout);

            //Total
            BurgurTextLables itemNameLabel4 = new BurgurTextLables();
            itemNameLabel4.setText("Total Price : ");
            gridLayout.gridx = 0;
            gridLayout.gridy = 3;
            customerIDPanel.add(itemNameLabel4, gridLayout);

            //Order status
            BurgurTextLables itemNameLabel5 = new BurgurTextLables();
            itemNameLabel5.setText("Order Status : ");
            gridLayout.gridx = 0;
            gridLayout.gridy = 4;
            customerIDPanel.add(itemNameLabel5, gridLayout);

            //set Text labels
            gridLayout.anchor = GridBagConstraints.WEST;

            itemLable1 = new BurgurTextLables();
            itemLable1.setText("");
            gridLayout.gridx = 1;
            gridLayout.gridy = 0;
            customerIDPanel.add(itemLable1, gridLayout);

            itemLable2 = new BurgurTextLables();
            itemLable2.setText("");
            gridLayout.gridx = 1;
            gridLayout.gridy = 1;
            customerIDPanel.add(itemLable2, gridLayout);

            itemLable3 = new BurgurTextLables();
            itemLable3.setText("");
            gridLayout.gridx = 1;
            gridLayout.gridy = 2;
            customerIDPanel.add(itemLable3, gridLayout);

            itemLable4 = new BurgurTextLables();
            itemLable4.setText("0.0");
            gridLayout.gridx = 1;
            gridLayout.gridy = 3;
            customerIDPanel.add(itemLable4, gridLayout);

            itemLable5 = new BurgurTextLables();
            itemLable5.setText("");
            gridLayout.gridx = 1;
            gridLayout.gridy = 4;
            customerIDPanel.add(itemLable5, gridLayout);


        }
        centerPanelLayout.gridx = 0;
        centerPanelLayout.gridy = 1;
        centerPanel.add(customerIDPanel, centerPanelLayout);


        centerPanelLayout.gridx = 0;
        centerPanelLayout.gridy = 1;
        centerPanel.add(customerIDPanel, centerPanelLayout);


        //Top Title
        BurgurPanelCover topTitle = new BurgurPanelCover(Color.RED);
        BurgurTextLables title = new BurgurTextLables();
        title.setBorder(new EmptyBorder(10, 10, 10, 10));
        title.setText("Search Order");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 40));
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        topTitle.add(title);

        rightSideMenu.add(topTitle, BorderLayout.NORTH);
        rightSideMenu.add(centerPanel, BorderLayout.CENTER);
        //Bottom Panel
        BurgurPanelCover backToHomePagePanel = new BurgurPanelCover();
        backToHomePagePanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagLayout = new GridBagConstraints();
        gridBagLayout.anchor = GridBagConstraints.EAST;
        gridBagLayout.insets = new Insets(10, 10, 10, 10);
        //Back Home

        backToHomePagePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        //Back
        CustomButton backTo = new CustomButton("Back", "GREEN", 200);
        gridBagLayout.gridx = 0;
        gridBagLayout.gridy = 0;
        backToHomePagePanel.add(backTo, gridBagLayout);
        gridBagLayout.gridx = 1;
        backToHomePagePanel.add(backtoHomePageS, gridBagLayout);
        rightSideMenu.add(backToHomePagePanel, BorderLayout.SOUTH);

        frame.add(rightSideMenu);
        frame.pack();
        frame.setVisible(true);

        new SearchOrderController(this).init();

//        Document doc1 = textFieldDevOptions.getDocument();
//        doc1.addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                setDeveloperOptions();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                setDeveloperOptions();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                setDeveloperOptions();
//            }
//        });
    }

    public void onOrderIDChange(DocumentListener l){
        doc.addDocumentListener(l);
    }

    //Get Elements
    public BurgurTextLables getWarningTextLabel(){
        return labelWarningText;
    }

    public BurgurTextField getTextFieldOrderIDEntered(){
        return textFieldOrderIDEntered;
    }

    public BurgurTextField getTextFieldDevOptions(){
        return textFieldDevOptions;
    }


    public void setValuesOnView(OrderDetails orderDetails) {
        itemLable1.setText(orderDetails.getCustomerID());
        itemLable2.setText(orderDetails.getCustomerName());
        itemLable3.setText(orderDetails.getBurgerNumber());
        itemLable4.setText(orderDetails.getTotalPrice());
        itemLable5.setText(orderDetails.getOrderStatus());
    }


}
