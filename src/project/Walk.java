package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 
 * @author Bianca
 *
 */

public class Walk extends GameObject {
	/**
	 * 
	 * @param x
	 *            axa X pe care se afla obiectul
	 * @param y
	 *            axa Y pe care se afla obiectul
	 * @param id
	 *            identificatorul pentru personaj
	 */
	private Color color;

	public Walk(float x, float y, ID id, Color color) {
		super(x, y, id);
		this.color = color;
	}

	public void render(Graphics g) {

		g.setColor(color);
		g.drawRect((int) x, (int) y, 32, 32);
		g.drawLine((int) x, (int) y, (int) x + 32, (int) y + 32);
		g.drawLine((int) x + 32, (int) y, (int) x, (int) y + 32);

	}

	@Override
	public void tick() {

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}