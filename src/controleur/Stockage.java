package controleur;

import modele.FigureColoree;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe avec que des attributs static a pour but de pouvoir stocker depuis n'importe qu'elle classe sans avoir de restriction d'instance<br>
 * Car le undo et redo doivent etre accessible partout;
 */
public class Stockage {

    /**
     * La liste des actions a faire
     */
    private static List<Sauvegarde> undo = new ArrayList<>();

    /**
     * La liste des action a refaire
     */
    private static List<Sauvegarde> redo = new ArrayList<>();

    /**
     * Permet d'ajouter une sauvegarde a l'undo, une copie de la liste mis en parametre est faite dans {@link Sauvegarde#Sauvegarde(List)}
     *
     * @param before la liste a socker
     * @see Sauvegarde
     */
    public static void addNewSauvegarde(List<FigureColoree> before) {
        Sauvegarde s = new Sauvegarde(before);
        undo.add(s);
    }


    /**
     * (Inverse de retriveBefore) <br>
     *
     * Permet d'avoir le undo a demander, suprime directement l'element de la liste et ajoute a la liste en parametre dans les redo
     *
     * @param fg la liste a save
     * @return le dernier undo ajouter
     */
    public static List<FigureColoree> retrieveLast(List<FigureColoree> fg) {
        redo.add(new Sauvegarde(fg));
        if (!undo.isEmpty()) {
            return undo.remove(undo.size() - 1).getLastThings();
        }

        return fg;

    }

    /**
     * (Inverse de retrieveLast) <br>
     *
     * Permet d'avoir le redo a demander, suprime directement l'element de la liste et ajoute a la liste en parametre dans les undos
     *
     * @param fg la liste a save
     * @return le dernier redo ajouter
     */
    public static List<FigureColoree> retriveBefore(List<FigureColoree> fg) {
        undo.add(new Sauvegarde(fg));
        if (!redo.isEmpty()) {
            return redo.remove(redo.size() - 1).getLastThings();
        }

        return fg;
    }

    /**
     * Savoir si il y a encore un redo a faire
     *
     * @return un boolean true si il n'y a plus de redo a faire
     */
    public static boolean redoEmpty() {
        return redo.isEmpty();
    }

    /**
     * Savoir si il y a encore un undo a faire
     *
     * @return un boolean true si il n'y a plus d'undo a faire
     */
    public static boolean undoEmpty() {
        return undo.isEmpty();
    }


    /**
     * Classe interne qui permet de sauvegarder une liste
     */
    private static class Sauvegarde {

        /**
         * La liste de sauvegarde
         */
        private List<FigureColoree> lastThings;

        /**
         * Cree une copie de la liste, pour eviter tout probleme mémoire
         *
         * @param lastThings la liste
         */
        public Sauvegarde(List<FigureColoree> lastThings) {
            this.lastThings = new ArrayList<>(lastThings);
        }

        /**
         * Permet d'avoir la liste de figure colorée
         *
         * @return la liste de figure colorée
         */
        public List<FigureColoree> getLastThings() {
            return lastThings;
        }
    }
}
