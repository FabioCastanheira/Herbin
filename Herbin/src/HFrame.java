import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HFrame {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menuFichier;
	private JMenuItem itemEnregistrerPNG;
	private JMenuItem itemEnregistrer;
	private JMenuItem itemOuvrir;
	private JMenuItem itemReset;
	private Dimension dimension;
	private boolean firstGeneration;
	private JCanvas firstJC;
	private JCanvas jCanvas;
	
	
	public HFrame(Dimension d, JCanvas fjc){
		this.jCanvas=fjc;
		this.firstJC=fjc;
		//this.firstGeneration=true;
		this.dimension = d;
		
		this.frame = new JFrame("†ber Herbin");
		this.frame.setMinimumSize(this.dimension);
		this.frame.setSize(this.dimension);
		this.frame.setResizable(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.menuBar=new JMenuBar();
		this.menuFichier = new JMenu("Fichier");
		this.itemEnregistrerPNG = new JMenuItem("Enregistrer PNG");
		this.itemEnregistrer = new JMenuItem("Enregistrer");
		this.itemOuvrir = new JMenuItem("Ouvrir");
		this.itemReset = new JMenuItem("Reset");
		
		itemEnregistrerPNG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {   
				JFileChooser fenetreMenu = new JFileChooser();
				fenetreMenu.setCurrentDirectory(new File("/Users/YPierru/Documents/HerbinImg"));
				//fenetreMenu.setCurrentDirectory(new File("/~"));
				fenetreMenu.setDialogTitle("lolsave");
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		itemEnregistrer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
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
						oos.writeObject(jCanvas);
						oos.flush();
						oos.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		itemOuvrir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//Choix du fichier et paramtres
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("/Users/YPierru/Documents/HerbinImg"));
				fileChooser.setDialogTitle("Ouvrir");
				fileChooser.setApproveButtonText("Ouvrir");
				int returnVal = fileChooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					//On rcupre le fichier
					
					File file = fileChooser.getSelectedFile();
					try{
						FileInputStream fichier = new FileInputStream(file);
						ObjectInputStream ois = new ObjectInputStream(fichier);
						jCanvas = (JCanvas) ois.readObject();
						destroy();
						addDraw(jCanvas);
					} catch(IOException e2){
						e2.printStackTrace();
					} catch (ClassNotFoundException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				}
				
			}
		});
		
		itemReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {     
				System.out.println("event");
				/*JFrame f = new JFrame();
				f.getContentPane().add(firstJC);
				f.setVisible(true);
				f.pack();*/
				destroy();
				addDraw(firstJC);
			}
		});
		
		this.menuFichier.add(this.itemEnregistrerPNG);
		this.menuFichier.add(this.itemEnregistrer);
		this.menuFichier.add(this.itemOuvrir);
		this.menuFichier.add(this.itemReset);
		this.menuBar.add(this.menuFichier);
		this.frame.setJMenuBar(this.menuBar);
		this.frame.setVisible(true);
	}
	
	public void addDraw(JComponent component){
		/*System.out.println("add draw"+component);
		if(this.firstJC==null){
			System.out.println("first jcnull"+component);
			this.firstJC=(JCanvas)component;
		}
		System.out.println("firstjc:"+this.firstJC);*/
		this.jCanvas=(JCanvas)component;
        this.frame.getContentPane().removeAll();
        this.frame.getContentPane().add(component);
        this.frame.pack();
        this.frame.setVisible(true);
        //this.firstGeneration=false;
	}
	
	public void destroy(){
		this.frame.dispose();
	}
	
}
