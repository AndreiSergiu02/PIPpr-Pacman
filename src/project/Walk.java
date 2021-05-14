package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
	/**
	 * 
	 * @author Bianca
	 *
	 */

import com.google.common.collect.Table;


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
		private Color color; 
		private Table<Integer, Integer, Integer> frequency;
		 
		public  Walk (float x, float y, ID id,Handler handler,KeyInput input, Color color, Table<Integer, Integer, Integer> frequency ) {
			super(x, y, id);
			this.handler = handler;
			this.input = input;
			this.color = color;
			this.frequency= frequency;
		}

		public void render(Graphics g) {
			
			g.setColor(color);
			g.fillRect((int)x, (int)y, 32, 32);
			
			}

		@Override
		public void tick() {
			
			if (input.keys[0]||input.keys[1]||input.keys[2]||input.keys[3])
					checkfreq();
			
		}

		@Override
		public Rectangle getBounds() {
			return new Rectangle((int)x,(int)y,32,32);
		}
	

	public void checkfreq(){
		
		frequency.put((int)y,(int) x, 1);
		 for(int i=0;i<handler.object.size();i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId()==ID.Pacman){
					if(tempObject.getBounds().intersects(tempObject.getBounds())){
						switch (frequency.get((int) y, (int) x)) {
						case 1:
							new Walk(x * 32, y * 32, ID.Walk, handler, input, Color.white, frequency);
							break;
						case 2:
							new Walk(x * 32, y * 32, ID.Walk, handler, input, Color.red, frequency);
							break;
						default:
							System.out.println("default");
						}
							frequency.put((int)y,(int) x, frequency.get((int)y,(int) x)+1);
						}
					}
	
				}	
				
				}
			}	