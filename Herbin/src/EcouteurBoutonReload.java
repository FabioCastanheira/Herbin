
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ypierru
 */
public class EcouteurBoutonReload  implements ActionListener{

    private Peindre tableau;

    public EcouteurBoutonReload(Peindre p){
        this.tableau=p;
    }

    public void actionPerformed(ActionEvent ae) {
        this.tableau.reload();
    }
}
