package modele;

import controleur.FabricantFigure;
import controleur.FabricantQuelconque;

public class PolygoneQuelconque extends Polygone {

    /**
     * Methode abstraite permettant de retourner le nombre de points de la figure
     *
     * @return le nombre de points
     */
    @Override
    public int nbPoints() {
        return 0;
    }

    /**
     * @return le nombre de click
     */
    @Override
    public int nbClics() {
        return 0;
    }

    @Override
    public FabricantFigure getConstructeur(DessinModele dessinModele) {
        return new FabricantQuelconque(this, dessinModele);
    }
}
