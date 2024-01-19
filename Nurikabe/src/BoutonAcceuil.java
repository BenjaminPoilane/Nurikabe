import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class BoutonAcceuil extends JButton implements MouseListener{
	
	Font police = new Font("Arial",Font.BOLD,30);
	
	public BoutonAcceuil(String s){
		super(s);
		this.setForeground(Color.WHITE);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFont(police);
		this.addMouseListener(this);
	}
	
	
public void mouseClicked (MouseEvent event){}
public void mousePressed (MouseEvent event){}
public void mouseReleased (MouseEvent event){}
public void mouseEntered (MouseEvent event){
	this.setForeground(Color.YELLOW);
}
public void mouseExited (MouseEvent event){
	this.setForeground(Color.WHITE);
}
}
