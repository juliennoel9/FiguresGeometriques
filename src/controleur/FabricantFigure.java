package controleur;

import modele.DessinModele;
import modele.FigureColoree;
import modele.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class FabricantFigure implements MouseListener {

    private DessinModele ds;

    private FigureColoree figureEnCours;

    private List<Point> pointsCliques;

    private int nbClique;

    public FabricantFigure(FigureColoree figureEnCours, DessinModele ds) {
        this.ds = ds;
        this.figureEnCours = figureEnCours;
        pointsCliques = new ArrayList<>();
        nbClique = 0;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        nbClique++;
        pointsCliques.add(new Point(e.getX(), e.getY()));
        if (nbClique == figureEnCours.nbClics()) {
            figureEnCours.modifierPoints(pointsCliques);
            ds.addFigureColore(figureEnCours);
            ds.finFigure();
        }

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    public FigureColoree getFigureEnCours() {
        return figureEnCours;
    }

    public List<Point> getPointsClique() {
        return pointsCliques;
    }

    public int getNbClique() {
        return nbClique;
    }
}
