
import java.awt.Dimension;

import javax.swing.JButton;

public class Herbin {

    private String mot;
    private JCanvas jc;
    private Dimension sizeHerbin;
    private MonThread thread;
    private MouseEvents mouseE;
    private Peindre tableau;
    private boolean firstLaunch;
    private boolean mouse;
    private boolean threadUsing;

    public Herbin() {
        this.jc = new JCanvas();
    }

    public void herbinGenerator(String s, int w, int h, boolean reload, boolean mouse, boolean threadUsing) {
        this.mot = s;
        this.sizeHerbin = new Dimension(w, h);
        this.jc.setPreferredSize(this.sizeHerbin);
        this.tableau = new Peindre(this.jc, this.mot);

        if (reload) {
            this.confButtonReload();
        }

        this.tableau.go();
        this.tableau.reload();

        if (mouse) {
            this.confMouse();
        }

        if (threadUsing) {
            this.confThread();
        }
    }

    public void herbinGenerator(boolean mouse, boolean threadUsing) {
        Saisie saisie = new Saisie(this);

        this.firstLaunch = true;

        this.mouse = mouse;
        this.threadUsing = threadUsing;
    }

    public void confButtonReload() {
        JButton buttonReload = new JButton("Reload");
        this.jc.add(buttonReload);
        buttonReload.addActionListener(new EcouteurBoutonReload(this.tableau));
    }

    public void confMouse() {
        this.mouseE = new MouseEvents(this.tableau.getHisto(), this.jc);
        this.jc.addMouseListener(this.mouseE);
        this.jc.addMouseMotionListener(this.mouseE);
    }

    public void confThread() {
        this.thread = new MonThread(this.tableau);
        this.thread.start();
        if (!this.thread.isAlive()) {
            System.out.println("Thread kaput !");
        }
    }

    public void launch(String mot, Dimension dim) {
        if (this.firstLaunch || (this.jc.getPreferredSize().width != dim.width || this.jc.getPreferredSize().height != dim.height) || !this.tableau.getMot().equals(mot)) {
            if (!this.firstLaunch) {
                this.tableau.destroyFrame();
            }
            this.firstLaunch = false;
            this.jc.clear();
            this.jc.setPreferredSize(dim);
            this.tableau = new Peindre(this.jc, mot);
            this.tableau.go();
            this.tableau.reload();
            if(this.mouse)
                this.confMouse();
            if(this.threadUsing)
                this.confThread();
        } else {
            this.tableau.reload();
            if(this.mouse)
                this.confMouse();
            if(this.threadUsing)
                this.confThread();
        }
    }

    public void reload() {
        this.tableau.reload();
    }
}
