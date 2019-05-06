package modele;

/**
 * Classe representant un Point
 */
public class Point {

    /**
     * La valeur de x
     */
    private int x;

    /**
     * La valeur de y
     */
    private int y;

    /**
     * Constructeur de Point
     *
     * @param x le nombre en x
     * @param y le nombre en y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter du X
     *
     * @return la valeur de X
     */
    public int getX() {
        return x;
    }

    /**
     * Setter du X
     *
     * @param x nouvelle valeur
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter du Y
     *
     * @return la valeur de Y
     */
    public int getY() {
        return y;
    }

    /**
     * Setter du Y
     *
     * @param y nouvelle valeur
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Permet d'incremetner de dx le x
     *
     * @param dx a incrementé
     */

    public void incrementerX(int dx) {
        x += dx;
    }

    /**
     * Permet d'incremetner de dy le y
     *
     * @param dy a incrementé
     */
    public void incrementerY(int dy) {
        y += dy;
    }

    /**
     * Permet de translater le points avec les dx et dy données
     *
     * @param dx le nombre a incrementer ne X
     * @param dy le nombre a incrementer ne Y
     */
    public void translater(int dx, int dy) {
        incrementerX(dx);
        incrementerY(dy);
    }

    /**
     * Calcule la distance entre 2 points, utilise la formule : <br>
     * AB={sqrt{(x{B}-x{A})^2+(y{B}-y{A})^2}}.
     *
     * @param p le points B
     * @return un double qui corresponds a la distance entre les 2 points ou Double.MAX_VALUE;
     */
    public double distance(Point p) {
        if (p == null) {
            return Double.MAX_VALUE;
        }
        return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
    }

    /**
     * Methode toString permettant d'afficher un point
     * @return Affichage du point
     */
    @Override
    public String toString() {
        return "Point{" + "x=" + x
                + ", y=" + y
                + '}';
    }
}

