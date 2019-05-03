package modele;

import java.awt.*;
import java.util.ArrayList;

public abstract class Polygone extends FigureColoree {
    private Polygon p;

    public Polygone(){
        this.p=new Polygon();
    }

    public void affiche(Graphics g){

    }

    public int nbClics(){
        return 0;
    }

    public void modifierPoints(ArrayList<Point> points){

    }
}