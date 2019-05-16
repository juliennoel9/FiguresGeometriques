package controleur;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.event.KeyEvent;

public class Menu extends JMenuBar {

    /**
     * Creates a new tool bar; orientation defaults to <code>HORIZONTAL</code>.
     */
    public Menu() {
        setVisible(true);
    }

    public void ajouterSousMenu(JMenu menu) {
        add(menu);
    }
}
