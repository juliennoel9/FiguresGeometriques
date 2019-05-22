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
     *
     * @return Le nombre de cliques necessaire
     */
    @Override
    public int nbPoints() {
        return 2;
    }

    /**
     * Methode permettant de calculer les 4 points du rectangle a partir des 2 cliques
     *
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
    public void transforamtionFigure(Point selected, int difX, int difY) {
        switch (getPoints().indexOf(selected)) {
            case 0:
                selected.translater(difX, difY);
                int ancienY1 = getPoints().get(1).getY();
                int ancienX3 = getPoints().get(3).getX();
                getPoints().get(1).translater(0, getPoints().get(0).getY() - ancienY1);
                getPoints().get(3).translater(getPoints().get(0).getX() - ancienX3, 0);
                break;
            case 1:
                selected.translater(difX, difY);
                int ancienX2 = getPoints().get(2).getX();
                int ancienY0 = getPoints().get(0).getY();
                getPoints().get(0).translater(0, getPoints().get(1).getY() - ancienY0);
                getPoints().get(2).translater(getPoints().get(1).getX() - ancienX2, 0);
                break;
            case 2:
                selected.translater(difX, difY);
                int ancienX1 = getPoints().get(1).getX();
                int ancienY3 = getPoints().get(3).getY();
                getPoints().get(3).translater(0, getPoints().get(2).getY() - ancienY3);
                getPoints().get(1).translater(getPoints().get(2).getX() - ancienX1, 0);
                break;
            case 3:
                selected.translater(difX, difY);
                int ancienX0 = getPoints().get(0).getX();
                int ancienY2 = getPoints().get(2).getY();
                getPoints().get(2).translater(0, getPoints().get(3).getY() - ancienY2);
                getPoints().get(0).translater(getPoints().get(3).getX() - ancienX0, 0);
                break;
        }
    }
}
