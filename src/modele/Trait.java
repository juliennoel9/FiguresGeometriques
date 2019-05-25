package modele;

import controleur.FabricantFigure;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.List;

public class Trait extends FigureColoree {

    public Trait() {

    }

    /**
     * Constructeur de la classe Trait initialisant les points de depart et d'arrive
     * ainsi que la couleur du trait
     *
     * @param couleur Couleur du trait
     * @param p1     Point de depart du trait
     * @param p2     Point d'arrive du trait
     */
    public Trait(Color couleur, Point p1, Point p2) {
        this.couleur=couleur;
        tab_mem.add(p1);
        tab_mem.add(p2);
    }

    /**
     * Methode permettant de retourner le nombre de points d'un trait
     *
     * @return Nombre de points d'un trait
     */
    @Override
    public int nbPoints() {
        return 2;
    }

    /**
     * Methode permettant de retourner le nombre de cliques d'un trait
     *
     * @return Nombre de cliques d'un trait
     */
    @Override
    public int nbClics() {
        return 2;
    }

    @Override
    public void modifierPoints(List<Point> points) {
        tab_mem = points;
    }

    public void affiche(Graphics g) {
        g.setColor(this.couleur);
        g.drawLine(tab_mem.get(0).getX(), tab_mem.get(0).getY(), tab_mem.get(1).getX(), tab_mem.get(1).getY());
        super.affiche(g);
    }

    @Override
    public boolean isInSelection(MouseEvent e) {
        int last_x = e.getX();
        int last_y = e.getY();

        if (!tab_mem.isEmpty()){
            Point              p1       = tab_mem.get(0);
            Point              p2       = tab_mem.get(1);

            int distX;
            int distY;
            int xRect;
            int yRect;

            if (p1.getX()<p2.getX()){
                xRect=p1.getX();
                distX = Math.abs(p2.getX()-p1.getX());
            }else if (p1.getX()>p2.getX()){
                xRect=p2.getX();
                distX = Math.abs(p2.getX()-p1.getX());
            }else {
                xRect=p1.getX()-5;
                distX = Math.abs(p2.getX()-p1.getX())+5;
            }

            if (p1.getY()<p2.getY()){
                yRect=p1.getY();
                distY = Math.abs(p2.getY()-p1.getY());
            }else if (p1.getY()>p2.getY()){
                yRect=p2.getY();
                distY = Math.abs(p2.getY()-p1.getY());
            }else {
                yRect=p1.getY()-5;
                distY = Math.abs(p2.getY()-p1.getY())+5;
            }

            int distance = (int) Math.abs(p1.distance(p2));

            if (distance>5){
                java.awt.Rectangle r        = new java.awt.Rectangle(xRect, yRect, distX, distY);
                if (r.contains(last_x, last_y)) {
                    selectionne();
                    return true;
                }
                return false;
            }else {
                if (Math.pow(p1.getX()-last_x,2)+Math.pow(p1.getY()-last_y,2)<=400){
                    selectionne();
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public FabricantFigure getContructeur(DessinModele dessinModele) {
        return new FabricantFigure(this, dessinModele);
    }
}
