import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * A Counter class that allows you to display a numerical value on screen.
 * 
 * The Counter is an actor, so you will need to create it, and then add it to
 * the world in Greenfoot.  If you keep a reference to the Counter then you
 * can adjust its value.  Here's an example of a world class that
 * displays a counter with the number of act cycles that have occurred:
 * 
 * <pre>
 * class CountingWorld
 * {
 *     private Counter actCounter;
 *     
 *     public CountingWorld()
 *     {
 *         super(600, 400, 1);
 *         actCounter = new Counter("Act Cycles: ");
 *         addObject(actCounter, 100, 100);
 *     }
 *     
 *     public void act()
 *     {
 *         actCounter.setValue(actCounter.getValue() + 1);
 *     }
 * }
 * </pre>
 * 
 * @author Neil Brown and Michael Kölling 
 * @version 1.0
 */
public class ActionBar extends Actor
{
    private static final Color transparent = new Color(0,0,0,0);  //declares constant color called transparent
    private GreenfootImage background;  //declares new greenfoot image
    private int value;  //declares integer called value
    private int target;  //declares integer called target
    private String prefix;  //declares string called prefix
    
    public ActionBar()
    {
        this(new String()); 
    }

    /**
     * Create a new counter, initialised to 0.
     */
    public ActionBar(String prefix)
    {
        background = getImage();  // get image from class
        value = 0;  //initializes value to 0
        target = 0;  //initializes target to 0
        this.prefix = prefix;  
        updateImage();  //updates image
    }
    
    /**
     * Animate the display to count up (or down) to the current target value.
     */
    public void act() 
    {
        if (value < target) {  //if the value is smaller than the target
            value++;  //increase value
            updateImage();  //update the iamge 
        }
        else if (value > target) {  //if the value is bigger than the target
            value--; //decrease value
            updateImage();  //update the image
        }
    }

    /**
     * Add a new score to the current counter value.  This will animate
     * the counter over consecutive frames until it reaches the new value.
     * 
     * @param score  New score for the current counter value
     */
    public void add(int score)
    {
        target += score;  //adds score to target
    }

    /**
     * Return the current counter value.
     */
    public int getValue()
    {
        return target; 
    }

    /**
     * Set a new counter value.  This will not animate the counter.
     * 
     * @param newValue  New value of the score being counted
     */
    public void setValue(int newValue)
    {
        target = newValue;  //sets the target as the new value from the parameter
        value = newValue; // sets value as the new value from the parameter
        updateImage();  //updates image
    }
    
    /**
     * Sets a text prefix that should be displayed before
     * the counter value (e.g. "Score: ").
     * 
     * @param prefix   The new prefix of the counter; what is being counted
     */
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;  //sets prefix to the parameter
        updateImage();  //updates image
    }

    /**
     * Update the image on screen to show the current value.
     */
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(background);  //new image
        GreenfootImage text = new GreenfootImage(prefix, 16, Color.BLACK, transparent);  //initializing the text of the counter
        
        /**
        if (text.getWidth() > image.getWidth() - 20)  //scales the counter's image to match the counter box
        {
            image.scale(text.getWidth() + 20, image.getHeight());
        }
        */
        
        image.drawImage(text, (image.getWidth()-text.getWidth())/2,  //draws the new counter
                        (image.getHeight()-text.getHeight())/2);
        setImage(image);  //set image
    }
}
