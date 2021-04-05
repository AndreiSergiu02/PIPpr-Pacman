import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Pacman extends GameObject{
	
	private float _acc = 1f;
	private float _dcc = 0.5f;
	
	private KeyInput input;
	
	public Pacman(float x, float y, ID id, KeyInput input) {
		super(x, y, id);
		this.input= input;
		
		velX=1;
		velY=1;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		//Vertical movement
		if(input.keys[2]) velY -= _acc;
		else if(input.keys[3]) velY += _acc;
		else if(!input.keys[2] && !input.keys[3]){
			if(velY > 0) velY -=_dcc;
			else if(velY < 0) velY += _dcc;
		}
		
		//Horizontal movement
		if(input.keys[0]) velX += _acc;
		else if(input.keys[1]) velX -= _acc;
		else if(!input.keys[0] && !input.keys[1]){
			if(velX > 0) velX -=_dcc;
			else if(velX < 0) velX += _dcc;
		}
		
		velX=clamp(velX,-5,5);
		velY=clamp(velY,-5,5);
	}	
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	
	private float clamp(float value, float min, float max){
		if(value >= max) value = max;
		else if(value<=min) value = min;
		
		return value;
	}
}
