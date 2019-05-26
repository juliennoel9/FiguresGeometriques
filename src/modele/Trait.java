package modele;

import controleur.FabricantFigure;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.List;

public class Trait extends FigureColoree {

    /**
     * Constructeur vide de la classe Trait
     */
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
     * Methode permettant de retourner le nombre de clics d'un trait
     *
     * @return Nombre de cliques d'un trait
     */
    @Override
    public int nbClics() {
        return 2;
    }

    /**
     * Methode permettant de modifier les points du Trait
     * @param points Liste de points a ajouter
     */
    @Override
    public void modifierPoints(List<Point> points) {
        tab_mem = points;
    }

    /**
     * Methode permettant d'afficher une figure Trait
     * @param g Graphics
     */
    public void affiche(Graphics g) {
        g.setColor(this.couleur);
        g.drawLine(tab_mem.get(0).getX(), tab_mem.get(0).getY(), tab_mem.get(1).getX(), tab_mem.get(1).getY());
        super.affiche(g);
    }

    /**
     * Methode permettant de savoir si la souris (MouseEvent) se situe sur un Trait et retourne un booleen
     * De plus, si le trait est une figure Trait et non pas un trait d'un trace a main levee, la figure sera
     * desormais selectionnee
     * @param e
     * @return
     */
    @Override
    public boolean isInSelection(MouseEvent e) {
        int last_x = e.getX();
        int last_y = e.getY();

        if (!tab_mem.isEmpty()){
            Point              p1       = tab_mem.get(0);
            Point              p2       = tab_mem.get(1);

            Point vectP1P2 = new Point(p1.getX()-p2.getX(), p1.getY()-p2.getY());

            Point vectP1S = new Point(p1.getX()-last_x, p1.getY()-last_y);

            int distance = (int) Math.abs(p1.distance(p2));

            // Distance > 10 signifie que c'est une figure Trait, sinon c'est un trace a main levee
            if (distance>10){
                // On chercher a savoir si les deux vecteurs sont colineaires
                if (vectP1P2.getX()*vectP1S.getY() - vectP1P2.getY()*vectP1S.getX() >= -2000 && vectP1P2.getX()*vectP1S.getY() - vectP1P2.getY()*vectP1S.getX() <= 2000){
                    if (vectP1P2.getY()!=0){
                        // Puis on cherche le coefficient de proportionalite afin de selectionner le trait que lorsque la souris est dessus
                        // c'est a dire que les deux vecteurs sont confondus
                        if ((double)vectP1S.getY()/vectP1P2.getY()>=0 && (double)vectP1S.getY()/vectP1P2.getY()<=1) {
                            System.out.println((double)vectP1S.getY()/vectP1P2.getY());
                            selectionne();
                            return true;
                        }
                    }
                }
                return false;
            }else {
                // Ici, on regarde si le premier point du trait du trace a main leve se situe dans le cercle de rayon 20 autour de la souris
                if (Math.pow(p1.getX()-last_x,2)+Math.pow(p1.getY()-last_y,2)<=400){
                   return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * Methode permettant de retourner l'instance qui crée un Trait
     * @param dessinModele
     * @return
     */
    @Override
    public FabricantFigure getConstructeur(DessinModele dessinModele) {
        return new FabricantFigure(this, dessinModele);
    }
}
