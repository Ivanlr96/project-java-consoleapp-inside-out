package dev.ivan.mappers;

import java.time.LocalDate;
import dev.ivan.dtos.MovieDTO;
import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.models.movie.Movie;

public class MovieMapper {

    public static Movie fromDTO(MovieDTO dto, EmotionEnum emotion) {
        String imdbId = dto.getImdbId();
        String title = dto.getShortInfo().getTitle();
        java.util.List<String> genres = dto.getShortInfo().getGenres();
        LocalDate releaseYear = LocalDate.parse(dto.getShortInfo().getReleaseYear());

        return new Movie(imdbId, title, genres, emotion, releaseYear);
    }
}
