package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.BufferedImageLoader;
import project.Game;
import project.Handler;
import project.KeyInput;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    private Game game;
    private BufferedImage level = null;
    public static int WIDTH = 815, HEIGHT = 532;
    public String title = "Pacman";
    private Handler handler;
    private KeyInput input;

    @BeforeEach
    void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void runningTest() {
        assertTrue(game.isRunning, "the game is running");
    }

    @Test
    public void loadMapTest() {
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/map/mapfinal.png");
        assertNotNull(level, "Map load complete");
    }

    @Test
    public void movementFromFileTest() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/movement/movement.txt"));
            String line = reader.readLine();
            assertNotNull(line, "Reading from file complete");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void handlerTest() {
        handler = new Handler();
        input = new KeyInput();
        game.addKeyListener(input);
        assertNotNull(input);
    }

    @Test
    public void windowWidthTest() {
        assertEquals(WIDTH, game.getWidth());
    }
    @Test
    public void windowHeightTest() {
        assertEquals(HEIGHT,game.getHeight());
    }

    @Test
    public void windowTitleTest() {
        assertEquals(title,game.getName());
    }




}