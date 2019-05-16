package modele;

import controleur.FabricantCarre;
import controleur.FabricantFigure;

import java.awt.Graphics;
import java.io.*;
import controleur.FabricantQuelconque;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Classe representant un Dessin Modele
 */
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
     * Permet d'avoir la figure a l'index donnée, si l'index n'est pas bon il retourne null
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
     * Init la liste a vide
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

    /**
     * @return la figure en cours
     */
    public FabricantFigure getFigureEnCours() {
        return figureEnCours;
    }

    /**
     * Permet de construire une figure
     *
     * @param fc la figure en cours
     */
    public void construit(FigureColoree fc) {
       figureEnCours = fc.getContructeur(this);
    }

    /**
     * Permet d'appeler l'observable
     */
    public void update() {
        setChanged();
        notifyObservers(this);
    }

    /**
     * Permet de stop la création
     */
    public void finFigure() {
        figureEnCours = null;
    }

    public void sauvegarderFigures(String destination, List<FigureColoree> listFigureColore) throws IOException {
        ObjectOutputStream d = new ObjectOutputStream(new FileOutputStream(destination));
        d.writeObject(listFigureColore);
        d.close();
    }

    public void chargerFigures(String source) throws IOException, ClassNotFoundException {
        ObjectInputStream di = new ObjectInputStream(new FileInputStream(source));
        List<FigureColoree> figureColoreeList = (List<FigureColoree>)(di.readObject());
        figureColoreeList.forEach(FigureColoree::deSelectionne);
        di.close();
        this.listFigureColore=figureColoreeList;
        update();
    }

    public void drawFigures(Graphics g) {
        for (FigureColoree figureColoree : listFigureColore) {
            g.setColor(figureColoree.getCouleur());
            figureColoree.affiche(g);
        }
    }
}