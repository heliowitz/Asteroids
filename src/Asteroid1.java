import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * One of the 3 types of asteroids that randomly generate onto the screen
 * They hit and collide with ships, and are vaporized by bullets, or shields
 * 
 * Class controls methods which determine if the asteroid has collided with bullets or bubble shields
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class Asteroid1 extends Actor
{
    /**
     * Act - do whatever the Asteroid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int speed = 1;//declare and initialize integer speed at 1
    
    private int directionX;  //declare integer directionX
    private int directionY;  //declare integer directionY
    
    private boolean remove = false;  //delcare and initialize boolean remove at false
    
    private ShipBullet sb1;  //declare ShipBullet sb1
    
    private int asteroidHealth = 5;   //declare and initialize integer scoreWorht at 5
     private BubbleShield bshld1;  //declare BubbleShield bshld1
  
     private GreenfootSound asteroidDestroy;  //declare new GreenfootSound asteroidDestroy
    
    private int scoreWorth = 5;  //declare and initialize integer scoreWorht at 5
    
    public Asteroid1 (int moveToX, int moveToY)
    {
        this.directionX = moveToX;  //assign directionX as moveToX
        this.directionY = moveToY;  //assign directionY as moveToY
        
        asteroidDestroy = new GreenfootSound ("asteroidDestroy1.wav");  //initialize asteroidDestroy as new sound from asteroidDestroy1.wav
    }
    
    /**
     * every act, the asteroid moves to a randomly assigned location at the border of the screen
     * the asteroid is checked to see if it has been destroyed by bubbleshields, or ship bullets 
     * the asteroid is checked to see if it has reached the edge of the world to be removed
     */
    public void act() 
    {
        // Add your action code here.
        turnTowards (directionX, directionY); //turn towards the direction of directionX,directionY
        move (speed);  //move the asteroid 
        
        if (atWorldEdge())  //if atWorldEdge method is true
        {
            remove = true; 
        }
        
         if (checkHit())  //if checkHit method is true
        {
            asteroidHealth = asteroidHealth - 1;  //reduce asteroidHealth by 1
            sb1.removeMe();   //remove ship bullet that hit it
        }
        
         if (checkBShieldCollision())  //if checkBShieldCollision method is true
        {
            remove = true;  //remove the asteroid
        }
        
        
        if (asteroidHealth <= 0)  //if and asteroid's health reaches 0 or smaller
        {
            getWorld().addObject (new Mineral(2), getX(), getY());  //add new mineral at the asteroid's location
            Space m = (Space)getWorld();
            m.addPoints(scoreWorth);//add scoreWorth to the total number of points the player has
            asteroidDestroy.play(); //play the asteroidDestroy sound file
            remove = true;//set remove as true
            
        }
        
        if (remove == true)// if remove is true
        {
            
            getWorld().removeObject (this);//remove asteroid
        }
    } 
    
    /**
     * Returns whether or not an object is at the edge of the world
     * 
     * @return boolean  true if at the edge, false otherwise
     */
    public boolean atWorldEdge ()
    {
        int maxX = getWorld().getBackground().getWidth();  //declare and initialize int maxX as the width of the background
        int maxY = getWorld().getBackground().getHeight();  //declare and initialize int maxY as the height of the background
        if (getX() <= 0 || getX() >= maxX - 1)  //if the x coordinate is at the edge of the width
        { return true;
        } 
        if (getY() <= 0 || getY() >= maxY - 1)  //if the y coordinate is at the edge of the height
        { return true; 
        }
        return false;
        
       
    }
    
     /**
     * Returns whether or not the asteroid was hit by a ShipBullet
     * 
     * @return boolean   true if hit, false otherwise
     */
    public boolean checkHit()
    {
        sb1 = (ShipBullet)getOneIntersectingObject (ShipBullet.class);  //initialize sb1 as a ShipBullet that is touching the asteroid
        if (sb1 == null)//if there is no sb1
        {
            
            return false;
        }
        else
        {
            //asteroid1Counter = asteroid1Counter + 1;
            
            return true;
        }
    }
    
    /**
     * Returns whether or not the asteroid was vaporized by a Bubble Shield
     * 
     * @return boolean true if vaporized, false otherwise
     */
      public boolean checkBShieldCollision()
    {
        bshld1 = (BubbleShield)getOneIntersectingObject (BubbleShield.class);  //initialize bshld1 as a BubbleShield that is touching the asteroid
        if (bshld1 == null)  //if there is not bshld1
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
  
}
