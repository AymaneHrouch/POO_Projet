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

import entitees.Client;
public class InterfaceClient extends Panel {
	Client cl = new Client();
	
	
	public InterfaceClient() {
		super();
		idText = "numeroclient";
		initTableau(new String[] { idText, "Nom", "Prenom", "Adresse", "Telephone"});
		idLabel.setText(idText + ": ");
		chargerTableau(4, "SELECT * FROM client");
	}

	public void setLabels() {
		jLabels[0].setText("Nom");
		jLabels[1].setText("Prénom");
		jLabels[2].setText("Adresse");
		jLabels[3].setText("Téléphone");
	}
	
	public boolean verifier() {
		if( txtFields[0].getText().isEmpty() ||
			txtFields[1].getText().isEmpty() ||
			txtFields[2].getText().isEmpty() ||
			txtFields[3].getText().isEmpty()
	) {
    	donneesIncompleteFenetre();
    	return false;
		}
		
		if(!txtFields[3].getText().matches("\\d{10}")) { // Si le telephone contenir quelque chose autre que des nombres.
			JOptionPane.showMessageDialog(null, "Telephone doit contenir 10 nombres.", "Nombre de telephone invalide.", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	public void remplirClient() {
		cl.nom = txtFields[0].getText();
		cl.prenom = txtFields[1].getText();
		cl.adresse = txtFields[2].getText();
		cl.telephone = txtFields[3].getText();
	}
	
	public void EcouterBoutons() {
		super.EcouterBoutons();
		btnAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verifier();
				remplirClient();
				// Remplir les information de client.
				String requete = "INSERT INTO Client(nom,prenom,adresse,telephone)"
						+ "VALUES('" + cl.nom + "', '" + cl.prenom + "', '" + cl.adresse + "', '" + cl.telephone + "')";

				DB.executeUpdate(requete);
				ajouterLigne(4, "SELECT * FROM client ORDER BY numeroclient DESC LIMIT 1");
			}
		});
		

		btnModifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model = (DefaultTableModel) tb.getModel();
				// TODO Auto-generated method stub
				String id = idTF.getText();
				if(!verifierId(id , idText) || !verifier()) return;
				remplirClient();
				String requete = "UPDATE client"
						+ " SET nom= '" + cl.nom + "'"
						+ ", prenom= '" + cl.prenom + "'"
						+ ", adresse= '" + cl.adresse + "'"
						+ ", telephone= '" + cl.telephone + "'"
						+ " WHERE numeroclient=3";
				int y = getRow(id);
				if(y == -1) {
					JOptionPane.showMessageDialog(null,  idText + " Inexistant");
					return;
				}
				
				DB.executeUpdate(requete);
				modifierLigne(new String[] {id, cl.nom, cl.prenom, cl.adresse, cl.telephone}, y);
			}
		});
	
	}
}
