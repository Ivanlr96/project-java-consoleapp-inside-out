package dev.ivan.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Moment {

    private String title;
    private LocalDate date;
    private String description;
    private EmotionEnum emotion;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Moment(String title, LocalDate date, String description, EmotionEnum emotion) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.emotion = emotion;
        this.createdAt = LocalDateTime.now(); 
        this.updatedAt = createdAt;          
    }

 
    public String getTitle() { return title; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
    public EmotionEnum getEmotionEnum() { return emotion; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt() { this.updatedAt = LocalDateTime.now(); }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Título: " + title + "\n" +
               "Fecha: " + date.format(formatter) + "\n" +
               "Descripción: " + description + "\n" +
               "Emoción: " + emotion.getDisplayName() + "\n";
    }
}
