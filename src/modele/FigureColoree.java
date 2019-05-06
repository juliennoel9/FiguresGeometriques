package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.*;

public abstract class FigureColoree {

    /**
     * Taille des petit carée
     */
    private final static int TAILLE_CARRE_SELECTION = 8;

    /**
     * Couleur de la figure
     */
    protected Color couleur;

    /**
     * Point de la figure
     */
    protected List<Point>         tab_mem;
    /**
     * Liste des rectangles, change a chaque affichage des rectangle de selection, set a vide dans les autres cas
     */
    private   Map<Polygon, Point> rList;
    /**
     * Pour savoir si la figure est selectioner
     */
    private   boolean             selected;

    public FigureColoree() {
        this.rList = new HashMap<>();
        this.selected = false;
        this.tab_mem = new ArrayList<>();
        this.couleur = Color.BLACK;
    }

    public Set<Polygon> getrList() {
        return rList.keySet();
    }

    /**
     * getter de la figure
     *
     * @return la couleur  de la figure
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * @return le nombre de points
     */
    public abstract int nbPoints();

    /**
     * @return le nombre de click
     */
    public abstract int nbClics();

    /**
     * Permet de modifie les
     *
     * @param points les points a ajouté
     */
    public abstract void modifierPoints(List<Point> points);

    public Point getPointFromPoly(Polygon p) {
        return rList.get(p);
    }

    public void affiche(Graphics g) {
        rList.clear();
        if (selected) {
            for (Point p : tab_mem) {
                int[] x = {
                        p.getX() + TAILLE_CARRE_SELECTION / 2,
                        p.getX() - TAILLE_CARRE_SELECTION / 2,
                        p.getX() - TAILLE_CARRE_SELECTION / 2,
                        p.getX() + TAILLE_CARRE_SELECTION / 2
                };
                int[] y = {
                        p.getY() - TAILLE_CARRE_SELECTION / 2,
                        p.getY() - TAILLE_CARRE_SELECTION / 2,
                        p.getY() + TAILLE_CARRE_SELECTION / 2,
                        p.getY() + TAILLE_CARRE_SELECTION / 2
                };
                Polygon r = new Polygon(x, y, 4);
                rList.put(r, p);
                g.setColor(Color.GRAY);
                g.drawPolygon(r);
            }
        }
    }


    /**
     * Permet de selectioner la figure
     */
    public void selectionne() {
        selected = true;
    }

    /**
     * Permet de déselectioner la figure
     */
    public void deSelectionne() {
        selected = false;
    }

    /**
     * Permet de changer la couleur
     *
     * @param color la nouvelle couleur
     */
    public void changeCouleur(Color color) {
        couleur = color;
    }

    public List<Point> getPoints() {
        return tab_mem;
    }

    @Override
    public String toString() {
        return String.valueOf(tab_mem);
    }
}