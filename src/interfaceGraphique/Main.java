package interfaceGraphique;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main extends JFrame {
	JToolBar barre = new JToolBar ();
	JButton deconnexion = new JButton("Deconnexion");
	JTabbedPane tp = new JTabbedPane();
	
	public Main() {
		ajouterComposants();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBounds(0,0,1100,600);
	}
	
	public void ajouterComposants() {
		barre.add(deconnexion);
		deconnexion.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				deconnexion();
				new LoginFrame();
			}
		});
		
		barre.setRollover(true);
		barre.setBounds(0,0,1100,30);
		tp.setBounds(0,30,1100,570);
		this.add(barre);
		this.add(tp);
	}
	
	public void afficher() {
		tp.add("Clients", new InterfaceClient());
		this.setVisible(true);
	}
	
	public void deconnexion() {
		this.setVisible(false);
	}
	
	
}
