package dev.ivan.dtos;

import dev.ivan.models.EmotionEnum;

public record MomentResponseDTO(
        String title,
        String date,
        String description,
        EmotionEnum emotion
)  {}
