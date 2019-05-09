package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.List;

public class Trait extends FigureColoree {

    /**
     * Couleur du trait
     */
    private Color color;

    /**
     * Abscisse du point de depart du trait
     */
    private int pAx;

    /**
     * Ordonnee du point de depart du trait
     */
    private int pAy;

    /**
     * Abscisse du point d'arrive du trait
     */
    private int pBx;

    /**
     * Ordonnee du point d'arrive du trait
     */
    private int pBy;

    /**
     * Constructeur vide
     */
    public Trait() {

    }

    /**
     * Constructeur de la classe Trait initialisant les points de depart et d'arrive
     * ainsi que la couleur du trait
     *
     * @param couleur Couleur du trait
     * @param pAx     Abscisse du point de depart du trait
     * @param pAy     Ordonnee du point de depart du trait
     * @param pBx     Abscisse du point d'arrive du trait
     * @param pBy     Ordonnee du point d'arrive du trait
     */
    public Trait(Color couleur, int pAx, int pAy, int pBx, int pBy) {
        this.color = couleur;
        this.pAx = pAx;
        this.pAy = pAy;
        this.pBx = pBx;
        this.pBy = pBy;
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
        setpAx(points.get(0).getX());
        setpAy(points.get(0).getY());
        setpBx(points.get(1).getX());
        setpBy(points.get(1).getY());
    }

    public void affiche(Graphics g) {
        g.setColor(this.color);
        g.drawLine(pAx, pAy, pBx, pBy);
    }

    @Override
    public boolean isInSelection(MouseEvent e) {
        return false;
    }

    /**
     * Setter de l'abscisse du point de depart du trait
     *
     * @param pAx Abscisse du point de depart du trait
     */
    public void setpAx(int pAx) {
        this.pAx = pAx;
    }

    /**
     * Setter de l'ordonnee du point de depart du trait
     *
     * @param pAy Ordonnee du point de depart du trait
     */
    public void setpAy(int pAy) {
        this.pAy = pAy;
    }

    /**
     * Setter de l'abscisse du point d'arrive du trait
     *
     * @param pBx Abscisse du point d'arrive du trait
     */
    public void setpBx(int pBx) {
        this.pBx = pBx;
    }

    /**
     * Setter de l'ordonnee du point d'arrive du trait
     *
     * @param pBy Ordonnee du point d'arrive du trait
     */
    public void setpBy(int pBy) {
        this.pBy = pBy;
    }
}
