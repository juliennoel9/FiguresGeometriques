package controleur;

import modele.*;

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
     * Attribut indiquant l'indice du point proche d'un carre de selection
     */
    private int indice;

    /**
     * Nombre effectif de figures apparaissant dans ce dessin
     */
    private int nbf;

    /**
     * Indice de la figure actuellement selectionnee (-1 si aucune figure n'est sélectionnee)
     */
    private int sel;

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
                if (fg.isInSelection(e)) {
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
        e.getComponent().requestFocusInWindow();
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
                if (lfg.get(sel).isCercle()) {
                    int difX = e.getX() - last_x;
                    int difY = e.getY() - last_y;
                    if (selected.equals(figureSelection().getPoints().get(0))) {
                        for (Point p : figureSelection().getPoints()) {
                            p.translater(difX, difY);
                        }
                    }
                    else {
                        selected.translater(difX, difY);
                    }

                }
                else {
                    if (lfg.get(sel).isRectangle()){
                        int difX = e.getX() - last_x;
                        int difY = e.getY() - last_y;
                        switch (lfg.get(sel).getPoints().indexOf(selected)) {
                            case 0 :
                                selected.translater(difX,difY);
                                int ancienY1 = lfg.get(sel).getPoints().get(1).getY();
                                int ancienX3 = lfg.get(sel).getPoints().get(3).getX();
                                lfg.get(sel).getPoints().get(1).translater(0,lfg.get(sel).getPoints().get(0).getY()-ancienY1);
                                lfg.get(sel).getPoints().get(3).translater(lfg.get(sel).getPoints().get(0).getX()-ancienX3,0);
                                break;
                            case 1 :
                                selected.translater(difX,difY);
                                int ancienX2 = lfg.get(sel).getPoints().get(2).getX();
                                int ancienY0 = lfg.get(sel).getPoints().get(0).getY();
                                lfg.get(sel).getPoints().get(0).translater(0,lfg.get(sel).getPoints().get(1).getY()-ancienY0);
                                lfg.get(sel).getPoints().get(2).translater(lfg.get(sel).getPoints().get(1).getX()-ancienX2,0);
                                break;
                            case 2 :
                                selected.translater(difX,difY);
                                int ancienX1 = lfg.get(sel).getPoints().get(1).getX();
                                int ancienY3 = lfg.get(sel).getPoints().get(3).getY();
                                lfg.get(sel).getPoints().get(3).translater(0,lfg.get(sel).getPoints().get(2).getY()-ancienY3);
                                lfg.get(sel).getPoints().get(1).translater(lfg.get(sel).getPoints().get(2).getX()-ancienX1,0);
                                break;
                            case 3 :
                                selected.translater(difX,difY);
                                int ancienX0 = lfg.get(sel).getPoints().get(0).getX();
                                int ancienY2 = lfg.get(sel).getPoints().get(2).getY();
                                lfg.get(sel).getPoints().get(2).translater(0,lfg.get(sel).getPoints().get(3).getY()-ancienY2);
                                lfg.get(sel).getPoints().get(0).translater(lfg.get(sel).getPoints().get(3).getX()-ancienX0,0);
                                break;
                        }
                    }else {
                        if (lfg.get(sel).isCarre()){
                            int dif = e.getX() - last_x;

                            switch (lfg.get(sel).getPoints().indexOf(selected)) {
                                case 0 :
                                    selected.translater(dif,dif);

                                    int ancienY2 = lfg.get(sel).getPoints().get(2).getY();
                                    int ancienX2 = lfg.get(sel).getPoints().get(2).getX();

                                    int diffCoteX=lfg.get(sel).getPoints().get(2).getX()-lfg.get(sel).getPoints().get(0).getX();

                                    lfg.get(sel).getPoints().get(3).setY(ancienY2-diffCoteX);
                                    lfg.get(sel).getPoints().get(1).setX(ancienX2-diffCoteX);
                                    break;
                                case 1 :
                                    selected.translater(dif,-dif);

                                    int ancienY3 = lfg.get(sel).getPoints().get(3).getY();
                                    int ancienX3 = lfg.get(sel).getPoints().get(3).getX();

                                    int diffCoteX1=lfg.get(sel).getPoints().get(3).getX()-lfg.get(sel).getPoints().get(1).getX();

                                    lfg.get(sel).getPoints().get(2).setY(ancienY3+diffCoteX1);
                                    lfg.get(sel).getPoints().get(0).setX(ancienX3-diffCoteX1);
                                    break;
                                case 2 :
                                    selected.translater(dif,dif);

                                    int ancienY0 = lfg.get(sel).getPoints().get(0).getY();
                                    int ancienX0 = lfg.get(sel).getPoints().get(0).getX();

                                    int diffCoteX2=lfg.get(sel).getPoints().get(2).getX()-lfg.get(sel).getPoints().get(0).getX();

                                    lfg.get(sel).getPoints().get(1).setY(ancienY0+diffCoteX2);
                                    lfg.get(sel).getPoints().get(3).setX(ancienX0+diffCoteX2);
                                    break;
                                case 3 :
                                    selected.translater(dif,-dif);

                                    int ancienY1 = lfg.get(sel).getPoints().get(1).getY();
                                    int ancienX1 = lfg.get(sel).getPoints().get(1).getX();

                                    int diffCoteX3=lfg.get(sel).getPoints().get(3).getX()-lfg.get(sel).getPoints().get(1).getX();

                                    lfg.get(sel).getPoints().get(0).setY(ancienY1-diffCoteX3);
                                    lfg.get(sel).getPoints().get(2).setX(ancienX1+diffCoteX3);
                                    break;
                            }
                        }else {
                            int difX = e.getX() - last_x;
                            int difY = e.getY() - last_y;
                            selected.translater(difX, difY);
                        }
                    }
                }
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
     * Methode retournant le nombre de figures presentes dans la liste
     *
     * @return Nombre de figures dans la liste
     */
    public int nbFigures() {
        return this.lfg.size();
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

    /**
     * Methode permettant de selectionner la prochaine figure dans la liste
     */
    public void selectionProchaineFigure() {
        this.sel += 1;
    }

    /**
     * Getter permettant de retourner la liste de figure de l'instance
     *
     * @return Liste de figures
     */
    public List<FigureColoree> getLfg() {
        return lfg;
    }
}
