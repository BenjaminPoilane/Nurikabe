import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;


public class Fenetre extends JFrame implements ActionListener{
	private int dim;
	private FormeNoire solution;
	private int[][] grille;
	private FenetreInfo fe ;
	private JButton quit = new JButton("    Quitter    ");
	private JButton sol = new JButton("afficher solution");
	private JButton correction = new JButton("Correction"); 
	private Bouton boutons[][];
	private JPanel cases[][];
	private JButton nouveau = new JButton("Nouvelle Partie");
	private JButton reinitialiser = new JButton("Réinitialiser");
	
	Font police = new Font("TimesNewRoman",Font.BOLD,50);
	private Panneau container= new Panneau();
	
	
	public Fenetre(String s,int w){
		super(s);
		
		//this.add(container);
		fe= new FenetreInfo("   Un Instant", Color.WHITE,false);
		this.dim=w;
		 
		solution=new FormeNoire(dim, (dim>5));
		fe.setVisible(false);
		int l =(int) (this.getContentPane().getWidth() - 60-10*(dim+1))*2 / (3*dim);
		int m =(int) (this.getContentPane().getHeight () - 60-10*(dim+1)) /dim;
		grille = solution.grilleConstante(dim,0);
		cases= new JPanel[dim][dim];
		boutons = new Bouton[dim][dim];
		this.setSize(800,600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		l =(int) (this.getContentPane().getWidth() - 60-10*(dim+1))*2 / (3*dim);
		m =(int) (this.getContentPane().getHeight () - 60-10*(dim+1)) /dim;
		this.getContentPane().setBackground(Color.WHITE);
		for(int i=0;i<dim;i++){
			for(int j=0;j<dim;j++){
			cases[i][j]= new JPanel();
			cases[i][j].setBackground(Color.WHITE);
			cases[i][j].setLocation(30+2+j*(l+2),70+2+i*(m+2));
			cases[i][j].setSize(l,m);
			  
			this.getContentPane().add(cases[i][j]);
			
			cases[i][j].setLayout(null);
			
			
			if (this.solution.renvoitGrilleInitiale()[i][j]>0){
				String q = "" + this.solution.renvoitGrilleInitiale()[i][j];
				boutons[i][j]=new Bouton(q,l-2,m-2,false,false);
			}
			else{boutons[i][j]=new Bouton("",l-2,m-2,false,true);
			}
			boutons[i][j].addActionListener(this);
			cases[i][j].add(boutons[i][j]);
			
			}
		}
	JPanel quitter = new JPanel();
	int d= ((this.getContentPane().getWidth()-60)/3)-20;
	quitter.setSize(d,100);
	quitter.setBackground(Color.WHITE);
	quitter.setLocation(40 + 2*(this.getContentPane().getWidth()-60)/3,430);
	quit.addActionListener(this);
	quit.setSize(d,800);
	quitter.add(quit);
	this.getContentPane().add(quitter);	
	

	this.getContentPane().setLayout(null);
	JPanel corriger = new JPanel();
	corriger.setSize(d,40);
	corriger.setBackground(Color.WHITE);
	corriger.setLocation(40 + 2*(this.getContentPane().getWidth()-60)/3,70);
	corriger.add(correction);
	correction.addActionListener(this);
	this.getContentPane().add(corriger);
	
	JPanel solu = new JPanel();
	solu.setSize(d,100);
	solu.setBackground(Color.WHITE);
	solu.setLocation(40 + 2*(this.getContentPane().getWidth()-60)/3,160);
	sol.addActionListener(this);
	sol.setSize(d,800);
	solu.add(sol);
	this.getContentPane().add(solu);
	
	JPanel reini = new JPanel();
	reini.setSize(d,100);
	reini.setBackground(Color.WHITE);
	reini.setLocation(40 + 2*(this.getContentPane().getWidth()-60)/3,340);
	reinitialiser.addActionListener(this);
	reinitialiser.setSize(d,800);
	reini.add(reinitialiser);
	this.getContentPane().add(reini);
	
	JPanel nouv = new JPanel();
	nouv.setSize(d,100);
	nouv.setBackground(Color.WHITE);
	nouv.setLocation(40 + 2*(this.getContentPane().getWidth()-60)/3,250);
	nouveau.addActionListener(this);
	nouveau.setSize(d,800);
	nouv.add(nouveau);
	this.getContentPane().add(nouv); 
	
	
	this.getContentPane().setVisible(true);
	this.container.setVisible(true);
	nouveau.setVisible(true);
	reinitialiser.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	this.add(container);
	
	}
	public int[][] returnGrille(){return(grille);}
	
	public void actionPerformed(ActionEvent action){
		if (action.getSource().equals(quit)){
			this.setVisible(false);
			FenetreAcceuil fene = new FenetreAcceuil();
		}
		
		
		
		for (int i=0 ; i<this.dim ; i++){
			for (int j=0 ; j<this.dim ; j++){
			if (action.getSource().equals(boutons[i][j])){
				
				int p=0;
				if (this.solution.renvoitGrilleInitiale()[i][j]==0){
				if (this.grille[i][j]==1){this.grille[i][j]=0;p=1;}
				if (this.grille[i][j]+p==0 ){this.grille[i][j]=1;}
			}
			
			
			}
			}}
		if (action.getSource().equals(sol)){
			
			for (int i=0 ; i<this.dim ; i++){
				for (int j=0 ; j<this.dim ; j++){
					if (this.solution.renvoitGrille()[i][j]==1)
					{boutons[i][j].changerCouleur(true);
					this.grille[i][j]=1;}
					if (this.solution.renvoitGrille()[i][j]==0)
					{boutons[i][j].changerCouleur(false);
					this.grille[i][j]=0;
					
					 }
				}
			}
			//this.grille=this.solution.grille;
		}	
		if (action.getSource().equals(correction)){
			for (int i=0 ; i<this.dim ; i++){
				for (int j=0 ; j<this.dim ; j++){
				if (boutons[i][j].renvoitEstNoir()){this.grille[i][j]=1;}
				if (boutons[i][j].renvoitEstNoir()==false){this.grille[i][j]=0;}
				
				}}
			FormeNoire forme=new FormeNoire(this.grille);
			
			
			
			if (forme.estCorrecte()&& forme.NombreZoneBlanche()==solution.NombreZoneBlanche()&&forme.renvoitTaille()==solution.renvoitTaille()){
				
				FenetreInfo f= new FenetreInfo("  grille correcte ",Color.WHITE );
			}
			else{
				FenetreInfo f= new FenetreInfo(" grille incorrecte",Color.RED );
			}
			
		}	
		if (action.getSource().equals(nouveau)){
			fe.setVisible(true);
			int d=this.dim;
			this.solution=new FormeNoire(d,(dim>5));
			this.grille=solution.grilleConstante(dim,0);
		
			for (int i=0;i<dim;i++){
				for (int j=0;j<dim;j++){
					boutons[i][j].changerCouleur(false);
					boutons[i][j].changerPeutChanger(true);
					boutons[i][j].setText("");
					if (solution.renvoitGrilleInitiale()[i][j]>0){
						String q=""+solution.renvoitGrilleInitiale()[i][j];
						boutons[i][j].setText(q);
						boutons[i][j].changerPeutChanger(false);}
					
					
					
				}
				
			}
		fe.setVisible(false);
		}
		if (action.getSource().equals(reinitialiser)){
			for (int i=0;i<dim;i++){
				for (int j=0;j<dim;j++){
					boutons[i][j].changerCouleur(false);
				}
			}
			this.grille=this.solution.grilleConstante(dim, 0);
			
		}
	}
		
}
	
	

