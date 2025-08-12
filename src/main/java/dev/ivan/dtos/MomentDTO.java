package dev.ivan.dtos;

import java.time.LocalDate;

import dev.ivan.models.EmotionEnum;

public record MomentDTO(String title, LocalDate date, String description, EmotionEnum emotion) {

}
