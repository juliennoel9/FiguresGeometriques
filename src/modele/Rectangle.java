package modele;

/**
 * Classe representant un Rectangle
 */
public class Rectangle extends Quadrilatere {

    public Rectangle() {

    }

    /**
     * Methode permettant de connaitre le nombre de points a cliquer necessaire a la construction du rectangle
     * @return Le nombre de cliques necessaire
     */
    @Override
    public int nbPoints() {
        return 2;
    }
}
