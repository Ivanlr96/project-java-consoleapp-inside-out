package dev.ivan.singletons;

import dev.ivan.controllers.MomentController;

public class MomentControllerSingleton {

    private static MomentController instance;

    private MomentControllerSingleton() {}

    public static MomentController getInstance() {
        if (instance == null) {
            instance = new MomentController();
        }
        return instance;
    } 
}
