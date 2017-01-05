import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * LineSaucer class acts as one of the main enemies in the game. They spawn at random intervals, and sweep in lines either vertically or horizonally
 * 
 * Holds the code to detect if the LineSaucer has been hit with either Shields or Bubble Shields
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class LineSaucer extends Actor
{
    private boolean remove = false;  //declare and initialize boolean remove as false
    private Shield sld1;  //declare Shield sld1
     private BubbleShield bshld1;  //delcare BubbleShield bshld1
     
     private int rotation;  //declare integer rotation
     private int scoreWorth = 5;  //declare and initialize integer scoreWorth at 5
     
     private GreenfootSound saucerDestroy;  //declare new GreenfootSound saucerDestroy
     
     public LineSaucer (int rotation)
     { 
         this.rotation = rotation;  //set rotation as rotation
         saucerDestroy = new GreenfootSound ("saucerDestroy.wav");  //initialize saucerDestroy using saucerDestroy.wav
        }
    
        /**
         * the Lines Saucer moves in the direction of its given rotation
         * 
         * LineSaucer is checked to see if it is at the world's edge, has been hit by a Shield or a BubbleShield
         */
    public void act() 
    {
        // Add your action code here.
        setRotation (rotation);  //set rotation of the LineSaucer
        move (1);  //move
        
         if (atWorldEdge())  //if atWorldEdge method is true
        {
            remove = true;  //set remove to true
        }
        
        if (checkShieldCollision())  //if checkShieldCollision method is true
       {
           remove = true;  //set remove to true
            Space m = (Space)getWorld();
            m.addPoints(scoreWorth);  //add scoreWorth to the current number of points
            saucerDestroy.play();  //play saucerDestroy sound file
        }
        
        if (checkBShieldCollision())  //if checkBShieldCollision method is true
        {
            remove = true; //set remove to true
             Space m = (Space)getWorld(); 
            m.addPoints(scoreWorth);  //add scoreWorth to the current number of points
            saucerDestroy.play();  //play SaucerDestroy sound file
        }
        
        if (remove == true)  //if remove is true
        {
            getWorld().removeObject (this);  //remove Line Saucer
        }
    }    
    
    /**
     * Returns whether or not an object is at the edge of the world
     * 
     * @return boolean  true if at the edge, false otherwise
     */
    public boolean atWorldEdge ()
    {
        int maxX = getWorld().getBackground().getWidth();//declare and initialize int maxX as the width of the background
        int maxY = getWorld().getBackground().getHeight();//declare and initialize int maxY as the height of the background
        if (getX() <= 0 || getX() >= maxX - 1) //if the x coordinate is at the edge of the width
        { return true;
        } 
        if (getY() <= 0 || getY() >= maxY - 1)//if the y coordinate is at the edge of the height
        { return true; 
        }
        return false;
        
       
    }
    
    /**
     * Returns a boolean to check to see if a LineSaucer has collided with a shield or not
     * 
     * @return boolean true if collision happened false otherwise
     */
      public boolean checkShieldCollision()
    {
        sld1 = (Shield)getOneIntersectingObject (Shield.class);  //initialize sld1 as a Shield that is touching a LineSaucer
        if (sld1 == null)  //if no such Shield exists
        {
            
            return false;
        }
        else
        {
         
            return true;
        }
    }
    
    /**
     * Returns a boolean to check to see if a LineSaucer has collided with a bubble shield or not
     * 
     * @return boolean true if collision happened false otherwise
     */
    public boolean checkBShieldCollision()
    {
        bshld1 = (BubbleShield)getOneIntersectingObject (BubbleShield.class);//initialize sld1 as a bubble Shield that is touching a LineSaucer
        if (bshld1 == null) //if not such bubble shield exists
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Removes the LineSaucer
     */
     public void removeMe()
    {
        getWorld().removeObject (this); //remove the LineSaucer
    }
}
