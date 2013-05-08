package herbin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
public class DrawCircle extends ShapeRectDraw {
	

	
	public DrawCircle(Color color, Point pos, Dimension dim) {
	super(color, pos, dim);
	}
	
	public void draw(Graphics g) {
	Graphics2D g2= ( Graphics2D) g;
	Color c = g2.getColor();
	g2.setColor(color);
	g2.fillOval(rect.x,rect.y,rect.height,rect.width);
	g2.setColor(c);
	}
	
	
	public Shape getShape(){
		Shape shape = new Ellipse2D.Float(rect.x,rect.y,rect.width,rect.height);
		return shape;
	}
	
	public Area getArea(Shape shape) {
		
		Area area = new Area(shape);
		// TODO Auto-generated method stub
		return area;
	}

        public void translate(int dx, int dy) {
            super.getRectangle().translate(dx, dy);
        }
	

	
}

