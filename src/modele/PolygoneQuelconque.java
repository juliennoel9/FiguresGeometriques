package modele;

import controleur.FabricantCarre;
import controleur.FabricantFigure;
import controleur.FabricantQuelconque;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.List;

public class PolygoneQuelconque extends Polygone {

    /**
     * Methode abstraite permettant de retourner le nombre de points de la figure
     *
     * @return le nombre de points
     */
    @Override
    public int nbPoints() {
        return 0;
    }

    /**
     * @return le nombre de click
     */
    @Override
    public int nbClics() {
        return 0;
    }

    /**
     * Permet de modifie les points
     *
     * @param points Liste de points a ajouter
     */
    @Override
    public void modifierPoints(List<Point> points) {
        tab_mem.addAll(points);
    }


    @Override
    public boolean isInSelection(MouseEvent e) {
        return getP().contains(e.getPoint());
    }

    @Override
    public FabricantFigure getContructeur(DessinModele dessinModele) {
        return new FabricantQuelconque(this, dessinModele);
    }
}
