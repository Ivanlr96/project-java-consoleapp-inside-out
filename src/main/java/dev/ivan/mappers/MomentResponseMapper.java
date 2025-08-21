package dev.ivan.mappers;

import dev.ivan.dtos.MomentResponseDTO;
import dev.ivan.models.moment.Moment;

import java.time.format.DateTimeFormatter;

public class MomentResponseMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static MomentResponseDTO toResponseDTO(Moment moment) {
        return new MomentResponseDTO(
                moment.getTitle(),
                moment.getDate().format(formatter),
                moment.getDescription(),
                moment.getEmotionEnum(),
                moment.getType()
        );
    }
}
