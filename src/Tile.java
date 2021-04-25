import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * @author Bianca
 */

/**
 * 
 * Clasa care creeaza obstacole
 *
 */
public class Tile extends GameObject{
	
	public Tile(float x,float y, ID id){
		super(x,y,id);
	}

	
	/** 
	 * @param g randeaza culoarea unei placi
	 */
	public void render(Graphics g){
		g.setColor(Color.blue);
		g.fillRect((int)x,(int)y,32,32);
		
	}
	
	/**
	 * Continuitatea jocului
	 */
	@Override
	public void tick() {
		
	}

	
	/** 
	 * @return Rectangle o placa
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
}
