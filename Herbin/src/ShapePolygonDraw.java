import java.awt.*;
import java.awt.geom.Area;
public abstract class ShapePolygonDraw implements JShapeDraw, java.io.Serializable {
	
	
	protected Polygon polygon ;
	protected Color color;
	
	public ShapePolygonDraw(Color color, int[] posx, int[] posy, int npoints){
	this.color=color;
	this.polygon = new Polygon(posx,posy,npoints);
	}
	public ShapePolygonDraw(Color color,Polygon p){
		this.color=color;
		this.polygon = p;
	}
		
	public abstract void draw(Graphics g) ;
	
	public  Polygon getPolygon(){
		return polygon;
	}
	
}  