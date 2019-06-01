package main;

import controleur.PanneauChoix;
import vue.VueDessin;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Classe Principale qui lance l'application
 */
public class Fenetre extends JFrame {

    private VueDessin    vdessin;
    private PanneauChoix choix;

    public Fenetre(String title, int longeur, int largeur) {
        setTitle(title);
        setPreferredSize(new Dimension(longeur, largeur));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(500, 493));
        JScrollPane scrollPane = new JScrollPane(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
        );
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(
                        vdessin,
                        "Voulez vous sauvegarder avant de quitter ?",
                        "Voulez vous sauvegarder avant de quitter ?",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );

                if (confirmed == JOptionPane.YES_OPTION) {
                    choix.save();
                }
                else if (confirmed == JOptionPane.CANCEL_OPTION || confirmed == JOptionPane.CLOSED_OPTION) {
                    return;
                }
                dispose();
                System.exit(0);
            }
        });
        vdessin = new VueDessin();
        choix = new PanneauChoix(vdessin);
        vdessin.addMenuControler(choix);
        scrollPane.setViewportView(vdessin);
        add(scrollPane, BorderLayout.CENTER);
        add(choix, BorderLayout.NORTH);
        pack();
        setJMenuBar(choix.getMenuBar());
        setVisible(true);
    }

    public PanneauChoix getChoix() {
        return choix;
    }

    public static void main(String[] arguments) {
        Fenetre f = new Fenetre("Figures Géometriques", 1024, 500);
    }
}
