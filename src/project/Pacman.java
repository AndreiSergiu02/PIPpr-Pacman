package project;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/**
 * @author Bianca
 */

/**
 * 
 * Clasa care creeaza personajul principal
 *
 */
@SuppressWarnings("unused")
public class Pacman extends GameObject{
	/**
	 * @param _acc se ocupa de accelerarea obiectului in fereastra cand o tasta este apasata
	 * @param _dcc decelerarea obiectului cand tasta nu mai este apasata
	 */
	
	private float _acc = 1f;
	private float _dcc = 0.5f;
	/**
	 * @param input primeste tasta care este apasata
	 * @param handler primeste date despre obiect
	 * @param right deplasare pe axa Y pozitiv
	 * @param left deplasare pe axa Y negativ
	 * @param up deplasare pe axa X pozitiv
	 * @param down deplasare pe axa X negativ
	 */
	private KeyInput input;
	private Handler handler;
	boolean right,left,up,down;
	
	/**
	 * 
	 * @param x deplasare pe axa X
	 * @param y deplasare pe axa Y
	 * @param id personaj
	 * @param input tastare care se apasa
	 * @param handler coordonator pentru obiect 
	 */
	public Pacman(float x, float y, ID id, KeyInput input,Handler handler) {
		super(x, y, id);
		this.input= input;
		this.handler=handler;
		
		velX=0;
		velY=0;
	}
	/*
	 * tick() se ocupa de miscarea obiectului
	 */
	@Override
	public void tick() {
			
		//keys 0 = true -> right
		//keys 1 = true -> left
		//keys 2 = true -> up
		//keys 3 = true -> down
		//Vertical movement		
		if(input.keys[2]) { 
			up=true;down=false;right=false;left=false;
			y -= 32;
			input.stopMovement();}
		
		else if(input.keys[3]) {
			up=false;down=true;right=false;left=false;
			y += 32;
			input.stopMovement();}
		
		else if(!input.keys[2] && !input.keys[3]){
			if(velY > 0) velY -=_dcc;
			else if(velY < 0) velY += _dcc;
		}
		
		//Horizontal movement
		if(input.keys[0]) {
			up=false;down=false;right=true;left=false;
			x += 32;
			input.stopMovement();}
		else if(input.keys[1]) {
			up=false;down=false;right=false;left=true;
			x -= 32;
			input.stopMovement();}
		else if(!input.keys[0] && !input.keys[1]){
			if(velX > 0) velX -=_dcc;
			else if(velX < 0) velX += _dcc;
		}
		
		velX=clamp(velX,-5,5);
		velY=clamp(velY,-5,5);
		
		Collision();
	}
	
	/**
	 * In momentului unei coleziuni, obiectul se va opri
	 */
	private void Collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.Wall){
				if(getBounds().intersects(tempObject.getBounds())){
				
					  if(right)  x=tempObject.getX() - 32;
					  if(left)	x=tempObject.getX() + 32;
					  if(down) y=tempObject.getY() - 32;
					  if(up) y=tempObject.getY() + 32;
					
				}		
			}
		}
	}
	

	public Rectangle getBounds(){//Horizontal Collision
		float bx = x;
		float by = y;
		float bw = 32;
		float bh = 32;
		
		return new Rectangle((int)bx,(int)by,(int)bw,(int)bh);
	}
	
	/**
	 * Randarea obiectului Pacman
	 */
	@Override
	public void render(Graphics g) {
	
		Graphics2D g2d =(Graphics2D) g;
		
		g2d.setColor(Color.blue);
		g2d.fill(getBounds());
		
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 32, 32);
	}
	
	/**
	 * 
	 * @param value viteaza cu care de deplasare
	 * @param min valorea minima cu care se depleseaza obiectul
	 * @param max valorea maxima cu care se deplaseaza obiectul
	 * @return o viteaza de deplasare astfel incat sa para ca merge constant
	 */
	private float clamp(float value, float min, float max){
		if(value >= max) value = max;
		else if(value<=min) value = min;
		
		return value;
	}
}
