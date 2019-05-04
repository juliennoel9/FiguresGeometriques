package vue;

import modele.DessinModele;
import modele.FigureColoree;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

public class VueDessin extends JPanel implements Observer {

    private DessinModele dessin;

    public VueDessin() {
        setVisible(true);
        setPreferredSize(new Dimension(100, 100));
    }

    @Override
    public void update(Observable o, Object arg) {
        dessin = (DessinModele) o;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (dessin != null) {
            for (FigureColoree figureColoree : dessin.getListFigureColore()) {
                g.setColor(figureColoree.getCouleur());
                figureColoree.affiche(g);
            }
        }
    }
}