import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * 
 * @author Bianca
 *
 */

public class Frame extends GameObject{
	/**
	 * @param x valoarea lui x pe axa 
	 * @param y valoarea lui y pe axa
	 * @param id care obiect din lista este
	 */
	public Frame(float x, float y, ID id) {
		super(x, y, id);
		w=710;
		h=482;
	}

	@Override
	public void tick() {		
	}

	/**
	 * Se ocupa de colorarea ferestrei
	 */
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.blue);
		g.drawLine(10, 10, 770, 10);
		g.drawLine(11, 11, 771, 11);
		
		g.drawLine(10, 550, 770, 550);
		g.drawLine(11, 551, 771, 551);
	
		g.drawLine(10, 10, 10, 551);
		g.drawLine(11, 11, 11, 551);
		
		g.drawLine(770, 10, 770, 551);
		g.drawLine(771, 10, 771, 551);

	}

	/**
	 * Dimeniuni pentru ferestra
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,(int)w,(int)h);
	}
}
