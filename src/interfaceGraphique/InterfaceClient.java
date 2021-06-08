package interfaceGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceClient extends JPanel {
    JLabel userLabel=new JLabel("USERNAME");

	public InterfaceClient() {
		this.add(userLabel);
	}
}
