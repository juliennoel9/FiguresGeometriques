import controleur.PanneauChoix;
import vue.VueDessin;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Fenetre extends JFrame {

    private VueDessin vdessin;
    private JPanel    choix;

    public Fenetre(String title, int longeur, int largeur) {
        setTitle(title);
        setPreferredSize(new Dimension(longeur, largeur));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        vdessin = new VueDessin();
        choix = new PanneauChoix(vdessin);
        add(choix, BorderLayout.NORTH);
        add(vdessin, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public static void main(String[] arguments) {
        Fenetre f = new Fenetre("", 1024, 500);
    }
}
