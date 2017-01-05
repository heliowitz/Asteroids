import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Is one of the powerups that the shield can collect 
 * Vaporizes all asteroids and saucers that touch it, and disappears after a set amount of time
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class BubbleShield extends Actor
{
    private Ship ship1;  //declaring new Ship ship1
    
    private int shieldCounterTimer = 700;  //declaring and initializing new shieldCOunterTimer at 700
    private boolean used = false;  //declaring and initializing new booolean used at false
    
    /**
     * every act, the shieldCounterTimer is decreased by 1
     * calls the checkCollected method to see if the ship has touched the bubble shield
     * 
     * checks to see when the Bubble Shield runs out
     */
    public void act() 
    {
        // Add your action code here.
        shieldCounterTimer --; //decrease shieldCounterTimer by 1
        
         Ship ship1 = (Ship)getOneIntersectingObject (Ship.class);  //initialize ship1 as a ship that is touching the BubbleShield
        
         checkCollected();  //calls the checkCollected method
        
        if (used == true)  //if used is true
        {
            setLocation (ship1.getXS(), ship1.getY());  //set the location of the BubbleShield at the ship's location
        }
        
        if (shieldCounterTimer <=0)  //if the shieldCounterTimer is smaller than or equal to 0
        {
            getWorld().removeObject(this);  //remove the bubble shield
        }
    }    
    
    /**
     * checks to see if the BubbleShield has been collected by the ship
     * If so, it sets the used boolean to true
     */
    public void checkCollected()
    {
        Ship ship1 = (Ship)getOneIntersectingObject (Ship.class); //initialize ship1 as a ship that is touching the BubbleShield
        
        if (ship1 == null)// if there is no ship that is touching the bubble shield
        {
            used = false;   //set used to false
        }
        
        else  //else
        {
            used = true; //set used to true
        }
    }
}
