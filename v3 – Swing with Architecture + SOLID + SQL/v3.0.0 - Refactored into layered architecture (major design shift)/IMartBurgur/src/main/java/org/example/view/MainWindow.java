package org.example.view;

import org.example.controller.MainWindowController;
import org.example.elements.BurgurFrame;
import org.example.elements.BurgurPanelCover;
import org.example.elements.CommonColors;
import org.example.elements.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainWindow {

    private BurgurFrame frame;

    private CustomButton buttonPlaceOrder;
    private CustomButton buttonSearch;
    private CustomButton buttonViewOrders;
    private CustomButton buttonUpdateOrderDetail;
    private CustomButton exitButton;

    private CustomButton addCustomers;
    private CustomButton testButton;

    public void openMainWindow() {

        frame = new BurgurFrame("Burger Shop");

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/frontImage.jpg")));
        JLabel imageLabel = new JLabel(imageIcon);
        frame.add(imageLabel);
        JPanel rightSideMenu = new JPanel();
        JPanel menuButtons = new JPanel();
        menuButtons.setLayout(new BoxLayout(menuButtons, BoxLayout.Y_AXIS));
        menuButtons.setBackground(CommonColors.commonBackgroundColor);

        //Button Initialization
        //UI
        String red = "RED";
        String green = "GREEN";
        buttonPlaceOrder = new CustomButton("Place Order", red, 200);
        buttonSearch = new CustomButton("Search", red, 200);
        buttonViewOrders = new CustomButton("View Orders", red, 200);
        buttonUpdateOrderDetail = new CustomButton("Update Order Detail", red, 200);
        exitButton = new CustomButton("Exit", green, 200);
        //Dev
        addCustomers = new CustomButton("Add Customers", green, 200);
        testButton = new CustomButton("TEst", green, 200);


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

        BurgurPanelCover backToHomePagePanel = new BurgurPanelCover();
        backToHomePagePanel.setLayout(new GridBagLayout());
        backToHomePagePanel.setPreferredSize(new Dimension(200, 100));
        GridBagConstraints gridBagLayout = new GridBagConstraints();
        gridBagLayout.anchor = GridBagConstraints.EAST;
        gridBagLayout.insets = new Insets(30, 10, 10, 20);

        backToHomePagePanel.add(Box.createRigidArea(new Dimension(20, 0)));

        gridBagLayout.gridx = 0;
        gridBagLayout.gridy = 0;
        backToHomePagePanel.add(testButton, gridBagLayout);
        gridBagLayout.gridx = 1;
        backToHomePagePanel.add(addCustomers, gridBagLayout);
        rightSideMenu.add(backToHomePagePanel, BorderLayout.NORTH);


        rightSideMenu.add(new BurgurPanelCover(), BorderLayout.WEST);
        rightSideMenu.add(new BurgurPanelCover(), BorderLayout.EAST);
        rightSideMenu.add(menuButtons, BorderLayout.CENTER);
        BurgurPanelCover exitButtonPanel = new BurgurPanelCover();
        exitButtonPanel.setLayout(new BoxLayout(exitButtonPanel, BoxLayout.Y_AXIS));
        exitButtonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        exitButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        exitButtonPanel.add(exitButton);
        rightSideMenu.add(exitButtonPanel, BorderLayout.SOUTH);
        frame.add(rightSideMenu);
        frame.pack();
        frame.setVisible(true);

        new MainWindowController(this).init();

    }

    public void setDeveloperOptions(boolean visibility) {
        addCustomers.setVisible(visibility);
        testButton.setVisible(visibility);
    }

    public void onPlaceOrder(ActionListener l) {
        buttonPlaceOrder.addActionListener(l);
    }

    public void onSearch(ActionListener l) {
        buttonSearch.addActionListener(l);
    }

    public void onViewOrders(ActionListener l) {
        buttonViewOrders.addActionListener(l);
    }

    public void onUpdateOrderDetail(ActionListener l) {
        buttonUpdateOrderDetail.addActionListener(l);
    }

    public void onExit(ActionListener l) {
        exitButton.addActionListener(l);
    }

    public void onAddCustomers(ActionListener l) {
        addCustomers.addActionListener(l);
    }

    public void onTestSystem(ActionListener l) {
        testButton.addActionListener(l);
    }


    public void close() {
        if (frame != null) frame.dispose();
    }


}