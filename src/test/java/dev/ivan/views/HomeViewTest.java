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

import dev.ivan.views.moment.MomentDeleteView;
import dev.ivan.views.moment.MomentFilterView;
import dev.ivan.views.moment.MomentPostView;

public class HomeViewTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private MockedStatic<MomentPostView> mockedMomentPostView;
    private MockedStatic<MomentDeleteView> mockedMomentDeleteView;
    private MockedStatic<MomentFilterView> mockedMomentFilterView;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        mockedMomentPostView = mockStatic(MomentPostView.class);
        mockedMomentDeleteView = mockStatic(MomentDeleteView.class);
        mockedMomentFilterView = mockStatic(MomentFilterView.class);
    }

    @AfterEach
    public void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);

        mockedMomentPostView.close();
        mockedMomentDeleteView.close();
        mockedMomentFilterView.close();

        View.SCANNER.close();
    }

    @Test
    void testPrintMenu_Option1_CallsMomentPostView() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        View.SCANNER = new Scanner(System.in);

        HomeView.printMenu();

        mockedMomentPostView.verify(() -> MomentPostView.printStoreMenu());
        assertThat(outputStreamCaptor.toString(), containsString("Seleccione una opción:"));
    }

    @Test
    void testPrintMenu_Option3_CallsMomentDeleteView() {
        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        View.SCANNER = new Scanner(System.in);

        HomeView.printMenu();

        mockedMomentDeleteView.verify(() -> MomentDeleteView.printDeleteMenu());
        assertThat(outputStreamCaptor.toString(), containsString("Seleccione una opción:"));
    }

    @Test
    void testPrintMenu_Option4_CallsMomentFilterView() {
        String input = "4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        View.SCANNER = new Scanner(System.in);

        HomeView.printMenu();

        mockedMomentFilterView.verify(() -> MomentFilterView.printFilterMenu());
        assertThat(outputStreamCaptor.toString(), containsString("Seleccione una opción:"));
    }
}
