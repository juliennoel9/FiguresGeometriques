package vue;

import controleur.*;
import modele.DessinModele;
import modele.FigureColoree;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe representant une Vue Dessin
 */
public class VueDessin extends JPanel implements Observer {

    public static final String IMAGES_RUBBER_SELECTED = "ressources/images/rubberSelected.png";

    /**
     * Le dessinModele
     */
    private DessinModele dessin;


    private PanneauChoix choix;

    /**
     * Constructeur ajoutant la taille
     */
    public VueDessin() {
        setVisible(true);
        setPreferredSize(new Dimension(1920, 1080));
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
        if (choix != null) {
            choix.look();
        }
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

    /**
     * Permet de crée les figure et d'ajouter le(s) listener(s)
     *
     * @param f la figure colorree
     */
    public void createFigure(FigureColoree f) {
        FabricantFigure fa = f.getConstructeur(dessin);
        addMouseListener(fa);
        if (fa.hasMotionListener()) {
            addMouseMotionListener((MouseMotionListener) fa);
        }
        setCursor(Cursor.getDefaultCursor());
    }

    /**
     * Permet d'enlever tout les listeners
     */
    public void enleverListeners() {
        for (MouseListener ml : getMouseListeners()) {
            removeMouseListener(ml);
        }
        for (MouseMotionListener mml : getMouseMotionListeners()) {
            removeMouseMotionListener(mml);
        }
    }

    /**
     * Petmet d'ajouter une manipulation
     *
     * @param manipulateurFormes le naipulateur de forme
     */
    public void ajoutManip(ManipulateurFormes manipulateurFormes) {
        setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        addMouseListener(manipulateurFormes);
        addMouseMotionListener(manipulateurFormes);
    }

    /**
     * Permet d'ajouter un traceur plus rapidement
     *
     * @param traceurForme le traceur
     */
    public void ajoutTraceur(TraceurForme traceurForme) {
        addMouseMotionListener(traceurForme);
        addMouseListener(traceurForme);
        addCursor("ressources/images/pencilTrace.png", new Point(6, 26));
    }

    public void ajoutGommmeur(Gommeur gommeur) {
        addMouseListener(gommeur);
        addMouseMotionListener(gommeur);
        addCursor(IMAGES_RUBBER_SELECTED, new Point(12, 24));
    }

    /**
     * Permet d'ouvir un PDF
     */
    public void openPDF() {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("ressources/pdf/Compte_rendu_IHM.pdf");
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

    public void toImage(File file) {
        String   name = file.getAbsolutePath();
        String   prot = "png";
        String[] n    = name.replace('.', '§').split("§");
        if (n.length > 0) {
            String ex = n[n.length - 1];
            if (ex.equalsIgnoreCase("png") || ex.equalsIgnoreCase("jpg") || ex.equalsIgnoreCase("jpeg")) {
                prot = ex;
            }
            else {
                name += ".png";
            }
        }
        else {
            name += ".png";
        }
        BufferedImage im = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        paintAll(im.getGraphics());
        try {
            file = new File(name);
            ImageIO.write(im, prot, file);
            JOptionPane.showMessageDialog(
                    this,
                    "La conversion est fini, votre image se trouve a la destination \n"
                    + file.getAbsolutePath()
            );
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Il y a eu une erreur lors de la converion ! ");

        }

    }

    public void addMenuControler(PanneauChoix choix) {
        this.choix = choix;
    }

    private void addCursor(String imagesPencilSelected, Point p) {
        Toolkit t1 = Toolkit.getDefaultToolkit();
        if (Files.exists(Paths.get(imagesPencilSelected))) {
            Image curs = t1.getImage(imagesPencilSelected);
            curs.flush();
            setCursor(t1.createCustomCursor(
                    curs,
                    p,
                    "Cursor"
            ));
        }
        else {
            setCursor(Cursor.getDefaultCursor());
        }
    }

}