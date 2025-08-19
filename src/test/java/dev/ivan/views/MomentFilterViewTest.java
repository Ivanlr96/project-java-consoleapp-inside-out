package dev.ivan.views;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mockStatic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class MomentFilterViewTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private MockedStatic<MomenFilterByEmotionView> mockedFilterByEmotionView;
    private MockedStatic<MomentFilterByDateView> mockedFilterByDateView;
    private MockedStatic<HomeView> mockedHomeView;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        mockedFilterByEmotionView = mockStatic(MomenFilterByEmotionView.class);
        mockedFilterByDateView = mockStatic(MomentFilterByDateView.class);
        mockedHomeView = mockStatic(HomeView.class);
    }

    @AfterEach
    public void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);

        mockedFilterByEmotionView.close();
        mockedFilterByDateView.close();
        mockedHomeView.close();


        View.SCANNER.close();
    }

    @Test
    void testPrintFilterMenu_Option1_CallsEmotionView() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        View.SCANNER = new Scanner(System.in);

        MomentFilterView.printFilterMenu();

        mockedFilterByEmotionView.verify(() -> MomenFilterByEmotionView.printFilterMenu());
        assertThat(outputStreamCaptor.toString(), containsString("Filtrar por ...:"));
    }

    @Test
    void testPrintFilterMenu_Option2_CallsDateView() {
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        View.SCANNER = new Scanner(System.in);

        MomentFilterView.printFilterMenu();

        mockedFilterByDateView.verify(() -> MomentFilterByDateView.printFilterMenu());
        assertThat(outputStreamCaptor.toString(), containsString("Filtrar por ...:"));
    }

    @Test
    void testPrintFilterMenu_Option3_CallsHomeView() {
        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        View.SCANNER = new Scanner(System.in);

        MomentFilterView.printFilterMenu();

        mockedHomeView.verify(() -> HomeView.printMenu());
        assertThat(outputStreamCaptor.toString(), containsString("Filtrar por ...:"));
    }

    @Test
    void testPrintFilterMenu_InvalidOption_ShowsErrorAndRetries() {
        String input = "9\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        View.SCANNER = new Scanner(System.in);

        MomentFilterView.printFilterMenu();

        assertThat(outputStreamCaptor.toString(), containsString("Opción inválida. Intentelo de nuevo."));
        mockedHomeView.verify(() -> HomeView.printMenu());
    }
}
