package org.example.view;

import org.example.controller.SearchBestCustomerController;
import org.example.elements.*;
import org.example.repository.BurgerShopDatabase;
import org.example.repository.OurDataBase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchBestCustomer {

    private CustomButton buttonBackToHomePage;
    private CustomButton buttonBack;
    private BurgurFrame frame;

    private final String[] columns = {"No", "Customer ID", "Name", "Total "};
    private DefaultTableModel model = new DefaultTableModel(columns, 0);
    private JTable table = new JTable(model);



    public void openSearchBestCustomer() {
        frame = new BurgurFrame("Burger Shop");
        JPanel rightSideMenu = new JPanel();
        rightSideMenu.setLayout(new BorderLayout());
        rightSideMenu.setBackground(CommonColors.commonBackgroundColor);
        //Center
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        model.setRowCount(0);
        table.setRowHeight(30);
        table.setFont(new Font("Serif", Font.BOLD, 15));

        int tableHeight = 400;

        int col1Width = 100;
        int col2Width = 300;
        int col3Width = 100;

        int tableWidth = col1Width + col2Width + col3Width;
        table.setEnabled(false);


        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);


        JScrollPane scrollTable = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(tableWidth, tableHeight));
        table.setFillsViewportHeight(true);
        tablePanel.add(scrollTable);


        //Top Title
        BurgurPanelCover topTitle = new BurgurPanelCover(Color.RED);
        BurgurTextLables title = new BurgurTextLables();
        title.setBorder(new EmptyBorder(10, 10, 10, 10));
        title.setText("Best Customer");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 40));
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        topTitle.add(title);

        rightSideMenu.add(topTitle, BorderLayout.NORTH);
        rightSideMenu.add(tablePanel, BorderLayout.CENTER);

        //Bottom Panel
        BurgurPanelCover backToHomePagePanel = new BurgurPanelCover();
        backToHomePagePanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagLayout = new GridBagConstraints();
        gridBagLayout.anchor = GridBagConstraints.EAST;
        gridBagLayout.insets = new Insets(10, 10, 10, 10);
        //Back Home
        buttonBackToHomePage = new CustomButton("Back to Home Page", "GREEN", 200);

        backToHomePagePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        //Back
        buttonBack = new CustomButton("Back", "GREEN", 200);
        gridBagLayout.gridx = 0;
        gridBagLayout.gridy = 0;
        backToHomePagePanel.add(buttonBack, gridBagLayout);
        gridBagLayout.gridx = 1;
        backToHomePagePanel.add(buttonBackToHomePage, gridBagLayout);
        rightSideMenu.add(backToHomePagePanel, BorderLayout.SOUTH);
        frame.add(rightSideMenu);
        frame.pack();
        frame.setVisible(true);

        new SearchBestCustomerController(this).inti();
    }

    public DefaultTableModel getTableModel(){
        return model;
    }


    public void onBackToHomePage(ActionListener l) {
        buttonBackToHomePage.addActionListener(l);
    }
    public void onBack(ActionListener l) {
        buttonBack.addActionListener(l);
    }

    public void close() {
        if (frame != null) frame.dispose();
    }

    //TODO - Create the controller class for this view
}
