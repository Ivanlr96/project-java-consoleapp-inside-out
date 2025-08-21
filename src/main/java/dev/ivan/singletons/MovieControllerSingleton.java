package dev.ivan.singletons;

import dev.ivan.controllers.MovieController;

public class MovieControllerSingleton {

    private static MovieController instance;

    private MovieControllerSingleton() {}

    public static MovieController getInstance() {
        if (instance == null) {
            instance = new MovieController();
        }
        return instance;
    }
}
