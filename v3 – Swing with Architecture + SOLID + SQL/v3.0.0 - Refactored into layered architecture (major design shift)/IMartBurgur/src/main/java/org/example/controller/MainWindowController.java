package org.example.controller;

import org.example.repository.OurDataBase;
import org.example.service.DeveloperOptions;
import org.example.view.*;

public class MainWindowController {

    private final MainWindow mainWindow;

    public MainWindowController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void init() {

        mainWindow.onPlaceOrder(e -> {
            disposeMainWindow();
            new PlaceOrderWindow().openPlaceOrderWindow();
        });

        mainWindow.onSearch(e -> {
            disposeMainWindow();
            new SearchMenu().openSearchMenu();
        });

        mainWindow.onViewOrders(e -> {
            disposeMainWindow();
            new ViewOrderMenu().openViewOrderMenuMenu();
        });

        mainWindow.onUpdateOrderDetail(e -> {
            disposeMainWindow();
            new UpdateOrder().openUpdateOrderWindow();
        });

        mainWindow.onExit(e ->
                mainWindow.close()
        );

        mainWindow.onAddCustomers(e ->
                new DeveloperOptions().addTestData(150)
        );

        mainWindow.onTestSystem(e -> {

        });

        setDeveloperElements();
    }

    private void disposeMainWindow() {
        mainWindow.close();
    }

    private void setDeveloperElements() {
        mainWindow.setDeveloperOptions( OurDataBase.devOptions==1);

    }
}
