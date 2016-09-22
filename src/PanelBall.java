import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class PanelBall extends JPanel implements Runnable {
    //fix
    private int ballX=17, ballY=110;        //initial position of the ball
    private int player1X= 5, player1Y = 100;  //initial position of player 1's paddle
    private int player2X= 225, player2Y= 100; //initial position of player 2's paddle
    Thread game;
    static double stepSizeX, stepSizeY;// how many pixels to change for each movement and splitting it allows for spin
    static double speed = 0;
    int winningScore = 100;
    int width, height; // Width and height of the ball
    // Scores
    int player1Score=0, player2Score=0;
    boolean player1UpFlag,player1DownFlag, player2UpFlag, player2DownFlag;
    static boolean play, gameOver;
    private EasySound noise; //setup for bouncing sound
    private static int pLength = 50; //paddle length so I can change it for level 2
    static double spin1 = 0, spin2 = 0; //for making spin
    static boolean hit = false; //saying it hit the wall or paddle

    public PanelBall(){
        play=true;
        game=new Thread(this);
        game.start();
       // noise = new EasySound("boink.wav");
    }
    
    //setters
    public static void setStepSizeX(double n) { stepSizeX = n; }
    public static void setStepSizeY(double n) { stepSizeY = n; }
    public static void setspeed(double n){ speed = n; }
    public static void setPaddleSize(int n) { pLength = n; }
    public static void setSpin1 (double n) { spin1 = n; }
    public static void setSpin2 (double n) { spin2 = n; }
    
    //getters
    public int getPlayer1Y() { return player1Y; }
    public int getPlayer2Y() { return player2Y; }
    public static double getStepSizeY() { return stepSizeY; }
    public static boolean getHit() { return hit; }
    
    // Draw ball and ships
    public void paintComponent(Graphics g){
        setOpaque(false);
        super.paintComponent(g);
        
        // Draw ball
        g.setColor(Color.GREEN.darker());
        g.fillOval(ballX, ballY, 8,8);
        
        // Draw paddles
        g.setColor(Color.BLUE);
        g.fillRect(player1X, player1Y, 10, pLength);
        g.setColor(Color.RED);
        g.fillRect(this.getWidth() - 15, player2Y, 10, pLength);
        
        //Draw scores
        g.setColor(Color.BLUE);
        g.drawString("Player1: " + player1Score, this.getWidth() / 2 - 95, 10);
        g.setColor(Color.RED);
        g.drawString("Player2: " + player2Score, this.getWidth() / 2 + 30, 10);
        
        g.setColor(Color.BLACK);
       
        if(gameOver)
            g.drawString("Game Over", this.getWidth() / 2 - 35, this.getHeight() / 2);
    }
    
    // Positions on X and Y for the ball
    public void positionBall (int nx, int ny)
    {
        ballX= nx; 
        ballY= ny; 
        this.width=this.getWidth();
        this.height=this.getHeight();
        repaint();
    }
    
    // Here we receive the key pressed from the Main class.
    // This class should receive a key from the Main class and set
    // player1UpFlag, player1DownFlag, player2UpFlag, or 
    // player2DownFlag to TRUE if the appropriate key is pressed.
    // event.getKeyCode() returns an ENUM data type of the form:
    // KeyEvent.VK_A = 'A', KeyEvent.VK_B = 'B', KeyEvent.VK_C = 'C',
    // KeyEvent.VK_UP = Up Arrow Key, KeyEvent.VK_DOWN = Down Arrow Key
    // You will need to complete the SWITCH statement.
    public void keyPressed(KeyEvent event)
    {
        switch(event.getKeyCode())
        {
            // Move Player 1's Paddle
            case KeyEvent.VK_A: player1UpFlag = true;
            	break;
            case KeyEvent.VK_Z: player1DownFlag = true;
            	break;           
            // Move Player 2's Paddle
            case KeyEvent.VK_UP: player2UpFlag = true;
            	break;
            case KeyEvent.VK_DOWN: player2DownFlag = true;
            	break;
        }
    }
    
    // Here we receive the key released from the Main class
    // This class should receive a key from the Main class and set
    // player1UpFlag, player1DownFlag, player2UpFlag, or 
    // player2DownFlag to FALSE if the appropriate key is released.
    // event.getKeyCode() returns an ENUM data type of the form:
    // KeyEvent.VK_A = 'A', KeyEvent.VK_B = 'B', KeyEvent.VK_C = 'C',
    // KeyEvent.VK_UP = Up Arrow Key, KeyEvent.VK_DOWN = Down Arrow Key
    // You will need to complete the SWITCH statement.
    public void keyReleased(KeyEvent event)
    {
        switch(event.getKeyCode())
        {
            // Stop Player 1's Paddle
        	case KeyEvent.VK_A: player1UpFlag = false;
        		break;
        	case KeyEvent.VK_Z: player1DownFlag = false;
        		break;
                        
            // Stop Player 2's Paddle
        	case KeyEvent.VK_UP: player2UpFlag = false;
        		break;
        	case KeyEvent.VK_DOWN: player2DownFlag = false;
        		break;
        }
    }
    
    // Move player 1
    // Uses the flags player1UpFlag and player1DownFlag to change the 
    // coordinates of Player 1's paddle (player1X, player1Y) by the 
    // stepSize specified.  It must also check to ensure that the user
    // cannot move their paddle off the top or bottom of the screen.
    // The method must call repaint() when it has changed the user's 
    // position.
    public void movePlayer1()
    {
      int i = 27;	
      
      if(pLength == 13)
    	  i = 14;
    
      if(player1UpFlag && player1Y > 0)
    	  player1Y -= stepSizeY + spin1;
      else if(player1DownFlag && player1Y < this.getHeight() - i)
    	  player1Y += stepSizeY + spin1;
      repaint();
    }
    
    // Move player 2
    // Uses the flags player2UpFlag and player2DownFlag to change the 
    // coordinates of Player 2's paddle (player2X, player2Y) by the 
    // stepSize specified.  It must also check to ensure that the user
    // cannot move their paddle off the top or bottom of the screen.
    // The method must call repaint() when it has changed the user's 
    // position.
    public void movePlayer2()
    {
    	int i = 27;	
        
        if(pLength == 13)
      	  i = 14;
    	
    	if(player2UpFlag && player2Y > 0)
      	  player2Y -= stepSizeY + spin1;
        else if(player2DownFlag && player2Y < this.getHeight() - i)
      	  player2Y += stepSizeY + spin1;
        repaint();
    }

    public void run() {
        boolean movingRight=false;
        boolean movingUp=false;
        
        while(true)
        { 	
        	if(play)
            {
            // The ball move from left to right
            if (movingRight) 
            {
            	ballX += stepSizeX + spin2;
            	hit = true;
                if (ballX >= (width - 8)) { movingRight = false; stepSizeX += speed; }
            }
            else
            {
                ballX += -stepSizeX + -spin2;
                hit = true;
                if ( ballX <= 0) { movingRight = true; stepSizeX += speed; }
            }
            // The ball moves from up to down
            if (movingUp) 
            {
            	ballY += stepSizeY + spin1;
            	hit = true;
                if (ballY >= (height - 8)) { movingUp= false; stepSizeY += speed; }
            }
            else
            {
            	ballY += -stepSizeY + -spin1;
            	hit = true;
                if ( ballY <= 0) { movingUp =  true; stepSizeY += speed; }
            }
            
            positionBall(ballX, ballY);
            
            // Delay
            try 
            {
                Thread.sleep(50);
            }
            catch(InterruptedException ex)
            {
            }
            
            // Move player 1
            movePlayer1();
            
            // Move player 2
            movePlayer2();
            
            // The score of the player 1 increase
            if (ballX >= (this.getWidth() - 8)) player1Score++; //took away -8
                            
            // The score of the player 2 increase
            if ( ballX <= 0) player2Score++; //I made it < instead of <= and ==
                                        
            // Game over. 
            // When the score reaches the value, the game will end
            if(player1Score==winningScore || player2Score==winningScore){
                play=false;
                gameOver=true;
            }
            
            if(movingUp && player2UpFlag && ballX>=this.getWidth() - 20 && ballY>=player2Y && ballY<=(player2Y+pLength))
            { movingUp = false; hit = true; }
            else if(!movingUp && player2DownFlag && ballX>=this.getWidth() - 20 && ballY>=player2Y && ballY<=(player2Y+pLength))
            { movingUp = true; hit = true; }
            
            if(movingUp && player1UpFlag && ballX<=player1X + 10 && ballY>=player1Y && ballY<=(player1Y+pLength))
            { movingUp = false; hit = true; }
            else if(!movingUp && player1DownFlag && ballX<=player1X + 10 && ballY>=player1Y && ballY<=(player1Y+pLength))
            { movingUp = true; hit = true; }
            
            // The ball stroke with the player 1
            if(ballX<=player1X + 10 && ballY>=player1Y && ballY<=(player1Y+pLength))
            {
            	movingRight=true;
            	hit = true;
            	try
            	{
            		noise.play();
            	} 
            	catch (Exception e)
            	{
            		e.printStackTrace();
            	}
            } 
            
            // The ball stroke with the player 2
            if(ballX>=this.getWidth() - 20 && ballY>=player2Y && ballY<=(player2Y+pLength))
            {
            	movingRight=false;
            	hit = true;
            	try
            	{
            		noise.play();
            	} 
            	catch (Exception e)
            	{
            	}
            }
            
            //if ball hits player 1 on top
            if(ballX >= -5 && ballX <= 13 && player1Y - ballY < 3 && player1Y - ballY > -2) { movingUp = false; hit = true; }
            //if ball hits player 1 on bottom
            if(ballX >= -5 && ballX <= 13 && (player1Y + pLength) - ballY < 3 && (player1Y + pLength) - ballY > -2)
            { movingUp = true; hit = true; }
            
            //if ball hits player 2 on top
            if((ballX <= this.getWidth() + 5 && ballX >= this.getWidth() - 20) && (player2Y - ballY < 3 && player2Y - ballY > -2))
            { movingUp = false; hit = true; }
            //if ball hits player 2 on bottom
            if(ballX <= this.getWidth() + 5 && ballX >= this.getWidth() - 20 && (player2Y + pLength) - ballY < 3 && (player2Y + pLength) - ballY > -2)
            { movingUp = true; hit = true; }
          
            }
        }
    }
}





