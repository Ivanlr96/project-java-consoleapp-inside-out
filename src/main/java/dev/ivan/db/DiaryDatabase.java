package dev.ivan.db;

import dev.ivan.contracts.InterfaceDatabase;
import dev.ivan.models.moment.Moment;

import java.util.ArrayList;
import java.util.List;

public class DiaryDatabase implements InterfaceDatabase<Moment> {

    private List<Moment> moments;

    public DiaryDatabase() {
        this.moments = new ArrayList<>();
    }

    @Override
    public void store(Moment moment) {
        moments.add(moment);
    }

    @Override
    public List<Moment> getAll() {
        return moments;
    }

    @Override
    public boolean delete(int index) {
        if (index >= 0 && index < moments.size()) {
            moments.remove(index);
            return true;
        }
        return false;
    }
}