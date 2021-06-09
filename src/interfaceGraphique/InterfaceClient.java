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

public class InterfaceClient extends Panel {
	JLabel 
	jLabel1 = new JLabel("Nom"),
	jLabel2 = new JLabel("Prénom"),
	jLabel3 = new JLabel("Adresse"),
	jLabel4 = new JLabel("Téléphone");
	
	JTextField 
	txtField1 = new JTextField(),
	txtField2 = new JTextField(),
	txtField3 = new JTextField(),
	txtField4 = new JTextField();
	
	JButton
	btn1 = new JButton("Ajouter"),
	btn2 = new JButton("Modifier"),
	btn3 = new JButton("Supprimer");
	
   
//    txtId = new JTextField();
//    jLabel3 = new JLabel();
//    JScrollPane jScrollPane1 = new JScrollPane();
//    JTable tblStudents = new JTable();
//    JButton btnSave = new JButton(),
//    btnUpdate = new JButton(),
//    btnDelete1 = new JButton(),

	public InterfaceClient() {
		super();
		setLocationAndSize();
		ajouterComposants();
	}
	
	public void setLocationAndSize() {
		jLabel1.setBounds(50, 30, 100, 30);
		jLabel2.setBounds(50, 90, 100, 30);
		jLabel3.setBounds(50, 150, 100, 30);
		jLabel4.setBounds(50, 210, 100, 30);
		txtField1.setBounds(150, 30, 150, 30);
		txtField2.setBounds(150, 90, 150, 30);
		txtField3.setBounds(150, 150, 150, 30);
		txtField4.setBounds(150, 210, 150, 30);
		btn1.setBounds(50, 280, 200, 30);
		btn2.setBounds(50, 320, 200, 30);
		btn3.setBounds(50, 360, 200, 30);
	}
	
	public void ajouterComposants() {
		jPanel1.add(jLabel1);
		jPanel1.add(jLabel2);
		jPanel1.add(jLabel3);
		jPanel1.add(jLabel4);
		jPanel1.add(txtField1);
		jPanel1.add(txtField2);
		jPanel1.add(txtField3);
		jPanel1.add(txtField4);
		jPanel1.add(btn1);
		jPanel1.add(btn2);
		jPanel1.add(btn3);
		jPanel1.setLayout(null);
		//String data[][]={ {"101","Amit","670000", "aaaaaaaa"},    
          //      {"102","Jai","780000"},    
            //    {"101","Sachin","700000",}};
		
//		String data[][];
		JTable tb = new JTable();
        tb.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "numeroclient", "Nom", "Prenom", "Adresse", "Telephone"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
        //tb.setCellSelectionEnabled(true);
        DefaultTableModel model = (DefaultTableModel) tb.getModel();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poo_db","root","");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from client");
			System.out.println(rs);
			while(rs.next()) {
		        Object[] ligne = new Object[5];
		        ligne[0] = rs.getString(1);
		        ligne[1] = rs.getString(2);
		        ligne[2] = rs.getString(3);
		        ligne[3] = rs.getString(4);
		        ligne[4] = rs.getString(5);
	
		        model.addRow(ligne);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(tb);
		jPanel2.add(sp);
		
		
		this.add(jPanel1);
		this.add(jPanel2);
		this.setLayout(null);
	}
}
