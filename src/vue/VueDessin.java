package vue;

import controleur.ManipulateurFormes;
import modele.DessinModele;
import modele.FigureColoree;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe representant une Vue Dessin
 */
public class VueDessin extends JPanel implements Observer {

    /**
     * Le dessinModele
     */
    private DessinModele dessin;

    /**
     * Manipulateur de forme de la vue
     */
    private ManipulateurFormes mf;

    /**
     * Constructeur ajoutant la taille
     */
    public VueDessin() {
        setVisible(true);
        setPreferredSize(new Dimension(100, 100));
    }

    /**
     * Methode d'update de l'observer
     *
     * @param o   l'observable {@link DessinModele}
     * @param arg les args
     */
    @Override
    public void update(Observable o, Object arg) {
        dessin = (DessinModele) o;
        repaint();
    }

    /**
     * permet de dessiner les figures
     *
     * @param g le graphique
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (dessin != null) {
            for (FigureColoree figureColoree : dessin.getListFigureColore()) {
                g.setColor(figureColoree.getCouleur());
                figureColoree.affiche(g);
            }
        }
    }
}