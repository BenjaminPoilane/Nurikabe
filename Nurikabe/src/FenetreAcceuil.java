import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FenetreAcceuil extends JFrame implements ActionListener{
	
	public int k=0;
	private PanneauAcceuil container= new PanneauAcceuil();
	private JLabel Nurikabe=new JLabel("Nurikabe");
	Font police = new Font("TimesNewRoman",Font.BOLD,50);
	private BoutonAcceuil jouer = new BoutonAcceuil("Jouer");
	private BoutonAcceuil quitter = new BoutonAcceuil("Quitter");
	private BoutonAcceuil regles = new BoutonAcceuil("Règles du jeu");
	
	
	GridLayout g =new GridLayout();

	
	public FenetreAcceuil(){
		super();
		jouer.addActionListener(this);
		quitter.addActionListener(this);
		regles.addActionListener(this);
		this.setTitle("Nurikabe");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900,700);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(container);
		container.setLayout(new GridLayout(4,1));
		
		Nurikabe.setFont(police);
		Nurikabe.setForeground(Color.WHITE);
		container.add(Nurikabe);
		container.add(jouer);
		container.add(regles);
		container.add(quitter);
		
		
	}
	
public void actionPerformed(ActionEvent action){
	if (action.getSource().equals(jouer)){
	this.setVisible(false);
	//FormeNoire forme=new FormeNoire(5);
	//forme.grille=forme.genererFormeNoire(5);
	
	
	
	FenetreChoixTaille fene = new FenetreChoixTaille();
	//k=1;
	}
	if (action.getSource().equals(quitter)){
		System.exit(0);	
		
	}
	if (action.getSource().equals(regles)){
	this.setVisible(false);
	Regles r = new Regles();
	}
	}
}

