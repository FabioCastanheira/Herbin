package herbin;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;

public interface JShapeDraw {
	public void draw(Graphics g);
	public Rectangle getRectangle();
	//public Polygon getPolygon();
	public Shape getShape();
	/*public Area getArea(Shape shape);*/
	public Area getArea(Shape shape);
        public void translate(int dx, int dy);

	
}


