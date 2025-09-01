package org.example.service;

import org.example.controller.MainWindowController;
import org.example.controller.UpdateDatabase;
import org.example.model.PlaceOrder;
import org.example.repository.BurgerShopDatabase;
import org.example.repository.Orders;
import org.example.repository.OurDataBase;
import org.example.view.MainWindow;
import org.example.view.SearchOrderDetails;

import java.util.Random;

public class DeveloperOptions {

private int go =5;
private int got =0;

    public void addTestData(int number) {
        BurgerShopDatabase db = OurDataBase.SHARED_DB;

        for (int i = 0; i < number; i++) {
            String zz = "";
            int latestOrder = db.getLatestOrder();

            if (latestOrder >= 99) {
                zz += "0";
            } else if (latestOrder >= 9) {
                zz += "00";
            } else if (latestOrder >= 0) {
                zz += "000";
            }
            String orderIDPO = "B" + zz + (latestOrder + 1);

            int w = new Random().nextInt(100);
            new Orders().placeNewOrder(new PlaceOrder((713769480 + 1 * i * w + 20 * i * w + 300 * i * w + 4000 * i * w), "Janiru" + i, (i * 10), orderIDPO));
        }
    }

    public void initiateDeveloperOptions() {
        OurDataBase.devOptions=1;
    }

    public int setDev() {
        SearchOrderDetails currentSearchOrder = OurDataBase.SEARCH_ORDER;
        return getDev();
    }


//    public void setDeveloperOptions(String devPassword) {
//        devPassword= textFieldDevOptions.getText().trim().toUpperCase();
//        System.out.println("We get " + devfinal);
//
//        if (devfinal.matches("[D]{1}[E]{1}[V]{1}[0]{4}[U]{1}")) {
//            got = 1;
//            System.out.println("Here We go");
//        }
//    }

    public int getDev() {
        System.out.println("We get " + got);
        return got;
    }
}
