import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is meant to soley provide an aesthetic touch to the game. It shows the game title, as well as some elements of the game such as the ship, saucers, powerups, and turrets and shields
 * 
 * @author (Jack Ding) 
 * @version (June 2013)
 */
public class IntroScreen extends World
{
    private GreenfootSound music; //delcares a new GreenfootSound called music
    
    public IntroScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 450, 1); 
        
        music = new GreenfootSound ("music.wav");  //initializes music as a sound from music.wav
    }
    
    public void act()
    {
        if (!(music.isPlaying()))  //if music is not currently playing
        {
            music.play();  //play the music sound file
        }
    
        if (Greenfoot.isKeyDown ("enter"))  //if the enter key is pressed
        {
            ChoiceScreen cs = new ChoiceScreen();  //declares and initializes new ChoiceScreen called cs
            Greenfoot.setWorld (cs);  //sets cs as the world
        }
    }
}
