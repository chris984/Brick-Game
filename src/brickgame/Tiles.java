package brickgame;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Tiles {
    
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    
    
    public Tiles(int rows, int col)
    {
        map = new int[rows][col];
        
        /*adding logic for collision
        if it has a 1 value then we will draw and if the ball
        intersect or collide with the tiles we will set that index
        equal 0 so that we can remove the tiles
        */
        for(int i = 0; i < map.length; i++)
        {
           for(int j = 0; j < map[0].length; j++)
           {
               map[i][j] = 1;
           }
        
        }
            brickWidth = 540/col;
            brickHeight = 150/rows;
            
    }
    
    public void Draw(Graphics2D g)
    {
        for(int i = 0; i < map.length; i++)
        {
      
           for(int j = 0; j < map[0].length; j++)
           {
               if(map[i][j] != 0)
               {
                   g.setColor(Color.WHITE);
                   g.fillRect(j * brickWidth + 80, i* brickHeight + 50, brickWidth, brickHeight);
                   g.setStroke(new BasicStroke(3));
                   
                   
                   g.setColor(Color.BLACK);
                  g.drawRect(j * brickWidth + 80, i* brickHeight + 50, brickWidth, brickHeight);
               
               }
           
           }
        
        }
        
    
    }
    
    public void setValue(int value, int rows, int col)
    {
        map[rows][col] = value;
    
    
    }
    
       
}
