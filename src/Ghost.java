import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ghost extends GameObject {
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
