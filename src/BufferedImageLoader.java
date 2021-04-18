import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 
 * @author Bianca
 *
 */

public class BufferedImageLoader {
		private BufferedImage image;
		
		/** 
		 * @param path
		 * @return BufferedImage
		 */
		public BufferedImage loadImage(String path){
			try{
				image=ImageIO.read(getClass().getResource(path));
			}catch(IOException e){
				e.printStackTrace();
			}
			return image;
		}
}
