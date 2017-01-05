import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Provides players with a choice to either play immediately or to follow and read the instructions first
 * uses the ship as the cursor
 * 
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class ChoiceScreen extends World
{
    private Ship shipNew;  //declaring new Ship called shipNew;
    private int shipSpeed = 4;  //declaring and initializing integer shipSpeed at 4
    private int shootTimer = 10;  //declaring and initializing integer shootTimer at 10
    
    
    
    public ChoiceScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 450, 1); 
        
        shipNew = new Ship();  //initializing shipNew as a new Ship
        addObject (shipNew, 400, 300);  //adding the object at the center of the screen
        
        PlayGate playGate = new PlayGate();  //declaring and initializing new playGate called playGate
        addObject (playGate, 92, 288);  //adding the object at left side of the screen
        
        InstructionGate instructionGate = new InstructionGate();  //declaring and initializing new instructionGate called instructionGate
        addObject (instructionGate, 671, 288);  //adding the object at the right side of the screen
       
    }
    
    /**
     * every act, the checkShipKeys method is called to see if the user wishes to move the ship
     */
    public void act()
    {
         checkShipKeys();
 
    }
    
    /**
     * checks if the user wants to move the ship
     */
     public void checkShipKeys()
    {
   
            if (Greenfoot.isKeyDown("right"))  //if right key is down
        {
            shipNew.turn(4);  //turn right
        }
        if (Greenfoot.isKeyDown("left"))  //if left key is down
        {
            shipNew.turn(-4);  //turn left
        }
        if (Greenfoot.isKeyDown("up"))  //if up key is down
        {
            shipNew.move(shipSpeed);  //move forward at the speed of integer shipSpeed
        }
    }       
    }

