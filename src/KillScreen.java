import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Is only used as an aesthetic element to the game
 * informs the player that the game is over
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class KillScreen extends World
{

    private GreenfootSound music;  //declaring new GreenfootSound called music
    
    public KillScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 450, 1); 
        
        music = new GreenfootSound ("music.wav");  //initializing music as a new GreenfootSound from music.wav
    }
    
    public void act()
    {
        music.play();  //play the music
    }
}
