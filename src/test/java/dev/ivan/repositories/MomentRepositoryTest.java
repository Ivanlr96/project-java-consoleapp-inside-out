package dev.ivan.repositories;
import dev.ivan.models.EmotionEnum;
import dev.ivan.models.Moment;
import dev.ivan.repositories.MomentRepository;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class MomentRepositoryTest<repository> {
    private MomentRepository repository;
    @BeforeEach
    void setUp() {
        repository = new MomentRepository();
    }
    @Test
    void testStoreAndGetAllMoments() {
        Moment moment = new Moment("Título", LocalDate.now(), "Descripción", EmotionEnum.ALEGRIA);
        repository.storeMoment(moment);
        List<Moment> allMoments = repository.getAllMoments();
        assertEquals(1, allMoments.size());
        assertEquals(moment, allMoments.get(0));
    }
    @Test
    void testDeleteMoment() {
        Moment moment = new Moment("Título", LocalDate.now(), "Descripción", EmotionEnum.ALEGRIA);
        repository.storeMoment(moment);
        boolean deleted = repository.deleteMoment(0);
        assertTrue(deleted);
        assertTrue(repository.getAllMoments().isEmpty());
        assertFalse(repository.deleteMoment(5));
    }
    @Test
    void testGetMomentsByEmotion() {
        Moment m1 = new Moment("A", LocalDate.now(), "Desc", EmotionEnum.ALEGRIA);
        Moment m2 = new Moment("B", LocalDate.now(), "Desc", EmotionEnum.TRISTEZA);
        repository.storeMoment(m1);
        repository.storeMoment(m2);
        List<Moment> happyMoments = repository.getMomentsByEmotion(EmotionEnum.ALEGRIA);
        assertEquals(1, happyMoments.size());
        assertEquals(m1, happyMoments.get(0));
        List<Moment> sadMoments = repository.getMomentsByEmotion(EmotionEnum.TRISTEZA);
        assertEquals(1, sadMoments.size());
        assertEquals(m2, sadMoments.get(0));
    }
    @Test
    void testGetMomentsByDate() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        Moment m1 = new Moment("A", today, "Desc", EmotionEnum.ALEGRIA);
        Moment m2 = new Moment("B", yesterday, "Desc", EmotionEnum.TRISTEZA);
        repository.storeMoment(m1);
        repository.storeMoment(m2);
        List<Moment> todayMoments = repository.getMomentsByDate(today);
        assertEquals(1, todayMoments.size());
        assertEquals(m1, todayMoments.get(0));
        List<Moment> yesterdayMoments = repository.getMomentsByDate(yesterday);
        assertEquals(1, yesterdayMoments.size());
        assertEquals(m2, yesterdayMoments.get(0));
    }
}