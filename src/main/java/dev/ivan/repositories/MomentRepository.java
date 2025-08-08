package dev.ivan.repositories;

import dev.ivan.db.DiaryDatabase;
import dev.ivan.models.Moment;

public class MomentRepository {

    private DiaryDatabase db;

    public MomentRepository() {
        this.db = new DiaryDatabase();
    }

    public void StoreMoment(Moment moment) {
        db.store(moment);
    }

}
