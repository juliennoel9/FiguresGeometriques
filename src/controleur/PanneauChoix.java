package controleur;

import modele.*;
import vue.VueDessin;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

/**
 * Classe representant un Panneau Choix
 */
public class PanneauChoix extends JPanel {

    /**
     * La vue dessin principale
     */
    private VueDessin vdessin;

    /**
     * Le dessin Modele
     */
    private DessinModele dmodele;

    /**
     * Le figure en cours
     */
    private FigureColoree figureEnCours;

    /**
     * Tableau des string des formes
     */
    private String[] tabForme;

    /**
     * Couleur perso
     */
    private Color coulPerso;

    /**
     * Combo box des formes
     */
    private JComboBox<String> formes;

    /**
     * Couleur en cours
     */
    private Color colorSelected;

    /**
     * Manipulateur de Formes
     */
    private ManipulateurFormes manipulateurFormes;

    private JButton effacerSelection;

    /**
     * Permet de crée un paneau choix avec tout les boutons / box
     *
     * @param vdessin la vue dessin principale
     */
    public PanneauChoix(VueDessin vdessin) {
        this.vdessin = vdessin;
        this.colorSelected = Color.BLACK;
        dmodele = new DessinModele();
        dmodele.addObserver(vdessin);
        tabForme = new String[]{"Rectangle", "Triangle", "Quadrilatere", "Cercle", "Carre"};
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

        effacerSelection = new JButton("Effacer");
        effacerSelection.setEnabled(false);

        effacerSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manipulateurFormes != null) {
                    dmodele.delFigureColoree(manipulateurFormes.figureSelection());
                    dmodele.update();
                }
            }
        });

        mainLevee.setSelected(true);
        newFig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formes.setEnabled(true);
                supFigure();
                formes.setSelectedIndex(formes.getSelectedIndex());
                effacerSelection.setEnabled(false);
            }
        });
        mainLevee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formes.setEnabled(false);
                supFigure();
                effacerSelection.setEnabled(false);
                //todo a completer pour le dessin
            }
        });
        manip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formes.setEnabled(false);
                effacerSelection.setEnabled(true);
                supFigure();
                manipulateurFormes = new ManipulateurFormes(dmodele);
                vdessin.addMouseListener(manipulateurFormes);
                vdessin.addMouseMotionListener(manipulateurFormes);
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
                if (manipulateurFormes != null) {
                    FigureColoree c = manipulateurFormes.figureSelection();
                    if (c != null) {
                        c.changeCouleur(colorSelected);
                        dmodele.update();
                    }
                }
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
                if (figureEnCours instanceof Carre) {
                    vdessin.addMouseMotionListener((FabricantCarre) dmodele.getFigureEnCours());
                }
            }
        });

        j.add(newFig);
        j.add(mainLevee);
        j.add(manip);
        this.add(j);
        j2.add(effacerSelection);
        j2.add(formes);
        j2.add(couleurs);
        this.add(j2);
    }


    /**
     * Permet de reselectioner l'objet a crée
     */
    public void reCreateObject() {
        formes.setSelectedIndex(formes.getSelectedIndex());
    }

    /**
     * @return la couleur selectoner
     */
    public Color getCouleur() {
        return colorSelected;
    }

    /**
     * Permet de suprimer la figure plus simplement
     */
    private void supFigure() {
        if (figureEnCours != null) {
            figureEnCours = null;
            dmodele.finFigure();
        }
        for (MouseListener ml : vdessin.getMouseListeners()) {
            vdessin.removeMouseListener(ml);
        }
        for (MouseMotionListener mml : vdessin.getMouseMotionListeners()) {
            vdessin.removeMouseMotionListener(mml);
        }
        if (manipulateurFormes != null) {
            var t = manipulateurFormes.figureSelection();
            if (t != null) {
                manipulateurFormes.figureSelection().deSelectionne();
            }
            manipulateurFormes = null;
        }
        dmodele.update();
    }

    /**
     * Permet de savoir rapidement la couleur
     *
     * @param couleur la selection de la couleur
     * @return la couleur
     */
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

    /**
     * Permet de crée rapidement la figure
     *
     * @param nb la selection de la forme
     * @return La figure corespondant a la figure
     */
    private FigureColoree creeFigure(int nb) {
        switch (tabForme[nb]) {
            case "Rectangle":
                return new Rectangle();
            case "Triangle":
                return new Triangle();
            case "Quadrilatere":
                return new Quadrilatere();
            case "Carre":
                return new Carre();
            case "Cercle":
                return new Cercle();
            default:
                return null;
        }
    }
}