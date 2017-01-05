import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Ship is the main actor in the game. The user controls it, and the game centres around it
 * 
 * Holds the code to determine if it has collided with asteroids, saucers, line saucers, or bullet powerups
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class Ship extends Actor
{
    private ArrayList <WarpGate> theList; //declare ArrayList theList
    
    private boolean warpPair = false; //declare boolean warpPair at false
    
    private int warpTimer = 50;//declare and initialize integer warpTimer at 50
    
    private int range = 800;//delcare and initialize integer range at 800
    
    private WarpGate w1;//declare WarpGate w1
    private BulletUpgrade bu1;//declare BulletUpgrade bu1
    
    private int bulletUpgradeCounter = 0;//delcare and initialize bulletUpgradeCounter at 0
    
    private GreenfootSound teleport; //new GreenfootSound teleport
    private GreenfootSound shipDestroy; //new GreenfootSound shipDestroy
    
    public void addedToMyWorld (World m)
    {
        theList = (ArrayList <WarpGate>) getObjectsInRange (range, WarpGate.class);  //initialize theList as all WarpGates within given range
    }
    
    public void act() 
    {
        // Add your action code here.
        teleport = new GreenfootSound ("teleport.wav"); //initialize teleport from teleport.wav
        shipDestroy = new GreenfootSound ("shipDestroy.wav");//initialize shipDestroy from shipDestroy.wav
        
        bulletUpgradeCounter = bulletUpgradeCounter - 1;
        
       if (checkSaucerCollision())  //if checkSaucerCollision method is true,
       {
           Space space = (Space)getWorld();
           shipDestroy.play();//play shipDestroy sound
           Greenfoot.delay (50);
           space.endGame();//call endGame
           
       }
       
       if (checkAsteroidCollision())//if checkAsteroidColllision method is true
       {
            Space space = (Space)getWorld();
            shipDestroy.play();//play shipDestroy sound
            Greenfoot.delay (50);
           space.endGame();//call endGame
        }
        
        if (checkLineSaucerCollision())  //if checkLineSaucerCollision method is true
        {
            Space space = (Space)getWorld();
            shipDestroy.play();//play shipDestroy sound
            Greenfoot.delay (50);
           space.endGame();//call endGame
        }
        
        if (checkBulletUpgradeCollection())//if checkBulletUpgradeCollection method is true
        {
            bu1.removeMe();//calle removeMe method
            bulletUpgradeCounter = 500;//reset bulletUpgradeCounter to 500
        }
        
       

        if (checkWarp())  //if checkWarp method is true
        {
            warpTimer = warpTimer - 1; //warpTimer decrease by 1
            
            
        

            if (warpTimer <= 0 && Greenfoot.isKeyDown("w"))//if warpTimer is smaller or equal to 0 and w is pressed
            { 
                warpTimer = warpTimer - 1;
                WarpGate w1 = (WarpGate)getOneIntersectingObject (WarpGate.class);
                if (w1.getPair())
                {
               setLocation (w1.getTransportX(),w1.getTransportY());//set new location of ship
               teleport.play();
               warpTimer = 50;//reset warpTimer to 50
            }
                
            }
        
    }
       
     
    }    
    
    /**
     * Shoot produces ShipBullets from the Ship's location
     */
    public void shoot ()
    {
       
        if (bulletUpgradeCounter > 0)  //If the Ship is currently with the upgrade counter
        {
            //add 5 bullets in different angles
            ShipBullet bul1 = new ShipBullet (getRotation());
            ShipBullet bul2 = new ShipBullet (getRotation()-10);
            ShipBullet bul3 = new ShipBullet (getRotation() +10);
            ShipBullet bul4 = new ShipBullet (getRotation() - 20);
            ShipBullet bul5 = new ShipBullet (getRotation() + 20);
            
            getWorld().addObject (bul1, getX(), getY());
            getWorld().addObject (bul2, getX(), getY());
            getWorld().addObject (bul3, getX(), getY());
            getWorld().addObject (bul4, getX(), getY());
            getWorld().addObject (bul5, getX(), getY());
        }
        
        ShipBullet L = new ShipBullet (getRotation()); //add bullet 

        getWorld().addObject (L, getX(), getY());
        
      
        
   
    }
    
    /**
     * check to see if the ship hit a saucer
     * 
     * @return boolean true if hit saucer, false otherwise
     */
    public boolean checkSaucerCollision()
    {
        Saucer s2 = (Saucer)getOneIntersectingObject (Saucer.class);
        if (s2 == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * check to see if the ship hit a LineSaucer
     * 
     * @return boolean true if hit line saucer, false otherwise
     */
    public boolean checkLineSaucerCollision()
    {
        LineSaucer ls2 = (LineSaucer) getOneIntersectingObject (LineSaucer.class);
        if (ls2 == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Check to see if the ship hit any of the 3 asteroids
     * 
     * @return boolean true if hit any of the 3 types of asteroids, false otherwise
     */
    public boolean checkAsteroidCollision()
    {
        Asteroid1 a1 = (Asteroid1)getOneIntersectingObject (Asteroid1.class);
        Asteroid2 a2 = (Asteroid2)getOneIntersectingObject (Asteroid2.class);
        Asteroid3 a3 = (Asteroid3)getOneIntersectingObject (Asteroid3.class);
        
        if (a1 == null && a2 == null && a3 == null)
        {
            return false;
        }
        else
        {
            return true; 
        }
    }
    
    /**
     * Check to see if the ship can warp
     * 
     * @return boolean true if w1 is touching a ship, false otherwise
     */
    public boolean checkWarp()
    {
        WarpGate w1 = (WarpGate)getOneIntersectingObject (WarpGate.class);
        
        if (w1 == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Check to see if the ship has collected a bullet upgrade
     * 
     * @return boolean true if there is a bullet upgrade touching the ship, false otherwise
     */
    public boolean checkBulletUpgradeCollection()
    {
         bu1 = (BulletUpgrade)getOneIntersectingObject (BulletUpgrade.class);
        
        if (bu1 == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public int getXS()  //gets the x coordinate
    {
        return getX();
    }
    
    public int getYS()  //gets the y coordinate
    {
        return getY();
    }
    
    
}










