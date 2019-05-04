package controleur;

import modele.*;
import vue.VueDessin;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PanneauChoix extends JPanel {

    private VueDessin         vdessin;
    private DessinModele      dmodele;
    private FigureColoree     figureEnCours;
    private String[]          tabForme;
    private Color             coulPerso;
    private JComboBox<String> formes;
    private Color             colorSelected;

    public PanneauChoix(VueDessin vdessin) {
        this.vdessin = vdessin;
        dmodele = new DessinModele();
        dmodele.addObserver(vdessin);
        tabForme = new String[]{"Rectangle", "Triangle", "Quadrilatere"};
        JPanel j  = new JPanel();
        JPanel j2 = new JPanel();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JRadioButton newFig    = new JRadioButton("Nouvelle figure");
        JRadioButton mainLevee = new JRadioButton("Trace a main levee");
        JRadioButton manip     = new JRadioButton("Manipulations");
        formes = new JComboBox<>(tabForme);
        JComboBox<String> couleurs = new JComboBox<>(new String[]{
                "Noir",
                "Rouge",
                "Vert",
                "Bleu",
                "Jaune",
                "Gris",
                "Magenta",
                "Rose",
                "Personnaliser"
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
                supFigure();
                formes.setSelectedIndex(formes.getSelectedIndex());
            }
        });
        mainLevee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formes.setEnabled(false);
                supFigure();
                //todo a completer pour le dessin
            }
        });
        manip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formes.setEnabled(false);
                supFigure();
            }
        });
        couleurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (couleurs.getSelectedIndex() == 8) {
                    Color r = new Color(rand(), rand(), rand(), 255);
                    coulPerso = JColorChooser.showDialog(vdessin,
                            "Choisissez votre couleur ! ", r
                    );
                }
                colorSelected = determineCouleur(couleurs.getSelectedIndex());
            }

            private int rand() {
                return new Random().nextInt(255);
            }
        });
        formes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vdessin.getMouseListeners().length != 0) {
                    vdessin.removeMouseListener(vdessin.getMouseListeners()[0]);
                }
                figureEnCours = creeFigure(formes.getSelectedIndex());
                figureEnCours.changeCouleur(determineCouleur(couleurs.getSelectedIndex()));
                dmodele.construit(figureEnCours);
                vdessin.addMouseListener(dmodele.getFigureEnCours());
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

    public Color getCoulPerso() {
        return coulPerso;
    }

    public void reCreateObject() {
        formes.setSelectedIndex(formes.getSelectedIndex());
    }

    public Color getCouleur() {
        return colorSelected;
    }

    private void supFigure() {
        if (figureEnCours != null) {
            figureEnCours = null;
            dmodele.finFigure();
            vdessin.addMouseListener(null);
        }
    }

    private Color determineCouleur(int couleur) {
        Color res;
        switch (couleur) {
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
            case 8:
                res = coulPerso;
                break;
            default:
                res = Color.BLACK;
        }
        return res;
    }

    private FigureColoree creeFigure(int nb) {
        switch (tabForme[nb]) {
            case "Rectangle":
                return new Rectangle();
            case "Triangle":
                return new Triangle();
            case "Quadrilatere":
                return new Quadrilatere();
            default:
                return null;
        }
    }
}