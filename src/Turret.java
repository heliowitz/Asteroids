import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
/**
 * The turret finds all of the saucers that exist on the screen and then determines the closest one, and shoots at it
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */

public class Turret extends Actor
{
    /**
     * Act - do whatever the Turret wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Saucer target; //declare Saucer target
    private double shortestDistance = 800;  //declare double shorestDistance
    private int range = 800;
    private ArrayList <Saucer> myList ;//declare ArrayList myList
    private int shootTimer = 20;
    
    private int ammo = 50;//can only shoot 50 ammo
    
    private boolean remove = false;

    public void addedToWorld(World w)
    {
        myList = (ArrayList<Saucer>) getObjectsInRange (range, Saucer.class);//initialize myList
    }
    
    /**
     * For each act, obtains all saucers on the screen, and determines which one is the closest, and then shoots at it
     */
    public void act() 
    {
        // Add your action code here.
        shootTimer --;

        myList = (ArrayList<Saucer>) getObjectsInRange (range, Saucer.class);
        
        for (Saucer o : myList)
        {
            if ( o.getWorld() != null)
            {
                Saucer s = (Saucer)o;
                double distance = getDistance (this, s);//calculate distance betwen turret and saucer target
                if (distance < shortestDistance)//if it's the smallest distance so far
                {
                    this.target = s;//becomes the new target
                    shortestDistance = distance;//the distance becomes the shortest distance
                }

                if (s != null)//if there are saucers to shoot
            {
                /////Truns the turret to the desired target
                turnTowards (s.getX(), s.getY());
                turnTowards (s.getX(), s.getY());
                turnTowards (s.getX(), s.getY());
                turnTowards (s.getX(), s.getY());
                turnTowards (s.getX(), s.getY());
                turnTowards (s.getX(), s.getY());
                turnTowards (s.getX(), s.getY());
                turnTowards (s.getX(), s.getY());
                turnTowards (s.getX(), s.getY());
                turnTowards (s.getX(), s.getY());
                turnTowards (s.getX(), s.getY());
                turnTowards (s.getX(), s.getY());
                
                
                
                if (shootTimer <= 0 && ammo > 0 )
        
        {
            
        shoot(getRotation());
        shootTimer = 20;
        ammo--;
    }
            }
            
            else if (s == null)//if there are no saucers
            {
                turn (2);
            }
            
            }
            
        }
        
        

        if (ammo <= 0)//if all ammo is gone
        {
            remove = true;
        }
    
        if (remove == true)
        {
            
            getWorld().removeObject (this);
        }
    
    }  

    /**
     *Calcuates distance between actor a and actor b
     *uses pythagorean theorum
     *
     *@return double the distance between the actors
     */
    private double getDistance (Actor a, Actor b)
    {
        double distance;
        distance = Math.sqrt(Math.pow(Math.abs(a.getX() - b.getX()), 2) + (Math.pow(Math.abs(a.getY() - b.getY()),2)));  
        return distance;
    }

    /**
     * Shoots turret bullets by spawning them at the location of the turret
     * 
     */
    private void shoot (int rotation)
    {
        TurretBullet L = new TurretBullet (getRotation());

        getWorld().addObject (L, getX(), getY());

    }

}
