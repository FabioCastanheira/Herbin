
import java.util.Comparator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hle
 */
public class OrdreTailleBoite implements Comparator<Boite>{

    public int compare(Boite b1, Boite b2) {
        int aireB1 = b1.getDim().height*b1.getDim().width;
        int aireB2 = b2.getDim().height*b2.getDim().width;
        int ordre;
        if (aireB1 < aireB2 ){
            ordre= -1;
        }
        else if (aireB1 > aireB2){
            ordre= 1;
        }
        // à aires égales
        else {
            // à ordonnées égales
            if (b1.getOrdonnee()==b2.getOrdonnee()){
                // x1 < x2
                if (b1.getAbscisse()<b2.getAbscisse()){
                    ordre = -1;
                }
                // si x1 > x2
                else if (b1.getAbscisse() > b2.getAbscisse()){
                    ordre = 1;
                }
                else {
                    ordre = 0;
                }
            }
            else {
                // à abscisses égales

                    // si y1 < y2
                    if (b1.getOrdonnee() < b2.getOrdonnee()){
                        ordre = -1;
                    }

                    // sinon y1 >= y2
                    else if (b1.getOrdonnee() > b2.getOrdonnee()){
                        ordre = -1;
                    }
                    else {
                        ordre = 0;
                    }
            }
        }
        
        return ordre;
    }
}
