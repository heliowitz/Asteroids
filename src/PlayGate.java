import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Serves as an aesthetic element in the ChoiceScreen
 * Mimics the appearance of a WarpGate
 * 
 * Controls the method that checks if the user has selected the "play" option
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class PlayGate extends Actor
{
    /**
     * The PlayGate turns 1 degree
     * 
     * calls the checkPlay method to see if the world should be set to the space world
     */
    public void act() 
    {
        // Add your action code here.
       
        
        turn (1); // turn 1 degree
        
        if (checkPlay())  //if the checkPlay method is true
        {
            Space s = new Space();  //declare and initialize Space s
            
            Greenfoot.setWorld(s);  //sets the world as s
        }
    }  
    
    /**
     * Checks to see if a ship has collided with the PlayGate, indicating that the user wants to play the game directly
     * 
     * @return boolean true if collision exists, false otherwise
     */
    public boolean checkPlay()
    {
         Ship shipNew1 = (Ship)getOneIntersectingObject (Ship.class);  //declares and initializes Ship shipNew1 as a Ship that is touching PlayGate
         if (shipNew1 == null)  // if there is not such ship
         {
             return false;
            }
         else
         {
             return true;
            }
    }
}
