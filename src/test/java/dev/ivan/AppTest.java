package dev.ivan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.OutputStream;

public class AppTest {

    @Test
    void testSalirConOpcion5() throws Exception {
        ProcessBuilder pb = new ProcessBuilder(
                "java", "-cp", "target/classes", "dev.ivan.App"
        );
        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (OutputStream os = process.getOutputStream()) {
            os.write("6\n".getBytes());
            os.flush();
        }

        String output = new String(process.getInputStream().readAllBytes());
        int exitCode = process.waitFor();

        assertTrue(output.contains("Hasta la proxima"),
           "La salida no contiene el mensaje de salida esperado: " + output);

        assertEquals(0, exitCode, "El código de salida no fue 0");
    }
}
