package org.example.view;

import org.example.controller.SearchMenuController;
import org.example.elements.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchMenu {

    private CustomButton buttonBestCustomer;
    private CustomButton buttonSearchOrder;
    private CustomButton buttonSearchCustomer;
    private CustomButton buttonBackToHomePage;
    private BurgurFrame frame;

    public void openSearchMenu() {
        frame = new BurgurFrame("Burger Shop");
        ImageIcon imageIcon = new ImageIcon("img/frontImage.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        frame.add(imageLabel);
        JPanel rightSideMenu = new JPanel();
        JPanel menuButtons = new JPanel();
        menuButtons.setLayout(new BoxLayout(menuButtons, BoxLayout.Y_AXIS));
        menuButtons.setBackground(CommonColors.commonBackgroundColor);

        buttonBestCustomer = new CustomButton("Best Customer", "RED", 200);
        buttonSearchOrder = new CustomButton("Search Order", "RED", 200);
        buttonSearchCustomer = new CustomButton("Search Customer", "RED", 200);

        menuButtons.setBorder(new EmptyBorder(50, 10, 10, 10));
        menuButtons.add(Box.createRigidArea(new Dimension(0, 20)));
        menuButtons.add(buttonBestCustomer);
        menuButtons.add(Box.createRigidArea(new Dimension(0, 20)));
        menuButtons.add(buttonSearchOrder);
        menuButtons.add(Box.createRigidArea(new Dimension(0, 20)));
        menuButtons.add(buttonSearchCustomer);
        menuButtons.add(Box.createRigidArea(new Dimension(0, 20)));
        menuButtons.add(Box.createRigidArea(new Dimension(0, 20)));
        rightSideMenu.setLayout(new BorderLayout());
        rightSideMenu.setBackground(CommonColors.commonBackgroundColor);

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
        rightSideMenu.add(new BurgurPanelCover(), BorderLayout.WEST);
        rightSideMenu.add(new BurgurPanelCover(), BorderLayout.EAST);
        rightSideMenu.add(menuButtons, BorderLayout.CENTER);

        BurgurPanelCover backToHomePagePanel = new BurgurPanelCover();
        buttonBackToHomePage = new CustomButton("Back to Home Page", "GREEN", 200);
        backToHomePagePanel.setLayout(new BoxLayout(backToHomePagePanel, BoxLayout.Y_AXIS));
        backToHomePagePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonBackToHomePage.setAlignmentX(Component.RIGHT_ALIGNMENT);
        backToHomePagePanel.add(buttonBackToHomePage);
        rightSideMenu.add(backToHomePagePanel, BorderLayout.SOUTH);
        frame.add(rightSideMenu);
        frame.pack();
        frame.setVisible(true);

        new SearchMenuController(this).init();
    }

    public void onBestCustomer(ActionListener l) {
        buttonBestCustomer.addActionListener(l);
    }

    public void onSearchOrder(ActionListener l) {
        buttonSearchOrder.addActionListener(l);
    }

    public void onSearchCustomer(ActionListener l) {
        buttonSearchCustomer.addActionListener(l);
    }

    public void onBackToHomePage(ActionListener l) {
        buttonBackToHomePage.addActionListener(l);
    }

    public void close() {
        if (frame != null) frame.dispose();
    }

}

