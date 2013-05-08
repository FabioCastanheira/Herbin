package herbin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JFileChooser;
import javax.swing.JFrame;



/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author ypierru
 */
public class Peindre implements java.io.Serializable{

    private JCanvas jc;
    private String stringToTranslate;
    private GenerationFormes randomGenerate;
    private ArrayList<JShapeDraw> histoFormes;
    private HFrame hFrame;
    private TreeSet<Boite> lb;

    public Peindre(JCanvas j, String s) {
        this.jc = j;
        this.stringToTranslate = s;
    }

    public JCanvas reload() {
        JShapeDraw forme = null;
        TreeSet<Boite> listeBoites;
        Dimension dimBoite, dimSousBoite = null;
        int widthOrHeight = 2;//0=> Widht; 1=>Height
        int nbSousBoites = 1;
        Point pointSousBoite = null;
        int wSousBoite, hSousBoite, caseColor;
        Color colorForme = null;
        //pour les couleurs des formes de remplissage
        ArrayList<Integer> listeCouleursDansBoite;
        String lettresDansBoite = "";
        //liste des formes à placer, car on doit choisir la couleur de la boîte à la fin
        ArrayList<JShapeDraw> listeFormesAPlacer;
        int rangLettre;
        int rangFormeAPlacer = 0;
        Color color = null;
        TraducteurLettre trad = null;
        MotDecompose mot = null;
        this.histoFormes = new ArrayList<JShapeDraw>();
        Boite b = new Boite(this.jc.getPreferredSize(), new Point(0, 0));

        //autant de boîtes que de lettres
        b.mosaique(stringToTranslate.length() - 1, b.getListBoites(), 100, 100);
        listeBoites = b.getListBoites();
        this.lb=listeBoites;
        this.randomGenerate = new GenerationFormes();

        //on décompose le mot en formes et couleurs
        mot = new MotDecompose(this.stringToTranslate);

        for (Boite box : listeBoites) {
            
            
            listeCouleursDansBoite = new ArrayList<Integer>();
            listeFormesAPlacer = new ArrayList<JShapeDraw>();
            lettresDansBoite = "";

            //on commence par choisir une couleurs pour les formes de remplissage, s'il y en a
            /*switch (caseColor) {
                case 0:
                    colorForme = this.generateColor(Constantes.NUANCE_JAUNE, Constantes.JAUNE);
                    break;

                case 1:
                    colorForme = this.generateColor(Constantes.NUANCE_VIOLET, Constantes.VIOLET);
                    break;

                case 2:
                    colorForme = this.generateColor(Constantes.NUANCE_ROUGE, Constantes.ROUGE);
                    break;

                case 3:
                    colorForme = this.generateColor(Constantes.NUANCE_VERT, Constantes.VERT);
                    break;

                case 4:
                    colorForme = Color.WHITE;
                    break;

                case 5:
                    colorForme = Color.BLACK;
                    break;

                case 6:
                    colorForme = this.generateColor(Constantes.NUANCE_ORANGE, Constantes.ORANGE);
                    break;

                case 7:
                    colorForme = this.generateColor(Constantes.NUANCE_BLEU, Constantes.BLEU);
                    break;

            }*/



            dimBoite = box.getDim();

            //Si on a une boite dont soit la largeur, soit la hauteur est inférieure à n
            if (dimBoite.getHeight() > 300 || dimBoite.getWidth() > 300) {
                //Si la boite est plus longue que haute
                if (dimBoite.getWidth() > dimBoite.getHeight()) {
                    widthOrHeight = 0;
                    if (dimBoite.getWidth() >= 1000) {
                        nbSousBoites = 4;
                    } else if (dimBoite.getWidth() >= 650) {
                        nbSousBoites = 3;
                    } else if (dimBoite.getWidth() >= 350) {
                        nbSousBoites = 2;
                    }
                } //Si la boite est plus haute que longue
                else {
                    widthOrHeight = 1;
                    if (dimBoite.getHeight() >= 650) {
                        nbSousBoites = 4;
                    } else if (dimBoite.getHeight() >= 500) {
                        nbSousBoites = 3;
                    } else if (dimBoite.getHeight() >= 350) {
                        nbSousBoites = 2;
                    }
                }
            }

            for (int i = 0; i < nbSousBoites; i++) {
                
                caseColor = (int) (Math.random() * 8);
                
                //on choisit une couleurs pour les formes de remplissage, s'il y en a
                switch (caseColor) {
                    case 0:
                        colorForme = this.generateColor(true, Constantes.NUANCE_JAUNE, Constantes.JAUNE);
                        break;

                    case 1:
                        colorForme = this.generateColor(true, Constantes.NUANCE_VIOLET, Constantes.VIOLET);
                        break;

                    case 2:
                        colorForme = this.generateColor(true, Constantes.NUANCE_ROUGE, Constantes.ROUGE);
                        break;

                    case 3:
                        colorForme = this.generateColor(true, Constantes.NUANCE_VERT, Constantes.VERT);
                        break;

                    case 4:
                        colorForme = Color.WHITE;
                        break;

                    case 5:
                        colorForme = Color.BLACK;
                        break;

                    case 6:
                        colorForme = this.generateColor(true, Constantes.NUANCE_ORANGE, Constantes.ORANGE);
                        break;

                    case 7:
                        colorForme = this.generateColor(true, Constantes.NUANCE_BLEU, Constantes.BLEU);
                        break;

                }
                
                switch (widthOrHeight) {
                    case 0://S'il y a besoin d'un redimensionnement par la largeur
                        wSousBoite = (int) (dimBoite.getWidth() / nbSousBoites);
                        hSousBoite = (int) dimBoite.getHeight();
                        dimSousBoite = new Dimension(wSousBoite, hSousBoite);
                        pointSousBoite = new Point(box.getAbscisse() + i * wSousBoite, box.getOrdonnee());
                        randomGenerate.setDimension(dimSousBoite, pointSousBoite);
                        break;

                    case 1://S'il y a besoin d'un redimensionnement par la hauteur
                        wSousBoite = (int) (dimBoite.getWidth());
                        hSousBoite = (int) (dimBoite.getHeight() / nbSousBoites);
                        dimSousBoite = new Dimension(wSousBoite, hSousBoite);
                        pointSousBoite = new Point(box.getAbscisse(), box.getOrdonnee() + i * hSousBoite);
                        randomGenerate.setDimension(dimSousBoite, pointSousBoite);
                        break;

                    case 2:
                        pointSousBoite = new Point(box.getAbscisse(), box.getOrdonnee());
                        dimSousBoite = dimBoite;
                        randomGenerate.setDimension(dimSousBoite, pointSousBoite);
                        break;
                }

                int caseForme;

                //s'il reste des lettres non placées
                if (mot.getNbLettres() > 0) {
                    
                    //on choisit une lettre au hasard
                    rangLettre = (int) (Math.random() * mot.getMot().size());
                    trad = mot.getMot().get(rangLettre);
                    
                    //on choisit une forme au hasard correspondant à cette lettre
                    rangFormeAPlacer = (int) (Math.random() * trad.getNbFormes());
                    color = trad.getCouleur();
                    caseForme = trad.formes.get(rangFormeAPlacer);
                    
                    //on enlève la forme qu'on a placée de cette lettre
                    trad.enleverForme(rangFormeAPlacer);
                    
                    //on stocke pour savoir quelles lettres contient la boîte
                    lettresDansBoite+=Character.toString(mot.getLettre(rangLettre));
                    
                    //s'il ne reste plus formes à placer pour cette lettre on la retire
                    if (trad.getNbFormes() == 0) {
                        mot.retirerLettre(rangLettre);
                    }
                }
                //sinon on remplit avec des formes aléatoires
                else {
                    caseForme = (int) (Math.random() * 4);
                    color = colorForme;
                    listeCouleursDansBoite.add(caseColor);
                }

                //on génère les formes
                switch (caseForme) {
                    case Constantes.TRIANGLE:
                        forme = randomGenerate.generateTriangle(color);
                        break;
                    case Constantes.DEMI:
                        forme = randomGenerate.generateDemi(color);
                        break;
                    case Constantes.CARRE:
                        forme = randomGenerate.generateCarre(color);
                        break;
                    case Constantes.CERCLE:
                        forme = randomGenerate.generateCercle(color);
                        break;
                    default:
                        forme = randomGenerate.generateTriangle(color);
                        break;
                }
                
                //on ajoute la forme à une liste, car on doit générer les formes
                //après les boîtes, mais la couelur de la boîte dépend des couleurs
                //de la forme
                listeFormesAPlacer.add(forme);
            }
            
            //on dessine la boîte
            this.jc.addShapeToDraw(new DrawRect(this.choisirCouleurBoite(lettresDansBoite, listeCouleursDansBoite),
                    new Point(box.getAbscisse(), box.getOrdonnee()),
                    new Dimension(box.getDim().width, box.getDim().height)));

            //on dessine les formes
            for (JShapeDraw formeAPlacer : listeFormesAPlacer) {
                this.jc.addShapeToDraw(formeAPlacer);
                this.histoFormes.add(formeAPlacer);
            }
        }
        //this.hFrame.addDraw(this.jc);
        return this.jc;
    }
    /*

    public void go(Dimension d) {
        //GUIPaint.paintOnFrame(this.jc, "EL HERBINATOR");
    	this.hFrame = new HFrame(d, this.jc);
    }*/
    /*
    public void destroyFrame(){
    	this.hFrame.destroy();
    }*/

