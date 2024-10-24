/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pingpong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Ball extends Rectangle {
    
    Random random;
    int XVolacity;
    int YVolacity;
    int initialSpeed = 2;
    
    public Ball(int x , int y , int width, int height){
        super(x,y,width,height);
        random = new Random();
        int randomXDirection = random.nextInt(2); // nmber 0 or 1
        //if 0 go right
        if(randomXDirection == 0)
            randomXDirection --;
        setXDirection(randomXDirection * initialSpeed);
           
        int randomyDirection = random.nextInt(2); // nmber 0 or 1
        //if 1 go left
        if(randomyDirection == 0)
            randomyDirection --;
        setYDirection(randomyDirection * initialSpeed);
        
        
    }
    
    public void setXDirection(int randomXDirection){
        XVolacity = randomXDirection;
    }
    public void setYDirection(int randomYDirection){
        YVolacity = randomYDirection;
    }
    
    public void move(){
        x += XVolacity;
        y += YVolacity;
    }
    
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
        
    }
}
