package controleur;

import modele.DessinModele;
import modele.FigureColoree;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

public class Gommeur implements MouseListener, MouseMotionListener {

    private DessinModele dessinModele;

    public Gommeur(DessinModele dm) {
        this.dessinModele = dm;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Stockage.addNewSauvegarde(dessinModele.getListFigureColore());
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

    }
}
