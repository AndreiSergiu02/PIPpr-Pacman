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
					//checkfreq();
			
		}

		@Override
		public Rectangle getBounds() {
			return new Rectangle((int)x,(int)y,32,32);
		}
	

	
			}	