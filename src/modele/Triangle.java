package modele;

/**
 * Classe representant un Triangle
 */
public class Triangle extends Polygone {

    /**
     * Methode permettant de connaitre le nombre de points a cliquer necessaire a la construction du triangle
     * @return Le nombre de cliques necessaire
     */
    @Override
    public int nbPoints() {
        return 3;
    }
}
