import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Saucer class acts as one of the main enemies in the game. They spawn at random locations on the borders of the screen, and target and seek the ship
 * 
 * Holds the code to determine if it has collided with a shield or a bubble shield
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */

public class Saucer extends Actor
{    private int fixX; //declare int fixX
    private int fixY;  //declare int fixY
    
    private int speed = 1; //declare and initialize int speed at 1
    private int scoreWorth = 5;  //declare and initialize int scoreWorth at 5
    
    private boolean remove = false;
    
  private Shield sld1; //declare Shield sld1
  private BubbleShield bshld1;  //declare BubbleShield bshld1
  
  private GreenfootSound saucerDestroy;  //GreenfootSound saucerDestroy
  
  
    /**
     * Gets the current location of the ship, and moves towards it
     * 
     * checks to see if the saucer has collided with a shield, a bubble shield
     */
    public void act() 
    {
        // Add your action code here.
        saucerDestroy = new GreenfootSound ("saucerDestroy.wav");  //initialize saucerDestroy from saucerDestroy.wav
        
        Space s = (Space)getWorld();
        fixX = s.getShipLocationX();//assign fixX to ship's x location
        fixY = s.getShipLocationY();//assign fixY to ship's y location
        
        
        
       turnTowards(fixX, fixY);//turn towards ship's location
       move (speed);//move
       
       if (checkShieldCollision())//if checkShieldCollision method is true
       {
           remove = true;
            Space m = (Space)getWorld();
            m.addPoints(scoreWorth);//add scoreWorth to total score
            saucerDestroy.play();//play saucerDestroy
        }
        
        if (checkBShieldCollision())//if checkBShieldCollision method is true
        {
            remove = true;
             Space m = (Space)getWorld();
            m.addPoints(scoreWorth);//add scoreWorth to total score
            saucerDestroy.play();//play saucerDestroy
        }
        
        if (remove == true)
        {
            getWorld().removeObject (this);//remove
        }
    }
    
    /**
     * Returns a boolean to check to see if a  Saucer has collided with a shield or not
     * 
     * @return boolean true if collision happened false otherwise
     */
    public boolean checkShieldCollision()
    {
        sld1 = (Shield)getOneIntersectingObject (Shield.class); //initialize sld1 as a Shield that is touching a Saucer
        if (sld1 == null)//if no such Shield exists
        {
            
            return false;
        }
        else
        {
         
            return true;
        }
    }
    
    /**
     * Returns a boolean to check to see if a Saucer has collided with a bubble shield or not
     * 
     * @return boolean true if collision happened false otherwise
     */
    public boolean checkBShieldCollision()
    {
        bshld1 = (BubbleShield)getOneIntersectingObject (BubbleShield.class);//initialize sld1 as a bubble Shield that is touching a  Saucer
        if (bshld1 == null)//if not such bubble shield exists
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Removes the  Saucer
     */
    public void removeMe()
    {
        getWorld().removeObject (this);  //remove Saucer
    }
  
    
    
}
