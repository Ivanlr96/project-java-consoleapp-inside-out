package dev.ivan.views;

import dev.ivan.dtos.MomentResponseDTO;
import dev.ivan.models.EmotionEnum;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mockStatic;

class AllMomentsViewTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private MockedStatic<HomeView> mockedHomeView;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        mockedHomeView = mockStatic(HomeView.class);
    }

    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
        mockedHomeView.close();
    }

    // @Test
    // void shouldPrintNoMomentsMessageWhenListIsEmpty() {
    //     AllMomentsView.printMoments(List.of());

    //     String output = outputStreamCaptor.toString();
    //     assertThat(output, containsString("No hay momentos guardados."));

    //     // Verifica que después llama al menú principal
    //     mockedHomeView.verify(() -> HomeView.printMenu());
    // }

    @Test
    void shouldPrintMomentsWhenListIsNotEmpty() {
        MomentResponseDTO moment = new MomentResponseDTO(
                "Mi título",
                "12/05/2020",
                "Una descripción",
                EmotionEnum.ALEGRIA
        );

        AllMomentsView.printMoments(List.of(moment));

        String output = outputStreamCaptor.toString();
        assertThat(output, containsString("Momentos guardados:"));
        assertThat(output, containsString("Ocurrió el: 12/05/2020"));
        assertThat(output, containsString("Título: Mi título"));
        assertThat(output, containsString("Descripción: Una descripción"));

        // Verifica que también vuelve al menú
        mockedHomeView.verify(() -> HomeView.printMenu());
    }
}
