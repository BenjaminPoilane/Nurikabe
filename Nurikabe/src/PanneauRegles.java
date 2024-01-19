import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauRegles extends JPanel{
	
	
	 public void paintComponent(Graphics g){
		
		try {
            Graphics2D g2d = (Graphics2D) g;
    		Image img = ImageIO.read(new File("src/IMG/Chrysanthemum.jpg") );
            g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);} 
    
		catch (IOException e) {e.printStackTrace();}
		
	}

}
