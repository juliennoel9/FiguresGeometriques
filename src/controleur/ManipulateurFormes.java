package controleur;

import modele.DessinModele;
import modele.FigureColoree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class ManipulateurFormes implements MouseListener, MouseMotionListener {

    /**
     * Liste de figures du modele
     */
    private List<FigureColoree> lfg;

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

    public ManipulateurFormes(DessinModele dessinModele){
        this.lfg=new ArrayList<>();
        this.dm=dessinModele;
        this.sel=-1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public int nbFigures(){
        return this.lfg.size();
    }

    public FigureColoree figureSelection(){
        if (this.sel!=-1){
            return this.lfg.get(this.sel);
        }else {
            return null;
        }
    }

    public void selectionProchaineFigure(){
        this.sel+=1;
    }
}
