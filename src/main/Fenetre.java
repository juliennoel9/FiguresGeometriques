package main;

import controleur.PanneauChoix;
import vue.VueDessin;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Classe Principale qui lance l'application
 */
public class Fenetre extends JFrame {

    private VueDessin    vdessin;
    private PanneauChoix choix;

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
        setJMenuBar(choix.getMenuBar());
        setVisible(true);
    }

    public PanneauChoix getChoix() {
        return choix;
    }

    public static void main(String[] arguments) {
        Fenetre f = new Fenetre("Figures Géometriques", 1024, 500);
    }
}
