package dev.ivan.models.movie;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import dev.ivan.models.moment.*;

public class Movie {

    private String imdbId;
    private String title;
    private List<String> genres; 
    private EmotionEnum emotion; 
    private LocalDate releaseYear; 
    private LocalDateTime createdAt; 

    public Movie(String imdbId, String title, List<String> genres, EmotionEnum emotion, LocalDate releaseYear) {
        this.imdbId = imdbId;
        this.title = title;
        this.genres = genres;
        this.emotion = emotion;
        this.releaseYear = releaseYear;
        this.createdAt = LocalDateTime.now();
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public EmotionEnum getEmotion() {
        return emotion;
    }

    public void setEmotion(EmotionEnum emotion) {
        this.emotion = emotion;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String[] toCSV() {
        return new String[]{
                imdbId,
                title,
                String.join(",", genres),
                emotion.getDisplayName(),
                releaseYear.toString(),
                createdAt.toString()
        };
    }

    @Override
    public String toString() {
        return "Movie{"
                + "imdbId='" + imdbId + "'"
                + ", title='" + title + "'"
                + ", genres=" + genres
                + ", emotion=" + (emotion != null ? emotion.getDisplayName() : "N/A")
                + ", releaseYear=" + releaseYear
                + ", createdAt=" + createdAt
                + "}";
    }
}