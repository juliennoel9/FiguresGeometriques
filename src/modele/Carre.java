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
        points.add(new Point(a.getX() + di, a.getY() + di)); // c
        points.add(d);
        super.modifierPoints(points);

    }

    @Override
    public void transforamtionFigure(Point selected, int difX, int difY) {
        switch (this.getPoints().indexOf(selected)) {
            case 0:
                selected.translater(difX, difX);

                int ancienY2 = this.getPoints().get(2).getY();
                int ancienX2 = this.getPoints().get(2).getX();

                int diffCoteX = this.getPoints().get(2).getX()
                                - this.getPoints().get(0).getX();

                this.getPoints().get(3).setY(ancienY2 - diffCoteX);
                this.getPoints().get(1).setX(ancienX2 - diffCoteX);
                break;
            case 1:
                selected.translater(difX, -difX);

                int ancienY3 = this.getPoints().get(3).getY();
                int ancienX3 = this.getPoints().get(3).getX();

                int diffCoteX1 = this.getPoints().get(3).getX()
                                 - this.getPoints().get(1).getX();

                this.getPoints().get(2).setY(ancienY3 + diffCoteX1);
                this.getPoints().get(0).setX(ancienX3 - diffCoteX1);
                break;
            case 2:
                selected.translater(difX, difX);

                int ancienY0 = this.getPoints().get(0).getY();
                int ancienX0 = this.getPoints().get(0).getX();

                int diffCoteX2 = this.getPoints().get(2).getX()
                                 - this.getPoints().get(0).getX();

                this.getPoints().get(1).setY(ancienY0 + diffCoteX2);
                this.getPoints().get(3).setX(ancienX0 + diffCoteX2);
                break;
            case 3:
                selected.translater(difX, -difX);

                int ancienY1 = this.getPoints().get(1).getY();
                int ancienX1 = this.getPoints().get(1).getX();

                int diffCoteX3 = this.getPoints().get(3).getX()
                                 - this.getPoints().get(1).getX();

                this.getPoints().get(0).setY(ancienY1 - diffCoteX3);
                this.getPoints().get(2).setX(ancienX1 + diffCoteX3);
                break;
        }
    }

    @Override
    public FabricantFigure getConstructeur(DessinModele ds) {
        return new FabricantCarre(this, ds);
    }
}
