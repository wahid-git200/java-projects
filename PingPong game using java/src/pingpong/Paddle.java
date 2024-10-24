/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pingpong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Paddle extends Rectangle{
    
    int id;
    int YVelocity;
    int speed = 10;
    
    public Paddle(int x , int y, int PADDLE_WIDTH,int PADDLE_HEIGHT,int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id= id;
    }
    
    public void keyPressed(KeyEvent e){
        switch(id){
            case 1 : 
                if(e.getKeyCode() == KeyEvent.VK_W){
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    setYDirection(speed);
                    move();
                }
                break;
            case 2 : 
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        switch(id){
            case 1 : 
                if(e.getKeyCode() == KeyEvent.VK_W){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    setYDirection(0);
                    move();
                }
                break;
            case 2 : 
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    setYDirection(0);
                    move();
                }
                break;
        }
    }
    
    public void setYDirection(int yDirection){
        YVelocity = yDirection;
    }
    
    public void move(){
        y += YVelocity;
    }
    
    public void draw(Graphics g){
        if(id ==1 )
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.red);
        
        g.fillRect(x, y, width, height);
    }
}
