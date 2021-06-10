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

import entitees.Commande;
public class InterfaceCommande extends Panel {
	Commande cmd = new Commande();
	
	
	public InterfaceCommande() {
		super();
		txtFields[3].setVisible(false);
		dateFormat.setVisible(true);
		tableName = "commande";
		idText = "numerocommande";
		tableHeader = new String[] { idText, "Date Commande", "num. Client", "num. Produit"};
		initTableau(tableHeader);
		idLabel.setText(idText + ": ");
		chargerTableau(tableHeader.length, "SELECT * FROM commande ORDER BY " + idText);
	}

	public void setLabels() {
		jLabels[0].setText("Date Commande");
		jLabels[1].setText("Num. Client");
		jLabels[2].setText("Num. Produit");
	}
	
	public boolean verifier() {
		if( txtFields[0].getText().isEmpty() ||
			txtFields[1].getText().isEmpty() ||
			txtFields[2].getText().isEmpty()	
	) {
    	donneesIncompleteFenetre();
    	return false;
		}
		
		if(!txtFields[0].getText().matches("[1-9][0-9]{3}-[01][1-9]-[0-3][0-9]")) {
			System.out.println(txtFields[0].getText());
			JOptionPane.showMessageDialog(null, "La date de commande doit etre une date !.", "Date invalide.", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(!txtFields[1].getText().matches("\\d*")) {
			JOptionPane.showMessageDialog(null, "Le numero de client doit être un nombre entier.", "Numero invalide.", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(!txtFields[2].getText().matches("\\d*")) {
			JOptionPane.showMessageDialog(null, "Le numero de produit doit être un nombre entier.", "Numero invalide.", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}	
		return true;
	}
	
	public void remplirCommande() {
		cmd.datecommande = txtFields[0].getText();
		cmd.fk_numeroclient = Integer.parseInt(txtFields[1].getText());
		cmd.fk_numeroproduit = Integer.parseInt(txtFields[2].getText());
	}
	
	public void EcouterBoutons() {
		super.EcouterBoutons();
		btnAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!verifier()) return;
				
				// modify code below
				remplirCommande();
				// Remplir les information de commande.
				String requete = "INSERT INTO Commande(datecommande,numeroclient,numeroproduit)"
						+ "VALUES('" + cmd.datecommande + "', '" + 
						cmd.fk_numeroclient + "', '" + cmd.fk_numeroproduit + "')";
				DB.executeUpdate(requete); // not this
				ajouterLigne(tableHeader.length, "SELECT * FROM commande ORDER BY numerocommande DESC LIMIT 1");
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
				remplirCommande();
				String requete = "UPDATE commande"
						+ " SET nom= '" + cmd.datecommande + "'"
						+ ", prenom= '" + cmd.fk_numeroclient + "'"
						+ ", adresse= '" + cmd.fk_numeroproduit + "'"
						+ " WHERE numerocommande=" + id;
				// edit code above
				
				DB.executeUpdate(requete);
				modifierLigne(new String[] {id, cmd.datecommande,  Integer.toString(cmd.fk_numeroclient),  Integer.toString(cmd.fk_numeroproduit)}, y);
			}
		});
	}
}
