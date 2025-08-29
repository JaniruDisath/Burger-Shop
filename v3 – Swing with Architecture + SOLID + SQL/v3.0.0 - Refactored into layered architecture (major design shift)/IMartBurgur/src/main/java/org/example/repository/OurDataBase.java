package org.example.repository;

import org.example.view.MainWindow;
import org.example.view.SearchOrderDetails;
import org.example.controller.UpdateDatabase;

public class OurDataBase {
    //The Common Object - One Database to Rule Them All ;)
    public static final BurgerShopDatabase SHARED_DB = new BurgerShopDatabase();
    public static final MainWindow MAIN_WINDOW = new MainWindow();
    public static SearchOrderDetails SEARCH_ORDER = new SearchOrderDetails();
    public static int devOptions = 0;
}
