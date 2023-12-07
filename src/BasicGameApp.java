//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import org.w3c.dom.html.HTMLDOMImplementation;


import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;



//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener, MouseInputListener {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

	//public int count;


   //Declare the variables needed for the graphics
	public JFrame frame;

	public Canvas canvas;
    public JPanel panel;
    KeyEvent event;



	public BufferStrategy bufferStrategy;

	public Image astroPic;
	public Image rockyPic;

	public Image astrophagePic;
	public Image starsBackground;
	public Image taumebaPic;
	public Image winPic;
	public ArrayList <Astrophage> astrophages = new ArrayList<Astrophage>();
	public ArrayList<Taumeba> taumebas = new ArrayList<Taumeba>();


	//Declare the objects used in the program
   //These are things that are made up of more than one variable type
//	private Astronaut astro;
	private Astronaut grace;
	private Astronaut rocky;
	private Astrophage astrophage;




	// Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {

		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
		//x = 11;


	//	addKeyListener(this);
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png"); //load the picture
		rockyPic = Toolkit.getDefaultToolkit().getImage("rocky.PNG"); //load the picture
		starsBackground = Toolkit.getDefaultToolkit().getImage("stars.jpg"); //load the picture
		astrophagePic = Toolkit.getDefaultToolkit().getImage("astrophage.png"); //load the picture
		taumebaPic = Toolkit.getDefaultToolkit().getImage("taumeba.png");
		winPic = Toolkit.getDefaultToolkit().getImage("win.jpg");

		int numAstrophage = (int)(Math.random()*100)+10;
		//astrophages = new Astrophage[numAstrophage];
		//count = numAstrophage;

		//astro = new Astronaut(950,100);
		grace = new Astronaut((int)(Math.random()*940), (int)(Math.random()*640), (int)(Math.random()*4));
		grace.name = "grace";
		rocky = new Astronaut((int)(Math.random()*940), (int)(Math.random()*640), (int)(Math.random()*4));
		rocky.name = "rocky";
		for(int i = 0; i < numAstrophage; i++){
			astrophage = new Astrophage();
			astrophage.dx = (int)(Math.random()*10)+1;
			astrophage.dx = astrophage.dx;
			astrophage.xpos = (int)(Math.random()*940);
			astrophage.ypos = (int)(Math.random()*640);
			astrophage.dy = (int)(Math.random()*10)+1;
			astrophages.add(astrophage);
			//count++;
		}


