import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Handler este o clasa care se ocupa cu updating si randare pentru fiecare obiect al jocului
 */
public class Handler {
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	/**
	 *  Actualizeaza fiecare obiect din joc
	 */
	public void tick(){
		//Updating every object in the game
		for(int i=0;i<object.size();i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	/**
	 * Face ca un obiect sa fie intr-o stare data de utilizator
	 * @param g se ocupa de randare
	 */
	public void render(Graphics g){
		for(int i=0;i<object.size();i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
		
	}
	
	/**
	 * 
	 * @param tempObject adauga obiecte in lista de obiecte
	 */
	public void addObject(GameObject tempObject){
		object.add(tempObject);		
	}
	
	/**
	 * 
	 * @param tempObject scoate un obiect din lista de obiecte
	 */
	public void removeObject(GameObject tempObject){
		object.remove(tempObject);
	}
	
}
