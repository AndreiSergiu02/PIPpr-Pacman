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
 * Clasa care creeaza inamicul
 *
 */
public class Ghost extends GameObject {
	/**
	 * 
	 * @param x axa X pe care se afla obiectul
	 * @param y axa Y pe care se afla obiectul
	 * @param id identificatorul pentru personaj
	 */
	public Ghost(float x, float y, ID id) {
		super(x, y, id);
		
	}

	public void render(Graphics g) {
			
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
}
