package interfaceGraphique;

import javax.swing.JPanel;

public class Panel extends JPanel {
	JPanel jPanel1 = new JPanel(),
			jPanel2 = new JPanel();
	
	public Panel() {
		setLocationAndSize();
	}
	
	public void setLocationAndSize() {
		jPanel1.setBounds(0,0,300,570);
		jPanel2.setBounds(300,0,800,570);
	}

}
