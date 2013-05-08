package herbin;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
	
public class JCanvas extends JPanel implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //private List drawshapesList = new LinkedList(); //collections
    private ArrayList<JShapeDraw> drawshapesList = new ArrayList<JShapeDraw>();
    
    @Override
    public void paint(Graphics g) {
        for (Iterator<JShapeDraw> iter = drawshapesList.iterator(); iter.hasNext();) {
                JShapeDraw shape = (JShapeDraw) iter.next();
                shape.draw(g);
        }
    }

    public void addShapeToDraw(JShapeDraw shape) {
        drawshapesList.add(shape);
        repaint();
    }
    public void removeShapeFromDraw(JShapeDraw shape) {
        drawshapesList.remove(shape);
        repaint();
    }
    public void clear() {
        drawshapesList.clear();
        repaint();
    }
}
