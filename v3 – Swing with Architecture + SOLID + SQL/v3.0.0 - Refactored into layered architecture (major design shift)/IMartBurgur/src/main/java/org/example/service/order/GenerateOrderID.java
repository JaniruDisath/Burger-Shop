package org.example.service.order;

import org.example.repository.BurgerShopDatabase;
import org.example.repository.OurDataBase;

public class GenerateOrderID {

    public int getLatestOrder() {
        BurgerShopDatabase database = OurDataBase.SHARED_DB;
        return database.getLatestOrder();
    }

    private String idZeros(int latestOrder) {
        if (latestOrder > 99) {
            return "0";
        }
        if (latestOrder > 9) {
            return "00";
        }
        if (latestOrder >= 0) {
            return "000";
        }
        return null;
    }

    public String generateId() {
        int latestOrder = getLatestOrder();
        String zeros = idZeros(latestOrder);
        if (zeros == null) {
            return "Invalid ID";
        }
        return "B" + zeros + (latestOrder + 1);
    }
}
