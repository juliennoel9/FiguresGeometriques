package controleur;

import modele.DessinModele;
import vue.VueDessin;

import javax.swing.*;
import java.awt.*;

public class PanneauChoix extends JPanel {
    private VueDessin vdessin;
    private DessinModele dmodele;

    public PanneauChoix(VueDessin vdessin){
        this.vdessin=vdessin;
        JRadioButton newFig = new JRadioButton("Nouvelle figure");
        JRadioButton mainLevee = new JRadioButton("Trace a main levee");
        JRadioButton manip = new JRadioButton("Manipulations");
        this.add(newFig);
        this.add(mainLevee);
        this.add(manip);
        JComboBox formes = new JComboBox(new String[]{"Triangle", "Rectangle"});
        JComboBox couleurs = new JComboBox(new String[] {"Noir","Rouge","Vert","Bleu","Jaune","Gris","Magenta","Rose"});
        this.add(couleurs);
        this.add(formes);
    }

    public Color determineCouleur(int couleur){
        return null;
    }
}
