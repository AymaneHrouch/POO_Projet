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

import entitees.Produit;
public class InterfaceProduit extends Panel {
	Produit prd = new Produit();
	
	public InterfaceProduit() {
		super();
		txtFields[3].setVisible(false);
		tableName = "produit";
		idText = "numeroproduit";
		tableHeader = new String[] { idText, "NomProduit", "Quantite", "Prix"};
		initTableau(tableHeader);
		idLabel.setText(idText + ": ");
		chargerTableau(tableHeader.length, "SELECT * FROM produit ORDER BY " + idText);
	}

	public void setLabels() {
		jLabels[0].setText("NomProduit");
		jLabels[1].setText("Quantite");
		jLabels[2].setText("Prix");
	}
	 
	public boolean verifier() {
		if( txtFields[0].getText().isEmpty() ||
			txtFields[1].getText().isEmpty() ||
			txtFields[2].getText().isEmpty() 
	) {
    	donneesIncompleteFenetre();
    	return false;
		}
		
		if(!txtFields[1].getText().matches("\\d*")) {
			Util.afficherInfo("La quantite doit etre un nombre entier.", "Quantite invalide.");
			return false;
		}

		if(!txtFields[2].getText().matches("[\\d\\.]*")) {
			Util.afficherInfo("Le prix doit �tre un nombre r�el.", "Prix invalide.");
			return false;
		}
		
		return true;
	}
	
	public void remplirProduit() {
		prd.nomproduit = txtFields[0].getText();
		prd.quantite = Integer.parseInt(txtFields[1].getText());
		prd.prix = Double.parseDouble(txtFields[2].getText());
	}
	
	public void EcouterBoutons() {
		super.EcouterBoutons();
		btnAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verifier();
				
				remplirProduit();

				String requete = "INSERT INTO Produit(nomproduit,quantite,prix)"
						+ "VALUES('" + prd.nomproduit + "', '" + 
						prd.quantite + "', '" + prd.prix + "')";
				DB.executeUpdate(requete); 
				ajouterLigne(tableHeader.length, "SELECT * FROM produit ORDER BY numeroproduit DESC LIMIT 1");

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
					Util.afficherInfo(idText + " Inexistant");
					return;
				}
				
				remplirProduit();
				String requete = "UPDATE produit"
						+ " SET nomproduit= '" + prd.nomproduit + "'"
						+ ", quantite= '" + prd.quantite + "'"
						+ ", prix= '" + prd.prix + "'"
						+ " WHERE numeroproduit=" + id;
				
				DB.executeUpdate(requete);
				modifierLigne(new String[] {id, prd.nomproduit, Integer.toString(prd.quantite), Double.toString(prd.prix)}, y);
			}
		});
	}
}
