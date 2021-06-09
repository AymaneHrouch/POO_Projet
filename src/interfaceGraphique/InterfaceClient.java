package interfaceGraphique;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class InterfaceClient extends Panel {
	public InterfaceClient() {
		super();
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		setTable(new String[] { "numeroclient", "Nom", "Prenom", "Adresse", "Telephone" }, "select * from client");
	}

	public void setLabels() {
		jLabels[0].setText("Nom");
		jLabels[1].setText("Prénom");
		jLabels[2].setText("Adresse");
		jLabels[3].setText("Téléphone");
	}
}
