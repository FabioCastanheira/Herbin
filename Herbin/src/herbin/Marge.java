package herbin;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hle
 */
public class Marge {
    private int top,right, bottom, left;

    public Marge(int marge) {
        this.top = marge;
        this.right = marge;
        this.bottom = marge;
        this.left = marge;
    }

    public Marge (int verticale, int horizontale) {
        this.top = verticale;
        this.bottom = verticale;
        this.right = horizontale;
        this.left = horizontale;
    }

    public Marge (int haut, int droite, int bas, int gauche) {
        this.top = haut;
        this.right = droite;
        this.bottom = bas;
        this.left = gauche;
    }

    public int getBottom() {
        return bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    

}
