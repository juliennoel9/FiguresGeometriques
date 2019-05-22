package modele;

import java.util.List;

/**
 * Classe representant un Rectangle
 */
public class Rectangle extends Quadrilatere {

    /**
     * Constructeur vide
     */
    public Rectangle() {

    }

    /**
     * Methode permettant de connaitre le nombre de points a cliquer necessaire a la construction du rectangle
     * @return Le nombre de cliques necessaire
     */
    @Override
    public int nbPoints() {
        return 2;
    }

    /**
     * Methode permettant de calculer les 4 points du rectangle a partir des 2 cliques
     * @param pointsCliques Liste des 2 points cliques
     */
    @Override
    public void modifierPoints(List<Point> pointsCliques) {
        Point a = pointsCliques.get(0);
        Point c = pointsCliques.get(1);
        Point b = new Point(c.getX(), a.getY());
        Point d = new Point(a.getX(), c.getY());
        pointsCliques.add(1, b);
        pointsCliques.add(d);
        super.modifierPoints(pointsCliques);
    }

    @Override
    public boolean isRectangle() {
        return true;
    }
}
