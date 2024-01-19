import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreChoixTaille extends JFrame implements ActionListener {
	private PanneauAcceuil container= new PanneauAcceuil();
	private JLabel Taille=new JLabel("Taille de la grille ?");
	Font police = new Font("TimesNewRoman",Font.BOLD,50);
	private BoutonAcceuil b0 = new BoutonAcceuil("");
	private BoutonAcceuil b4 = new BoutonAcceuil("4");
	private BoutonAcceuil b5 = new BoutonAcceuil("5");
	private BoutonAcceuil b6 = new BoutonAcceuil("6");
	private BoutonAcceuil b7 = new BoutonAcceuil("7");
	private BoutonAcceuil b8 = new BoutonAcceuil("8");
	public FenetreChoixTaille(){
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900,700);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(container);
		Taille.setFont(police);
		Taille.setForeground(Color.WHITE);
		container.setLayout(new GridLayout(4,0));
		container.add(Taille);
		container.add(b0);
		container.add(b4);
		container.add(b5);
		container.add(b6);
		container.add(b7);
		container.add(b8);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
	}
	
	
	
	
	
	
	
	
	
	public void actionPerformed(ActionEvent action){
		if (action.getSource().equals(b4)){
			this.setVisible(false);
			Fenetre f = new Fenetre("NURIKABE",4);
		}
		if (action.getSource().equals(b5)){
			this.setVisible(false);
			Fenetre f = new Fenetre("NURIKABE",5);
		}
		if (action.getSource().equals(b6)){
			this.setVisible(false);
			Fenetre f = new Fenetre("NURIKABE",6);
			
		}
		if (action.getSource().equals(b7)){
			this.setVisible(false);
			Fenetre f = new Fenetre("NURIKABE",7);
		}
		if (action.getSource().equals(b8)){
			this.setVisible(false);
			Fenetre f = new Fenetre("NURIKABE",8);
		}
	}
}
