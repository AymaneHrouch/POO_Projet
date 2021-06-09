package interfaceGraphique;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public abstract class Panel extends JPanel {
	abstract public void setLabels();
	abstract public boolean verifier();
	
	public String idText;
		
	JPanel jPanel1 = new JPanel(),
			jPanel2 = new JPanel();
	
	JLabel jLabels[] = new JLabel[4];
	JTextField txtFields[] = new JTextField[4];
	
	JLabel idLabel = new JLabel();
	JTextField idTF = new JTextField();
	
	JButton
	btnAjouter = new JButton("Ajouter"),
	btnModifier = new JButton("Modifier"),
	btnSupprimer = new JButton("Supprimer");
	
	JTable tb = new JTable();
	JScrollPane sp = new JScrollPane();

    DefaultTableModel model = null;

	
	public Panel() {
		for(int i=0; i < jLabels.length; i++) {
			jLabels[i] = new JLabel();
			txtFields[i] = new JTextField();
		}
		
		setLabels();
		ajouterComposants();
		setLocationAndSize();
		EcouterBoutons();
	}
//	
//    private JLabel[] createLabels(){
//        JLabel[] labels=new JLabel[10];
//        for (int i=0;i<4;i++){
//            labels[i]=new JLabel("message" + i);
//        }
//        return labels;
//    }
	
	public void setLocationAndSize() {
		jPanel1.setBounds(0,0,300,570);
		jPanel2.setBounds(300,0,800,570);
		
		jLabels[0].setBounds(50, 30, 100, 30);
		jLabels[1].setBounds(50, 90, 100, 30);
		jLabels[2].setBounds(50, 150, 100, 30);
		jLabels[3].setBounds(50, 210, 100, 30);
		
		idLabel.setBounds(50, 400, 100, 30);
		idTF.setBounds(150, 400, 30, 40);
		
		txtFields[0].setBounds(150, 30, 150, 30);
		txtFields[1].setBounds(150, 90, 150, 30);
		txtFields[2].setBounds(150, 150, 150, 30);
		txtFields[3].setBounds(150, 210, 150, 30);
		
		btnAjouter.setBounds(50, 280, 200, 30);
		btnModifier.setBounds(50, 320, 200, 30);
		btnSupprimer.setBounds(50, 360, 200, 30);
	}
	
	public void ajouterComposants() {
		jPanel1.add(jLabels[0]);
		jPanel1.add(jLabels[1]);
		jPanel1.add(jLabels[2]);
		jPanel1.add(jLabels[3]);
		jPanel1.add(txtFields[0]);
		jPanel1.add(txtFields[1]);
		jPanel1.add(txtFields[2]);
		jPanel1.add(txtFields[3]);
		jPanel1.add(btnAjouter);
		jPanel1.add(btnModifier);
		jPanel1.add(btnSupprimer);
		jPanel1.add(idLabel);
		jPanel1.add(idTF);
		sp.setViewportView(tb); 
		jPanel2.add(sp);
		jPanel1.setLayout(null);
		this.add(jPanel1);
		this.add(jPanel2);
		this.setLayout(null);
	}
	
	public void initTableau(String th[]) {
		boolean[] canEdit = new boolean[5];
		Arrays.fill(canEdit, false);
        tb.setModel(new DefaultTableModel( new Object [][] { }, th ) {
        	// Pour mettre les cellule
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
        });
        tb.setCellSelectionEnabled(false);
		ecouterTable(tb);
	}
	
	public void ecouterTable(JTable table) {
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					tb.setSelectionBackground(Color.red);
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int columnCount = target.getColumnCount();
					String valeur = "";
					for(int i=1; i<columnCount; i++) {
						valeur = table.getModel().getValueAt(row, i).toString();
						txtFields[i-1].setText(valeur);
					}
				}
			}
		});
	}
	
	public void chargerTableau(int tableauLength, String requete) {
        model = (DefaultTableModel) tb.getModel();
		try {
			ResultSet rs = DB.executeQuery(requete);
			while(rs.next()) {
		        Object[] ligne = new Object[tableauLength+1];
		        for(int i = 0; i < tableauLength + 1; i++) {
		        	ligne[i] = rs.getString(i+1);
		        }
		        model.addRow(ligne);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void ajouterLigne(int tableauLength, String requete) {
        model = (DefaultTableModel) tb.getModel();
		try {
			ResultSet rs = DB.executeQuery(requete);
			rs.next();
	        Object[] ligne = new Object[tableauLength+1];
	        for(int i=0; i < tableauLength + 1; i++) {
	        	ligne[i] = rs.getString(i+1);
			}
	        model.addRow(ligne);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void modifierLigne(String[] objet, int y) {
        model = (DefaultTableModel) tb.getModel();
        for(int i=0; i < tb.getColumnCount(); i++ ) {
        	model.setValueAt(objet[i], y, i);       	
        }
	}
	
	public void donneesIncompleteFenetre() {
		JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Données incomplétes.", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public boolean verifierId(String id, String label) {
		if(!id.matches("\\d+")) {
			JOptionPane.showMessageDialog(null, label + " doit être un nombre entier.");
			return false;
		}
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Veuillez Entrer le " + idText);
			return false;
		}
		return true;
	}
	
	public int getRow(String id) {
		int y = -1;
		for(int i=0; i<model.getRowCount(); i++) {
			String idEnTable = (String) model.getValueAt(i, 0);
			if(id.equals(idEnTable)) {
				y = i;
				break;
			}
		}
		return y;
	}
	
	public void EcouterBoutons() {
		btnSupprimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = idTF.getText();
				if(!verifierId(id , idText)) return;
				String requete = "DELETE FROM Client WHERE " + idText + "=" + id;
				int y = getRow(id);
				if(y == -1) {
					JOptionPane.showMessageDialog(null,  idText + " Inexistant");
					return;
				}
				model = (DefaultTableModel) tb.getModel();
				model.removeRow(y);
				DB.executeUpdate(requete);
			}
		});
	}
}
