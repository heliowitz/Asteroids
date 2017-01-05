import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ShipBullet is the bullet that the ship shoots when the user wants to shoot
 * It can destroy asteroids, saucers, line saucers
 * 
 * Holds the code to detect if a ship's bullet has hit either a line saucer or a saucer
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class ShipBullet extends Actor
{
    private int angle;  //declare integer angle
    private int speed = 5;   //declare and initialize speed at 5
    
    private boolean remove = false;  //declare and initialize boolean remove at false
    
    private Saucer s1;  //declare Saucer s1
    private LineSaucer ls3;  //declare LineSaucer ls3
    
    private int scoreWorth = 5;  //declare and initialize integer scoreWorth at 5
    
    private GreenfootSound saucerDestroy;  //declare new GreenfootSound saucerDestroy
    
    public ShipBullet (int rotation)
    {
        this.angle = rotation; //set angle to rotation
        
        saucerDestroy = new GreenfootSound ("saucerDestroy.wav");  //initialize saucerDestroy as a new GreenfootSound from saucerDestroy.wav
        
    }
    
    /**
     * Every act, the Ship Bullet moves in the direction of the set angle
     * 
     * ShipBullet is also checked to see if it has hit saucers, line saucers, or has reached the edge of the world
     */
    public void act() 
    {
        // Add your action code here.
        
       
        setRotation (angle);  //setRotation of the ShipBullet to angle
        move (speed);  //move at the integer speed
        
        
        if (checkHit())  //if checkHit method is true
        {
            Space m = (Space)getWorld(); 
            m.addPoints(scoreWorth);  //add scoreWorth to total points
            remove = true;  //set remove to true
            saucerDestroy.play();  //play saucerDestroy music
            s1.removeMe();  //remove s1
        }
        
        if (checkLineHit())  //if checkLineHit method is true
        {
              Space m = (Space)getWorld();  
            m.addPoints(scoreWorth);  //add scoreWorth to total points
            remove = true;  //set remove to true
            saucerDestroy.play();  //play saucerDestroy music
           ls3.removeMe();  //remove ls3
        }
        
        
        if (atWorldEdge())  //if atWorldEdge method is true
        {
            remove = true; //set remove to true
        }
        
        if (remove == true)  //if remove is true
        {
            getWorld().removeObject (this);  //remove ShipBullet
        }
    }    
    
    /**
     * Checks if this Actor is at the edge of the World
     * 
     * @return boolean  true if at edge of the World, otherwise false
     */
    public boolean atWorldEdge ()
    {
        int maxX = getWorld().getBackground().getWidth();
        int maxY = getWorld().getBackground().getHeight();
        if (getX() <= 0 || getX() >= maxX - 1)
        { return true;
        } 
        if (getY() <= 0 || getY() >= maxY - 1)
        { return true; 
        }
        return false;
        
       
    }
    
    /**
     * Checks to see if the ShipBullet has hit a saucer
     * 
     * @return boolean true if hit saucer, false otherwise
     */
    public boolean checkHit()
    {
        s1 = (Saucer)getOneIntersectingObject (Saucer.class);  //initializing s1 as a saucer that is touching ShipBullet
        if (s1 == null)  //if not such saucer exists
        {
            
            return false;
        }
        else
        {

            return true;
        }
    }
    
    /**
     * Checks to see if the ShipBullet has hit a LineSaucer
     * 
     * @return booelan true if hit LineSaucer, false otherwise
     */
    public boolean checkLineHit()
    {
        ls3 = (LineSaucer)getOneIntersectingObject (LineSaucer.class);  //initializing ls3 as a LineSaucer that is touching ShipBullet
        if (ls3 == null)  //if not usch LineSaucer exists
        {
            return false;
           
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Removes the ShipBullet
     */
    public void removeMe()
    {
        getWorld().removeObject (this);   //removes ShipBullet
    }


}
