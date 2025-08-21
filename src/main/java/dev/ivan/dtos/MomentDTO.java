package dev.ivan.dtos;

import java.time.LocalDate;

import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.models.moment.MomentTypeEnum;

public record MomentDTO(String title, LocalDate date, String description, EmotionEnum emotion, MomentTypeEnum type) {

}
