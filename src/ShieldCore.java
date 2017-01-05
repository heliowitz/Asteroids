import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Is the core of the shield powerup that the player is able to build
 * It holds only aethetic purpose
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class ShieldCore extends Actor
{
    private int timer = 1800;  //declare and initialize new integer timer at 1800 for the time that the shield lasts
    private boolean remove = false; // declare and initialize new boolean remove as false
    
    /**
     * every act, the shield core turns 1 degree
     * its timer integer decreases by 1
     * 
     * If the timer reaches 0, the shield core is removed
     */
    public void act() 
    {
        // Add your action code here.
        
        turn (1);  //turn 1 degree
        timer --;  //decrease timer integer by 1
        
        if (timer <= 0) //if timer integer is smaller or equal to 0
        {
            remove = true;  //set remove to true
        }
        
        if (remove == true)  //if remove is true
        {
            getWorld().removeObject (this);  //remove shield core
        }
        
    }        
}
