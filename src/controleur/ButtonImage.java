package controleur;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import java.awt.MediaTracker;
import java.nio.file.Files;
import java.nio.file.Path;

public class ButtonImage extends JRadioButton {


    private String desactivated;
    private String actvated;


    /**
     * Permet de cree un bouton avec une image dynamique en icon
     *
     * @param text         le titre du bouto
     * @param desactivated le path de l'image desactiver
     * @param activated    le path de l'image activer
     */
    public ButtonImage(String text, String desactivated, String activated) {
        super(text, new ImageIcon(desactivated));
        this.actvated = activated;
        this.desactivated = desactivated;
    }

    public void setActvated() {
        setImageIcon(actvated);
        setSelected(true);
    }

    public void setDesactivated() {
        setImageIcon(desactivated);
        setSelected(false);
    }

    private void setImageIcon(String image) {
        ImageIcon ic = new ImageIcon(image);
        if (Files.exists(Path.of(image))) {
            if (ic.getImageLoadStatus() == MediaTracker.COMPLETE) {
                setIcon(ic);
            }
        }
        else {
            setIcon(null);
        }
        ic.getImage().flush();
    }
}