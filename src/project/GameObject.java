package project;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clasa GameObject creeaza  metode sau functii pentru fiecare obiect in parte fiind clasa parinte
 */
public abstract class GameObject {
	
	/**
	 * 
	 * @return o valoare a latimii
	 */
		public float getW() {
		return w;
	}

	/**
	 * 
	 * @param w preia valoarea latimii
	 */
	public void setW(float w) {
		this.w = w;
	}
	
	/**
	 * 
	 * @return valorea inaltimii
	 */
	public float getH() {
		return h;
	}

	/**
	 * 
	 * @param h preia valoarea inaltimii
	 */
	public void setH(float h) {
		this.h = h;
	}
	
	/**
	 * @param x valoarea de pe axa x
	 * @param y valoarea de pe axa y 
	 * @param id un obiect din lista
	 * @param velx viteaza pe axa x
	 * @param velY viteaza pe axa y
	 * @param w valoarea latimii
	 * @param h valoarea inaltimii
	 */
		protected float x,y;
		protected float velX, velY;
		protected ID id;
		protected float w,h;
		
		public GameObject(float x, float y, ID id){
			this.x = x;
			this.y = y;
			this.id = id;
		}
		
		
		/**
		 * Continuitatea jocului
		 */
		public abstract void tick();
		
		/**
		 * 
		 * @param g randarea unui obiect
		 */
		public abstract void render(Graphics g);
		
		/**
		 * 
		 * @return specifica o zona dintr-un spatiu de coordonate care este închisa de punctul stanga sus al obiectului dreptunghiular (x, y) în spatiul de coordonate, latimea si inaltimea acestuia.
		 */
		public abstract Rectangle getBounds();
		
		/**
		 * @return valoarea lui x
		 */
		public float getX() {
			return x;
		}

		/**
		 * @param x preia valoarea lui x
		 */
		public void setX(float x) {
			this.x = x;
		}

		/**
		 * 
		 * @return valoarea lui y
		 */
		public float getY() {
			return y;
		}

		/**
		 * 
		 * @param y preia valoarea lui y
		 */
		public void setY(float y) {
			this.y = y;
		}

		/**
		 * 
		 * @return valoarea lui velX
		 */
		public float getVelX() {
			return velX;
		}

		/**
		 * 
		 * @param velX preia valoarea lui velX
		 */
		public void setVelX(float velX) {
			this.velX = velX;
		}

		/**
		 * 
		 * @return valoarea lui velY
		 */
		public float getVelY() {
			return velY;
		}

		/**
		 * 
		 * @param velY preia valoarea lui velY
		 */
		public void setVelY(float velY) {
			this.velY = velY;
		}

		/**
		 * 
		 * @return valoarea lui id
		 */
		public ID getId() {
			return id;
		}
		
		/**
		 * 
		 * @param id preia valoarea lui id
		 */
		public void setId(ID id) {
			this.id = id;
		}

		
		

}

