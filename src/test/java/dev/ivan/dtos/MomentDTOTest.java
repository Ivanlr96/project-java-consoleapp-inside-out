package dev.ivan.dtos;

import dev.ivan.models.EmotionEnum;
import dev.ivan.models.MomentTypeEnum;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MomentDTOTest {

    @Test
    void testMomentDTOStoresValues() {
        LocalDate date = LocalDate.of(2025, 8, 14);
        MomentDTO dto = new MomentDTO("Cumpleaños", date, "Mi cumpleaños", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno);

        assertThat(dto.title(), is("Cumpleaños"));
        assertThat(dto.date(), is(date));
        assertThat(dto.description(), is("Mi cumpleaños"));
        assertThat(dto.emotion(), is(EmotionEnum.ALEGRIA));
    }

    @Test
    void testMomentDTOEqualsAndHashCode() {
        LocalDate date = LocalDate.of(2025, 8, 14);
        MomentDTO dto1 = new MomentDTO("Cumpleaños", date, "Mi cumpleaños", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno);
        MomentDTO dto2 = new MomentDTO("Cumpleaños", date, "Mi cumpleaños", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno);

        assertThat(dto1.equals(dto2), is(true));
        assertThat(dto1.hashCode(), is(dto2.hashCode()));
    }
}
