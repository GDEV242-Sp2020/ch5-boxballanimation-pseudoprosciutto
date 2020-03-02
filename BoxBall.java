import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Class BoxBall - a graphical ball that bounces each time it contacts an edge.
 * The ball has the ability to move. 
 * Details of movement are determined by the ball itself.
 * When ball hits an object it will bounce back at an angle
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Matthew Sheehan
 *
 * @version 03-01-2020
 */


public class BoxBall
{
    private int diameter;
    private Color color;
    private int xPosition;
    private int yPosition;
    private int xSpeed;                 
    private int ySpeed;                
    private Canvas canvas;
    /**
     * Constructor for BoxBall
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball     
     * @param drawingCanvas  the canvas to draw this ball on
     * 
     */
    public BoxBall(int xPosit, int yPosit, int ballDiameter, Canvas drawingCanvas) 
    {
        xPosition = xPosit;
        yPosition = yPosit;
        Random random = new Random();
        diameter = ballDiameter;
        
        // randomize the speed and direction at which this ball travels about
        xSpeed = random.nextInt(7) +1;
        ySpeed = random.nextInt(7) +1;
        
        
        // creates random color. The random bounds is shorter than color max
        //so that the colors don't come out too light. the balls can be seen
        color = new Color(random.nextInt(220),random.nextInt(220),random.nextInt(220));
        
        canvas = drawingCanvas;

    }
    
    /**
     * Method to erase ball at its current position.
     */
     public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
        
    } 
    
     /**
     * Method to draw ball at its current position onto the canvas.
     **/
     public void draw()
     {
      canvas.setForegroundColor(color);
      canvas.fillCircle(xPosition, yPosition, diameter);
    }   
    
    /**
     * Method for moving the ball
     * when a boundary is hit, speed and direction change.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();

        // set the speed ball
        xPosition+=xSpeed;
        yPosition+=ySpeed; 

        // bounce in opposite direction when touching boundary
        if (xPosition < 50) {
            xPosition = diameter + 50;
            xSpeed = -xSpeed;
        }
        if (xPosition > 550-diameter) {
            xPosition = 550 - diameter;
            xSpeed = -xSpeed;
        }
        if (yPosition < 50) {
            yPosition = diameter + 50;
            ySpeed = -ySpeed;
        }
        if (yPosition > 450-diameter) {
            yPosition = 450 - diameter;
            ySpeed = -ySpeed;
        }
    
        // draw again at new position
        draw();
    }
    
     /**
     * Method that returns the horizontal position of the ball
     */
       public int getXPosition()
       {
        return xPosition;
       }
       
     /**
     * Method that returns the vertical position of the ball
     */
       public int getyPosition()
       {
           return yPosition;
        }
    }

