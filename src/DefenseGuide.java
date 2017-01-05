import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Provides players with a proper breakdown of all of the different defenses that you can build
 * also provides players with information on what the powerups are and what they do
 * 
 * 
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class DefenseGuide extends World
{
    public DefenseGuide()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

    }
    
    public void act()
    {
       
        if (Greenfoot.isKeyDown("enter"))  //if enter is down
        {
            Space s1 = new Space();  //declare and initialize new Space called s1
          
            Greenfoot.setWorld(s1);  //set the world as s1
            Greenfoot.delay(50);  //delay Greenfoot by 50
        }
        
    }
}
