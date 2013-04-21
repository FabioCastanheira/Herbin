
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YPierru
 */
public class GenerationFormes {

    private Dimension dimBoite;
    //Liste des formes créées
    private ArrayList<JShapeDraw> historiqueFormes;
    private int largeurMax;
    private int hauteurMax;
    private int xBoite;
    private int yBoite;
   

    public GenerationFormes(){
        this.historiqueFormes=new ArrayList<JShapeDraw>();
    }

    public DrawPolygon generateTriangle(Color colorForme){
        //Color colorForme=this.randomColorForme();//Couleur de la forme
        int orientation = (int) (Math.random() * 2);//0 haut 1 bas
        //Le dessin de polygone prend des tableaux de coordonnées
        int[] posx= new int[3];//Tableau des positions abscisses
        int[] posy= new int[3];//Tableau des positions ordonnée
        DrawPolygon triangle;//Triangle final

        if(orientation==0){//=>TRIANGLE POINTE EN HAUT
            posx[0]=this.xBoite+Constantes.MARGE;
            posy[0]=this.yBoite+this.hauteurMax+Constantes.MARGE;

            posx[1]=this.xBoite+this.largeurMax+Constantes.MARGE;
            posy[1]=this.yBoite+this.hauteurMax+Constantes.MARGE;

            posx[2]=this.xBoite+(this.largeurMax/2)+Constantes.MARGE;
            posy[2]=this.yBoite+Constantes.MARGE;
        }
        else{//=>TRIANGLE POINTE EN BAS
            posx[0]=this.xBoite+Constantes.MARGE;
            posy[0]=this.yBoite+Constantes.MARGE;

            posx[1]=this.xBoite+this.largeurMax+Constantes.MARGE;
            posy[1]=this.yBoite+Constantes.MARGE;

            posx[2]=this.xBoite+(this.largeurMax/2)+Constantes.MARGE;
            posy[2]=this.yBoite+this.hauteurMax+Constantes.MARGE;
        }

        triangle=new DrawPolygon(colorForme,posx,posy,posx.length);

        //On ajoute la forme à l'historique
        this.historiqueFormes.add(triangle);
        return triangle;
        
    }


    public DrawArc generateDemi(Color colorForme) {
        int angleDebut = 0, ecart = 0;
        //Color colorForme = this.randomColorForme();

        DrawArc demi;
        int rayon = 0;
        Point position = null;
        Dimension dimForme = null;

        if (this.largeurMax < this.hauteurMax/2) {
            rayon = this.largeurMax;
            int orientation = (int) (Math.random() * 2);
            if (orientation == 0) {
                position = new Point(xBoite + Constantes.MARGE - rayon, yBoite + ((this.hauteurMax+Constantes.MARGE*2)/2) - rayon);
                angleDebut = -90;
                ecart = 180;
            } else {
                position = new Point(xBoite + Constantes.MARGE, yBoite + ((this.hauteurMax+Constantes.MARGE*2)/2) - rayon);
                angleDebut = -90;
                ecart = -180;
            }
        } else if (this.largeurMax <= this.hauteurMax) {
            rayon = this.hauteurMax/2;
            int orientation = (int) (Math.random() * 2);
            if (orientation == 0) {
                position = new Point(xBoite + ((this.largeurMax+Constantes.MARGE*2)/2) - rayon/2 - rayon, yBoite + Constantes.MARGE);
                angleDebut = -90;
                ecart = 180;
            } else {
                position = new Point(xBoite + ((this.largeurMax+Constantes.MARGE*2)/2) - rayon/2, yBoite + Constantes.MARGE);
                angleDebut = -90;
                ecart = -180;
            }
        } else if (this.hauteurMax < this.largeurMax/2) {
            rayon = this.hauteurMax;
            int orientation = (int) (Math.random() * 2);
            if (orientation == 0) {
                position = new Point(xBoite + ((this.largeurMax+Constantes.MARGE*2)/2) - rayon, yBoite + Constantes.MARGE);
                angleDebut = 0;
                ecart = 180;
            } else {
                position = new Point(xBoite + ((this.largeurMax+Constantes.MARGE*2)/2) - rayon, yBoite + Constantes.MARGE - rayon);
                angleDebut = 0;
                ecart = -180;
            }
        } else {
            rayon = this.largeurMax/2;
            int orientation = (int) (Math.random() * 2);
            if (orientation == 0) {
                position = new Point(xBoite + Constantes.MARGE, yBoite + ((this.hauteurMax+Constantes.MARGE*2)/2) - rayon/2);
                angleDebut = 0;
                ecart = 180;
            } else {
                position = new Point(xBoite + Constantes.MARGE, yBoite + ((this.hauteurMax+Constantes.MARGE*2)/2) - rayon/2 - rayon);
                angleDebut = 0;
                ecart = -180;
            }
        }
        dimForme = new Dimension(rayon*2, rayon*2);
        demi = new DrawArc(colorForme, position, new Dimension(dimForme.width, dimForme.height), angleDebut, ecart);


        this.historiqueFormes.add(demi);
        return demi;
    }

    public DrawRect generateCarre(Color colorForme){
        //Color colorForme = this.randomColorForme();
        DrawRect carre;

        Point position = new Point(this.xBoite+Constantes.MARGE,this.yBoite+Constantes.MARGE);
        
        int largeur = this.largeurMax;
        int hauteur = this.hauteurMax;

        Dimension dimForme = new Dimension(largeur, hauteur);
        carre=new DrawRect(colorForme, position, dimForme);

        this.historiqueFormes.add(carre);
        return carre;
    }

    public DrawCircle generateCercle(Color colorForme){
        //Color colorForme = this.randomColorForme();
        DrawCircle cercle;

        //Test de la dimension la plus petite
        int rayon;
        Point position = null;

        if (this.largeurMax < this.hauteurMax) {
            rayon = this.largeurMax;
            position = new Point(xBoite + Constantes.MARGE, yBoite + (this.hauteurMax - this.largeurMax)/2 +Constantes.MARGE);
        } else {
            rayon = this.hauteurMax;
            position = new Point(xBoite + (this.largeurMax - this.hauteurMax)/2 +Constantes.MARGE, yBoite + Constantes.MARGE);
        }

        Dimension dimForme = new Dimension(rayon,rayon);

        cercle=new DrawCircle(colorForme, position, dimForme);

        this.historiqueFormes.add(cercle);
        return cercle;
    }

    public void setDimension(Dimension dim, Point p) {
        this.dimBoite=dim;
        this.xBoite = p.x;
        this.yBoite = p.y;

        this.setBornes();
    }

    public void setBornes() {
        this.hauteurMax = (this.dimBoite.height)-(Constantes.MARGE*2);
        this.largeurMax = (this.dimBoite.width)-(Constantes.MARGE*2);
    }

    public ArrayList<JShapeDraw> getHisto() {

        return this.historiqueFormes;
    }

}
