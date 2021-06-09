package interfaceGraphique;
import javax.swing.JFrame;

public class Controleur {
//	static Main m = new Main();
	public static void main(String args[]) {
		DB.demarrer();
//		m.afficher();
        new LoginFrame();
		//String th[] = { "numeroclient", "Nom", "Prenom", "Adresse", "Telephone" };
	}
}
