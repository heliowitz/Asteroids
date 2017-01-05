import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShipBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TurretBullet extends Actor
{
    /**
     * Act - do whatever the ShipBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int angle;//declare integer angle
    private int speed = 4;//declare and initialize speed
    
    private boolean remove = false; //declare and initialize boolean remove at false
    
    private Saucer s1;//declare Saucer s1
    
    private int scoreWorth = 5;//declare and initialize integer scoreWorth at 5
    
    private GreenfootSound saucerDestroy; //declare new GreenfootSound saucerDestroy
    
    public TurretBullet (int rotation)
    {
        this.angle = rotation; //set angle to rotation
        
        saucerDestroy = new GreenfootSound ("saucerDestroy.wav"); //initialize saucerDestroy as a new GreenfootSound from saucerDestroy.wav
    }
    
    /**
     * Every act, the Turret Bullet moves in the direction of the set angle
     * 
     * TurretBullet is also checked to see if it has hit saucers,
     * or has reached the edge of the world
     */
    public void act() 
    {
        // Add your action code here.
        
       
        setRotation (angle);
        move (speed);//move at the integer speed
        
        
        if (checkHit())//if checkHit method is true
        {
            Space m = (Space)getWorld();
            m.addPoints(scoreWorth); //add scoreWorth to total points
            remove = true;
            saucerDestroy.play(); //play saucerDestroy music
            s1.removeMe(); //remove s1
        }
        
        
        if (atWorldEdge()) //if atWorldEdge method is true
        {
            remove = true;
        }
        
        if (remove == true)
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
     * Checks to see if the TurretBullet has hit a saucer
     * 
     * @return boolean true if hit saucer, false otherwise
     */
    public boolean checkHit()
    {
        s1 = (Saucer)getOneIntersectingObject (Saucer.class);//initializing s1 as a saucer that is touching TurretBullet
        if (s1 == null)//if not such saucer exists
        {
            
            return false;
        }
        else
        {
           // saucerCounter = saucerCounter + 1;
            return true;
        }
    }
    
    public void removeMe()//remove the TurretBullet
    {
        getWorld().removeObject (this);   
    }
    
    

}