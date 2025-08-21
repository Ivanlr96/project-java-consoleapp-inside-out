package dev.ivan.controllers;

import dev.ivan.dtos.MomentDTO;
import dev.ivan.dtos.MomentResponseDTO;
import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.models.moment.MomentTypeEnum;
import dev.ivan.repositories.MomentRepository;
import dev.ivan.singletons.MomentRepositorySingleton;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MomentControllerTest {

    private MomentController controller;

    @BeforeEach
    void setUp() {
        MomentRepository repo = MomentRepositorySingleton.getInstance();
        repo.getAllMoments().clear();
        controller = new MomentController();
    }

    @Test
    void testStoreMomentAndGetAllMoments() {
        MomentDTO dto = new MomentDTO("Título", LocalDate.of(2023, 8, 19), "Descripción", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno);
        controller.storeMoment(dto);

        List<MomentResponseDTO> moments = controller.getAllMoments();
        assertEquals(1, moments.size());
        assertEquals("Título", moments.get(0).title());
        assertEquals("Descripción", moments.get(0).description());
        assertEquals(EmotionEnum.ALEGRIA, moments.get(0).emotion());
        assertEquals(MomentTypeEnum.Bueno, moments.get(0).type());
        assertEquals("19/08/2023", moments.get(0).date());
    }

    @Test
    void testDeleteMoment() {
        MomentDTO dto = new MomentDTO("Borrar", LocalDate.now(), "Desc", EmotionEnum.TRISTEZA, MomentTypeEnum.Malo);
        controller.storeMoment(dto);

        boolean deleted = controller.deleteMoment(0);
        assertTrue(deleted);
        assertEquals(0, controller.getAllMoments().size());
    }

    @Test
    void testDeleteMomentInvalidIndex() {
        boolean deleted = controller.deleteMoment(99);
        assertFalse(deleted);
    }

    @Test
    void testShowMomentsByEmotion() {
        controller.storeMoment(new MomentDTO("Feliz", LocalDate.now(), "Desc1", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno));
        controller.storeMoment(new MomentDTO("Triste", LocalDate.now(), "Desc2", EmotionEnum.TRISTEZA, MomentTypeEnum.Malo));

        List<MomentResponseDTO> happyMoments = controller.showMomentsByEmotion(EmotionEnum.ALEGRIA);
        assertEquals(1, happyMoments.size());
        assertEquals("Feliz", happyMoments.get(0).title());
        assertEquals(EmotionEnum.ALEGRIA, happyMoments.get(0).emotion());
    }

    @Test
    void testGetMomentsByDate() {
        LocalDate date = LocalDate.of(2025, 8, 19);
        controller.storeMoment(new MomentDTO("Hoy", date, "Desc", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno));
        controller.storeMoment(new MomentDTO("Otro día", LocalDate.of(2024, 1, 1), "Desc2", EmotionEnum.ALEGRIA, MomentTypeEnum.Malo));

        List<MomentResponseDTO> moments = controller.getMomentsByDate(date);
        assertEquals(1, moments.size());
        assertEquals("Hoy", moments.get(0).title());
        assertEquals("19/08/2025", moments.get(0).date());
    }

    @Test
    void testShowMomentsByType() {

    controller.storeMoment(new MomentDTO("Momento bueno 1", LocalDate.now(), "Desc1", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno));
    controller.storeMoment(new MomentDTO("Momento malo 1", LocalDate.now(), "Desc2", EmotionEnum.TRISTEZA, MomentTypeEnum.Malo));
    controller.storeMoment(new MomentDTO("Momento bueno 2", LocalDate.now(), "Desc3", EmotionEnum.MIEDO, MomentTypeEnum.Bueno));

    List<MomentResponseDTO> buenos = controller.showMomentsByType(MomentTypeEnum.Bueno);
    assertEquals(2, buenos.size());
    assertTrue(buenos.stream().anyMatch(m -> m.title().equals("Momento bueno 1")));
    assertTrue(buenos.stream().anyMatch(m -> m.title().equals("Momento bueno 2")));


    List<MomentResponseDTO> malos = controller.showMomentsByType(MomentTypeEnum.Malo);
    assertEquals(1, malos.size());
    assertEquals("Momento malo 1", malos.get(0).title());
}

}
