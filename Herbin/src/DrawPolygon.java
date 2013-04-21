
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
//import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
public class DrawPolygon  extends ShapePolygonDraw {
	
	public DrawPolygon(Color color, int[] posx, int[] posy, int npoints) {
	super(color, posx, posy,npoints);
	}
	
	public DrawPolygon(Color color,Polygon p){
		super(color,p);
		}
	
	public void draw(Graphics g) {
	Graphics2D g2= ( Graphics2D) g;
	Color c = g2.getColor();
	g2.setColor(color);
	g2.fillPolygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
	g2.setColor(c);
	}

	@Override
	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		return polygon.getBounds();
	}
	
	public Shape getShape(){
		 Shape shape = new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
		return shape;
	}
	public Area getArea(Shape shape){
		Area area= new Area(shape);
		return area;
	}
	
	public Polygon getPolygon(){
		return this.polygon;
	}

        public void translate(int dx, int dy) {
            this.polygon.translate(dx, dy);
        }

}

