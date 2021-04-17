import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){
		//Updating every object in the game
		for(int i=0;i<object.size();i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	public void render(Graphics g){
		
		for(GameObject tempObject : object){
			tempObject.render(g);
		}
		
	}
	
	public void addObject(GameObject tempObject){
		object.add(tempObject);		
	}
	
	public void removeObject(GameObject tempObject){
		object.remove(tempObject);
	}
	
}
