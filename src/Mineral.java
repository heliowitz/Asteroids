import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Mineral class holds the code for the mineral, which is essential in order for the play to be able to build different structures and defenses
 * Minerals are collected by destroying asteroids
 * 
 * Holds the code to determine if the mineral has been collected by the ship
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class Mineral extends Actor
{
    private int turnSpeed;//declare integer turnSpeed
    private Ship sh1;  //declare Ship sh1

    
    private boolean remove = false;  //declare and initialize boolean remove at false
    
    private int mineralWorth = 1;  //declare and initialize integer mineralWorth at 1
    
    private GreenfootSound mineral;  //declare new GreenfootSound mineral
    
    public Mineral(int turn)
    {
        this.turnSpeed = turn;  //assign turnSpeed to turn
        
        mineral = new GreenfootSound ("mineral.wav");  //initialize mineral as new GreenfootSound from mineral.wav
    }
    
    /**
     * every act, it turns
     * checks to see if it has been collected by a ship
     */
    public void act() 
    {
       // mineralTimer = mineralTimer - 1;
        turn (turnSpeed);//turn 
        
        if (checkShipCollection())//if checkShipColletion method is true
        {
            mineral.play();  //play mineral sound file
            removeMe();  //call removeMe method
        }
    }  
    
    /**
     * checks to see if the mineral has been collected by the ship or not
     * 
     * @return boolean true if collided with ship, false otherwise
     */
    public boolean checkShipCollection()  
    {
        sh1 = (Ship)getOneIntersectingObject (Ship.class); //initializes sh1 as a ship that is touching mineral
        if (sh1 == null)// if sh1 does not exist
        {
            return false;
        }
        else
        {
            Space m = (Space)getWorld();
            m.addMinerals(mineralWorth);//add mineralWorth to total minerals
            return true;
        }
    }
    
    /**
     * Removes mineral
     */
    public void removeMe()
    {
         getWorld().removeObject (this);   //removes mineral
    }
    
    
}
