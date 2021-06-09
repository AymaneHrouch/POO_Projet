package interfaceGraphique;
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
		barre.setRollover(true);
		barre.setBounds(0,0,1100,30);
		tp.setBounds(0,30,1100,570);
		this.add(barre);
		this.add(tp);
		tp.add("Clients", new InterfaceClient());
	}
}