    public Color generateColor(boolean nuance, int[] cst_nuance, int[] cst) {
        if(!nuance){
            return new Color(cst[0], cst[1], cst[2]);
        }else{
            return new Color(
                (int) (Math.random() * cst_nuance[0]) + cst[0],
                (int) (Math.random() * cst_nuance[1]) + cst[1],
                (int) (Math.random() * cst_nuance[2]) + cst[2]
            );
        }
    }

    //on ne doit pas choisir une couleur pour la boîte dans la même teinte qu'une de ses formes
    public Color choisirCouleurBoite(String s, ArrayList<Integer> l) {
        
        Color color = null;
        //0 rouge 1 vert 2 bleu 3 blanc 4 noir
        int teinte;
        Boolean[] contient = new Boolean[5];

        contient[0] = false;
        contient[1] = false;
        contient[2] = false;
        contient[3] = false;
        contient[4] = false;
        
        //on utilise un système de booléens : si une couleur est présente dans une boîte,
        //elle et ses nuances ne peuvent plus être utilisées pour colorier la boîte,
        //sachant qu'on ne peut avoir que 4 formes différentes dans une boîte,
        //et qu'il y a 5 nuances de couleurs disponibles pour la boîte
        if (s.contains("a")
                || s.contains("b")
                || s.contains("c")
                || s.contains("d")
                || s.contains("e")
                || s.contains("f")
                || s.contains("g")
                || s.contains("h")
                || s.contains("i")
                || s.contains("j")
                || s.contains("k")
                || s.contains("l")
                || s.contains("m")
                || s.contains("x")
                || l.contains(0)
                || l.contains(2)
                || l.contains(6)) {
            contient[0] = true;
        }
        if (s.contains("o")
                || s.contains("p")
                || s.contains("q")
                || s.contains("s")
                || l.contains(3)) {
            contient[1] = true;
        }
        if (s.contains("r")
                || s.contains("t")
                || s.contains("u")
                || s.contains("w")
                || s.contains("y")
                || s.contains("z")
                || l.contains(1)
                || l.contains(7)) {
            contient[2] = true;
        }
        if (s.contains("n")
                || l.contains(4)) {
            contient[3] = true;
        }
        if (s.contains("v")
                || l.contains(5)) {
            contient[4] = true;
        }
        
        //on choisit une nuance aléatoirement tant qu'elle est déjà présente dans la boîte
        do {
            teinte = (int) (Math.random() * 5);
            if (!contient[teinte]) {
                break;
            }
        } while (true);

        switch (teinte) {
            case 0:
                color = this.generateColor(false, null, Constantes.ROUGE);
                break;
            case 1:
                color = this.generateColor(false, null, Constantes.VERT);
                break;
            case 2:
                color = this.generateColor(false, null, Constantes.BLEU);
                break;
            case 3:
                color = Color.WHITE;
                break;
            case 4:
                color = Color.BLACK;
                break;
            default:
                break;
        }
        return color;
    }

    public ArrayList<JShapeDraw> getHisto() {

        return this.histoFormes;
    }

    public String getMot(){
        return this.stringToTranslate;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}