package modele;

import controleur.FabricantFigure;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.List;

public abstract class Polygone extends FigureColoree {

    /**
     * Constructeur vide
     */
    protected Polygon p;

    /**
     * Constructeur qui initialise le poly
     */
    public Polygone() {
        this.p = new Polygon();
    }

    /**
     * Permet d'afficher la figure
     *
     * @param g le graphique
     */
    public void affiche(Graphics g) {
        int[] tabX = new int[tab_mem.size()];
        int[] tabY = new int[tab_mem.size()];
        int   i    = 0;
        for (Point pt : this.tab_mem) {
            tabX[i] = pt.getX();
            tabY[i] = pt.getY();
            i++;
        }
        p = new Polygon(tabX, tabY, tab_mem.size());
        g.fillPolygon(p);
        super.affiche(g);
    }

    @Override
    public boolean isInSelection(MouseEvent e) {
        int last_x = e.getX();
        int last_y = e.getY();
        if (this.getP().contains(last_x, last_y)) {
            this.selectionne();
            return true;
        }
        return false;
    }

    @Override
    public FabricantFigure getConstructeur(DessinModele dessinModele) {
        return new FabricantFigure(this, dessinModele);
    }

    /**
     * @return le nombre points
     */
    public int nbClics() {
        return nbPoints();
    }

    /**
     * setter des points
     *
     * @param points les points a ajouter
     */
    public void modifierPoints(List<Point> points) {
        tab_mem = points;
    }

    /**
     * @return le polygone
     */
    public Polygon getP() {
        return p;
    }
}