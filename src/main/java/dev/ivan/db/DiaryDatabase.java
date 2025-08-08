package dev.ivan.db;

import java.util.ArrayList;
import java.util.List;
import dev.ivan.models.Moment;

public class DiaryDatabase {

    private List<Moment> moments;

    public DiaryDatabase() {
        this.moments = new ArrayList<>();
    }

    public void store(Moment moment) {
        moments.add(moment);
    }


}

