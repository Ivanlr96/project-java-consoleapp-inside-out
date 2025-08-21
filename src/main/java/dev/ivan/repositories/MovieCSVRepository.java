package dev.ivan.repositories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.models.movie.Movie;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MovieCSVRepository {

    private final File file;

    public MovieCSVRepository() {
        String projectDir = System.getProperty("user.dir");
        String path = projectDir + "/data/movies.csv";
        this.file = new File(path);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                writeHeader();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeHeader() throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(file), ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            writer.writeNext(new String[]{"imdbId", "title", "genres", "emotion", "releaseYear", "createdAt"});
        }
    }

    public void save(Movie movie) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(file, true), ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            writer.writeNext(movie.toCSV());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(file))
                .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(';').build())
                .build()) {
            reader.readNext(); // Skip header
            String[] line;
            while ((line = reader.readNext()) != null) {
                String imdbId = line[0];
                String title = line[1];
                List<String> genres = Arrays.asList(line[2].split(","));
                EmotionEnum emotion = EmotionEnum.valueOf(line[3]);
                LocalDate releaseYear = LocalDate.parse(line[4]);
                LocalDateTime createdAt = LocalDateTime.parse(line[5]);

                Movie movie = new Movie(imdbId, title, genres, emotion, releaseYear);
                movie.setCreatedAt(createdAt);
                movies.add(movie);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public void delete(String imdbId) {
        List<Movie> movies = findAll();
        List<Movie> updatedMovies = movies.stream()
                .filter(movie -> !movie.getImdbId().equals(imdbId))
                .collect(Collectors.toList());

        try (CSVWriter writer = new CSVWriter(new FileWriter(file), ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            writeHeader();
            for (Movie movie : updatedMovies) {
                writer.writeNext(movie.toCSV());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}