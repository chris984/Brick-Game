package brickgame;

import javax.swing.JFrame;

public class Main extends JFrame {
    
    int WIDTH = 700, HEIGHT = 600;
    private GameScreen window;
    
    
    public Main()
    {
      
        setTitle("Brick Game");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        window = new GameScreen();
        add(window);
        
        setVisible(true);
    
    }
    
    public static void main(String[] args)
    {
    
        new Main();
    
    }
    
    
    
}
