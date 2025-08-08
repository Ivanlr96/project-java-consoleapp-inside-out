package dev.ivan.controllers;

import dev.ivan.views.HomeView;

;
public class HomeController {

    public HomeController() {
        index();
    }

    public static void index() {
        HomeView.printMenu();
    }

}
