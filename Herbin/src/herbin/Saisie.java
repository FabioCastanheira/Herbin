package herbin;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Cpt.Pringles
 */
public class Saisie {

    private JFrame fenetre;
    private JLabel labelLargeur;
    private JTextField champLargeur;
    private JLabel labelHauteur;
    private JTextField champHauteur;
    private JLabel labelMot;
    private JTextField champMot;
    private JButton boutonQuitter;
    private JButton boutonGenerer;
    private Herbin herbin;
    private HProperties hProp;
    private HFrame herbinF;

    
    public Saisie(HFrame f) {

    	this.herbinF=f;
        this.fenetre = new JFrame("Commandes d'Herbinator-3000");

        this.labelLargeur = new JLabel("Largeur (600-1200)");
        this.champLargeur = new JTextField(4);
        this.labelHauteur = new JLabel("Hauteur (400-800)");
        this.champHauteur = new JTextField(3);
        this.labelMot = new JLabel("Mot à traduire (3-10 lettres minuscules)");
        this.champMot = new JTextField(10);

        this.boutonQuitter = new JButton("Quitter");
        this.boutonGenerer = new JButton("Générer");
        this.boutonGenerer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) { 
                if (checkChamps()) {
                	fenetre.setVisible(false);
                    hProp = new HProperties(Integer.parseInt(champLargeur.getText()), Integer.parseInt(champHauteur.getText()), champMot.getText());
                    herbinF.addDraw(hProp);
                    destroy();
                }
            }
        });

        this.fenetre.setLayout(new GridLayout(4, 2));

        this.fenetre.add(labelLargeur);
        this.fenetre.add(champLargeur);
        this.fenetre.add(labelHauteur);
        this.fenetre.add(champHauteur);
        this.fenetre.add(labelMot);
        this.fenetre.add(champMot);
        this.fenetre.add(boutonQuitter);
        this.fenetre.add(boutonGenerer);

        this.fenetre.pack();
        this.fenetre.setVisible(true);
    }
    
    public boolean checkChamps() {
        boolean largeurOk=false;
        boolean hauteurOk=false;
        boolean motOk;
        int largeur;
        int hauteur;

        try {
            largeur = Integer.parseInt(this.champLargeur.getText());

            if (largeur >= 600 && largeur <= 1200) {
                largeurOk = true;
            } else {
            	JOptionPane.showMessageDialog(null,"alert");
            }
        } catch (NumberFormatException e) {
        	JOptionPane.showMessageDialog(null,"alert");
        }

        try {
            hauteur = Integer.parseInt(this.champHauteur.getText());

            if (hauteur >= 400 && hauteur <= 800) {
                hauteurOk = true;
            } else {
            	JOptionPane.showMessageDialog(null,"alert");
            }
        } catch (NumberFormatException e) {
        	JOptionPane.showMessageDialog(null,"alert");
        }

        motOk = this.champMot.getText().matches("[a-z]+") && this.champMot.getText().length() >= 3 && this.champMot.getText().length() <= 10;

        return largeurOk && hauteurOk && motOk;
    }
    
    public HProperties gethProp(){
    	return this.hProp;
    }
    
    public void destroy(){
    	this.fenetre.dispose();
    }
   
    
    public JFrame getFrame(){
    	return this.fenetre;
    }
    
    public JButton getButton(){
    	return this.boutonGenerer;
    }
}
