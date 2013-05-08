package herbin;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ypierru
 */
public class MonThread extends Thread {

    private Peindre tableau;

    public MonThread(Peindre p){
        this.tableau=p;
    }

    @Override
    public void run(){
        while(super.isAlive()){
            System.out.println("JE SUIS LA");
                this.tableau.reload();
            try {
                sleep(15000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MonThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}