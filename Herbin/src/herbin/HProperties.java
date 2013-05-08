package herbin;

public class HProperties {
	
	private int largeur;
	private int longueur;
	private String mot;
	
	public HProperties(int la, int lo, String m){
		this.largeur=la;
		this.longueur=lo;
		this.mot=m;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public String getMot() {
		return mot;
	}

	public void setMot(String mot) {
		this.mot = mot;
	}
	
	

}
