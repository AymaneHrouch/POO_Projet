package interfaceGraphique;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	static Statement stmt = null;
	
	public static void demarrer() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poo_db","root","");
        	stmt = con.createStatement();
        } catch(ClassNotFoundException ex) {
        	Util.AfficherErreur(new JFrame(),"Erreur lors de chargement de drive: " + ex.getMessage());
            System.exit(1);
        }
        catch(SQLException ex) {
        	Util.AfficherErreur(new JFrame(), ex.getMessage());
            System.exit(1);
        }
	}
}
