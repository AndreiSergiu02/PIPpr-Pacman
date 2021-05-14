package project;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * @author Bianca
 */

/**
 * 
 * Clasa in care se creeaza input-ul tastelor si actiunea acestora asupra personajelor
 *
 */
public class KeyInput extends KeyAdapter {
	/**
	 * Asigura miscarea obiectului cu ajutorul tastelor
	 * Vectorul keys[] inmagazineaza informatii despre tastele care fac ca obiectul sa se miste
	 */
	public boolean keys[] = new boolean[4];
	//keys 0 = true -> right
	//keys 1 = true -> left
	//keys 2 = true -> up
	//keys 3 = true -> down
	/**
	 * @param e verifica daca tasta este apasata
	 */
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key==KeyEvent.VK_D) { keys[0]=true; keys[1]=false;keys[2]=false;keys[3]=false;}
		if(key==KeyEvent.VK_A) { keys[0]=false;keys[1]=true; keys[2]=false;keys[3]=false;}
		if(key==KeyEvent.VK_W) { keys[0]=false;keys[1]=false;keys[2]=true; keys[3]=false;}
		if(key==KeyEvent.VK_S) { keys[0]=false;keys[1]=false;keys[2]=false;keys[3]=true;}
	}
	
	/**
	 * Daca tastele nu sunt apasate, obiectul se opreste
	 */
	public void stopMovement(){
		
		keys[0]=false;
		keys[1]=false;
		keys[2]=false;
		keys[3]=false;
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key==KeyEvent.VK_D) { keys[0]=false;}
		if(key==KeyEvent.VK_A) { keys[1]=false;}
		if(key==KeyEvent.VK_W) { keys[2]=false;}
		if(key==KeyEvent.VK_S) { keys[3]=false;}

	}
	
}
