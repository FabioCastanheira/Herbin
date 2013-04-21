import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
public class DrawBBox  extends ShapeRectDraw {
	
	public DrawBBox(Color color, Point pos, Dimension dim) {
	super(color, pos, dim);
	}
	
	public void draw(Graphics g) {
	Color c = g.getColor();
	g.setColor(color);
	g.drawRect(rect.x,rect.y,rect.width,rect.height);
	g.setColor(c);

	}
	
/*	@Override
	public Shape getShape(){
		Shape shape = new Rectangle(rect.x,rect.y,rect.width,rect.height);
		return shape;
	}
	@Override
	public Area getArea(Shape shape) {
		
		Area area = new Area(shape);
		// TODO Auto-generated method stub
		return area;
	}*/
	public void translate(int dx, int dy) {
            super.getRectangle().translate(dx, dy);
        }

}

