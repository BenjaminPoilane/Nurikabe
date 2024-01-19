import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class Regles extends JFrame implements ActionListener{
	private BoutonAcceuil retour= new BoutonAcceuil("retour");
	private PanneauRegles pan =new PanneauRegles();
	
	public Regles(){
		super();
		this.setTitle("Regles");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900,700);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		pan.add(retour);
		this.add(pan);
		retour.addActionListener(this);
		
	}
	
	
	public void actionPerformed(ActionEvent action){
		this.setVisible(false);
		FenetreAcceuil fene = new FenetreAcceuil();
		
		
	}
	
	
}
