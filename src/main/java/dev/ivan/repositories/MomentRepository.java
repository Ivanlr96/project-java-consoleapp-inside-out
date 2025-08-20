package dev.ivan.repositories;

import dev.ivan.contracts.InterfaceDatabase;
import dev.ivan.db.DiaryDatabase;
import dev.ivan.models.Moment;
import dev.ivan.models.MomentTypeEnum;
import dev.ivan.models.EmotionEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class MomentRepository {

    private InterfaceDatabase<Moment> db;

    public MomentRepository() {
        this.db = new DiaryDatabase();
    }

    public void setDb(String dbType) {
        if (dbType.equalsIgnoreCase("diary")) {
            this.db = new DiaryDatabase();
        }

    }

    public void storeMoment(Moment moment) {
        db.store(moment);
    }

    public List<Moment> getAllMoments() {
        return db.getAll();
    }

    public boolean deleteMoment(int index) {
        return db.delete(index);
    }

    public List<Moment> getMomentsByEmotion(EmotionEnum emotion) {
        return db.getAll().stream()
                .filter(m -> m.getEmotionEnum() == emotion)
                .toList();
    }

    public List<Moment> getMomentsByDate(LocalDate date) {
        List<Moment> filtered = new ArrayList<>();
        for (Moment moment : db.getAll()) {
            if (moment.getDate().isEqual(date)) {
                filtered.add(moment);
            }
        }
        return filtered;
    }

    public List<Moment> getMomentsByType(MomentTypeEnum type) {
        return db.getAll().stream()
                .filter(m -> m.getType() == type)
                .toList();
    }
}
