package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class FigureColoree {
    private final static int TAILLE_CARRE_SELECTION=5;
    protected Color couleur;
    protected ArrayList<Point> tab_mem;
    private boolean selected;

    public FigureColoree(){
           this.selected=false;
           this.tab_mem=new ArrayList<>();
           this.couleur=Color.BLACK;
    }

    public abstract int nbPoints();

    public abstract int nbClics();

    public abstract void modifierPoints(List<Point> points);

    public void affiche(Graphics g){

    }

    public void selectionne(){

    }

    public void deSelectionne(){

    }

    public void changeCouleur(Color color){

    }
}
