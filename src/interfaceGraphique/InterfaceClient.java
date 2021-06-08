package interfaceGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceClient extends JPanel {
	JPanel jPanel1 = new JPanel(),
			jPanel2 = new JPanel();
	
	JLabel 
	jLabel1 = new JLabel("Nom"),
	jLabel2 = new JLabel("Prénom"),
	jLabel3 = new JLabel("Adresse"),
	jLabel4 = new JLabel("Téléphone"),
	jtext = new JLabel("testing!!");
	
	JTextField 
	txtField1 = new JTextField(),
	txtField2 = new JTextField(),
	txtField3 = new JTextField(),
	txtField4 = new JTextField();
    
    
   
//    txtId = new JTextField();
//    jLabel3 = new JLabel();
//    JScrollPane jScrollPane1 = new JScrollPane();
//    JTable tblStudents = new JTable();
//    JButton btnSave = new JButton(),
//    btnUpdate = new JButton(),
//    btnDelete1 = new JButton(),

	public InterfaceClient() {
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
		
		jPanel1.setLayout(null);
		
		jPanel2.add(jtext);
		
		this.add(jPanel1);
		this.add(jPanel2);
		this.setLayout(new GridLayout());
	}
}
