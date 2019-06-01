package controleur;

import main.Fenetre;
import modele.DessinModele;
import modele.FigureColoree;
import modele.Point;
import vue.VueDessin;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class FabricantFigure implements MouseListener {

    /**
     * Dessin Modele du Fabricant Figure
     */
    protected DessinModele ds;

    /**
     * Figure Coloree en cours de fabrication
     */
    protected FigureColoree figureEnCours;

    /**
     * Liste de stockage des points ou l'on a clique
     */
    protected List<Point> pointsCliques;

    /**
     * Nombre de clique qui s'incremente a chaque clique
     */
    protected int nbClique;

    /**
     * Panneau Choix de la fenetre
     */
    protected PanneauChoix panneauChoix;

    /**
     * Constructeur de la classe Fabricant Figure
     *
     * @param figureEnCours Figure coloree en cours
     * @param ds            Dessin Modele
     */
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

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        nbClique++;
        if (panneauChoix == null) {
            panneauChoix = ((Fenetre) SwingUtilities.getWindowAncestor((VueDessin) e.getSource())).getChoix();
        }
        figureEnCours.changeCouleur(panneauChoix.getCouleur());
        pointsCliques.add(new Point(e.getX(), e.getY()));
        if (nbClique == figureEnCours.nbClics()) {
            figureEnCours.modifierPoints(pointsCliques);
            ds.addFigureColore(figureEnCours);
            panneauChoix.reCreateObject();
        }
        ds.update();
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


    public boolean hasMotionListener() {
        return false;
    }
}
