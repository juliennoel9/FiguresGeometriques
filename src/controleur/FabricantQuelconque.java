package controleur;

import main.Fenetre;
import modele.DessinModele;
import modele.FigureColoree;
import modele.Point;
import vue.VueDessin;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;

public class FabricantQuelconque extends FabricantFigure {

    /**
     * Constructeur de la classe Fabricant Figure
     *
     * @param figureEnCours Figure coloree en cours
     * @param ds            Dessin Modele
     */
    public FabricantQuelconque(FigureColoree figureEnCours, DessinModele ds) {
        super(figureEnCours, ds);
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
        if (panneauChoix == null) {
            panneauChoix = ((Fenetre) SwingUtilities.getWindowAncestor((VueDessin) e.getSource())).getChoix();
        }
        if (!ds.getListFigureColore().contains(figureEnCours)) {
            ds.addFigureColore(figureEnCours);
        }
        figureEnCours.getPoints().add(new Point(e.getX(), e.getY()));
        figureEnCours.changeCouleur(panneauChoix.getCouleur());
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

}
