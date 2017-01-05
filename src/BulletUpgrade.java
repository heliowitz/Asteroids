import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Is a Bullet Upgrade class that is one of the powerups that can be collected by the ship
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class BulletUpgrade extends Actor
{
    /**
     * turn -1 degree 
     */
    public void act() 
    {
        // Add your action code here.
        turn (-1); // turn -1 degree
    }    
    
    /**
     * removes the object
     */
    public void removeMe()
    {
        getWorld().removeObject(this);// removes the BulletUpgrade
    }
}
