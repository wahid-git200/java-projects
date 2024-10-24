/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pingpong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable{
    
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = 555;
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1,paddle2;
    Ball ball;
    Score score;
    
    public GamePanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);/////////////////////////
        this.addKeyListener(new AL());
        this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        
        gameThread = new Thread(this);
        gameThread.start();
    }
     
    public void newBall(){
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER,BALL_DIAMETER);
    }
    
    public void newPaddles(){
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle(GAME_WIDTH -PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }
    
    public void paint(Graphics g){
        image = createImage(this.getWidth(),this.getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    
    public void checkCollision(){
        // stop paddles at  window edges
        if(paddle1.y <= 0){
            paddle1.y = 0;
        }
        if(paddle1.y >= GAME_HEIGHT-PADDLE_HEIGHT){
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        }
        
        if(paddle2.y <= 0){
            paddle2.y = 0;
        }
        if(paddle2.y >= GAME_HEIGHT-PADDLE_HEIGHT){
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
        }
   
        // for ball check collision
        if(ball.y <= 0){
            ball.setYDirection(-ball.YVolacity);
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER){
            ball.setYDirection(-ball.YVolacity);
        }
        
        //bounce ball off paddles
        if(ball.intersects(paddle1)){  // this method will return true if is collosion bettwen ball and paddle1 / this is from rectengle class
            ball.XVolacity = Math.abs(ball.XVolacity);
            ball.XVolacity++; // for more defficulty    is optional
            
            if(ball.YVolacity > 0)
                ball.YVolacity++;// for more defficulty    is optional
            else
                ball.YVolacity--;
            ball.setXDirection(ball.XVolacity);
            ball.setYDirection(ball.YVolacity);
        }
        if(ball.intersects(paddle2)){  // this method will return true if is collosion bettwen ball and paddle1 / this is from rectengle class
            ball.XVolacity = Math.abs(ball.XVolacity);
            ball.XVolacity++; // for more defficulty    is optional
            
            if(ball.YVolacity > 0)
                ball.YVolacity++;// for more defficulty    is optional
            else
                ball.YVolacity--;
            ball.setXDirection(-ball.XVolacity);
            ball.setYDirection(ball.YVolacity);
        }
        
        // when a player gaved one  point and create new paddles & ball
        if(ball.x <= 0){
            score.player2++;
//            newPaddles();
            newBall();
        }
        if(ball.x >= GAME_WIDTH - BALL_DIAMETER){
            score.player1++;
//            newPaddles();
            newBall();
        } 
    }
    
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        
        while(true){
            long now = System.nanoTime();
            delta += (now-lastTime) /ns;
            lastTime = now;
            
            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
