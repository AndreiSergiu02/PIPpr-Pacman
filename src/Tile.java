import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Tile extends GameObject{
	
	public Tile(float x,float y, ID id){
		super(x,y,id);
	}

	public void render(Graphics g){
		g.setColor(Color.blue);
		g.fillRect((int)x,(int)y,32,32);
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
}
