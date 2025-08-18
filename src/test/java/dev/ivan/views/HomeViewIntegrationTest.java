package dev.ivan.views;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HomeViewIntegrationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final java.util.Scanner originalScanner = HomeView.SCANNER;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        HomeView.SCANNER = originalScanner;
    }

    @Test
    void testPrintMenu_AddMomentOption() {

        String simulatedInput = String.join("\n",
                "1",
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                "1",
                "5"
        );

        HomeView.SCANNER = new java.util.Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        HomeView.printMenu();

        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ingrese el título:"));
        assertTrue(output.contains("Ingrese la fecha (dd/MM/yyyy):"));
        assertTrue(output.contains("Ingrese la descripción:"));
        assertTrue(output.contains("Seleccione la emoción:"));
        assertTrue(output.contains("1. "));
        assertTrue(output.contains("Seleccione una opción:"));

    }

    @Test
    void testPrintMenu_ShowAllMomentsOption() {

        String simulatedInput = "2\n5\n";
        HomeView.SCANNER = new java.util.Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        HomeView.printMenu();

        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("1. Añadir momento"));
        assertTrue(output.contains("2. Ver todos los momentos disponibles"));
        assertTrue(output.contains("Seleccione una opción:"));
        assertTrue(output.contains("No hay momentos guardados.") || output.contains("Título:"));
    }

    @Test
    void testPrintMenu_InvalidOption() {

        String simulatedInput = "9\n5\n";
        HomeView.SCANNER = new java.util.Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        HomeView.printMenu();

        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Opción inválida. Inténtalo de nuevo."));
        assertTrue(output.contains("Seleccione una opción:"));
    }
}
