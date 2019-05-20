package modele;

import controleur.FabricantFigure;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Classe representant un Cercle
 */
public class Cercle extends FigureColoree {

    /**
     * Methode permettant de connaitre le nombre de points a cliquer necessaire a la construction du cercle
     *
     * @return Le nombre de cliques necessaire
     */
    public int nbPoints() {
        return 2;
    }

    @Override
    public int nbClics() {
        return 2;
    }

    /**
     * Setter des points
     *
     * @param points les points a ajouter
     */
    @Override
    public void modifierPoints(List<Point> points) {
        tab_mem = points;
    }


    /**
     * Permet d'afficher le Cercle
     *
     * @param g Graphics
     */
    @Override
    public void affiche(Graphics g) {
        Point p1 = tab_mem.get(0);
        Point p2 = tab_mem.get(1);
        g.fillOval(p1.getX(), p1.getY(), (int) p1.distance(p2), (int) p1.distance(p2));
        super.affiche(g);
    }

    @Override
    public boolean isInSelection(MouseEvent e) {
        int                last_x   = e.getX();
        int                last_y   = e.getY();
        Point              p1       = getPoints().get(0);
        Point              p2       = getPoints().get(1);
        int                distance = (int) p1.distance(p2);
        java.awt.Rectangle r        = new java.awt.Rectangle(p1.getX(), p1.getY(), distance, distance);
        if (r.contains(last_x, last_y)) {
            selectionne();
            return true;
        }
        return false;
    }

    @Override
    public boolean isCercle() {
        return true;
    }

    @Override
    public FabricantFigure getContructeur(DessinModele dessinModele) {
        return new FabricantFigure(this, dessinModele);
    }

}
