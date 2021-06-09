package interfaceGraphique;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginFrame extends JFrame implements ActionListener {
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    
    public LoginFrame()
    {
       //Calling methods inside constructor.
        setLocationAndSize();
        ajouterComposants();
        loginButton.addActionListener(this);
        this.setLayout(null);
        this.setTitle("Login Form");
        this.setVisible(true);
        this.setBounds(10,10,370,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }
    
    public void setLocationAndSize()
    {
        //Setting location and Size of each components using setBounds() method.
        userLabel.setBounds(50,150,100,30);
        passwordLabel.setBounds(50,220,100,30);
        userTextField.setBounds(150,150,150,30);
        passwordField.setBounds(150,220,150,30);
        loginButton.setBounds(135,300,100,30);
    }
    
    public void ajouterComposants()
    {
       //Adding each components to the Container
        this.add(userLabel);
        this.add(passwordLabel);
        this.add(userTextField);
        this.add(passwordField);
        this.add(loginButton);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String loginText;
            String pwdText;
            loginText = userTextField.getText();
            pwdText = new String(passwordField.getPassword());
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
            	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poo_db","root","");
            	Statement stmt = con.createStatement();
            	ResultSet rs = DB.executeQuery("select login, pwd from users"); 
            	while(rs.next()){
            		if(loginText.equals(rs.getString(1)) && pwdText.equals(rs.getString(2))) {
                        JOptionPane.showMessageDialog(this, "Connecté avec succés!");
                        this.setVisible(false);
            			new Main().afficher();
            			return;
            		}
            	}
            	JOptionPane.showMessageDialog(this, "Login ou mot de passe Invalide.");
            } catch(ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de chargement de drive: " + ex.getMessage(), "ERREUR!", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            catch(SQLException ex) {
            	JOptionPane.showMessageDialog(this, ex.getMessage(), "ERREUR!", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
    }
}
