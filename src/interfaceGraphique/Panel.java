package interfaceGraphique;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public abstract class Panel extends JPanel {
	JPanel jPanel1 = new JPanel(),
			jPanel2 = new JPanel();
	JLabel jLabels[] = new JLabel[4];
	
	JTextField txtFields[] = new JTextField[4];
	
	JButton
	btn1 = new JButton("Ajouter"),
	btn2 = new JButton("Modifier"),
	btn3 = new JButton("Supprimer");
	
	JTable tb = new JTable();
	JScrollPane sp = new JScrollPane();

	abstract public void setLabels();
	
	public Panel() {
		for(int i=0; i < jLabels.length; i++) {
			jLabels[i] = new JLabel();
			txtFields[i] = new JTextField();
		}
		
		setLabels();
		ajouterComposants();
		setLocationAndSize();
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
		
		txtFields[0].setBounds(150, 30, 150, 30);
		txtFields[1].setBounds(150, 90, 150, 30);
		txtFields[2].setBounds(150, 150, 150, 30);
		txtFields[3].setBounds(150, 210, 150, 30);
		
		btn1.setBounds(50, 280, 200, 30);
		btn2.setBounds(50, 320, 200, 30);
		btn3.setBounds(50, 360, 200, 30);
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
		jPanel1.add(btn1);
		jPanel1.add(btn2);
		jPanel1.add(btn3);
		sp.setViewportView(tb);
		jPanel2.add(sp);
		jPanel1.setLayout(null);
		this.add(jPanel1);
		this.add(jPanel2);
		this.setLayout(null);
	}
	
	public void setTable(String th[], String requete) {
		boolean[] canEdit = new boolean[5];
		Arrays.fill(canEdit, false);
        tb.setModel(new DefaultTableModel( new Object [][] { }, th ) {
        	// Pour mettre les cellule
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
        });
        DefaultTableModel model = (DefaultTableModel) tb.getModel();
        tb.setCellSelectionEnabled(false);
		try {
			ResultSet rs = DB.stmt.executeQuery(requete);
			System.out.println(rs);
			while(rs.next()) {
		        Object[] ligne = new Object[th.length];
		        for(int i=0; i<th.length; i++) {
		        	ligne[i] = rs.getString(i+1);
		        }
		        model.addRow(ligne);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		EcouterTable(tb);
	}
	
	public void EcouterTable(JTable table) {
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
}
