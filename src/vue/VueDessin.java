package vue;

import controleur.FabricantCarre;
import controleur.FabricantFigure;
import controleur.ManipulateurFormes;
import controleur.TraceurForme;
import modele.DessinModele;
import modele.FigureColoree;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe representant une Vue Dessin
 */
public class VueDessin extends JPanel implements Observer {

    /**
     * Le dessinModele
     */
    private DessinModele dessin;

    /**
     * Manipulateur de forme de la vue
     */
    private ManipulateurFormes mf;

    /**
     * Constructeur ajoutant la taille
     */
    public VueDessin() {
        setVisible(true);
        setPreferredSize(new Dimension(100, 100));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLoweredSoftBevelBorder());
    }

    /**
     * Methode d'update de l'observer
     *
     * @param o   l'observable {@link DessinModele}
     * @param arg les args
     */
    @Override
    public void update(Observable o, Object arg) {
        dessin = (DessinModele) o;
        repaint();
    }

    /**
     * permet de dessiner les figures
     *
     * @param g le graphique
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (dessin != null) {
            dessin.drawFigures(g);
        }
    }

    public void createFigure(FigureColoree f) {
        FabricantFigure fa = f.getContructeur(dessin);
        addMouseListener(fa);
        if (fa.hasMotionListener()) {
            addMouseMotionListener((FabricantCarre) fa);
        }
    }

    public void enleverListeners() {
        for (MouseListener ml : getMouseListeners()) {
            removeMouseListener(ml);
        }
        for (MouseMotionListener mml : getMouseMotionListeners()) {
            removeMouseMotionListener(mml);
        }
    }

    public void ajoutManip(ManipulateurFormes manipulateurFormes) {
        addMouseListener(manipulateurFormes);
        addMouseMotionListener(manipulateurFormes);
    }

    public void ajoutTraceur(TraceurForme traceurForme) {
        addMouseMotionListener(traceurForme);
        addMouseListener(traceurForme);
    }

    public void openPDF() {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("ip.pdf");
                Desktop.getDesktop().open(myFile);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Aucune application pour ouvrir le PDF");
            }
            catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(this, "Fichier inexistant");
            }
        }
    }
}