package dev.ivan.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dev.ivan.db.DiaryDatabase;
import dev.ivan.models.EmotionEnum;
import dev.ivan.models.Moment;

public class MomentRepository {

    private DiaryDatabase db;

    public MomentRepository() {
        this.db = new DiaryDatabase();
    }

    public void StoreMoment(Moment moment) {
        db.store(moment);
    }
       public List<Moment> getAllMoments() {
        return db.getAll();
    }

    public boolean deleteMoment(int index) {
    return db.deleteMoment(index);
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

}
