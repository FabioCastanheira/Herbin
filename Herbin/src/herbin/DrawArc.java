package herbin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;

import java.awt.geom.Rectangle2D;
public class DrawArc extends ShapeRectDraw {
	
	
	
	private static final int CHORD = 1;
	private static final int PIE = 2;
	public DrawArc(Color color, Point pos, Dimension dim, int startAngle, int arcAngle ) {
	
	super(color, pos, dim, startAngle, arcAngle);
		
	}
	
	public void draw(Graphics g) {

	Graphics2D g2= (Graphics2D) g;
	Color c = g2.getColor();
	g2.setColor(color);
	g2.fillArc(rect.x,rect.y,rect.width,rect.height,this.startAngle, this.arcAngle);
	g2.setColor(c);
	}
	@Override
	public Shape getShape(){
		Shape shape = new Arc2D.Float((Rectangle2D ) rect,  (float) this.startAngle, (float) this.arcAngle, PIE);
		return shape;
	}
/*	@Override
	public Area getArea(Shape shape) {
		
		Area area = new Area(shape);
		// TODO Auto-generated method stub
		return area;
	}*/
	public void translate(int dx, int dy) {
            super.getRectangle().translate(dx, dy);
        }

}
