import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridLayout;

public class FenetreInfo extends JFrame implements ActionListener{

	private PanneauAcceuil container = new PanneauAcceuil();
	private BoutonAcceuil ok = new BoutonAcceuil("ok");
	private JLabel message;
	private Font police = new Font("TimesNewRoman",Font.BOLD,25);
	
	public FenetreInfo(String s,Color c){
		super(s);
		message = new JLabel(s);
		message.setFont(police);
		message.setForeground(c);
		
		this.setSize(205,205);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		container.setLayout(new GridLayout(2,1));
		this.add(container);
		container.add(message);
		this.container.add(ok);
		ok.addActionListener(this);
	}
	public FenetreInfo(String s, Color c, boolean b){
		super(s);
		message = new JLabel(s);
		message.setFont(police);
		message.setForeground(c);
		
		this.setSize(205,205);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		container.setLayout(new GridLayout(2,1));
		this.add(container);
		container.add(message);
		
	}
	public void actionPerformed(ActionEvent action){
		this.setVisible(false);
	}
	
	
}
