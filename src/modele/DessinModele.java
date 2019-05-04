package modele;

import controleur.FabricantFigure;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class DessinModele extends Observable {

    private List<FigureColoree> listFigureColore;

    private FabricantFigure figureEnCours;

    public DessinModele() {
        initDessinModele();
    }

    /**
     * Permet de del un figure dans la list, si l'index n'est pas bon, rien ne se passe
     *
     * @param index index la figure a del
     */
    public void delFigureColoree(int index) {
        if (index > 0 && index < listFigureColore.size()) {
            listFigureColore.remove(index);
        }
    }

    /**
     * Permet de del un figure dans la list
     *
     * @param fg la figure a del
     */
    public void delFigureColoree(FigureColoree fg) {
        listFigureColore.remove(fg);
    }

    /**
     * @return la list de figure coloree
     */
    public List<FigureColoree> getListFigureColore() {
        return listFigureColore;
    }

    /**
     * permet d'avoir la figure a l'index donnée, si l'index n'est pas bon il retourne null
     *
     * @param index l'objet a get
     * @return la figure ou null
     */
    public FigureColoree getFigure(int index) {
        if (index > 0 && index < listFigureColore.size()) {
            return listFigureColore.get(index);
        }
        return null;
    }

    /**
     * init la list a vide
     */
    public void initDessinModele() {
        listFigureColore = new ArrayList<>();
    }

    /**
     * Permet d'ajouter la figure en param
     *
     * @param fc la figure
     */
    public void addFigureColore(FigureColoree fc) {
        listFigureColore.add(fc);
    }

    public FabricantFigure getFigureEnCours() {
        return figureEnCours;
    }

    public void construit(FigureColoree fc) {
        figureEnCours = new FabricantFigure(fc, this);
    }

    public void update() {
        setChanged();
        notifyObservers(this);
    }

    public void finFigure() {
        figureEnCours = null;
    }
}