package dev.ivan.repositories;

import com.opencsv.CSVWriter;

import dev.ivan.models.moment.Moment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MomentCSVRepository {

    public boolean exportMomentsToCSV(List<Moment> moments, String path) {
        try {
            String projectDir = System.getProperty("user.dir");
            String defaultPath = projectDir + "/data/momentos.csv";
            File file = new File(path != null ? path : defaultPath);

            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            try (
                OutputStreamWriter osw = new OutputStreamWriter(
                        new FileOutputStream(file),
                        StandardCharsets.UTF_8
                );
                CSVWriter writer = new CSVWriter(
                        osw,
                        ';',
                        CSVWriter.DEFAULT_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END
                )
            ) {

                osw.write('\uFEFF');


                writer.writeNext(new String[] { "Título", "Descripción", "Fecha", "Emoción", "Tipo" });


                for (Moment m : moments) {
                    writer.writeNext(new String[] {
                        m.getTitle() != null ? m.getTitle() : "",
                        m.getDescription() != null ? m.getDescription() : "",
                        m.getDate() != null ? m.getDate().toString() : "",
                        m.getEmotionEnum() != null ? m.getEmotionEnum().getDisplayName() : "",
                        m.getType() != null ? m.getType().name() : ""
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
