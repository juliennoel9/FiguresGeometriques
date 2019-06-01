package modele;

import controleur.FabricantFigure;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.*;

/**
 * Classe representant une Figure Coloree
 */
public abstract class FigureColoree implements Serializable {

    /**
     * Taille des petit carrés
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
     * Getter de la figure
     *
     * @return la couleur  de la figure
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * Methode abstraite permettant de retourner le nombre de points de la figure
     *
     * @return le nombre de points
     */
    public abstract int nbPoints();

    /**
     * @return le nombre de click
     */
    public abstract int nbClics();

    /**
     * Permet de modifie les points
     *
     * @param points Liste de points a ajouter
     */
    public abstract void modifierPoints(List<Point> points);

    /**
     * Permet d'avoir un point par rapport a une repesentation de lui meme en polygon
     *
     * @param p le polygon repesentant le point
     * @return le point determiner par le polygon
     */
    public Point getPointFromPoly(Polygon p) {
        return rList.get(p);
    }

    /**
     * Methode permettant d'afficher les carres lorsqu'une figure est selectionnee
     *
     * @param g Graphics
     */
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

    /**
     * Permet de savoir si la souris est dans la figure
     *
     * @param e l'event
     * @return true si elle est dedans, false sinon
     */
    public abstract boolean isInSelection(MouseEvent e);

    /**
     * Permet d'avoir la class qui construira la figure
     *
     * @param dessinModele le dessin model
     * @return une classe avec des events souris
     */
    public abstract FabricantFigure getConstructeur(DessinModele dessinModele);

    /**
     * Permet de translater un point de la figure
     *
     * @param selected le point slelectioner
     * @param difX     de combien le bouger de x
     * @param difY     de combien le bouger de y
     */
    public void transforamtionFigure(Point selected, int difX, int difY) {
        selected.translater(difX, difY);
    }

    public boolean isSelected() {
        return selected;
    }

    /**
     * Si la figure est un trait a mains levee
     *
     * @return true si un trait a main levee aussi non false
     */
    public boolean isMainLevee() {
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(tab_mem);
    }
}