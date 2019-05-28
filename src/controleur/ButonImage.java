package controleur;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import java.awt.MediaTracker;
import java.nio.file.Files;
import java.nio.file.Path;

public class ButonImage extends JRadioButton {


    private String desactivated;
    private String actvated;


    /**
     * Permet de cree un bouton avec une image dynamique en icon
     *
     * @param text         le titre du bouto
     * @param desactivated le path de l'image desactiver
     * @param activated    le path de l'image activer
     */
    public ButonImage(String text, String desactivated, String activated) {
        super(text, new ImageIcon(desactivated));
        this.actvated = activated;
        this.desactivated = desactivated;
    }

    public void setActvated() {
        ImageIcon ic = new ImageIcon(actvated);
        if (Files.exists(Path.of(actvated))) {
            if (ic.getImageLoadStatus() == MediaTracker.COMPLETE) {
                setIcon(ic);
            }
        }
        else {
            setIcon(null);
        }
        ic.getImage().flush();
        setSelected(true);
    }

    public void setDesactivated() {
        ImageIcon ic = new ImageIcon(desactivated);
        if (Files.exists(Path.of(desactivated))) {
            if (ic.getImageLoadStatus() == MediaTracker.COMPLETE) {
                setIcon(ic);
            }
        }
        else {
            setIcon(null);
        }
        ic.getImage().flush();
        setSelected(false);
    }
}
