package herbin;

import java.awt.Color;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cpt.Pringles
 */
public class TraducteurLettre {

    char lettre;
    ArrayList<Integer> formes;
    Color couleur;

    public TraducteurLettre(char c) {
        this.lettre = c;
        this.formes = new ArrayList<Integer>();
        
        switch (c) {
            case 'a':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.DEMI);
                this.formes.add(Constantes.CARRE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(249,54,96);
                break;
            case 'b':
                this.formes.add(Constantes.CARRE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(121,26,34);
                break;
            case 'c':
                this.formes.add(Constantes.CARRE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(186,28,34);
                break;
            case 'd':
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(245,31,23);
                break;
            case 'e':
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(246,35,24);
                break;
            case 'f':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(245,42,25);
                break;
            case 'g':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(219,61,36);
                break;
            case 'h':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(249,89,29);
                break;
            case 'i':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(255,77,37);
                break;
            case 'j':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(254,122,40);
                break;
            case 'k':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(255,173,48);
                break;
            case 'l':
                this.formes.add(Constantes.TRIANGLE);
                this.couleur=new Color(245,122,40);
                break;
            case 'm':
                this.formes.add(Constantes.TRIANGLE);
                this.couleur=new Color(255,210,118);
                break;
            case 'n':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.DEMI);
                this.formes.add(Constantes.CARRE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(255,255,255);
                break;
            case 'o':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.DEMI);
                this.couleur=new Color(30,104,17);
                break;
            case 'p':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.DEMI);
                this.couleur=new Color(94,173,58);
                break;
            case 'q':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.DEMI);
                this.couleur=new Color(44,70,23);
                break;
            case 'r':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.DEMI);
                this.couleur=new Color(63,86,140);
                break;
            case 's':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.DEMI);
                this.couleur=new Color(37,60,66);
                break;
            case 't':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.DEMI);
                this.couleur=new Color(51,31,84);
                break;
            case 'u':
                this.formes.add(Constantes.DEMI);
                this.couleur=new Color(24,30,88);
                break;
            case 'v':
                this.formes.add(Constantes.TRIANGLE);
                this.formes.add(Constantes.DEMI);
                this.formes.add(Constantes.CARRE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(0,0,0);
                break;
            case 'w':
                this.formes.add(Constantes.DEMI);
                this.formes.add(Constantes.CARRE);
                this.couleur=new Color(32,1,58);
                break;
            case 'x':
                this.formes.add(Constantes.CARRE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(102,2,56);
                break;
            case 'y':
                this.formes.add(Constantes.CARRE);
                this.couleur=new Color(58,17,77);
                break;
            case 'z':
                this.formes.add(Constantes.DEMI);
                this.formes.add(Constantes.CARRE);
                this.formes.add(Constantes.CERCLE);
                this.couleur=new Color(12,6,40);
                break;
            default:
                break;
        }
    }

    public Color getCouleur(){
    	return this.couleur;
    }

    public ArrayList<Integer> getFormes() {
        return this.formes;
    }

    public int getNbFormes() {
        return this.formes.size();
    }
    
    public void enleverForme(int i) {
        this.formes.remove(i);
    }
    
    public char getLettre() {
        return this.lettre;
    }
}
