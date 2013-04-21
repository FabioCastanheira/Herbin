
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Cpt.Pringles
 */
public class MotDecompose {
    
    private ArrayList<TraducteurLettre> mot;

    public MotDecompose(String s) {
        mot = new ArrayList<TraducteurLettre>();
        
        for (int i = 0; i < s.length(); i++) {
            mot.add(new TraducteurLettre(s.charAt(i)));
        }
    }

    public ArrayList<TraducteurLettre> getMot() {
        return this.mot;
    }
    
    public int getNbLettres() {
        return this.mot.size();
    }
    
    public void retirerLettre(int i) {
        this.mot.remove(i);
    }
    
    public char getLettre(int i) {
        return mot.get(i).getLettre();
    }
}
