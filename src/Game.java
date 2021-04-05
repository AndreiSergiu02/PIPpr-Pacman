import java.awt.Canvas;


public class Game extends Canvas implements Runnable{
	
	public static int WIDTH=800,HEIGHT=608;
	public String title="Pacman";
	
	public Game(){
		new Window(WIDTH,HEIGHT,title,this);
	}
	//gameloop
	public void run(){
		 
	
	}
	
	public static void main(String args[]){
		new Game();
	}
}
