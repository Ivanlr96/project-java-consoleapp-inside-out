package dev.ivan.views;

import dev.ivan.controllers.MomentController;
import dev.ivan.dtos.MomentDTO;
import dev.ivan.models.EmotionEnum;
import dev.ivan.singletons.MomentControllerSingleton;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MomentPostViewTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private final ByteArrayOutputStream outputCaptor = new ByteArrayOutputStream();

    private MockedStatic<HomeView> mockedHomeView;
    private MomentController mockController;

 @BeforeEach
void setUp() throws Exception {
    System.setOut(new PrintStream(outputCaptor));

    mockController = mock(MomentController.class);

    MockedStatic<MomentControllerSingleton> mockedSingleton =
            mockStatic(MomentControllerSingleton.class);
    mockedSingleton.when(MomentControllerSingleton::getInstance)
            .thenReturn(mockController);


    var field = MomentPostView.class.getDeclaredField("CONTROLLER");
    field.setAccessible(true);
    field.set(null, mockController);

    mockedHomeView = mockStatic(HomeView.class);
}

    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
        mockedHomeView.close();
    }

    @Test
    void shouldStoreMomentAndReturnToHomeMenu() throws Exception {

        String input = String.join("\n",
                "MiTitulo",
                "12/05/2020",
                "MiDescripcion",
                "1"
        ) + "\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        View.SCANNER = new Scanner(System.in);

        MomentPostView.printStoreMenu();

        String output = outputCaptor.toString();

        assertThat(output, containsString("Ingrese el título:"));
        assertThat(output, containsString("Ingrese la fecha (dd/MM/yyyy):"));
        assertThat(output, containsString("Ingrese la descripción:"));
        assertThat(output, containsString("Seleccione la emoción:"));


        verify(mockController, times(1)).storeMoment(any(MomentDTO.class));


        mockedHomeView.verify(() -> HomeView.printMenu());
    }
}
