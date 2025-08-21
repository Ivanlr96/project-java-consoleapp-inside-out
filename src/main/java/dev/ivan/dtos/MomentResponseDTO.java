package dev.ivan.dtos;

import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.models.moment.MomentTypeEnum;

public record MomentResponseDTO(
        String title,
        String date,
        String description,
        EmotionEnum emotion,
        MomentTypeEnum type
)  {}
