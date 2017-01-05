import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Submission for Mr.Cohen's final Greenfoot Game Assignment
 * 
 * This program, called "Deep Space Defenders", is an Asteroids-style game which also combines elements of tower defense.
 * The player must control a spaceship to destroy and avoid enemy saucers and asteroids, while simultaneously collecting minerals to build turrets, as well as helpful powerups
 * 
 * Known bugs: 
 * 
 * Credit to Neil Brown and Michael Kolling for the Counter class
 *
 *
 * @author Jack Ding
 * @verision June 2013
 */



public class Space extends World
{   
    private int shipSpeed = 4; //Declaring integer that determines the ship's speed
    private int shootTimer = 10;  //Declaring integer that determines how fast the ship can shoot
    
    private Counter pointCounter;  //Declaring Counter that keeps track of the overall score
    private Counter mineralCounter;  //Declaring Counter that keeps track of how many minerals the player currently has
    private Counter HPCounter;  //Declaring Counter that keeps track of how much HP the player has left
    
    private int saucerSpawnRate = 200;  //Declaring integer that determines how fast saucers spawn
    private int asteroidSpawnRate = 300;   //Declaring integer that determines how fast asteroids spawn
    
    private int turretSpawnCounter = 100;  //Declaring integer that determines how long it takes to build a turret
    private int shieldSpawnCounter = 100;  //Declaring integer that determines how long it takes to build a shield
    private int warpSpawnCounter = 175;  //Declaring integer that determines how long it takes to build a warp gate

    private Ship ship;  //Delcaring new Ship called ship
    
    private int difficultyCounter = 0;  //Declaring integer that determines how long the player has played the game, thus determining suitable difficulty
    
    private ActionBar action;  //Declaring new ActionBar called action to keep track of what the ship is building
    
    private Tab tab;  //Declaring new Tab called tab to show the player the building costs 
    private boolean tabUp = false;  //Declaring new boolean called tabUp to determine is the tab is already up or not
    private int tabTimer = 10;  //Declaring new integer called tabTimer that helps to mimic a "isPressed" method instead of "isDown"
    
    private GreenfootSound beat;  //Declaring new GreenfootSound called beat as the underlying music
    private GreenfootSound move;  //Declaring new GreenfootSound called move for when the ship is moving forwards
    private GreenfootSound shoot;  //Declaring new GreenfootSound called shoot for when the ship is shooting
    private GreenfootSound build;  //Declaring new GreenfootSound called build for when the ship is building
    private GreenfootSound finishBuild;  //Declaring new GreenfootSound called finishBuild for when the ship is finished building something

