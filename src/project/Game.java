package project;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import java.util.Map;
import java.util.Set;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * 
 * @author Bianca
 *
 */
/**
 * Se initializeaza si creeaza jocul propriu zis
 */
@SuppressWarnings({ "unused", "serial" })
public class Game extends Canvas implements Runnable {
	/**
	 * * @param WIDTH setam latimea
	 * 
	 * @param HEIGHT
	 *            setam inaltimea
	 * @param title
	 *            setam numele ferestrei
	 * @param thread
	 *            fir de executie
	 * @param isRunning
	 *            spune daca jocul este pornit sau oprit
	 * @param handler
	 *            primeste informatii despre jucator
	 * @param input
	 *            primeste informatii de la tastatura
	 * @param level
	 */
	public static int WIDTH = 815, HEIGHT = 560;
	public String title = "Pacman";
	private Thread thread;
	public boolean isRunning = false;

	// Instances
	private Handler handler;
	private KeyInput input;
	private BufferedImage level = null;
	private Table<Integer, Integer, Integer> frequency = HashBasedTable
			.create();
	int initY = 32;
	int initX = 32;

	/**
	 * Costructor Game Creeaza o noua fereastra prin apelul constructorului
	 * Window
	 */
	public Game() {
		new Window(WIDTH, HEIGHT, title, this);
		start();
		init();

		handler.addObject(new Pacman(initX, initY, ID.Pacman, input, handler));
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/map/mapfinal.png");
		loadLevel(level);

		// MovementFromFile();

	}

	public void MovementFromFile() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"src/movement/movement.txt"));
			String line = reader.readLine();
			while (line != null) {
				switch (line) {
				case "up":
					input.keys[2] = true;

					try {
						TimeUnit.MILLISECONDS.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case "down":
					input.keys[3] = true;

					try {
						TimeUnit.MILLISECONDS.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case "left":
					input.keys[1] = true;

					try {
						TimeUnit.MILLISECONDS.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case "right":
					input.keys[0] = true;

					try {
						TimeUnit.MILLISECONDS.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("wrong line");
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void init() {
		handler = new Handler();
		input = new KeyInput();
		this.addKeyListener(input);
	}

	/**
	 * Verifica starea in care se afla jocul, daca ruleaza sau nu
	 * 
	 */
	private synchronized void start() {
		if (isRunning)
			return;

		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

	/**
	 * Verifica starea in care se afla jocul, daca ruleaza sau nu
	 * 
	 */
	private synchronized void stop() {
		if (!isRunning)
			return;

		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isRunning = false;
	}

	// gameloop
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 30.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;

			}

			GameObject pacObject = handler.object.get(0);

			if (initY != (int) pacObject.y || initX != (int) pacObject.x) {
				checkFrequency(initX, initY);
				initX = (int) pacObject.x;
				initY = (int) pacObject.y;
			}
		}
		stop();
	}

	/**
	 * tick() si render() se ocupa de functionalitatea jocului Fac ca jocul sa
	 * nu se intrerupa
	 */
	private void tick() {
		// Updates the game
		handler.tick();

	}

	private void render() {
		// Renders the game
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// Our rendering
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		bs.show();
		g.dispose();
	}

	/**
	 * @param image
	 */
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		int mat[][] = new int[h][w];
		System.out.println("w:" + w + "h:" + h);

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				mat[i][j] = 0;
			}
		}

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (blue == 255) {
					handler.addObject(new Tile(xx * 32, yy * 32, ID.Wall));
					mat[yy][xx] = 0;
				}
				if (green == 255) {
					handler.addObject(new Walk(xx * 32, yy * 32, ID.Walk,
							Color.BLACK));
					handler.addObject(new Ghost(xx * 32, yy * 32, ID.Ghost));
					frequency.put(yy * 32, xx * 32, 1);
					mat[yy][xx] = 9;
				}
				if (red == 255) {
					mat[yy][xx] = 1;
					frequency.put(yy * 32, xx * 32, 1);
					handler.addObject(new Walk(xx * 32, yy * 32, ID.Walk,
							Color.BLACK));

				}

			}
		}
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(mat[i][j]);
			}
			System.out.println("");
		}

	}

	public void checkFrequency(int x, int y) {
		GameObject pacObject = handler.object.get(0);

		for (int i = 1; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Walk) {
				if (tempObject.getBounds().intersects(pacObject.getBounds())) {
					switch (frequency.get(y, x)) {
					case 1:
						handler.addObject(new Walk(x, y, ID.Walk, Color.WHITE));
						frequency.put(y, x, frequency.get(y, x) + 1);
						return;
					case 2:
						handler.addObject(new Walk(x, y, ID.Walk, Color.ORANGE));
						frequency.put(y, x, frequency.get(y, x) + 1);
						return;
					case 3:
						handler.addObject(new Walk(x, y, ID.Walk, Color.RED));
						frequency.put(y, x, frequency.get(y, x) + 1);
						return;
					case 4:
						handler.addObject(new Walk(x, y, ID.Walk, Color.MAGENTA));
						frequency.put(y, x, frequency.get(y, x) + 1);
						return;
					default:
						handler.addObject(new Walk(x, y, ID.Walk, Color.GREEN));
						System.out.println("Maximum reached");
						return;
					}
					// frequency.put(y, x, frequency.get(y, x) + 1);

				}
			}
		}
	}

	/**
	 * Cream o noua instanta de Game
	 */
	public static void main(String args[]) {
		new Game();
	}

}
