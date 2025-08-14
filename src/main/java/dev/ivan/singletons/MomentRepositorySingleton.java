package dev.ivan.singletons;

import dev.ivan.repositories.MomentRepository;

public class MomentRepositorySingleton {
     private static MomentRepository INSTANCE; 

    private MomentRepositorySingleton() {} 

    public static MomentRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MomentRepository();
        }
        return INSTANCE;
    }
}
