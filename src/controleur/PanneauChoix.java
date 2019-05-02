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
    }

    public Color determineCouleur(int couleur){
        return null;
    }
}
