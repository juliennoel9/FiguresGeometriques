import vue.VueDessin;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;

public class Fenetre extends JFrame {

    private JPanel    principal;
    private VueDessin vdessin;
    private JPanel    choix;

    public Fenetre(String title, int longeur, int largeur) {
        setTitle(title);
        setPreferredSize(new Dimension(longeur, largeur));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] arguments) {
        Fenetre f = new Fenetre("", 1024, 500);
    }
}
