package herbin;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.SliderUI;

/*
 * Fenêtre principale. Permet toutes les opérations sur le tableau 
 *
 */

public class HFrame {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menuFichier;
	private JMenuItem itemGenerate;
	private JMenuItem itemEnregistrerPNG;
	private JMenuItem itemEnregistrer;
	private JMenuItem itemOuvrir;
	private JMenuItem itemRegenerate;
	private JCanvas jCanvas;//Panel contenant le tableau (graphique)
	private HProperties hProp;//Objet contenant les paramètres de création du tableau
	private Peindre peinture;//Objet contenant le tableau (logique : liste de forme etc)
	private MouseEvents mousE;
	
	
	public HFrame(){
		
		this.frame = new JFrame("Über Herbin");
		this.frame.setSize(new Dimension(700, 600));
		this.frame.setResizable(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.menuBar =new JMenuBar();
		this.menuFichier = new JMenu("Wanna have fun ?");
		this.itemGenerate = new JMenuItem("Herbinisation");
		this.itemRegenerate = new JMenuItem("Regénérer (même paramètres)");
		this.itemEnregistrerPNG = new JMenuItem("Graphic save");
		this.itemEnregistrer = new JMenuItem("Herbin save");
		this.itemOuvrir = new JMenuItem("Herbin open");
		
		itemGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {   
				//frame.setVisible(false);
				genererTableau();
			}
		});
		
		itemRegenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {   
				//frame.setVisible(false);
				regenerer();
			}
		});
		
		itemEnregistrerPNG.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				savePNG();
			}
		});
		
		itemEnregistrer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		itemOuvrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});
		

		this.menuFichier.add(this.itemGenerate);
		this.menuFichier.add(this.itemRegenerate);
		this.menuFichier.add(this.itemEnregistrer);
		this.menuFichier.add(this.itemOuvrir);
		this.menuFichier.add(this.itemEnregistrerPNG);
		this.menuBar.add(this.menuFichier);
		this.frame.setJMenuBar(this.menuBar);
		this.frame.setVisible(true);
	}
	
	public void genererTableau(){
		Saisie saisieFrame = new Saisie(this);
	}
	
	//Instancie le tableau (logique et graphique) à partir des paramètres fournies.
	public void addDraw(HProperties hp){
		this.hProp=hp;
		this.jCanvas = new JCanvas();
		this.jCanvas.setPreferredSize(new Dimension(this.hProp.getLargeur(), this.hProp.getLongueur()));
		this.peinture = new Peindre(this.jCanvas, this.hProp.getMot());
		
		this.jCanvas=this.peinture.reload();
		this.mousE = new MouseEvents(this.peinture.getHisto(), this.jCanvas);
        this.jCanvas.addMouseListener(this.mousE);
        this.jCanvas.addMouseMotionListener(this.mousE);
        this.frame.getContentPane().removeAll();
        this.frame.getContentPane().add(this.jCanvas);
        this.frame.pack();
        this.frame.setVisible(true);
	}
	
	//Redessine le tableau à partir des objets déjà existants (utilisés à l'ouverture d'un .herbinator
	public void addDraw(JCanvas jc, Peindre p){
		this.jCanvas=jc;
		this.peinture=p;
		this.mousE = new MouseEvents(this.peinture.getHisto(), this.jCanvas);
        this.jCanvas.addMouseListener(this.mousE);
        this.jCanvas.addMouseMotionListener(this.mousE);
        this.frame.getContentPane().removeAll();
        this.frame.getContentPane().add(this.jCanvas);
        this.frame.pack();
        this.frame.setVisible(true);
	}
	
	public void savePNG(){
		JFileChooser fenetreMenu = new JFileChooser();
		fenetreMenu.setCurrentDirectory(new File("/Users/YPierru/Documents/HerbinImg"));
		//fenetreMenu.setCurrentDirectory(new File("/~"));
		fenetreMenu.setDialogTitle("Save");
		fenetreMenu.setFileFilter(new FileNameExtensionFilter(".png", new String[] {".png"}));
		int resultat = fenetreMenu.showDialog(fenetreMenu,"=>Save<=");
		if (resultat == JFileChooser.APPROVE_OPTION){
			File file = new File(fenetreMenu.getSelectedFile().getAbsolutePath()+".png");
			BufferedImage tamponSauvegarde = new BufferedImage(
					jCanvas.getPreferredSize().width,
					jCanvas.getPreferredSize().height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tamponSauvegarde.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, jCanvas.getPreferredSize().width,jCanvas.getPreferredSize().height);
			jCanvas.paint(g);
			try {
				ImageIO.write(tamponSauvegarde, "PNG", file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void save(){
		JFileChooser fenetreMenu = new JFileChooser();
		fenetreMenu.setCurrentDirectory(new File("/Users/YPierru/Documents/HerbinImg"));
		//fenetreMenu.setCurrentDirectory(new File("/~"));
		fenetreMenu.setDialogTitle("lolsave");
		fenetreMenu.setFileFilter(new FileNameExtensionFilter(".herbinator", new String[] {".herbinator"}));
		int resultat = fenetreMenu.showDialog(fenetreMenu,"=>Save<=");
		if (resultat == JFileChooser.APPROVE_OPTION){
			File file = new File(fenetreMenu.getSelectedFile().getAbsolutePath()+".herbinator");
			FileOutputStream fichier;
			try {
				fichier = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fichier);
				//On save un objet HBlock qui contient le tableau logique (peinture) et graphique (jCanvas)
				oos.writeObject(new HBlock(jCanvas, peinture));
				oos.flush();
				oos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void open(){
		//Choix du fichier et param�tres
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("/Users/YPierru/Documents/HerbinImg"));
		fileChooser.setDialogTitle("Ouvrir");
		fileChooser.setApproveButtonText("Ouvrir");
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			//On récupère le fichier
			
			File file = fileChooser.getSelectedFile();
			try{
				FileInputStream fichier = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fichier);
				HBlock hb= (HBlock) ois.readObject();
				destroy();
				//Une fois l'objet récupérer, on redessine à partir des tableaux logique et graphique
				addDraw(hb.getJc(),hb.getTableau());
			} catch(IOException e2){
				e2.printStackTrace();
			} catch (ClassNotFoundException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		}
	}
	
	public void regenerer(){
		this.addDraw(this.hProp);
	}
	
	public void destroy(){
		this.frame.dispose();
	}
	
}
