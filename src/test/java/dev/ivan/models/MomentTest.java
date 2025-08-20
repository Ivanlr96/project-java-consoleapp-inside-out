package dev.ivan.models;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class MomentTest {
    @Test
    void testMomentGetters() {
        LocalDate date = LocalDate.of(2025, 8, 14);
        Moment moment = new Moment("Título prueba", date, "Descripción prueba", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno);
        assertEquals("Título prueba", moment.getTitle());
        assertEquals(date, moment.getDate());
        assertEquals("Descripción prueba", moment.getDescription());
        assertEquals(EmotionEnum.ALEGRIA, moment.getEmotionEnum());
        assertNotNull(moment.getCreatedAt());
        assertNotNull(moment.getUpdatedAt());
    }
    @Test
    void testSetUpdatedAt() throws InterruptedException {
        Moment moment = new Moment("Título", LocalDate.now(), "Descripción", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno);
        LocalDateTime firstUpdatedAt = moment.getUpdatedAt();
        Thread.sleep(10);
        moment.setUpdatedAt();
        assertTrue(moment.getUpdatedAt().isAfter(firstUpdatedAt));
    }
    @Test
    void testToStringContainsAllFields() {
        LocalDate date = LocalDate.of(2025, 8, 14);
        Moment moment = new Moment("Título prueba", date, "Descripción prueba", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno);
        String str = moment.toString();
        assertTrue(str.contains("Título: Título prueba"));
        assertTrue(str.contains("Fecha: 14/08/2025"));
        assertTrue(str.contains("Descripción: Descripción prueba"));
        assertTrue(str.contains("Emoción: Alegría"));
    }
}
