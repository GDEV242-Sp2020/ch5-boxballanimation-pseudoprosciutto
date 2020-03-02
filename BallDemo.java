import java.awt.Color;
import java.util.Random;
import java.util.HashSet;


/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 * 
 *
 * @author Matthew Sheehan
 * @version 03-01-3020
 */

public class BallDemo   
{
    private Canvas myCanvas;
    
    //declare variables for the window size used for future calculations
    private final int WIDTH = 600;
    private final int HEIGHT = 500;
    //initialize a random generator
    private Random random;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", WIDTH, HEIGHT);
    }

    /**
     * Simulate two bouncing balls. Each ball starts in a random position 
     * on top half of the window
     */
    public void bounce()
    {
        Random random = new Random();   //generator for starting position
        int ground = 400;   // position of the ground line
        
        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls. 
        BouncingBall ball = new BouncingBall(random.nextInt(WIDTH),random.nextInt(HEIGHT/2), 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(random.nextInt(WIDTH),random.nextInt(HEIGHT/2), 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    
    /**
     * Simulates user-defined amount of bouncing balls in a boxed frame.
     * Each BoxBall's color and size will be random.
     * 
     * @param amtOfBall amount of Balls created
     */
    public void boxBounce(int amtOfBalls ){
       myCanvas.erase();
       myCanvas.setVisible(true);
       Random random = new Random();
       
       HashSet<BoxBall> BoxBalls = new HashSet<BoxBall>();
        //add user-defined amount of BouncingBall objects to HashSet Balls
        for(int i = 0; i < amtOfBalls; i ++){
            BoxBall ball = new BoxBall( (random.nextInt(WIDTH-100)+50),
                (random.nextInt(HEIGHT-100)+50), random.nextInt(7)+10,
                myCanvas);
            BoxBalls.add(ball);
            ball.draw();
        }
        boolean finished = false;
        
        
       while (!finished){
        //redraw box frame each iteration incase it gets clipped
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, 50, 550, 50);   //top boundary
        myCanvas.drawLine(550, 50, 550, 450);     //right boundary
        myCanvas.drawLine(50, 450, 550, 450);     //bottom boundary
        myCanvas.drawLine(50, 50, 50, 450);   //left boundary;
       
        myCanvas.wait(50); //quick pause just in case drawing is sloppy
        // move balls going through the set a ball at a time.
            for(BoxBall ball : BoxBalls){
                    ball.move();
                }
            }
    }
    
    
    /**
    * Simulates user-defined amount of bouncing balls using a HashSet to
    * store the objects
    * @param amtOfBalls amount of balls created. must be > 0.
    */ 
    public void manyBalls(int amtOfBalls){
        int ground = 400;   // position of the ground line
        Random random = new Random();
        myCanvas.setVisible(true);
        
        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);
        
        // create HashSet balls to hold user-defined amount of BouncingBall objects
        HashSet<BouncingBall> Balls = new HashSet<BouncingBall>();
        //add user-defined amount of BouncingBall objects to HashSet Balls
        for(int i = 0; i < amtOfBalls; i ++){
            BouncingBall ball = new BouncingBall(random.nextInt(WIDTH),random.nextInt(HEIGHT/2), 16, Color.BLUE, ground, myCanvas);
            Balls.add(ball);
            ball.draw();
        }

            // make them bounce
        boolean finished =  false;
            // counter to end loop from ball bouncing
        int counter = 0;
        
        while (!finished) {
            myCanvas.wait(50);           // small delay
            for(BouncingBall ball : Balls){
                ball.move();
                
                // Erase ball has travelled a certain distance on x axis
                if(ball.getXPosition() >= 550) {
                    ball.erase();
                    counter++;
                    if(counter > amtOfBalls){
                    finished = true;
                    }
                }

            }
            
        }
    }
}
