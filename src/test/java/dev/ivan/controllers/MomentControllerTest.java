package dev.ivan.controllers;

import dev.ivan.dtos.MomentDTO;
import dev.ivan.models.EmotionEnum;
import dev.ivan.models.Moment;
import dev.ivan.repositories.MomentRepository;
import dev.ivan.singletons.MomentRepositorySingleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MomentControllerTest {

    private MomentController controller;

    @BeforeEach
    void setUp() {
   
        MomentRepositorySingleton.resetInstance();
        controller = new MomentController();
    }

    @Test
    void testStoreAndRetrieveMoment() {
        LocalDate date = LocalDate.of(2025, 8, 14);
        MomentDTO dto = new MomentDTO("Cumpleaños", date, "Mi cumpleaños", EmotionEnum.ALEGRIA);

        controller.StoreMoment(dto);

        List<Moment> moments = controller.getAllMoments();
        assertThat(moments, hasSize(1));

        Moment stored = moments.get(0);
        assertThat(stored.getTitle(), is("Cumpleaños"));
        assertThat(stored.getDate(), is(date));
        assertThat(stored.getEmotionEnum(), is(EmotionEnum.ALEGRIA));
    }

    @Test
    void testDeleteMoment() {
        LocalDate date = LocalDate.of(2025, 8, 14);
        controller.StoreMoment(new MomentDTO("Cumpleaños", date, "Mi cumpleaños", EmotionEnum.ALEGRIA));

        boolean deleted = controller.deleteMoment(0);
        assertThat(deleted, is(true));

        assertThat(controller.getAllMoments(), is(empty()));
    }

    @Test
    void testGetMomentsByEmotion() {
        LocalDate date = LocalDate.of(2025, 8, 14);
        controller.StoreMoment(new MomentDTO("Cumpleaños", date, "Mi cumpleaños", EmotionEnum.ALEGRIA));
        controller.StoreMoment(new MomentDTO("Tristeza", date, "Día triste", EmotionEnum.TRISTEZA));

        List<Moment> alegriaMoments = controller.getAllMoments().stream()
                .filter(m -> m.getEmotionEnum() == EmotionEnum.ALEGRIA)
                .toList();

        assertThat(alegriaMoments, hasSize(1));
        assertThat(alegriaMoments.get(0).getEmotionEnum(), is(EmotionEnum.ALEGRIA));
    }

    @Test
    void testGetMomentsByDate() {
        LocalDate date1 = LocalDate.of(2025, 8, 14);
        LocalDate date2 = LocalDate.of(2025, 8, 15);
        controller.StoreMoment(new MomentDTO("Cumpleaños", date1, "Mi cumpleaños", EmotionEnum.ALEGRIA));
        controller.StoreMoment(new MomentDTO("Otro día", date2, "Otra fecha", EmotionEnum.TRISTEZA));

        List<Moment> momentsOn14 = controller.getMomentsByDate(date1);
        assertThat(momentsOn14, hasSize(1));
        assertThat(momentsOn14.get(0).getDate(), is(date1));
    }
}
