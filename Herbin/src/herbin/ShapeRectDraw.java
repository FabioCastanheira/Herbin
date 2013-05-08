package herbin;

import java.awt.*;
import java.awt.geom.*;
public abstract class ShapeRectDraw implements JShapeDraw, java.io.Serializable {
	
	
	protected Rectangle rect ;
	protected Color color;
	protected int startAngle;
	protected int arcAngle;
	
	public ShapeRectDraw(Color color, Point pos, Dimension dim){
	this.color=color;
	this.rect = new Rectangle(pos,dim);
	}
	public ShapeRectDraw(Color color, Point pos, Dimension dim, int startAngle, int arcAngle){
		this.color=color;
		this.arcAngle= arcAngle;
		this.startAngle= startAngle;
		this.rect = new Rectangle(pos,dim);
		}
	public abstract void draw(Graphics g) ;
	
	
	public  Rectangle getRectangle(){
	
		return this.rect; 
	}
	public Polygon getPolygon(){
		
		PathIterator path =rect.getPathIterator(null);
		return (Polygon) rect.getPathIterator(null);
	}
	public Shape getShape(){
		Shape shape = new Rectangle(rect.x,rect.y,rect.width,rect.height);
		return shape;
	}
	public Area getArea(Shape shape){
		Area area = new Area(shape);
		return area;
	}


	
	
	
}

