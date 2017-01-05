import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Serves as an aesthetic element in the ChoiceScreen
 * Mimics the appearance of a WarpGate
 * 
 * Controsl the method that chekcs to see if hte user has selected the "instructions" option
 * 
 * @author (Jack Ding   ) 
 * @version (June 2013)
 */
public class InstructionGate extends Actor
{
    /**
     * turns -1 degrees
     * 
     * calls the checkInsturctions method, to see if the world should be set to the instructions world
     */
    public void act() 
    {
        // Add your action code here.
        turn (-1);  //turn -1 degrees
        
        if (checkInstructions())  //if the checkInstructions method is true
        {
            Instructions is1 = new Instructions();  //declare and initialize Instructions is1
            Greenfoot.setWorld(is1);  //set world to is1
        }
    }    
    
    /**
     * Returns a boolean to see if a ship has collided with the InstructionGate
     * 
     * @return boolean true if collision happened, false otherwise
     */
    public boolean checkInstructions()
    {
        Ship shipNew2 = (Ship)getOneIntersectingObject (Ship.class);  //declare and initialize shipNew2 as a ship that collides with InstructionGate
        if (shipNew2 == null)  //if shipNew2 doesn't exist
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
