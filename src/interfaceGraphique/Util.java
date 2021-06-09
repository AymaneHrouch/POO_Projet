package interfaceGraphique;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Util {
    public static void AfficherErreur(JFrame frame, String message)
    {
        JOptionPane.showMessageDialog(frame, message, "Erreur!", JOptionPane.ERROR_MESSAGE);
    } 
}
