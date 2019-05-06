package modele;

import java.awt.*;
import java.util.List;

/**
 * Classe representant un Cercle
 */
public class Cercle extends FigureColoree{

    /**
     * Methode permettant de connaitre le nombre de points a cliquer necessaire a la construction du cercle
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
     * @param g Graphics
     */
    @Override
    public void affiche(Graphics g){
        Point p1 = tab_mem.get(0);
        Point p2 = tab_mem.get(1);
        g.fillOval(p1.getX(),p1.getY(),(int)p1.distance(p2),(int)p1.distance(p2));
        super.affiche(g);
    }
}
