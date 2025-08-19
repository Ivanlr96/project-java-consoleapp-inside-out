package dev.ivan.views;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.ivan.views.MomentFilterView;

public class MomentFilterViewTest {

    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testFilterMenuOptionEmotion() {

        String simulatedInput = "1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            MomentFilterView.printFilterMenu();
        } catch (Exception e) {

        }

        String output = outContent.toString();

        assertTrue(output.contains("Filtrar por ...:"));
        assertTrue(output.contains("1. Emoción"));
        assertTrue(output.contains("2. Fecha"));
    }

    @Test
    void testFilterMenuInvalidOption() {
      
        String simulatedInput = "9\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            MomentFilterView.printFilterMenu();
        } catch (Exception e) {
        
        }

        String output = outContent.toString();

        assertTrue(output.contains("Opción inválida. Intentelo de nuevo."));
    }
}
