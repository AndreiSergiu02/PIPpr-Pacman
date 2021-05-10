import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class GameTest {

    private Game game;

    @BeforeEach
    void setUp() throws Exception {
        game = new Game();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void movementFromFile() {
        //TODO if throws exception
    }

    @Test
    public void testrun() {
        assertTrue(game.isRunning, "the game is running");
    }
}