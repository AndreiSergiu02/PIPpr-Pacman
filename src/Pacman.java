import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Pacman extends GameObject{
	
	private float _acc = 1f;
	private float _dcc = 0.5f;
	
	private KeyInput input;
	private Handler handler;
	
	public Pacman(float x, float y, ID id, KeyInput input,Handler handler) {
		super(x, y, id);
		this.input= input;
		this.handler=handler;
		
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
		
		Collision();
		handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.yellow,0.02f,handler));
	}
	
	private void Collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.Wall){
				if(!getBounds().intersects(tempObject.getBounds())){
					
					if(velX>0){//Right
						velX = 0;
						x=tempObject.getW()+27; 
						input.stopMovement();
						
					}else if(velX < 0){//Left
						velX = 0;
						x=tempObject.getX()-27; 
						input.stopMovement();
						
					}
				}
				if(tempObject.getId()==ID.Wall){
					if(!getBounds().intersects(tempObject.getBounds())){
						
						if(velY>0){//Down
							velY = 0;
							y=tempObject.getH() + 35; 
							input.stopMovement();
							
						}else if(velY < 0){//Up
							velY = 0;
							y=tempObject.getY() - 27; 
							input.stopMovement();

						}
					}
				}
			}
		}
	}
	
	public Rectangle getBounds(){//Horizontal Collision (red rectangle)
		
		float bx=x + velX;
		float by=y;
		float bw=32 + velX/4;
		float bh=32;
		
		return new Rectangle((int)bx,(int)by,(int)bw,(int)bh);
	}
	
	public Rectangle getBounds2(){//Vertical Collision (blue rectangle)
	
		float bx=x; 
		float by=y+ velY;
		float bw=32;
		float bh=32 + velY/4;
		
		return new Rectangle((int)bx,(int)by,(int)bw,(int)bh);
	}
	
	@Override
	public void render(Graphics g) {
	
		Graphics2D g2d =(Graphics2D) g;
		
		g2d.setColor(Color.red);
		g2d.fill(getBounds());
		
		g2d.setColor(Color.blue);
		g2d.fill(getBounds2());
		
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 32, 32);
	}
	
	private float clamp(float value, float min, float max){
		if(value >= max) value = max;
		else if(value<=min) value = min;
		
		return value;
	}
}
