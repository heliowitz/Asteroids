import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Helps to guide the user through the basic controls of the game
 * guides the user on the basic movement of the ship, as well as the building of turrets, shields, or warp gates
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class Controls extends World
{
  
    
    public Controls()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        
    }
    
    public void act()
    {
      
        if (Greenfoot.isKeyDown("enter"))  //if enter is pressed
        {
            DefenseGuide dg1 = new DefenseGuide();  //Declares and initializes new DefenseGuide dg1
            Greenfoot.setWorld(dg1);  //sets the world as dg1
            Greenfoot.delay(50);  //delays Greenfoot by 50
        }
    }
}
