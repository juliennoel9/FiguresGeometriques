package controleur;

import modele.DessinModele;
import modele.FigureColoree;
import vue.VueDessin;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

public class Gommeur implements MouseListener, MouseMotionListener {

    private DessinModele dessinModele;

    private VueDessin vueDessin;

    public Gommeur(DessinModele dm, VueDessin vd) {
        this.dessinModele = dm;
        this.vueDessin = vd;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Stockage.addNewSauvegarde(dessinModele.getListFigureColore());
        this.affiche(this.vueDessin.getGraphics(), e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.affiche(this.vueDessin.getGraphics(), e.getX(), e.getY());
        Iterator<FigureColoree> fgI = this.dessinModele.getListFigureColore().iterator();

        while (fgI.hasNext()) {
            FigureColoree figureColoree = fgI.next();
            if (figureColoree.isInSelection(e)) {
                if (!figureColoree.isMainLevee()) {
                    Stockage.addNewSauvegarde(dessinModele.getListFigureColore());
                }
                fgI.remove();
                this.dessinModele.update();
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.affiche(this.vueDessin.getGraphics(), e.getX(), e.getY());
    }

    public void affiche(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.fillOval(x - 15, y - 15, 30, 30);
        dessinModele.update();
    }
}
