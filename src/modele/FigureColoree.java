package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class FigureColoree {

    /**
     * Taille des petit carée
     */
    private final static int TAILLE_CARRE_SELECTION = 5;

    /**
     * Couleur de la figure
     */
    protected Color couleur;

    /**
     * Point de la figure
     */
    protected List<Point> tab_mem;

    /**
     * Pour savoir si la figure est selectioner
     */
    private boolean selected;

    public FigureColoree() {
        this.selected = false;
        this.tab_mem = new ArrayList<>();
        this.couleur = Color.BLACK;
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

    /**
     * Permet d'afficher les carré si la figure est selectionner
     *
     * @param g le graphique
     */
    public void affiche(Graphics g) {

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
}