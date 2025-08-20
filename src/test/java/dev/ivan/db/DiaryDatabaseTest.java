package dev.ivan.db;

import dev.ivan.models.EmotionEnum;
import dev.ivan.models.Moment;
import dev.ivan.models.MomentTypeEnum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class DiaryDatabaseTest {

    private DiaryDatabase db;

    @BeforeEach
    void setUp() {
        db = new DiaryDatabase();
    }

    @Test
    void testStoreAndGetAll() {
        Moment moment = new Moment("Cumpleaños", LocalDate.of(2025, 8, 14), "Mi cumpleaños", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno);
        db.store(moment);

        List<Moment> moments = db.getAll();


        assertThat(moments, hasSize(1));
        assertThat(moments.get(0).getTitle(), is("Cumpleaños"));
        assertThat(moments.get(0).getEmotionEnum(), is(EmotionEnum.ALEGRIA));
    }

    @Test
    void testDeleteValidIndex() {
        Moment moment = new Moment("Cumpleaños", LocalDate.of(2025, 8, 14), "Mi cumpleaños", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno);
        db.store(moment);

        boolean deleted = db.delete(0);

        assertThat(deleted, is(true));
        assertThat(db.getAll(), is(empty()));
    }

    @Test
    void testDeleteInvalidIndex() {
        boolean deleted = db.delete(5);
        assertThat(deleted, is(false));
    }

    @Test
    void testGetAllEmpty() {
        List<Moment> moments = db.getAll();
        assertThat(moments, is(empty()));
    }
}
