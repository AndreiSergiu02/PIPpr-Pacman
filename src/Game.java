import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{
	
	public static int WIDTH=815,HEIGHT=560;
	public String title="Pacman";
	private Thread thread;
	private boolean isRunning = false;
	
	//Instances
	private Handler handler;
	private KeyInput input;
	private BufferedImage level = null;
	
	
	public Game(){
		new Window(WIDTH,HEIGHT,title,this);
		start();
		init();
		//
		handler.addObject(new Pacman(60, 60, ID.Pacman,input,handler));
		// handler.addObject(new Frame(37,40,ID.Wall));

		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/map/mapfinal.png");
		
		loadLevel(level);
		
	}
	private void init(){
		handler = new Handler();
		input = new KeyInput();
		this.addKeyListener(input);
	}
	
	private synchronized void start(){
		if(isRunning) return;
		
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	private synchronized void stop(){
		if(!isRunning) return; 
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isRunning = false;
	}
	
	
	//gameloop
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		//Updates the game
		handler.tick();
	}
	
	private void render(){
		//Renders the game
		BufferStrategy bs= this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//Our rendering
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		bs.show();
		g.dispose();
	}
	
	private void loadLevel(BufferedImage image){
		int w=image.getWidth();
		int h=image.getHeight();
		
		for(int xx=0;xx<w;xx++){
			for(int yy=0;yy<h;yy++){
				int pixel=image.getRGB(xx, yy);
				int red = (pixel>>16) & 0xff;
				int green = (pixel>>8) & 0xff;
				int blue= (pixel) & 0xff;
				
				if(blue!=255)
					handler.addObject(new Tile(xx*32,yy*32,ID.Wall));
			}
		}
	}
	
	public static void main(String args[]){
		new Game();
	}

}
