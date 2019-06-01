package controleur;

import modele.DessinModele;
import modele.FigureColoree;
import modele.Point;
import javax.swing.SwingUtilities;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManipulateurFormes implements MouseListener, MouseMotionListener {

    /**
     * Liste de figures du modele
     */
    private List<FigureColoree> lfg;

    /**
     * Le point selectioner si c'est un point ou c'est null
     */
    private Point selected;

    /**
     * Modele
     */
    private DessinModele dm;


    /**
     * Abscisse d'un clic de souris
     */
    private int last_x;

    /**
     * Ordonnee d'un clic de souris
     */
    private int last_y;

    /**
     * Attribut indiquant si un déplacement est en cours
     */
    private boolean trans;

    /**
     * Indice de la figure actuellement selectionnee (-1 si aucune figure n'est sélectionnee)
     */
    private int sel;

    /**
     * Constructeur de la classe ManipulateurFormes prenant en parametre un DessinModele afin d'avoir
     * acces a la liste de figures colorees de celui-ci
     *
     * @param dessinModele DessinModele
     */
    public ManipulateurFormes(DessinModele dessinModele) {
        this.lfg = new ArrayList<>(dessinModele.getListFigureColore());
        Collections.reverse(lfg);
        this.dm = dessinModele;
        this.sel = -1;
    }

    /**
     * Litener du bouton clique de la souris
     *
     * @param e Evenement de la souris
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Litener du bouton enfonce de la souris
     *
     * @param e Evenement de la souris
     */
    @Override
    public void mousePressed(MouseEvent e) {
        boolean found = false;
        if (sel != -1) {
            lfg.get(sel).deSelectionne();
        }
        trans = false;
        selected = null;
        sel = -1;
        if (SwingUtilities.isLeftMouseButton(e)) {
            int i = 0;
            for (FigureColoree fg : lfg) {
                last_y = e.getY();
                last_x = e.getX();
                if (fg.isInSelection(e) && fg.isSelected()) {
                    dm.update();
                    sel = i;
                    found = true;
                    break;
                }
                i++;
            }
            if (!found) {
                if (figureSelection() != null) {
                    figureSelection().deSelectionne();
                    dm.update();
                }
                sel = -1;
            }
        }
        else if (SwingUtilities.isRightMouseButton(e)) {
            int i = 0;
            last_y = e.getY();
            last_x = e.getX();
            for (FigureColoree fg : lfg) {
                for (Polygon p : fg.getrList()) {
                    last_x = e.getX();
                    last_y = e.getY();
                    if (p.contains(last_x, last_y)) {
                        this.selected = fg.getPointFromPoly(p);
                        trans = true;
                        sel = i;
                        fg.selectionne();
                        break;
                    }
                }
                if (trans) {
                    break;
                }
                i++;
            }
            if (!found && !trans) {
                if (figureSelection() != null) {
                    figureSelection().deSelectionne();
                    dm.update();
                }
                sel = -1;
            }
        }
    }

    /**
     * Litenner du bouton relache de la souris
     *
     * @param e Evenement de la souris
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        dm.update();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Litenner du bouton deplace de la souris
     *
     * @param e Evenement de la souris
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (sel != -1) {
                int difX = e.getX() - last_x;
                int difY = e.getY() - last_y;
                for (Point p : lfg.get(sel).getPoints()) {
                    p.incrementerY(difY);
                    p.incrementerX(difX);
                }
            }
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            if (selected != null) {
                int difX = e.getX() - last_x;
                int difY = e.getY() - last_y;
                lfg.get(sel).transforamtionFigure(selected, difX, difY);
            }
        }
        dm.update();
        last_y = e.getY();
        last_x = e.getX();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Methode permettant de retourner la Figure Coloree actuellement
     * selectionnee dans la liste
     *
     * @return Figure Coloree selectionnee
     */
    public FigureColoree figureSelection() {
        if (this.sel != -1) {
            return this.lfg.get(this.sel);
        }
        else {
            return null;
        }
    }

}
