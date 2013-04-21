import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;


public class DrawArea extends ShapeAreaDraw{
	public DrawArea(Area area, Color color) {
		super(area, color);
		}
	public void draw(Graphics g) {
		Graphics2D g2= ( Graphics2D) g;
		Color c = g2.getColor();
		g2.setColor(color);
		g2.draw(area);
		g2.fill(area);
		g2.setColor(c);
	}
	
	
	
	
	public Area getArea(Shape shape) {
		
		Area area = new Area(shape);
		// TODO Auto-generated method stub
		return area;
	}
	
        public Area getArea() {
		
		
		return area;
	}

        public void translate(int dx, int dy) {
            super.getRectangle().translate(dx, dy);
        }
	
	
	
}
