package dev.ivan.controllers;

import dev.ivan.dtos.MomentDTO;
import dev.ivan.dtos.MomentResponseDTO;
import dev.ivan.models.EmotionEnum;
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
        // Limpiar la base de datos antes de cada test
        MomentRepository repo = MomentRepositorySingleton.getInstance();
        repo.getAllMoments().clear();
        controller = new MomentController();
    }

    @Test
    void testStoreMomentAndGetAllMoments() {
        MomentDTO dto = new MomentDTO("Título", LocalDate.of(2023, 8, 19), "Descripción", EmotionEnum.ALEGRIA);
        controller.storeMoment(dto);

        List<MomentResponseDTO> moments = controller.getAllMoments();
        assertEquals(1, moments.size());
        assertEquals("Título", moments.get(0).title());
        assertEquals("Descripción", moments.get(0).description());
        assertEquals(EmotionEnum.ALEGRIA, moments.get(0).emotion());
        assertEquals("19/08/2023", moments.get(0).date());
    }

    @Test
    void testDeleteMoment() {
        MomentDTO dto = new MomentDTO("Borrar", LocalDate.now(), "Desc", EmotionEnum.TRISTEZA);
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
        controller.storeMoment(new MomentDTO("Feliz", LocalDate.now(), "Desc1", EmotionEnum.ALEGRIA));
        controller.storeMoment(new MomentDTO("Triste", LocalDate.now(), "Desc2", EmotionEnum.TRISTEZA));

        List<MomentResponseDTO> happyMoments = controller.showMomentsByEmotion(EmotionEnum.ALEGRIA);
        assertEquals(1, happyMoments.size());
        assertEquals("Feliz", happyMoments.get(0).title());
        assertEquals(EmotionEnum.ALEGRIA, happyMoments.get(0).emotion());
    }

    @Test
    void testGetMomentsByDate() {
        LocalDate date = LocalDate.of(2025, 8, 19);
        controller.storeMoment(new MomentDTO("Hoy", date, "Desc", EmotionEnum.ALEGRIA));
        controller.storeMoment(new MomentDTO("Otro día", LocalDate.of(2024, 1, 1), "Desc2", EmotionEnum.ALEGRIA));

        List<MomentResponseDTO> moments = controller.getMomentsByDate(date);
        assertEquals(1, moments.size());
        assertEquals("Hoy", moments.get(0).title());
        assertEquals("19/08/2025", moments.get(0).date());
    }
}