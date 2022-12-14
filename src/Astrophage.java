import java.awt.*;

public class Astrophage {


    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;
    public boolean duplicate = false;

    public Rectangle rec;
    public boolean isCrashingScientist;




    public Astrophage()
    {
        xpos = (int)(Math.random()*10)+980;
        ypos = (int)(Math.random()*650);
        dx = -(int)(Math.random()*10+1);
        dy = (int)(Math.random()*10);
        //System.out.println(dx+", "+dy);
        width = 20;
        height = 20;
        isAlive = true;
        isCrashingScientist = false;
        rec = new Rectangle(xpos, ypos, height, width);

    }
    public void wrap(){
        if(xpos>1000){
            xpos = 0;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, height, width);

    }

    public void bounce(){
        if(xpos>1000){
            dx = -dx;
            duplicate = true;
        }
        else{
            duplicate = false;
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
