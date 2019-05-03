package controleur;

import modele.DessinModele;
import modele.FigureColoree;
import modele.Quadrilatere;
import vue.VueDessin;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauChoix extends JPanel {

    private VueDessin     vdessin;
    private DessinModele  dmodele;
    private FigureColoree figureEnCours;
    private String[]      tabForme;

    public PanneauChoix(VueDessin vdessin) {
        this.vdessin = vdessin;
        tabForme = new String[]{"Rectangle", "Triangle"};
        JPanel j  = new JPanel();
        JPanel j2 = new JPanel();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JRadioButton      newFig    = new JRadioButton("Nouvelle figure");
        JRadioButton      mainLevee = new JRadioButton("Trace a main levee");
        JRadioButton      manip     = new JRadioButton("Manipulations");
        JComboBox<String> formes    = new JComboBox<>(tabForme);
        JComboBox<String> couleurs = new JComboBox<>(new String[]{
                "Noir",
                "Rouge",
                "Vert",
                "Bleu",
                "Jaune",
                "Gris",
                "Magenta",
                "Rose"
        });
        formes.setEnabled(false);
        ButtonGroup b = new ButtonGroup();
        b.add(newFig);
        b.add(mainLevee);
        b.add(manip);

        mainLevee.setSelected(true);
        newFig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formes.setEnabled(true);
            }
        });
        mainLevee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formes.setEnabled(false);
                //todo a completer pour le dessin
            }
        });
        manip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formes.setEnabled(false);
            }
        });

        formes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                figureEnCours = creeFigure(formes.getSelectedIndex());
            }
        });

        j.add(newFig);
        j.add(mainLevee);
        j.add(manip);
        this.add(j);
        j2.add(formes);
        j2.add(couleurs);
        this.add(j2);
    }

    private Color determineCouleur(int couleur) {
        Color res;
        switch (couleur) {
            case 0:
                res = Color.BLACK;
                break;
            case 1:
                res = Color.RED;
                break;
            case 2:
                res = Color.GREEN;
                break;
            case 3:
                res = Color.BLUE;
                break;
            case 4:
                res = Color.YELLOW;
                break;
            case 5:
                res = Color.GRAY;
                break;
            case 6:
                res = Color.MAGENTA;
                break;
            case 7:
                res = Color.PINK;
                break;
            default:
                res = Color.BLACK;
        }
        return res;
    }

    private FigureColoree creeFigure(int nb) {
        switch (tabForme[nb]) {
            case "Rectangle":
                return new Quadrilatere();
            default:
                return null;
        }
    }
}
