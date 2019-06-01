package modele;

import controleur.Stockage;
import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Classe representant un Dessin Modele
 */
public class DessinModele extends Observable {

    private List<FigureColoree> listFigureColore;

    public DessinModele() {
        initDessinModele();
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

    public void setListFigureColore(List<FigureColoree> listFigureColore) {
        this.listFigureColore = listFigureColore;
        update();
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
        Stockage.addNewSauvegarde(listFigureColore);
        listFigureColore.add(fc);
    }

    /**
     * Permet d'appeler l'observable
     */
    public void update() {
        setChanged();
        notifyObservers(this);
    }

    /**
     * Permet de sauvegarder un etat
     *
     * @param destination      le fichier de destination
     * @param listFigureColore la liste a sauvegarder
     * @throws IOException Si il y a une erreur
     */
    public void sauvegarderFigures(String destination, List<FigureColoree> listFigureColore) throws IOException {
        ObjectOutputStream d = new ObjectOutputStream(new FileOutputStream(destination));
        d.writeObject(listFigureColore);
        d.close();
    }

    /**
     * Permet de charger une figure
     *
     * @param source le fichier source
     * @throws IOException            Si le fichier a un probleme
     * @throws ClassNotFoundException Si la class n'est pas la bonne ou que le code a été changé
     */
    public void chargerFigures(String source) throws IOException, ClassNotFoundException {
        ObjectInputStream   di                = new ObjectInputStream(new FileInputStream(source));
        List<FigureColoree> figureColoreeList = (List<FigureColoree>) (di.readObject());
        figureColoreeList.forEach(FigureColoree::deSelectionne);
        di.close();
        this.listFigureColore = figureColoreeList;
        update();
    }

    /**
     * Permet de dessiner les figure
     *
     * @param g le graphic
     */
    public void drawFigures(Graphics g) {
        for (FigureColoree figureColoree : listFigureColore) {
            g.setColor(figureColoree.getCouleur());
            figureColoree.affiche(g);
        }
    }
}