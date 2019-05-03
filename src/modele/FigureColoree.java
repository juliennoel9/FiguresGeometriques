package modele;

import java.awt.*;
import java.util.ArrayList;

public abstract class FigureColoree {
    private final static int TAILLE_CARRE_SELECTION=5;
    private boolean selected;
    protected Color couleur;
    protected ArrayList<Point> tab_mem;

    public FigureColoree(){
           this.selected=false;
           this.tab_mem=new ArrayList<>();
           this.couleur=Color.BLACK;
    }

    public abstract int nbPoints();

    public abstract int nbClics();

    public abstract void modifierPoints(ArrayList<Point> points);

    public void affiche(Graphics g){

    }

    public void selectionne(){

    }

    public void deSelectionne(){

    }

    public void changeCouleur(Color color){

    }
}
