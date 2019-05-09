package main;

import controleur.PanneauChoix;
import vue.VueDessin;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Classe Principale qui lance l'application
 */
public class Fenetre extends JFrame {

    private VueDessin vdessin;
    private JPanel    choix;

    public Fenetre(String title, int longeur, int largeur) {
        setTitle(title);
        setPreferredSize(new Dimension(longeur, largeur));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(500, 493));
        vdessin = new VueDessin();
        choix = new PanneauChoix(vdessin);
        add(vdessin, BorderLayout.CENTER);
        add(choix, BorderLayout.NORTH);
        pack();
        setVisible(true);
    }

    public PanneauChoix getChoix() {
        return (PanneauChoix) choix;
    }

    public static void main(String[] arguments) {
        Fenetre f = new Fenetre("Figures Géometriques", 1024, 500);
    }
}
