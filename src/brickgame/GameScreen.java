package brickgame;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.Timer;
import javax.swing.JPanel;


public class GameScreen extends JPanel implements KeyListener, ActionListener, MouseMotionListener{
    
    //Properties
    private boolean play = false;
    private Timer timer;
    private int PlayerXspeed = 20;
    private int PlayerPosX = 300;
    private int BallPosX = 150;
    private int BallPosY = 250;
    private int delay = 1;
    private int BalldirX = -2;
    private int BalldirY = -2;
    private Tiles tiles;
    private int TotalBricks = 21;
    private int score = 0;
  
    
    public GameScreen()
    {
        //initialization
        tiles = new Tiles(3, 7);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
        addMouseMotionListener(this);
        timer = new Timer(delay, this);
        timer.start();
        
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 700, 600);
        
        //Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN,20));
        g.drawString("Score: " + score, 14, 25);
        
        
        // Tiles
        tiles.Draw((Graphics2D)g);
        
        //ball
        g.setColor(Color.yellow);
        g.fillOval(BallPosX, BallPosY, 20, 20);
        
        //paddle
        g.setColor(Color.ORANGE);
        g.fillRect(PlayerPosX, 530, 100, 10);
        
        g.dispose();
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       timer.start();
       
       
       //check if play is true then move the ball
       if(play)
       {
           //check if ball collide with paddle then move
           if(new Rectangle(BallPosX, BallPosY, 20, 20).intersects(PlayerPosX, 530, 100, 10))
           {
               BalldirY = -BalldirY;
           
           
           }
           
           //this is for the detecting collision between bricks and the ball
          A:for(int i = 0; i < tiles.map.length; i++)
           {
               for(int j = 0; j < tiles.map[0].length; j++)
               {
                   //check if tiles has 1
                   if(tiles.map[i][j] > 0)
                   {
                       //then create new bricks
                       int brickX = j * tiles.brickWidth + 80;
                       int brickY = i * tiles.brickHeight + 50;
                       int brickWidth = tiles.brickWidth;
                       int brickHeight = tiles.brickHeight;
                       
                       //and then create new object 
                       Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                       Rectangle ballRect = new Rectangle(BallPosX,BallPosY,20,20);
                       Rectangle brickRect = rect;
                       
                       //now after creating object, check if ball collide to tiles 
                       if(ballRect.intersects(brickRect))
                       {
                           //if two object collide set the value of rows and coll of tile equal to 0;
                          tiles.setValue(0, i, j);
                          TotalBricks --;
                          score += 5;
                          
                          if(BallPosX + 19 <= brickRect.x || BallPosX + 1 >= brickRect.x + brickRect.width)
                          {
                              BalldirX = -BalldirX;
                          }
                          else
                          {
                              BalldirY = -BalldirY;
                              
                          }
                            break A;
                       }
                   
                   }
               
               }
           
           
           }
           
           
          BallPosX += BalldirX;
          BallPosY += BalldirY;
          
          //check if the ball collide in the border
          if(BallPosX < 0)
          {
              BalldirX = -BalldirX;
          }
          
          if(BallPosY < 0)
          {
              BalldirY = -BalldirY;
          
          }
          
          if(BallPosX > 665)
          {
              BalldirX = -BalldirX;
          
          }
       }
        repaint();
       
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
   
        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            //check if player collide in border
            if(PlayerPosX >=600)
            {
                PlayerPosX = 600;
            }
            
            else {
                PlayerPosX += PlayerXspeed;
                play = true;
            }
        
        }
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
        
            if(PlayerPosX <= 0)
            {
                PlayerPosX  = 0;
            }
            
            else {
                PlayerPosX -= PlayerXspeed;
                play = true;
            }
        
        }
        
        
    }
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    

    @Override
    public void keyReleased(KeyEvent e) {
     
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        
        
    }
    
    
}
