import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class Bouton extends JButton implements MouseListener {
private boolean estNoir;
private boolean peutChanger;
	
	
public Bouton (String s,int l,int h,boolean noir,boolean chang){
	super(s);
	peutChanger=chang;
	estNoir=noir;
	if (estNoir){this.setBackground(Color.BLACK) ;}
	else {this.setBackground(Color.WHITE);}
	this.setSize(l,h);
	this.addMouseListener(this);
}

public void changerCouleur(boolean noir){
	estNoir=noir;
	if (estNoir){this.setBackground(Color.BLACK) ;}
	else {this.setBackground(Color.WHITE);}
	
}
public void changerPeutChanger(boolean boug){
	this.peutChanger=boug;
	
}
public boolean renvoitEstNoir(){
	return(estNoir);
}


public void mouseClicked(MouseEvent event){
	
}
public void mousePressed(MouseEvent event){
	if(peutChanger){
	if (this.estNoir){
		this.setBackground(Color.WHITE);
		this.estNoir=false;
	}
	else{
		this.setBackground(Color.BLACK);
		this.estNoir=true;
	}}
	}
public void mouseReleased(MouseEvent event){}
public void mouseEntered(MouseEvent event){}
public void mouseExited(MouseEvent event){}


}
