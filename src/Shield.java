import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Is the protective force field that is generated by building a shield
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class Shield extends Actor
{
    private int timer = 1800;//declare and initialize timer at 1800
    
    private boolean remove = false;
    
    public void act() 
    {
        // Add your action code here.
        
        timer --;//timer decrease by 1
        
        if (timer <= 0)//if timer smaller or equal to 0
        {
            remove = true;
        }
        
        if (remove == true)  
        {
            getWorld().removeObject (this);//remove
        }
        
    }    
}