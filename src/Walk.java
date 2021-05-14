
	import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
	/**
	 * 
	 * @author Bianca
	 *
	 */
import java.awt.image.BufferStrategy;


	/**
	 * 
	 * Clasa care creeaza inamicul
	 *
	 */

	
	public class Walk  extends GameObject  {
		/**
		 * 
		 * @param x axa X pe care se afla obiectul
		 * @param y axa Y pe care se afla obiectul
		 * @param id identificatorul pentru personaj
		 */
		private KeyInput input;
		private Handler handler;
		 protected int par=0;
		 
		public  Walk (float x, float y, ID id,Handler handler,KeyInput input) {
			super(x, y, id);
			this.handler=handler;
			this.input= input;
		}

		public void render(Graphics g) {
			
				
			g.setColor(new Color(par, 255, 255));
			g.drawRect((int)x, (int)y, 32, 32);
			if (input.keys[0]||input.keys[1]||input.keys[2]||input.keys[3]){
				checkfreq(g);
			}
		}

		@Override
		public void tick() {
			
			
			
		}

		@Override
		public Rectangle getBounds() {
			return new Rectangle((int)x,(int)y,32,32);
		}
	

	public void checkfreq(Graphics g ){
		
		
		
		
		 for(int i=0;i<handler.object.size();i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId()==ID.Pacman){
					if(tempObject.getBounds().intersects(tempObject.getBounds())){
						par+=50;
						g.setColor(new Color(par, 255, 255));
						g.fillRect((int)x, (int)y, 32, 32);
						if (par>200){
								par=0;}
						}
					}
	
				}	}}	
