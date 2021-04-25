import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/**
 * 
 * @author Bianca
 *
 */

/**
 * 
 * Clasa care se ocupa de parcurgerea corecta a traseului
 */

public class Trail extends GameObject{
	
	private float alpha=1;
	private float life;
	private Color color;
	
	private Handler handler;
	/**
	 * 
	 * @param x deplasarea pe axa X
	 * @param y deplasarea pe axa Y
	 * @param id identificatorul pentru personaj
	 * @param color atributul de culoare
	 * @param life contor de viata la lovirea de obstacol
	 * @param handler primeste date despre obiect
	 */
	public Trail(int x, int y, ID id,Color color, float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.life = life;
		this.color = color;
	}
	/**
	 * Continuitatea jocului
	 */
	public void tick() {
		 if(alpha > life)
		 {
			 alpha -= (life-0.00001f);
		 }
		 else handler.removeObject(this);
	}

	
	/** 
	 * @param g randeaza urma
	 */
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g2d.setColor(color);
		g.fillRect((int)x, (int)y, 32, 32);
		
		g2d.setComposite(makeTransparent(1));
	}
	
	
	/** 
	 * @param alpha
	 * @return AlphaComposite
	 */
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type,alpha));
	}

	
	/** 
	 * @return Rectangle
	 */
	public Rectangle getBounds() {
		return null;
	}
}
