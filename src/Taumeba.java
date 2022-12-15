import java.awt.*;

public class Taumeba {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public boolean isCrashingAstrophage;
    public Rectangle rec;
    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.


    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Taumeba(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =7;
        dy =7;
        width = 10;
        height = 10;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, height, width);

    }
    public void bounce(){
        if(xpos>1000) {
            dx = -dx;
        }
        if(xpos < 0){
            dx = -dx;
        }
        if(ypos>700){
            dy = -dy;
        }
        if(ypos < 0){
            dy = -dy;
        }
        //duplicate = false;
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, height, width);
    }


}
