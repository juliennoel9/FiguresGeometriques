package controleur;

import modele.*;
import vue.VueDessin;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
     * Bouttons d'effacement
     */
    private JMenuItem effacerTout, effacerSelection;

    /**
     * Bouton de controle de sauvegarde
     */
    private JMenuItem sauvegarder, charger;

    /**
     * Manipulateur de Formes
     */
    private ManipulateurFormes manipulateurFormes;

    private Menu menu;

    public PanneauChoix(VueDessin vdessin) {
        this.vdessin = vdessin;
        this.colorSelected = Color.BLACK;
        dmodele = new DessinModele();
        dmodele.addObserver(vdessin);
        tabForme = new String[]{"Rectangle", "Triangle", "Quadrilatere", "Cercle", "Carre", "Trait", "Polygone"};
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

        effacerSelection = new JMenuItem("Effacer");
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

        effacerTout = new JMenuItem("Effacer Tout");
        effacerTout.setEnabled(false);

        sauvegarder = new JMenuItem("Enregister sous ...");
        sauvegarder.setEnabled(true);

        charger = new JMenuItem("Ouvrir ...");
        charger.setEnabled(true);

        charger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileFilter(new FileNameExtensionFilter("IHM extension", "ihm"));
                    int a = fc.showDialog(vdessin, "Set File directory");
                    if (a == 0) {
                        String s = fc.getSelectedFile().getAbsolutePath();
                        dmodele.chargerFigures(s.endsWith(".ihm") ? s : s + ".ihm");
                    }
                }
                catch (IOException | ClassNotFoundException e1) {
                    JOptionPane.showMessageDialog(vdessin, "Aucun fichier de sauvegarde disponible");
                }
            }
        });

        sauvegarder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileFilter(new FileNameExtensionFilter("IHM extension", "ihm"));
                    int a = fc.showDialog(vdessin, "Get File directory");
                    if (a == 0) {
                        String s = fc.getSelectedFile().getAbsolutePath();
                        dmodele.sauvegarderFigures(s.endsWith(".ihm") ? s : s + ".ihm", dmodele.getListFigureColore());
                    }
                }
                catch (IOException e1) {
                    JOptionPane.showMessageDialog(vdessin, "Erreur lors de la sauvegarde");
                }
            }
        });

        effacerTout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dmodele.getListFigureColore().clear();
                dmodele.update();
            }
        });

        newFig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formes.setEnabled(true);
                supFigure();
                formes.setSelectedIndex(formes.getSelectedIndex());
                effacerSelection.setEnabled(false);
                effacerTout.setEnabled(false);
            }
        });

        //todo rework
        mainLevee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formes.setEnabled(false);
                supFigure();
                effacerSelection.setEnabled(false);
                effacerTout.setEnabled(false);
                TraceurForme traceurForme = new TraceurForme(dmodele);
                vdessin.ajoutTraceur(traceurForme);
            }
        });


        //todo Rework
        manip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formes.setEnabled(false);
                effacerSelection.setEnabled(true);
                effacerTout.setEnabled(true);
                supFigure();
                manipulateurFormes = new ManipulateurFormes(dmodele);
                vdessin.ajoutManip(manipulateurFormes);
            }
        });


        //todo rework
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


        //todo rework
        formes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vdessin.enleverListeners();
                figureEnCours = creeFigure(formes.getSelectedIndex());
                figureEnCours.changeCouleur(determineCouleur(couleurs.getSelectedIndex()));
                vdessin.createFigure(figureEnCours);
            }
        });
        j.add(newFig);
        j.add(mainLevee);
        j.add(manip);
        this.add(j);
        j2.add(effacerTout);
        j2.add(effacerSelection);
        j2.add(formes);
        j2.add(couleurs);
        j2.add(sauvegarder);
        j2.add(charger);
        this.add(j2);
    }

    public Menu getMenuBar() {
        if (menu == null) {
            initMenu();
        }
        return menu;
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

    private void initMenu() {
        menu = new Menu();
        JMenu file = new JMenu("Fichier");
        file.add(sauvegarder);
        file.add(charger);
        menu.ajouterSousMenu(file);

        JMenu suprimer = new JMenu("Supression");
        suprimer.add(effacerSelection);
        suprimer.add(effacerTout);
        suprimer.add(suprimer);
        menu.ajouterSousMenu(suprimer);

        JMenu     aide    = new JMenu("Aide");
        JMenuItem aideBut = new JMenuItem("Aide");
        aide.addActionListener(e -> {
            //page d'aide
        });
        aide.add(aideBut);
        menu.ajouterSousMenu(aide);


    }


    /**
     * Permet de suprimer la figure plus simplement
     */
    private void supFigure() {
        if (figureEnCours != null) {
            figureEnCours = null;
            //  dmodele.finFigure();
        }
        vdessin.enleverListeners();
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
            case "Trait":
                return new Trait();
            case "Polygone":
                return new PolygoneQuelconque();
            default:
                return null;
        }
    }
}