    public Space()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 450, 1); 
        
        ship = new Ship();  //initializing new Ship called ship
        addObject (ship, 400,300);  //adding ship to its default location at the center of the screen
        
        pointCounter = new Counter ("Score: ");  //initializing new Counter called pointCounter
        addObject (pointCounter, 35, 15);  //adding pointCounter to the top left corner of the screen
        
        mineralCounter = new Counter ("Mined: ");  //initializing new Counter called mineralCounter
        addObject (mineralCounter, 765, 15);  //adding mineralCounter to the top right corner of the screen
        
        HPCounter = new Counter ("HP: ");  //initializing new Counere called HPCounter
        addObject (HPCounter, 110, 15);  //adding HPCounter to the top left corner of the screen beside pointCounter
        HPCounter.setValue(100);  //setting the original value of HP to 100 points
        
        action = new ActionBar ("");  //initializing new ActionBar called action
        addObject (action, 400, 15);  //adding action to the top center of the screen
        
        tab = new Tab();  //initializing new Tab called tab
        
        beat = new GreenfootSound("beat.wav");  //initializing the beat sound from beat.wav
        move = new GreenfootSound("move.wav");  //initializing the move sound from move.wav
        shoot = new GreenfootSound ("shoot.wav");  //initializing the shoot sound from shoot.wav
        build = new GreenfootSound ("build.wav");  //initializing the build sound from build.wav
        finishBuild = new GreenfootSound ("finishBuild.wav");  //initializing the finishBuild sound from finishBuild.wav
    }
    
    /**
     * Every act, the checkShipKeys method is called to see if the player wishes to move the ship or build a turret or warp 
     * The shootTimer integer is decreased in order to control the number of bullets able to be shot in a span of time
     * The tabTimer integer if decreased in order to mimic a "isPressed" method
     * The difficultyCounter integer increases, signalling that the difficulty of the game should be escalating as time goes by
     * 
     * Ensures that the beat music is still playing
     * Ensures that the spawn rate of saucers matches the difficulty counter of the game and is gradually increasing
     * 
     * Calls the spawn methods of saucers, asteroids, powerups, and line saucers
     * 
     * Controls the toggling of the tab for players to keep track of defenses
     * 
     */
    public void act()
    {
       checkShipKeys();  //calls the checkShipKeys method to check for movement, building, or warping
       shootTimer--;  //decreases the integer shootTimer by 1, helping to limit the number of bullets that can be shot in a span of time
       tabTimer --;  //decreases the integer tabTimer by one to mimic a "isPressed" method
       difficultyCounter++;  //increases the difficultyCounter integer by one to simulate an increase in difficulty
         
       if (!(beat.isPlaying())) //checks if the beat is not playing
       {
          beat.play();  //if the beat is not playing, play the beat sound file
       }
         
       if (difficultyCounter % 2000 == 0)  //checks to see if the difficultyCounter has increased by 2000
       { 
             saucerSpawnRate = saucerSpawnRate - 5;  //for every 2000 acts, the spawn rate for saucers goes down, thus increasing the number of saucers spawned
       }
       
        randomSpawnSaucer();  //calls the randomSpawnSaucer method to randomly spawn saucers onto the screen
        randomSpawnAsteroid1();  //calls the randomSpawnAsteroid1 method to randomly spawn asteroids onto the screen
        randomSpawnAsteroid2();  //calls the randomSpawnAsteroid2 method to randomly spawn asteroids onto the screen
        randomSpawnAsteroid3();  //calls the randomSpawnAsteroid3 method to randomly spawn asteroids onto the screen
        
        randomSpawnBubbleShield();  //calls the randomSpawnBubbleShield method to randomly spawn bubble shield upgrades
        randomSpawnBulletUpgrade();  //calls the randomSpawnBulletUpgrade method to randomly spawn bullet upgrades 
        
        if (difficultyCounter % 3500 == 0)  //for every 3500 increases in the difficultyCounter
        {
            randomSpawnLineSaucerRight();  //calls the randomSpawnLineSaucerRight method to spawn a line of saucers 
        }
        
        if (difficultyCounter % 4500 == 0)  //for every 4500 increases in the difficultyCounter
        {
            randomSpawnLineSaucerLeft();  //calls the randomSpawnLineSaucerRight method to spawn a line of saucers 
        }
        
        if (difficultyCounter % 5500 == 0)  //for every 5500 increases in the difficultyCounter
        {
            randomSpawnLineSaucerUp();  //calls the randomSpawnLineSaucerRight method to spawn a line of saucers 
        }
        
        if (difficultyCounter % 6500 == 0)  //for every 6500 increases in the difficultyCounter
        {
            randomSpawnLineSaucerDown();  //calls the randomSpawnLineSaucerRight method to spawn a line of saucers 
        }
        
        if (Greenfoot.isKeyDown("shift") && tabTimer <= 0)  //if the shift key is down and the tabTimer is smaller than or equal to 0
        {
            if (tabUp == false)  //if tabUp is false, meaning that the tab is currently down
            {
                addObject (tab, 568, 575);  //add the tab at the bottom right of the screen
                tabUp = true;  //set tabUp to true
                tabTimer = 10;  //reset the tabTimer integer
            }
            
            else if (tabUp == true)  //if tabUp is true, meaning that the tab is currently up
            {
                removeObject (tab);  //remove the tab from the screen
                tabUp = false;  //set tabUp to false
                tabTimer = 10;  //reset the tabTimer integer
            }       
        }   
    } 
    
    /**
     * Checks the keys for user input, and also sets the action bar to the necessary message for building
     * Checks for if the user wishes to move the ship, or build a defense 
     * 
     */
    public void checkShipKeys()
    {
        
        action.setPrefix ("");  //sets the ActionBar action to nothing as its default state
        
        if (Greenfoot.isKeyDown ("1") && mineralCounter.getValue() >= 5)  //if the 1 key is downm and the user has 5 or more minerals
        {
            action.setPrefix ("Building Turret"); // sets the ActionBar to read building turret
         
               turretSpawnCounter = turretSpawnCounter - 1;  //decreases the turretSpawnCounter integer by 1
               build.play();  //play the build sound file
               
               if (turretSpawnCounter <= 0)  //if the turretSpawnCounter reaches 0 or smaller
               {
                   addObject (new Turret(), ship.getX()-16, ship.getY()-16);  //add a new Turret beside the ship's location
                   mineralCounter.setValue(mineralCounter.getValue() - 5);  //subtracts 5 minerals from the current mineral count
                   turretSpawnCounter = 100;  //resets the turretSpawnCounte integer to 100
                   action.setPrefix ("Turret Complete");  //sets the ActionBar to read turret complete
                   finishBuild.play();  //play the finishBuild sound file
               }
        }
        
        if (Greenfoot.isKeyDown ("2") && mineralCounter.getValue() >= 5)  //if the 2 key is down and the user thas 5 or more minerals
        {
            action.setPrefix ("Building Shield");  //sets the ActionBar to read building shield
           
                shieldSpawnCounter = shieldSpawnCounter - 1;  //decreases the shieldSpawnCounter integer by 1
                build.play();  //play the build sound file
                
                if (shieldSpawnCounter <= 0)  //if the shieldSpawnCounter integer reaches 0 or smaller
                {
                    addObject (new ShieldCore(), ship.getX(), ship.getY());  //add a new ShieldCore at the ship's location
                    addObject (new Shield(), ship.getX(), ship.getY());  //add a shield at the ship's location
                    mineralCounter.setValue(mineralCounter.getValue() - 5);  //take away 5 minerals from the user
                    shieldSpawnCounter = 100;  //resets the shieldSpawnCounter integer
                    action.setPrefix ("Shield Complete");  //set action to read shield complete
                    finishBuild.play();  //play the finishBuild sound filie
                }  
        }
        
        if (Greenfoot.isKeyDown ("3") && mineralCounter.getValue() >= 5)
        {
            warpSpawnCounter = warpSpawnCounter - 1;
            action.setPrefix ("Building Warp Gate");
            build.play();
            if (warpSpawnCounter <= 0)
            {
                addObject (new WarpGate(), ship.getX()+16, ship.getY()+16);
                warpSpawnCounter = 175;
                 mineralCounter.setValue(mineralCounter.getValue() - 5);
                action.setPrefix ("Warp Gate Complete");
                finishBuild.play();
            }
        }
        
        if ((!(Greenfoot.isKeyDown("1")) && (!(Greenfoot.isKeyDown("2")))  && (!(Greenfoot.isKeyDown("3")))))  //if the user is not trying to build anything
        {
             turretSpawnCounter = 100;  //resets turretSpawnCounter
            shieldSpawnCounter = 100;  //resets shieldSpawnCounter
            warpSpawnCounter = 175;  //resets warpSpawnCounter
            
            if (Greenfoot.isKeyDown("right"))  //if the right arrow key is down
            {
                ship.turn(4);  //turn right
            }
            if (Greenfoot.isKeyDown("left"))  //if the left arrow key is down
            {
                ship.turn(-4);  //turn left
            }
            if (Greenfoot.isKeyDown("up"))  //if the up arrow key is down
            {
                ship.move(shipSpeed);  //move at the shipSpeed integer 
                move.play();  //play the move sound file 
            }
      
            if (Greenfoot.isKeyDown ("space") && shootTimer <= 0)  //if the space key is down and the shootTimer is smaller than or equal to 0
            {
                ship.shoot();  //call the shoot method for the Ship
                shootTimer = 10;  //resets the shootTimer to 10
                shoot.play();  //plays the shoot sound file
            }
        
            if (!(Greenfoot.isKeyDown("up")))  //if the up arrow key is not down
            {
                move.stop();  //stop playing the move sound file 
            }    
    }
    }
    
    /**
     * Randomly generates saucers from the sides of the screen
     */
    public void randomSpawnSaucer()
    {
        int xS;  //declares an integer xS
        int yS;  //dcleares an integer yS
        
        if (Greenfoot.getRandomNumber(saucerSpawnRate) == 1)  //if a random number within the range of the saucerSpawnRate is 1
        {
            if (Greenfoot.getRandomNumber(4) == 0)  //if a random number between 0 and 3 is 0
            {
                xS = (Greenfoot.getRandomNumber(801));  //xS is set as a random number between 0 and 800
                addObject (new Saucer(), xS, 0);  //adds the saucer, which will be at the top of the screen
            }
            else if (Greenfoot.getRandomNumber(4) == 1)  //if a random number between 0 and 3 is 1
            {
                yS = (Greenfoot.getRandomNumber(601)); //yS is set as a random number between 0 and 600
                addObject (new Saucer(), 0, yS); //adds the saucer, which will be at the left of the screen
            }
            else if (Greenfoot.getRandomNumber(4) == 2)  //if a random number between 0 and 3 is 2
            {
                yS = (Greenfoot.getRandomNumber(601));  //yS is set as a random number between 0 and 600
                addObject (new Saucer(), 800, yS);  //adds the saucer, which will be at the right of the screen
            }
            else if (Greenfoot.getRandomNumber(4) == 3)  //if a random number between 0 and 3 is 3
            {
                xS = (Greenfoot.getRandomNumber(801));   //xS is set as a random number between 0 and 800
                addObject (new Saucer(), xS, 600);  //adds the saucer, which will be at hte bottom of the screen
            }
        }
    }
    
    /**
     * generates a line of LineSaucers which will move from the right to the left
     */
    public void randomSpawnLineSaucerRight()
    {
        addObject (new LineSaucer(180), 800, 50);  //adding LineSaucer 
        addObject (new LineSaucer(180), 800, 80);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 110);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 140);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 170);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 200);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 230);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 260);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 290);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 320);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 350);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 380);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 410);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 440);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 470);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 500);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 530);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 560);//adding LineSaucer 
        addObject (new LineSaucer(180), 800, 590);//adding LineSaucer 
    }
    
    /**
     * generates a line of LineSaucers which will move from the left to the right
     */
    public void randomSpawnLineSaucerLeft()
    {
        addObject (new LineSaucer(0), 0, 52);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 82);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 112);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 142);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 172);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 202);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 232);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 262);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 292);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 322);//adding LineSaucer 
         addObject (new LineSaucer(0), 0, 352);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 382);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 412);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 442);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 472);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 502);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 532);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 562);//adding LineSaucer 
        addObject (new LineSaucer(0), 0, 592);//adding LineSaucer 
   
    }
    
    /**
     * generates a line of LineSaucers which will move from the top to the bottom
     */
    public void randomSpawnLineSaucerUp()
    {
        addObject (new LineSaucer(90),23 , 0);//adding LineSaucer 
        addObject (new LineSaucer(90),53 , 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 83, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 113, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 143, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 173, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 203, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 233, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 263, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 293, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 323, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 353, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 383, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 413, 0);//adding LineSaucer 
        addObject (new LineSaucer(90),443 , 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 473, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 503, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 533, 0);//adding LineSaucer 
        addObject (new LineSaucer(90),563 , 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 593, 0);//adding LineSaucer 
        addObject (new LineSaucer(270),623 , 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 653, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 683, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 713, 0);//adding LineSaucer 
        addObject (new LineSaucer(90), 743,0);//adding LineSaucer 
        addObject (new LineSaucer(90), 773,0);//adding LineSaucer 
        addObject (new LineSaucer(90), 800, 0);//adding LineSaucer 
    }
    
    /**
     * generates a line of LineSaucers which will move from the bottom to the top
     */
    public void randomSpawnLineSaucerDown()
    {
        addObject (new LineSaucer (270), 20, 600);//adding LineSaucer 
        addObject (new LineSaucer(270),50 , 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 80, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 110, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 140, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 170, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 200, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 230, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 260, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 290, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 320, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 350, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 380, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 410, 600);//adding LineSaucer 
        addObject (new LineSaucer(270),440 , 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 470, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 500, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 530, 600);//adding LineSaucer 
        addObject (new LineSaucer(270),560 , 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 590, 600);//adding LineSaucer 
        addObject (new LineSaucer(270),620 , 600);//adding LineSaucer
        addObject (new LineSaucer(270), 650, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 680, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 710, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 740, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 770, 600);//adding LineSaucer 
        addObject (new LineSaucer(270), 800, 600);//adding LineSaucer 
    }
    
    /**
     * gets the ship's current location at Y
     * 
     * @return int  the ship's Y coordinate
     */
    public int getShipLocationY()
    {
        return ship.getY();  //returns the ship's Y coordinate
    }
    
     /**
     * gets the ship's current location at X
     * 
     * @return int  the ship's X coordinate
     */
    public int getShipLocationX()
    {
        return ship.getX();  //returns's the ship's X coordinate
    }
    
    /**
     * randomly spawns the first type of asteroid
     */
    public void randomSpawnAsteroid1()
    {
        int xA1;  //declaring new integer xA1
        int yA1;  //declaring new integer yA1
        
        if (Greenfoot.getRandomNumber (asteroidSpawnRate) == 1)  //if a randomly generated number within the range of the asteroidSpawnRate is 1
        {
        if (Greenfoot.getRandomNumber(4) == 0)  //if a randomly generated number between 0 and 3 is 0
            {
                xA1 = (Greenfoot.getRandomNumber(799)+1);  //xA1 is set as a randomly generated number between 1 and 799
                addObject (new Asteroid1(Greenfoot.getRandomNumber(801), 600), xA1, 1);  //adds asteroid that moves top to bottom
            }
            else if (Greenfoot.getRandomNumber(4) == 1)  //if a randomly generated number between 0 and 3 is 1
            {
                yA1 = (Greenfoot.getRandomNumber(599)+1);  //yA1 is set as a randomly generated number between 1 and 599
                addObject (new Asteroid1(Greenfoot.getRandomNumber(601), 800), 1, yA1);  //adds asteroid that moves from left to right
            }
            else if (Greenfoot.getRandomNumber(4) == 2)  //if a randomly generated number btween 0 and 3 is 2
            {
                yA1 = (Greenfoot.getRandomNumber(599)+1);  //yA1 is set as a randomly generated number between 1 and 599
                addObject (new Asteroid1(0, Greenfoot.getRandomNumber(601)), 799, yA1);  //adds asteroid that moves from right to left
            }
            else if (Greenfoot.getRandomNumber(4) == 3)  //if a randomly generated number between 0 and 3 is 3
            {
                xA1 = (Greenfoot.getRandomNumber(799)+1);  //xA1 is set as a randomly generated number between 1 and 799
                addObject (new Asteroid1(Greenfoot.getRandomNumber(801), 0), xA1, 599);  //adds asteroid that moves from bottom to top
            }
        }
    }
    
     /**
     * randomly spawns the second type of asteroid
     */
    public void randomSpawnAsteroid2()
    {
        int xA2;//declaring new integer xA2
        int yA2;//declaring new integer yA2
        
         if (Greenfoot.getRandomNumber (asteroidSpawnRate) == 1)  //if a randomly generated number within the range of the asteroidSpawnRate is 1
        {
            if (Greenfoot.getRandomNumber(4) == 0)  //if a randomly generated number between 0 and 3 is 0
            {
                xA2 = (Greenfoot.getRandomNumber(799)+1);  //xA2 is set as a randomly generated number between 1 and 799
                addObject (new Asteroid2(Greenfoot.getRandomNumber(801), 600), xA2, 1);  //adds asteroid that moves top to bottom
            }
            else if (Greenfoot.getRandomNumber(4) == 1)  //if a randomly generated number between 0 and 3 is 1
            {
                yA2 = (Greenfoot.getRandomNumber(599)+1);  //yA2 is set as a randomly generated number between 1 and 599
                addObject (new Asteroid2(Greenfoot.getRandomNumber(601), 800), 1, yA2);    //adds asteroid that moves from left to right
            }
            else if (Greenfoot.getRandomNumber(4) == 2)  //if a randomly generated number btween 0 and 3 is 2
            {
                yA2 = (Greenfoot.getRandomNumber(599)+1);  //yA2 is set as a randomly generated number between 1 and 599
                addObject (new Asteroid2(0, Greenfoot.getRandomNumber(601)), 799, yA2);  //adds asteroid that moves from right to left
            }
            else if (Greenfoot.getRandomNumber(4) == 3)  //if a randomly generated number between 0 and 3 is 3
            {
                xA2 = (Greenfoot.getRandomNumber(799)+1);  //xA2 is set as a randomly generated number between 1 and 799 
                addObject (new Asteroid2(Greenfoot.getRandomNumber(801), 0), xA2, 599);   //adds asteroid that moves from bottom to top
            }
        }
    }
    
     /**
     * randomly spawns the third type of asteroid
     */
    public void randomSpawnAsteroid3()
    {
        int xA3;  //declaring new integer xA3
        int yA3;  //declaring new integer yA3
        
         if (Greenfoot.getRandomNumber (asteroidSpawnRate) == 1)  //if a randomly generated number within the range of the asteroidSpawnRate is 1
        {
            if (Greenfoot.getRandomNumber(4) == 0)  //if a randomly generated number between 0 and 3 is 0
            {
                xA3 = (Greenfoot.getRandomNumber(799)+1);  //xA3 is set as a randomly generated number between 1 and 799
                addObject (new Asteroid1(Greenfoot.getRandomNumber(801), 600), xA3, 1);  //adds asteroid that moves top to bottom
            }
            else if (Greenfoot.getRandomNumber(4) == 1)  //if a randomly generated number between 0 and 3 is 1
            {
                yA3 = (Greenfoot.getRandomNumber(599)+1);  //yA3 is set as a randomly generated number between 1 and 599
                addObject (new Asteroid1(Greenfoot.getRandomNumber(601), 800), 1, yA3);      //adds asteroid that moves from left to right
            }
            else if (Greenfoot.getRandomNumber(4) == 2)  //yA3 is set as a randomly generated number between 1 and 599
            {
                yA3 = (Greenfoot.getRandomNumber(599)+1);  //yA3 is set as a randomly generated number between 1 and 599
                addObject (new Asteroid1(0, Greenfoot.getRandomNumber(601)), 799, yA3);  //adds asteroid that moves from right to left
            }
            else if (Greenfoot.getRandomNumber(4) == 3)  //if a randomly generated number between 0 and 3 is 3
            {
                xA3 = (Greenfoot.getRandomNumber(799)+1);   //xA3 is set as a randomly generated number between 1 and 799
                addObject (new Asteroid1(Greenfoot.getRandomNumber(801), 0), xA3, 599);    //adds asteroid that moves from bottom to top
            }
        }
    }
    
    /**
     * randomly generates bubble shield powerups
     */
    public void randomSpawnBubbleShield()
    {
        if (difficultyCounter % 3800 == 0)  //if the difficultyCounter has increased by 3800
        {
        addObject (new BubbleShield(), Greenfoot.getRandomNumber(801), Greenfoot.getRandomNumber (601));  //add a new BubbleShield, at a random location on the screen 
    }
    }
    
    /**
     * randomly gnerates bullet upgrade powerups
     */
    public void randomSpawnBulletUpgrade()
    {
        if (difficultyCounter % 2800 ==0)  //if the difficultyCounter has increased by 2800
        {
            addObject (new BulletUpgrade(), Greenfoot.getRandomNumber(801), Greenfoot.getRandomNumber (601)); //add a new BulletUpgrade, at a random location on the screen
        }         
    }
    
    /**
     * adds points to the pointCounter
     */
    public void addPoints (int added)
    {
        pointCounter.add(added); //calls the add method to add points to the pointCounter
    }
    
    /**
     * adds minerals to the mineralCounter
     */
    public void addMinerals (int added)
    {
        mineralCounter.add(added);  //calls the add method to add points to the mineralCounter
    }
    
    /**
     * takes away 25 HP every time the ship gets hit with an asteroid or a saucer
     */
    public void takeHP()
    {
        HPCounter.setValue (HPCounter.getValue() - 25);  //calls the setValue method to set the value of the HPCounter to 25 points less than it was before
    }
    
    /**
     * ends the game
     * Is called either when the ship is hit, or when the ship runs out of HP
     */
    public void endGame()
    {
       takeHP(); //calls the takeHP method to take away the ship's HP
       
        if (HPCounter.getValue() <= 0)  //if the ship has HP that is equal to or smaller than 0
       {
           KillScreen ks1 = new KillScreen();  //declares and initializes KillScreen ks1 
           Greenfoot.setWorld(ks1);  //sets the world as ks1
        Greenfoot.stop();  //stops Greenfoot
    }
       
        ship.setLocation(400, 300);  //sets the ship's location back to the center of the screen
        
        removeObjects (getObjects (Asteroid1.class));  //remove all asteroid1
        removeObjects (getObjects (Asteroid2.class));  //remove all asteroid2
        removeObjects (getObjects (Asteroid3.class));  //remove all asteroid3
        removeObjects (getObjects (Saucer.class));  //remove all saucers
        removeObjects (getObjects (LineSaucer.class));  //remove all linesaucers
       
        Greenfoot.delay (75);  //delays Greenfoot by 75
    }
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
}
