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

    }

    public int nbClics() {
        return 0;
    }

    public void modifierPoints(List<Point> points) {

    }
}