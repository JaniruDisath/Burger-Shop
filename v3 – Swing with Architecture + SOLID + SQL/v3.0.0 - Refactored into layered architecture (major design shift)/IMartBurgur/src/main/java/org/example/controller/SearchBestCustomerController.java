package org.example.controller;

import org.example.repository.OurDataBase;
import org.example.view.MainWindow;
import org.example.view.SearchBestCustomer;
import org.example.view.SearchMenu;

public class SearchBestCustomerController {

    private SearchBestCustomer view;
    public SearchBestCustomerController(SearchBestCustomer view){
        this.view=view;
    }
    public void inti(){
        view.onBackToHomePage(e -> {
            view.close();
            new MainWindow().openMainWindow();
        });
        view.onBack(e -> {
            view.close();
            new SearchMenu().openSearchMenu();
        });
        loadTable();
    }

    public int[] getDataForTable(){

        double[] totalValues = new double[OurDataBase.SHARED_DB.getLatestProfile()];
        int[] totalPositions = new int[OurDataBase.SHARED_DB.getLatestProfile()];

        int profileNumberCount = OurDataBase.SHARED_DB.getLatestProfile();

        for (int i = 0; i < profileNumberCount; i++) {
            totalValues[i] = OurDataBase.SHARED_DB.getCustTotalSOD(i);
            totalPositions[i] = i;
        }

        for (int i = 0; i < profileNumberCount - 1; i++) {
            for (int j = i + 1; j < profileNumberCount; j++) {
                if (totalValues[i] < totalValues[j]) {
                    double temp = totalValues[i];
                    totalValues[i] = totalValues[j];
                    totalValues[j] = temp;
                    int tempPos = totalPositions[i];
                    totalPositions[i] = totalPositions[j];
                    totalPositions[j] = tempPos;
                }
            }
        }
        return totalPositions;
    }


    public void loadTable() {
        for (int i = 0; i < OurDataBase.SHARED_DB.getLatestProfile(); i++) {
            int r = getDataForTable()[i];
            view.getTableModel().addRow(new Object[]{(i + 1), "0" + OurDataBase.SHARED_DB.getProfCustIDSOD(r), OurDataBase.SHARED_DB.getCustNameSOD(r), OurDataBase.SHARED_DB.getCustTotalSOD(r)});
        }
    }

}
