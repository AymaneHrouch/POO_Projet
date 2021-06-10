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

import entitees.Livraison;
public class InterfaceLivraison extends Panel {
	Livraison liv = new Livraison();

	public InterfaceLivraison() {
		super();
		dateFormat.setVisible(true);
		txtFields[2].setVisible(false);
		txtFields[3].setVisible(false);
		tableName = "livraison";
		idText = "numerolivraison";
		tableHeader = new String[] { idText, "dateLivraison", "num. commande"};
		initTableau(tableHeader);
		idLabel.setText(idText + ": ");
		chargerTableau(tableHeader.length, "SELECT * FROM livraison ORDER BY " + idText);
	}

	public void setLabels() {
		jLabels[0].setText("Date De Livraison");
		jLabels[1].setText("Num. commande");
		
	}
	
	public boolean verifier() {
		if( txtFields[0].getText().isEmpty() ||
			txtFields[1].getText().isEmpty() ) 
		{
    	donneesIncompleteFenetre();
    	return false;
		}
		
		if(!txtFields[0].getText().matches("[1-9][0-9]{3}-[01][1-9]-[0-3][0-9]")) {
			JOptionPane.showMessageDialog(null, "La date de livraison doit etre une date !.", "Date invalide.", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(!txtFields[1].getText().matches("\\d*")) {
			JOptionPane.showMessageDialog(null, "Le numero de commande doit être un nombre entier.", "Numero invalide.", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}	
		return true;
	}
	
	public void remplirLivraison() {
		liv.dateLivraison = txtFields[0].getText();
		liv.fk_numerocommande = Integer.parseInt(txtFields[1].getText());
		
	}
	
	public void EcouterBoutons() {
		super.EcouterBoutons();
		btnAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!verifier()) return;
				
				// modify code below
				remplirLivraison();
				// Remplir les information de client.
				String requete = "INSERT INTO Livraison(dateLivraison,numerocommande)"
						+ "VALUES('" + 
						liv.dateLivraison + "', " + liv.fk_numerocommande + ")";
				int res = DB.executeUpdate(requete); // not this
				System.out.println(res);
				ajouterLigne(tableHeader.length, "SELECT * FROM livraison ORDER BY numeroLivraison DESC LIMIT 1");
				// modify code above
				clearTextFields();
			}
		});
		

		btnModifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model = (DefaultTableModel) tb.getModel();
				String id = idTF.getText();
				if(!verifierId(id , idText) || !verifier()) return;
				int y = getRow(id);
				if(y == -1) {
					JOptionPane.showMessageDialog(null,  idText + " Inexistant");
					return;
				}
				// edit code below
				remplirLivraison();
				String requete = "UPDATE livraison"
						+ " SET dateLivraison = '" + liv.dateLivraison + "'"
						+ ", numerocommande= " + liv.fk_numerocommande + ""
						+ " WHERE numerolivraison=" + id;
				// edit code above
				
				DB.executeUpdate(requete);
				modifierLigne(new String[] {id, liv.dateLivraison,  Integer.toString(liv.fk_numerocommande)}, y);
			}
		});
	}
}
