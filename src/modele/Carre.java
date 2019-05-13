package modele;

import controleur.FabricantCarre;
import controleur.FabricantFigure;
import java.util.List;

public class Carre extends Polygone {

    @Override
    public int nbPoints() {
        return 0;
    }

    @Override
    public void modifierPoints(List<Point> points) {
        Point a  = points.get(0);
        Point c  = points.remove(1);
        int   di = c.getY() - a.getX();
        Point b  = new Point(a.getX(), a.getY() + di);
        Point d  = new Point(a.getX() + di, a.getY());
        points.add(b);
        points.add(new Point(a.getX() + di, a.getY() + di));
        points.add(d);
        super.modifierPoints(points);

    }

    @Override
    public FabricantFigure getContructeur(DessinModele ds) {
        return new FabricantCarre(this, ds);
    }
}
