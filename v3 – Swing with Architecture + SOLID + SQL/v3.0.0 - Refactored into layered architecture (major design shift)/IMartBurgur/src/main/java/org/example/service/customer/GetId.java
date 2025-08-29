package org.example.service.customer;

import org.example.repository.BurgerShopDatabase;
import org.example.repository.OurDataBase;
import org.example.elements.InCodeTools;

public class GetId {

    private static BurgerShopDatabase getDatabse() {
        return OurDataBase.SHARED_DB;
    }

    private static int getLatestOrder() {
        return getDatabse().getLatestOrder();
    }

    private static long searchID(int i) {
        return getDatabse().getProfCustIDSOD(i);
    }

    private Integer findID(long customerID) {

        for (int i = 0; i < getLatestOrder(); i++) {
            if (searchID(i) == customerID) {
                return i;
            }
        }
        return null;
    }

    public void getCustIDFound(long customerID) {
        int latestOrder = getLatestOrder();

        System.out.println("We Got the Cust ID : " + customerID);

        int n = latestOrder, nn, gh = 0, prof = -1, gotit = 0;


        if (findID(customerID) != null) {
            System.out.println("\n\n  Customer ID found");
            InCodeTools.printExtra(2, 3);
            InCodeTools.printExtra(62, 1);
            System.out.println("\n   Customer ID - " + "0" + customerID);
            System.out.println("   Name        - " + getDatabse().getCustNameSOD(prof));


            System.out.println("\n\nCustomer Order List");
            System.out.println("=====================\n");
        }
    }


}
