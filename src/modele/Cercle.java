package modele;

import controleur.FabricantCarre;
import controleur.FabricantCercle;
import controleur.FabricantFigure;

import java.awt.*;
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
        if (!tab_mem.isEmpty()){
            Point p1 = tab_mem.get(0);
            Point p2 = tab_mem.get(1);
            int distX = (int) p1.distance(p2);
            g.fillOval(p2.getX()-2*distX, p2.getY()-distX, distX*2, distX*2);
            super.affiche(g);
        }
    }

    @Override
    public boolean isInSelection(MouseEvent e) {
        int                last_x   = e.getX();
        int                last_y   = e.getY();

        if (!tab_mem.isEmpty()){
            Point              p1       = getPoints().get(0);
            Point              p2       = getPoints().get(1);
            int distX = p2.getX()-p1.getX();


            int                distance = (int) p1.distance(p2);
            java.awt.Rectangle r        = new java.awt.Rectangle(p2.getX()-2*distX, p2.getY()-distX, distance*2, distance*2);
            if (r.contains(last_x, last_y)) {
                selectionne();
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void transforamtionFigure(Point selected, int difX, int difY) {
        if (selected.equals(getPoints().get(0))) {
            for (Point p : getPoints()) {
                p.translater(difX, difY);
            }
        }
        else {
            if (tab_mem.get(1).getX()+difX>tab_mem.get(0).getX()+10){
                selected.translater(difX, 0);
            }
        }
    }

    @Override
    public FabricantFigure getContructeur(DessinModele dessinModele) {
        return new FabricantCercle(this, dessinModele);
    }

}
