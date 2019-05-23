package controleur;

import main.Fenetre;
import modele.DessinModele;
import modele.FigureColoree;
import modele.Point;
import vue.VueDessin;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class FabricantCercle extends FabricantFigure implements MouseMotionListener {

    private Point firstPoint;

    public FabricantCercle(FigureColoree figureEnCours, DessinModele ds) {
        super(figureEnCours, ds);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public boolean hasMotionListener() {
        return true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            firstPoint = new Point(e.getX(), e.getY());
            pointsCliques.add(firstPoint);
            ds.addFigureColore(figureEnCours);
            ds.update();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ((Fenetre) SwingUtilities.getWindowAncestor((VueDessin) e.getSource())).getChoix().reCreateObject();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (firstPoint == null) {
                firstPoint = new Point(e.getX(), e.getY());
            }
            pointsCliques.clear();
            pointsCliques.add(firstPoint);
            pointsCliques.add(new Point(e.getX(), firstPoint.getY()));
            figureEnCours.modifierPoints(new ArrayList<>(pointsCliques));
            ds.update();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}