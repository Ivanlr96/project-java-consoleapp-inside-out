package dev.ivan.dtos;

import dev.ivan.models.EmotionEnum;

public record MomentDTO(String title, String date, String description, EmotionEnum emotion) {

}
