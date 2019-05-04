package modele;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class Polygone extends FigureColoree {

    private Polygon p;

    public Polygone() {
        this.p = new Polygon();
    }

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
    }

    public int nbClics() {
        return nbPoints();
    }

    public void modifierPoints(List<Point> points) {
        tab_mem = points;
    }

    public Polygon getP() {
        return p;
    }
}