package herbin;

public class HBlock implements java.io.Serializable{
	private JCanvas jc;
	private Peindre tableau;
	
	public HBlock(JCanvas j, Peindre p){
		this.jc=j;
		this.tableau=p;
	}

	public JCanvas getJc() {
		return jc;
	}

	public Peindre getTableau() {
		return tableau;
	}
	
}
