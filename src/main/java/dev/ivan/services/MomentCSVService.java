package dev.ivan.services;

import com.opencsv.CSVWriter;
import dev.ivan.models.Moment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MomentCSVService {


    public boolean exportMomentsToCSV(List<Moment> moments, String path) {
        try {
            String projectDir = System.getProperty("user.dir");
            String defaultPath = projectDir + "/data/momentos.csv";
            File file = new File(path != null ? path : defaultPath);

            file.getParentFile().mkdirs();

            try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {

                writer.writeNext(new String[] { "Título", "Descripción", "Fecha", "Emoción", "Tipo" });

                for (Moment m : moments) {
                    writer.writeNext(new String[] {
                        m.getTitle(),
                        m.getDescription(),
                        m.getDate().toString(),
                        m.getEmotionEnum().getDisplayName(),
                        m.getType().name()
                    });
                }
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
