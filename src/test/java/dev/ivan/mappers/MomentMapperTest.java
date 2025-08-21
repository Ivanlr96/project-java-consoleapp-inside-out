package dev.ivan.mappers;

import dev.ivan.dtos.MomentDTO;
import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.models.moment.Moment;
import dev.ivan.models.moment.MomentTypeEnum;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MomentMapperTest {

    @Test
    void testToEntityMapsCorrectly() {

        LocalDate date = LocalDate.of(2025, 8, 14);
        MomentDTO dto = new MomentDTO("Cumpleaños", date, "Mi cumpleaños", EmotionEnum.ALEGRIA, MomentTypeEnum.Bueno);
        Moment moment = MomentMapper.toEntity(dto);

        assertThat(moment.getTitle(), is(dto.title()));
        assertThat(moment.getDate(), is(dto.date()));
        assertThat(moment.getDescription(), is(dto.description()));
        assertThat(moment.getEmotionEnum(), is(dto.emotion()));

    }
}
