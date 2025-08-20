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

public class MomentFilterByTypeViewTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private MockedStatic<AllMomentsView> mockedAllMomentsView;
    private MockedStatic<HomeView> mockedHomeView;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        mockedAllMomentsView = mockStatic(AllMomentsView.class);
        mockedHomeView = mockStatic(HomeView.class);
    }

    @AfterEach
    public void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);

        mockedAllMomentsView.close();
        mockedHomeView.close();

        View.SCANNER.close();
    }

    @Test
    void testPrintFilterMenu_ValidChoice_CallsPrintMomentsAndHomeMenu() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        View.SCANNER = new Scanner(System.in);

        MomentFilterByTypeView.printFilterMenu();

        mockedAllMomentsView.verify(() -> AllMomentsView.printMoments(org.mockito.ArgumentMatchers.any()));
        mockedHomeView.verify(HomeView::printMenu);

        assertThat(outputStreamCaptor.toString(), containsString("Seleccione el tipo de momento:"));
    }

    @Test
    void testPrintFilterMenu_InvalidChoice_CallsHomeMenu() {
        String input = "99\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        View.SCANNER = new Scanner(System.in);

        MomentFilterByTypeView.printFilterMenu();

        mockedHomeView.verify(HomeView::printMenu);
        assertThat(outputStreamCaptor.toString(), containsString("Opción inválida."));
    }
}
