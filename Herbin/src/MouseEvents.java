import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseEvents implements MouseListener, MouseMotionListener {
	
	int preX, preY;
	ArrayList<JShapeDraw> listeFormes;
	JCanvas jc;
	JShapeDraw shape;
	boolean touche; //Booléen, vrai quand la souris est toujours sur la forme
	
	public MouseEvents(ArrayList<JShapeDraw> listeFormes, JCanvas jc) {
	    
	    this.listeFormes = listeFormes;
	    this.jc = jc;
	}
        
	@Override
	public void mousePressed(MouseEvent e) {
		//Boucle qui cherche si la souris est placée sur une forme
		for (int i=0; i<this.listeFormes.size(); i++) {
                        //Si on est sur une forme
			if (this.listeFormes.get(i).getShape().contains(e.getX(), e.getY())) {
                            //On récupère les différences du x et du y pour calculer la position finale de la forme
                            preX = this.listeFormes.get(i).getShape().getBounds().x - e.getX();
			    preY = this.listeFormes.get(i).getShape().getBounds().y - e.getY();
			   // System.out.println("X du Shape : " + this.listeFormes.get(i).getShape().getBounds().x );
			    //System.out.println("X de la souris : " + e.getX());
                            //System.out.println("VOUS AVEZ TOUCHE UNE FORME BRAVO");
                            //Le shape "touché"
			    this.shape = this.listeFormes.get(i);
                            //on remet la forme au 1er plan
                            this.jc.removeShapeFromDraw(this.shape);
                            this.jc.addShapeToDraw(this.shape);
                            //System.out.println("Shape : " + this.shape);
			    this.deplacer(e, this.shape);  
			    touche = true;
			    break;
			}
		}
	}
        
	@Override
	public void mouseDragged(MouseEvent e) {
		if (touche) {
			//System.out.println("DRAAAAAAAAAAAAAAAAAAAAAAAG");
			this.deplacer(e, shape);
		}
		
	}
        
        @Override
	public void mouseReleased(MouseEvent e) {
		if (touche) {
			this.deplacer(e, this.shape);
			this.shape = null;
			touche = false;
			//System.out.println("VOUS AVEZ RELACHE LA FORME");
		}
		
	}
        
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void deplacer(MouseEvent e, JShapeDraw shape) {
                //Fait déplacer la forme

		//shape.getRectangle().setLocation(preX + e.getX(), preY + e.getY());
                shape.translate(e.getX() - shape.getRectangle().x + preX, e.getY() - shape.getRectangle().y + preY );
                /*System.out.println("preX : " + preX + "\npreY : " + preY);
		System.out.println("**************\nX : "+ (e.getX()) +"\nY: "+ (e.getY()));
		System.out.println("X Shape : "+ shape.getRectangle().x + "\nY Shape: " + shape.getRectangle().y);
		System.out.println("Location Rect : "+shape.getRectangle().getLocation());*/
		this.jc.repaint();
	}


}