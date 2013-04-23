package HandCapture;


	/* --------------------------------------------------------------------------
	 * SimpleOpenNI NITE Hands
	 * --------------------------------------------------------------------------
	 * Processing Wrapper for the OpenNI/Kinect library
	 * http://code.google.com/p/simple-openni
	 * --------------------------------------------------------------------------
	 * prog:  Max Rheiner / Interaction Design / zhdk / http://iad.zhdk.ch/
	 * date:  03/19/2011 (m/d/y)
	 * ----------------------------------------------------------------------------
	 * This example works with multiple hands, to enable mutliple hand change
	 * the ini file in /usr/etc/primesense/XnVHandGenerator/Nite.ini:
	 *  [HandTrackerManager]
	 *  AllowMultipleHands=1
	 *  TrackAdditionalHands=1
	 * on Windows you can find the file at:
	 *  C:\Program Files (x86)\Prime Sense\NITE\Hands\Data\Nite.ini
	 * ----------------------------------------------------------------------------
	 */
/*
import SimpleOpenNI.*;

import java.awt.Color;
import java.util.*;

import processing.core.PApplet;
import processing.core.PVector;

public class HandsCapture extends PApplet{

	SimpleOpenNI      context;
	// NITE
	XnVSessionManager sessionManager;
	XnVFlowRouter     flowRouter;

	PointDrawer       pointDrawer;
	Hand hand;
	
	public static void main(String args[]){
		PApplet.main(new String[] { HandsCapture.class.getName() });
	}

	public void setHand(Hand h){
		this.hand=h;
	}
	
	public void setup()
	{
		//System.out.println("setup");
	  context = new SimpleOpenNI(this);
	   
	  // mirror is by default enabled
	  context.setMirror(true);
	  
	  // enable depthMap generation 
	  if(context.enableDepth() == false)
	  {
	     println("Can't open the depthMap, maybe the camera is not connected!"); 
	     exit();
	     return;
	  }
	  
	  // enable the hands + gesture
	  context.enableGesture();
	  context.enableHands();
	 
	  // setup NITE 
	  sessionManager = context.createSessionManager("Click,Wave", "RaiseHand");

	  pointDrawer = new PointDrawer(this.hand);
	  flowRouter = new XnVFlowRouter();
	  flowRouter.SetActive(pointDrawer);
	  
	  sessionManager.AddListener(flowRouter);
	        
	  //size(context.depthWidth(), context.depthHeight()); 
	  size(900,800);
	  smooth();
	  
	}

	public void draw()
	{
		System.out.println(this.hand);
		if(this.hand!=null){
			this.hand.afficheCooHand();
		}
	//System.out.println("draw");
	  background(200,0,0);
	  // update the cam
	  context.update();
	  
	  // update nite
	  context.update(sessionManager);
	  
	  // draw depthImageMap
	  image(context.depthImage(),0,0);
	  
	  // draw the list
	  pointDrawer.draw();
	}

	public void keyPressed()
	{
	  switch(key)
	  {
	  case 'e':
	    // end sessions
	    sessionManager.EndSession();	
	    println("end session");
	    break;
	  }
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// session callbacks

	void onStartSession(PVector pos)
	{
	  println("onStartSession: " + pos);
	}

	void onEndSession()
	{
	  println("onEndSession: ");
	}

	void onFocusSession(String strFocus,PVector pos,float progress)
	{
	  println("onFocusSession: focus=" + strFocus + ",pos=" + pos + ",progress=" + progress);
	}

	public PointDrawer getPointDrawer(){
		return pointDrawer;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// PointDrawer keeps track of the handpoints

	public class PointDrawer extends XnVPointControl
	{
	  HashMap    _pointLists;
	  int        _maxPoints;
	  Color[]    _colorList = { new Color(255,0,0),new Color(0,255,0),new Color(0,0,255),new Color(255,255,0)};
	  Hand hand;
	  
	  public PointDrawer(Hand h)
	  {
	    _maxPoints = 30;
	    _pointLists = new HashMap();
	    this.hand =h;
	  }
		
	  public void OnPointCreate(XnVHandPointContext cxt)
	  {
	    // create a new list
	    addPoint(cxt.getNID(),new PVector(cxt.getPtPosition().getX(),cxt.getPtPosition().getY(),cxt.getPtPosition().getZ()));
	    //Hand hand = new Hand();
	    //System.out.println("X="+cxt.getPtPosition().getX()+" Y="+cxt.getPtPosition().getY());
	    println("OnPointCreate, handId: " + cxt.getNID());
	  }
	  
	  public void OnPointUpdate(XnVHandPointContext cxt)
	  {
		  PVector tmpVec = new PVector(cxt.getPtPosition().getX(),cxt.getPtPosition().getY(),cxt.getPtPosition().getZ());
	    //println("OnPointUpdate X=" + tmpVec.x + " Y = "+tmpVec.y);   
		  this.hand.setXY(tmpVec.x, tmpVec.y);
	    addPoint(cxt.getNID(),tmpVec);
	  }
	  
	  public void OnPointDestroy(long nID)
	  {
	    println("OnPointDestroy, handId: " + nID);
	    
	    // remove list
	    if(_pointLists.containsKey(nID))
	       _pointLists.remove(nID);
	  }
	  
	  public ArrayList getPointList(long handId)
	  {
	    ArrayList curList;
	    if(_pointLists.containsKey(handId))
	      curList = (ArrayList)_pointLists.get(handId);
	    else
	    {
	      curList = new ArrayList(_maxPoints);
	      _pointLists.put(handId,curList);
	    }
	    return curList;  
	  }
	  
	  public void addPoint(long handId,PVector handPoint)
	  {
	    ArrayList curList = getPointList(handId);
	    
	    curList.add(0,handPoint);      
	    if(curList.size() > _maxPoints)
	      curList.remove(curList.size() - 1);
	  }
	  
	  public Hand getHand(){
		  return this.hand;
	  }
	  
	  public void draw()
	  {
	    if(_pointLists.size() <= 0)
	      return;
	      
	    pushStyle();
	      noFill();
	      
	      PVector vec;
	      PVector firstVec;
	      PVector screenPos = new PVector();
	      int colorIndex=0;
	      
	      // draw the hand lists
	      Iterator<Map.Entry> itrList = _pointLists.entrySet().iterator();
	      while(itrList.hasNext()) 
	      {
	        strokeWeight(2);
	        //stroke(_colorList[colorIndex % (_colorList.length - 1)]);

	        ArrayList curList = (ArrayList)itrList.next().getValue();     
	        
	        // draw line
	        firstVec = null;
	        Iterator<PVector> itr = curList.iterator();
	        beginShape();
	          while (itr.hasNext()) 
	          {
	            vec = itr.next();
	            if(firstVec == null)
	              firstVec = vec;
	            // calc the screen pos
	            context.convertRealWorldToProjective(vec,screenPos);
	            vertex(screenPos.x,screenPos.y);   
	          } 
	        endShape();   
	  
	        // draw current pos of the hand
	        if(firstVec != null)
	        {
	          strokeWeight(8);
	          context.convertRealWorldToProjective(firstVec,screenPos);
	          point(screenPos.x,screenPos.y);
	          //System.out.println("Hx="+screenPos.x+" Hy="+screenPos.y);
	        }
	        colorIndex++;
	      }
	      
	    popStyle();
	  }

	}
}
*/