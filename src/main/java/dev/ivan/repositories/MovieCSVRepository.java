package dev.ivan.repositories;

import com.opencsv.CSVWriter;
import dev.ivan.models.movie.Movie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
        try (CSVWriter writer = new CSVWriter(new FileWriter(file, true), ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            writer.writeNext(new String[]{"imdbId", "Titulo", "Genero", "Emocion", "Fecha de estreno", "Fecha de Creacion"});
        }
    }

    public void save(Movie movie) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(file, true), ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            writer.writeNext(movie.toCSV());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
