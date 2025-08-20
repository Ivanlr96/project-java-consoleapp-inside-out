package dev.ivan.dtos;

import dev.ivan.models.EmotionEnum;
import dev.ivan.models.MomentTypeEnum;

public record MomentResponseDTO(
        String title,
        String date,
        String description,
        EmotionEnum emotion,
        MomentTypeEnum type
)  {}
