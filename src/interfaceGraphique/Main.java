package interfaceGraphique;
import javax.swing.*;

public class Main {
	public Main() {
		JFrame mainFrame = new JFrame();
		mainFrame.setBounds(0,0,600,300);
		mainFrame.setVisible(true);
		JTabbedPane tp = new JTabbedPane();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		tp.setBounds(0,0,600,300);
		tp.add("Clients", new InterfaceClient());
		tp.add(p2);
		mainFrame.add(tp);
	}
}
