import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class Panneau extends JPanel {
	
	
	
	
		
		public void paintComponent(Graphics g){
            try {
                    Graphics2D g2d = (Graphics2D) g;
            		Image img = ImageIO.read(new File("src/IMG/Chrysanthemum.jpg") );
                    g2d.drawImage(img, 0, 0, this);
                    //Pour une image de fond
                    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            } 
            
            catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
		
		
		
	

}
}