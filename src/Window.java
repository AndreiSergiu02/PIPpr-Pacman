import java.awt.Dimension;

import javax.swing.JFrame;
/**
 * @author Bianca
 */

/**
 * 
 * Fereastra in care se desfasoara jocul
 *
 */
public class Window {
	/**
	 * @param width seteaza latimea ferestrei jocului
	 * @param height seteaza inaltimea ferestrei jocului
	 * @param title seteaza titul
	 * @param game instanta a clasei Game
	 */
	public Window(int width, int height, String title, Game game){
		/**
		 * Se creeaza un JFrame nou cu numele primit de title
		 */
			JFrame frame = new JFrame(title);
		/**
		 *   Setam marginile frame-ului
		 */
			frame.setPreferredSize(new Dimension(width,height));
			frame.setMaximumSize(new Dimension(width,height));
			frame.setMinimumSize(new Dimension(width,height));
			/**
			 * Atunci cand apasam pe butonul de EXIT, fereastra se va inchide si nu va rula in fundal
			 * Setam ca fereastra sa nu fie redimensionata
			 * Atunci ca jocul va porni, ferestra se va deschide pe ecran in mijloc
			 * Adaugam ferestrei compomenta game
			 */	
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.add(game);
			frame.setVisible(true);
	}
}
