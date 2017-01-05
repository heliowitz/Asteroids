import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Is the Warp Gate class that creates the Warp Gate defense feature that can be build
 * The WarpGate class detects if there are surrouding WarpGates, and allows the ship to change its location to another WarpGate
 * 
 * Holds the code that determines if any other WarpGates are on the screen, and determines WarpGate that is closest 
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class WarpGate extends Actor
{
    WarpGate pair;  //declare WarpGate called pair
    private double shortestDistance = 800;  //declare and initialize double shortestDistance at 800
    private int range = 800;  //declare and initialize integer range at 800
    private ArrayList <WarpGate> myList;  //declare ArrayList called myList
    
    private int transportX; //declare integer transportX
    private int transportY;  //declare integer transportY
    private boolean pairExist = false;  //declare and initialize boolean pairExst at false
    
    private int gateLast = 9001;  //declare and initialize integer gateLast at 9001
    private boolean remove = false;  //declare and initialize boolean remove at false
    
    public void addedToWorld(World w)
    {
        myList = (ArrayList<WarpGate>) getObjectsInRange (range, WarpGate.class);  //initializing myList
    }
    
    /**
     * WarpGate rotates -1 degrees
     * WarpGate checked to see if it should still remain on the screen
     * 
     * Examines all other WarpGates on the screen, and determines which one is closes 
     */
    public void act() 
    {
        // Add your action code here.
        turn (-1);  //turn -1 degrees
        gateLast = gateLast - 1;  //decrease gateLast integer by one
        
        if (gateLast <=0)  //if gateLast is smaller than or equal to 0
        {
            remove = true;  //set remove to true
        }
        
        myList = (ArrayList<WarpGate>) getObjectsInRange (range, WarpGate.class);  //initializing myList as all WarpGates in range
        
        for (WarpGate w: myList)  //for all WarpGates in myList
        {
            if (w.getWorld() != null)  //if there are other WarpGates
            { 
              
                double distance = getDistance (this, w);  //initialize distance as the distance between the observed WarpGate and this one
                if (distance < shortestDistance) //if distance is smaller than the shortestDistance
                {
                    this.pair = w;  //assigns pair to the observed WarpGate
                    shortestDistance = distance;  //assigns shorestDistance to distance
                }
                
                if (w != null)  //if there are more than 2 WarpGates on the screen
                {
                    transportX = w.getX();  //assign transportX to the observed WarpGate
                    transportY = w.getY();  //assigns transportY to the observed WarpGate
                    pairExist = true;  //set pairExist to true
                }
                
                if (w == null) // if there is only 1 WarpGate on the screen or none
                {
                    pairExist = false;  //set pairExist to false
                }
            }
    }
    
    if (remove == true)  //if remove is true
    {
        getWorld().removeObject (this);  //remove WarpGate
    }
}
    
    /**
     * Returns a double that is the distance between 2 different actors
     * Uses the pythagorean theorum 
     * 
     * @return double the distance between actor a and actor b
     */
     public double getDistance (Actor a, Actor b)
    {
        double distance;  //declare double distance
        distance = Math.sqrt(Math.pow(Math.abs(a.getX() - b.getX()), 2) + (Math.pow(Math.abs(a.getY() - b.getY()),2)));  //the pythagorean theorum, and assigns the value to distance
        return distance;
    }
    
    /**
     * Returns the variable transportX
     * 
     * @return int transportX
     */
    public int getTransportX()
    {
        return transportX;
    }
    
    /**
     * Returns the variable transportY
     * 
     * @return int transportY
     */
    public int getTransportY()
    {
        return transportY;
    }
    
    /**
     * Returns the boolean pairExist
     * 
     * @return boolean pairExist
     */
    public boolean getPair()
    {
        return pairExist;
    }
    
}
