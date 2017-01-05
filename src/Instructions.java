import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Provides the user with a basic set of instructions about what the game is about 
 * Gives the basic objective of the game
 * 
 * @author (Jack Ding) 
 * @version (Jack Ding)
 */
public class Instructions extends World
{
    public Instructions()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

    }
    
    public void act()
    {

        if (Greenfoot.isKeyDown("enter"))  //if the enter key is pressed down
        {
            Controls cls = new Controls();  //declare and initialize new Controls cls
            Greenfoot.setWorld(cls);  //sets the world to cls
            Greenfoot.delay(50);  //delays Greenfoot by 50
        }
    }
        
}
