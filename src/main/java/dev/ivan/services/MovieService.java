package dev.ivan.services;

import com.google.gson.Gson;
import dev.ivan.daos.ApiMoviesDAO;
import dev.ivan.dtos.MovieDTO;
import dev.ivan.mappers.MovieMapper;
import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.models.movie.Movie;

public class MovieService {

    private final ApiMoviesDAO apiMoviesDAO;
    private final Gson gson;

    public MovieService() {
        this.apiMoviesDAO = new ApiMoviesDAO();
        this.gson = new Gson();
    }

    public Movie getMovie(String imdbId, EmotionEnum emotion) {
        String jsonResponse = apiMoviesDAO.getMovie(imdbId);
        MovieDTO movieDTO = gson.fromJson(jsonResponse, MovieDTO.class);
        return MovieMapper.fromDTO(movieDTO, emotion);
    }
}