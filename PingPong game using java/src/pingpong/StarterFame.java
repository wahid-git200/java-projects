/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pingpong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class StarterFame extends JFrame{
    
    public StarterFame(){
        
        
        JLabel masseg = new JLabel("Wellcom To Ping Pong Game");
        masseg.setBounds(50, 90, 320, 30);
        masseg.setFont(new Font("Consolas" , Font.PLAIN,20));
        masseg.setForeground(Color.GREEN);
        
        JButton startBtn = new JButton("Click to Start");
        startBtn.setFocusable(false);
        startBtn.setFont(new Font("Consolas" , Font.PLAIN,20));
        startBtn.setForeground(Color.GREEN);
        startBtn.setBackground(Color.GRAY);
        startBtn.setBounds(84, 160, 230, 35);
        startBtn.setVerticalAlignment(JButton.CENTER);
        

        this.setTitle("Ping Pong");
        this.setLayout(null);
        this.getContentPane().setBackground(Color.BLACK);
        add(masseg);
        add(startBtn);
        
        this.setSize(400,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        startBtn.addActionListener(e->{
            this.dispose();
            new GameFrame();
        });
    }
//    
//    public static void main(String [] args){
//        new StarterFame();
//    }
//    
//    
}
