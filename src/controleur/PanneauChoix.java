package controleur;

import modele.DessinModele;
import vue.VueDessin;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauChoix extends JPanel {

    private VueDessin    vdessin;
    private DessinModele dmodele;

    public PanneauChoix(VueDessin vdessin) {
        this.vdessin = vdessin;
        setPreferredSize(new Dimension(50,150));
        JRadioButton      newFig    = new JRadioButton("Nouvelle figure");
        JRadioButton      mainLevee = new JRadioButton("Trace a main levee");
        JRadioButton      manip     = new JRadioButton("Manipulations");
        JComboBox<String> formes    = new JComboBox<>(new String[]{"Triangle", "Rectangle"});
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
        this.add(newFig);
        this.add(mainLevee);
        this.add(manip);
        this.add(formes);
        this.add(couleurs);
    }

    public Color determineCouleur(int couleur) {
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
}
