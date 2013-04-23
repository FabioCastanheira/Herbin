import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;


public abstract class ShapeAreaDraw implements JShapeDraw, java.io.Serializable {
	protected Area area ;
	protected Color color;
	public ShapeAreaDraw(Area area, Color color){
		this.color=color;
		this.area=area;
	}
	
	public Area getArea(){
		return area;
	}
	public abstract void draw(Graphics g) ;
	
	//public abstract Rectangle getRectangle();
public  Rectangle getRectangle(){
		
		return (Rectangle) area.getBounds(); //on peut le cloner
	}
	public Polygon getPolygon(){
		
		PathIterator path =area.getPathIterator(null);
		return (Polygon) area.getPathIterator(null);
	}
	public Shape getShape(){
		Shape shape = (Shape) area;
		return shape;
	}
	
}