//		jack.dy = 1;
//		jack.dx = 0;

		setUpGraphics();
	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{

      //calls the move( ) code in the objects
		//astro.wrap();
		//grace.bounce();
		//keyPressed();
		//keyPressed(KeyEvent);
		graceXRockyCrashing();
		astrophageXScientistCrashing();
		astrophageXTaumeba();
//		for(int i = 0; i < astrophages.size(); i++){
//			astrophages.get(i).bounce();
//			if(astrophages.get(i).duplicate){
//				Astrophage newAstrophage = new Astrophage();
//				//newAstrophage.bounce();
//				astrophages.add(newAstrophage);
//			}
//
//		}
		//rocky.bounce();


	}

	public void graceXRockyCrashing(){
		if(rocky.rec.intersects(grace.rec) && rocky.isCrashingSciencist == false){
			rocky.isCrashingSciencist = true;
			rocky.dx = -rocky.dx;
			grace.dx = -grace.dx;
			Taumeba newTaumeba = new Taumeba(rocky.xpos, grace.ypos);
			taumebas.add(newTaumeba);
			newTaumeba.bounce();
		}
		if(!rocky.rec.intersects(grace.rec))
		{
			rocky.isCrashingSciencist = false;
		}
		rocky.bounce();
		grace.bounce();
		for(Taumeba t: taumebas){
			t.bounce();
		}
	}

	public void astrophageXTaumeba(){
		for(int t = 0; t< taumebas.size(); t++){
			for(int a  = 0; a<astrophages.size(); a++) {
			//	for(int i = 0; i < astrophages.size(); i++){
				if(astrophages.get(a).rec.intersects(taumebas.get(t).rec)){
					astrophages.get(a).isAlive = false;
					//count--;
				}
			}
		}
	}
	public void astrophageXScientistCrashing()
	{
		for(int i = 0; i < astrophages.size(); i++){
			if(astrophages.get(i).isAlive == true) {
				astrophages.get(i).bounce();

//					System.out.println(astrophages.get(i).xpos +" " +astrophages.get(i).dx);
				if (astrophages.get(i).rec.intersects(rocky.rec) || astrophages.get(i).rec.intersects(grace.rec)){// && astrophages.get(i).isCrashingScientist == false) {
//					System.out.println("CRASH");
					astrophages.get(i).isAlive = false;
					astrophages.get(i).dx = 0;
					astrophages.get(i).dy = 0;
					//count--;
					if(astrophages.get(i).rec.intersects(rocky.rec)){
						rocky.fuel= rocky.fuel+30;
					}
					if(astrophages.get(i).rec.intersects(grace.rec)){
						grace.fuel= grace.fuel+30;
					}
				} else {
					if (astrophages.get(i).duplicate) {

						Astrophage newAstrophage = new Astrophage();
						//newAstrophage.bounce();
						astrophages.add(newAstrophage);
						//count++;
					}
				}


				if(!astrophages.get(i).rec.intersects(rocky.rec)){
					astrophages.get(i).isCrashingScientist = false;
				}
			}

		}

	}
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
	//	event = new KeyEvent();
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it
		//frame.add(grace);
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   //frame.add(listen);
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
     // panel.add(grace);
	  canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
	canvas.addKeyListener(this);
	canvas.addMouseListener(this);
      panel.add(canvas);  // adds the canvas to the panel

      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      //frame.add(grace);
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }

   public int countAlive()
   {
	   int [] arr = {1, 2, 3};

	   for(int utd5o76d: arr)
	   {
		//   System.out.println("arr[x] "+ utd5o76d);
	   }
	   int count = 0;
	   for (Astrophage a: astrophages) {
		   if(a.isAlive ==true){
			   count++;
		   }
	   }
//	   System.out.println(count);
	   return count;
   }

	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image of the astronaut
		g.drawImage(starsBackground, 0, 0, WIDTH, HEIGHT, null);

		//g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);

		if(countAlive()<=0)
		{
			g.drawImage(winPic, 149, 0, 702, HEIGHT, null);


		}else{
			//System.out.println(countAlive());

			g.drawImage(astroPic, grace.xpos, grace.ypos, grace.width, grace.height, null);
			//render a health bar
			//g.setColor(Color.red);
			//g.fillRect(grace.xpos, grace.ypos-10, (grace.width)*(grace.health/10), 10);
			g.drawImage(rockyPic, rocky.xpos, rocky.ypos, rocky.width, rocky.height, null);

			for(Astrophage a: astrophages){
				if(a.isAlive) {
					g.drawImage(astrophagePic, a.xpos, a.ypos, a.width, a.height, null);
				}
			}
			for(Taumeba t: taumebas)
			{
				if(t.isAlive){
					g.drawImage(taumebaPic, t.xpos, t.ypos, t.width, t.height, null);
				}
			}
		}



		//g.draw(new Rectangle(astro.xpos, astro.ypos, astro.width, astro.height));
		g.dispose();

		bufferStrategy.show();
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		//System.out.println(e.getKeyChar());

		if(e.getKeyCode() == 38){ //north
			grace.north = true;
		}
		if(e.getKeyCode() == 39){//east
			grace.east = true;
		}
		if(e.getKeyCode() == 40){ //south
			grace.south = true;
		}
		if(e.getKeyCode() == 37){ //west
			grace.west = true;	
		}
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == 38){ //north
			grace.north = false;
		}
		if(e.getKeyCode() == 39){//east
			grace.east = false;
		}
		if(e.getKeyCode() == 40){ //south
			grace.south = false;
		}
		if(e.getKeyCode() == 37){ //west
			grace.west = false;	

		}
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getID());
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
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


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e);
	}
}