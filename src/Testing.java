import javax.swing.JFrame;

import interfaceGraphique.*;

public class Testing {
	public static void main(String args[]) {
		DB.demarrer();
        //new LoginFrame();
		new Main().setVisible(true);
	}
}
