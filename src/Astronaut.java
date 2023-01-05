import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
/**
 * Created by chales on 11/6/2017.
 */
public class Astronaut extends JPanel implements ActionListener, KeyListener {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public boolean isCrashingSciencist;
    public int health;

    public int fuel;
    public Rectangle rec;
    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.


    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Astronaut(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =3;
        dy =3;
        width = 50;
        height = 50;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, height, width);
        fuel = 1000;
        health = 10;

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

    } // constructor
    public void keyTyped(KeyEvent e) {}
    public void actionPerformed(ActionEvent e) {
        //String s = e.getActionCommand();
        repaint();

    }
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(name.equals("grace")) {
            System.out.println("press");

            if (code == KeyEvent.VK_DOWN) {
                dy = -Math.abs(dy);
            }
            if (code == KeyEvent.VK_UP) {
                dy = Math.abs(dy);

            }
            if (code == KeyEvent.VK_LEFT) {

                dx = -Math.abs(dx);
            }


            if (code == KeyEvent.VK_RIGHT) {

                dx = Math.abs(dx);

            }
            bounce();

        }
    }
    public void keyReleased(KeyEvent e) {
        bounce();
    }
    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        if(fuel>0) {
            xpos = xpos + dx;
            ypos = ypos + dy;
            rec = new Rectangle(xpos, ypos, height, width);
            fuel--;
        }

    }

    public void wrap(){
        if(fuel>0) {
            if (xpos > 1000) {
                xpos = 0;
            }
            xpos = xpos + dx;
            ypos = ypos + dy;
            rec = new Rectangle(xpos, ypos, height, width);
            fuel--;
        }
    }

    public void bounce(){
        if(fuel >0) {
            if (xpos > 1000 - 50) {
                dx = -dx;
            }
            if (xpos < 0) {
                dx = -dx;
            }
            if (ypos > 700 - 50) {
                dy = -dy;
            }
            if (ypos < 0) {
                dy = -dy;
            }

            xpos = xpos + dx;
            ypos = ypos + dy;
            rec = new Rectangle(xpos, ypos, height, width);
            fuel--;
        }
    }
}






