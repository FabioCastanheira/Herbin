package HandCapture;

public class Hand {
	
	public static float xHand;
	public static float yHand;
	public static float zHand;
	public static boolean detectedHand;
	
	public static String desc(){
		return "X = "+xHand+" Y = "+yHand+" Z = "+zHand;
	}
	
	/*public Hand(){
		this.detected=false;
	}*/
	
	/*public Hand(int x, int y){
		this.setX(x);
		this.setY(y);
	}*/
	/*
	public void setXY(float x, float y){
		this.x=(int)x;
		this.y=(int)y;
		this.detected=true;
	}
	
	public void setX(int x){
		this.detected=true;
		this.x=x;
	}
	
	public void setY(int y){
		this.detected=true;
		this.y=y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public boolean isDetected(){
		return this.detected;
	}


	public void afficheCooHand(){
		System.out.println("Xh = "+this.x + "Yh = "+this.y);
	}*/
	
}
