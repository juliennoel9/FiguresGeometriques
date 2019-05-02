package vue;

import modele.DessinModele;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueDessin extends JPanel implements Observer {
    private DessinModele dessin;

    public VueDessin(){

    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void paintComponent(Graphics g){

    }
}
