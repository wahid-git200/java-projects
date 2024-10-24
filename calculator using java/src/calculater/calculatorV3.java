/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class calculatorV3 extends JFrame {
    
    Stack<String> stack1 = new Stack<>();
    Stack<String> stack2 = new Stack<>();
    Stack<String> stack3 = new Stack<>();
       
    private JTextField tfDisplay;
    private JTextArea taHistory;
    private JCheckBox ch;
    private JCheckBox darkModech;
    
    private Font f  = new Font("arial",Font.BOLD,20);
    
    private Color Color1= Color.decode("#e6e6e6");
    private Color color2 = Color.decode("#f0f0f0");
    
    JMenuBar menuBar;
    JMenu  fileMenu,editMenu;
    JMenuItem setting,about,exit;
    
    private JScrollPane js;

    private JPanel panelDispaly = new JPanel(new GridLayout(2,1));
    private JPanel panelOperation = new JPanel(new GridLayout(5,1));
    private JPanel panelNumBtn = new JPanel(new GridLayout(5,3));
    
    private JButton [] btnNumbers = new JButton[15];
    private String [] numberText = {"C","x","P","7","8","9","4","5","6","1","2","3","0",".","+/-"};
    
    private JButton[] btnOperation = new JButton[5];
    String [] OperationText={"/","*","-","+","="};
    
    double firstNumber , secondNumber, result;
    String operation;
    
    JButton equalBtn;
     String s1="";
    public calculatorV3(){
        
        setupNumberBtn();
        setuoMenubar();
        setupMainDisplay();
        setupOperationBtns();
        setupMainFrame(); 
        setHover();
    }
    
    class MouseOpBtnHover extends MouseAdapter{
        public void mouseEntered(MouseEvent e) {
            if(!darkModech.isSelected())
                e.getComponent().setBackground(Color.decode("#d0d0d0"));
            else
                e.getComponent().setBackground(new Color(65, 65, 65));    
        }
        
        public void mouseExited(MouseEvent e) {
            e.getComponent().setBackground(btnNumbers[1].getBackground());
            e.getComponent().setBackground(btnNumbers[2].getBackground());
        }
    }
    
    class MouseBumberBtnHover extends MouseAdapter{
        public void mouseEntered(MouseEvent e) {
            if(!darkModech.isSelected())
                e.getComponent().setBackground(new Color(248, 248, 248));
            else
                e.getComponent().setBackground(new Color(85,85,85));
        }
        
        public void mouseExited(MouseEvent e){
                e.getComponent().setBackground(btnNumbers[4].getBackground());
                e.getComponent().setBackground(btnNumbers[5].getBackground());
        }
    }
    
    public void setHover(){
        for(int i=0;i<3;i++){
          btnNumbers[i].addMouseListener(new MouseOpBtnHover() );
        }
        
        for(int i=0;i<btnOperation.length-1;i++){
          btnOperation[i].addMouseListener(new MouseOpBtnHover() );
        }
        
        for(int i=3;i<btnNumbers.length;i++){
           btnNumbers[i].addMouseListener(new MouseBumberBtnHover() );
        }
    }
    
    public void setupOperationBtns(){
        for(int i=0;i<btnOperation.length-1;i++){
            btnOperation[i]= new JButton(OperationText[i]);
            btnOperation[i].setBackground(Color.decode("#f0f0f0"));
            btnOperation[i].setFont(new Font("arial",Font.PLAIN,25));
            btnOperation[i].setFocusable(false);
            btnOperation[i].setForeground(Color.GRAY);
            btnOperation[i].setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
            panelOperation.add(btnOperation[i]);
            
            int index=i;
            btnOperation[i].addActionListener( new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    operation="";
                    operation = btnOperation[index].getText();
                    
                    firstNumber = Double.parseDouble(tfDisplay.getText());
                    s1+=tfDisplay.getText()+" "+operation+" ";
                    stack1.add(tfDisplay.getText());
                    if(stack2.isEmpty()){
                        stack2.add(operation);
                    }else if(operation=="*" || stack2.peek()=="/"  ){
                    
                    }
                    else if(operation=="+" || stack2.peek()=="-"  ){
                    
                    }
                     
                    
                    
                }
            });
        }
        
        equalBtn=btnOperation[btnOperation.length-1];
        equalBtn = new JButton("  =  ");
        equalBtn.setFocusable(false);
        equalBtn.setBackground(Color.decode("#1c577c"));
        equalBtn.setFont(new Font("arial",Font.PLAIN,25));
        equalBtn.setForeground(Color.WHITE);
        
        panelOperation.add(equalBtn);
        
        equalBtn.addActionListener(e->{
            s1+=tfDisplay.getText();
            while(!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
            System.out.println(stack1.toString());
            
            
                
            secondNumber = Double.parseDouble(tfDisplay.getText());
            tfDisplay.setText("");
            taHistory.append(s1+"\n");
        });
    }
    
    public void setupNumberBtn(){
        for(int i =0;i<btnNumbers.length;i++){
            btnNumbers[i]= new JButton(numberText[i]);
            btnNumbers[i].setBackground(Color.WHITE);
            btnNumbers[i].setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
            btnNumbers[i].setFont(f);
            btnNumbers[i].setFocusable(false);
            panelNumBtn.add(btnNumbers[i]);
        }
        
        for(int i =0;i<3;i++){
            btnNumbers[i].setBackground(Color.decode("#f0f0f0"));
            btnNumbers[i].setFont(new Font("arial",Font.PLAIN,25));
            btnNumbers[i].setForeground(Color.GRAY);
        }
        
        for(int i =3;i<btnNumbers.length-1;i++){
            int index = i;
            btnNumbers[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    double d = Double.parseDouble(tfDisplay.getText());
                    if(d==0 || d==firstNumber || d==result){
                        tfDisplay.setText("");
                    }
                        tfDisplay.setText(tfDisplay.getText() + btnNumbers[index].getText());
                }
            });
        }
        
        btnNumbers[0].addActionListener(e->{
            if(!tfDisplay.equals("0")){
                tfDisplay.setText("0");
                taHistory.setText("");
            }
        });
        
        btnNumbers[1].addActionListener(e->{
            String s = tfDisplay.getText();
            if(s.length()==1){
                tfDisplay.setText("0");
            }else{
                s = s.substring(0,s.length()-1);
            }
            tfDisplay.setText(s);
        });
        
        btnNumbers[2].addActionListener(e->{
            double d = Double.parseDouble(tfDisplay.getText());
            tfDisplay.setText(Math.pow(d, 2) + "");
            taHistory.append(d+ " powr OF 2 = "+Math.pow(d, 2)+"\n");
        });
        
        btnNumbers[btnNumbers.length-1].addActionListener(e->{
            double d = Double.parseDouble(tfDisplay.getText());
            d*=-1;
            tfDisplay.setText(d+"");
            
        });
    }
            
    public void setuoMenubar(){
        menuBar = new JMenuBar();

        setJMenuBar(menuBar);
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        
        setting = new JMenuItem("Setting");
        about = new JMenuItem("About");
        exit = new JMenuItem("Exit");
        
        ch = new JCheckBox("Show History");
        darkModech = new JCheckBox("Dark Mode");
        
        editMenu.add(ch);
        editMenu.add(darkModech);
        fileMenu.add(setting);
        fileMenu.add(about);
        fileMenu.add(exit);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        
        menuBar.setBackground(Color.decode("#e6e6e6"));
        menuBar.setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
        
        taHistory = new JTextArea(3,3);
        js = new JScrollPane(taHistory);
        panelDispaly.add(js);
        taHistory.setEditable(false);
        js.setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
        taHistory.setBackground(Color.decode("#e6e6e6"));
        panelDispaly.setBackground(Color.decode("#e6e6e6"));
        js.setVisible(false);
        
        ch.addItemListener(e->{
            if(ch.isSelected()){
                js.setVisible(true);
            }
        });
        
        exit.addActionListener(e->{
            System.exit(0);
        });
        
        about.addActionListener(e->{
            new AboutFrame();
        });
        //////////////////////////////// dark mode/////////////////////////////////////////////
        darkModech.addActionListener(e->{ 
            Color color1 = null;
            Color color2 =null;
            Color color3 =null;
            Color color4 =null;
            Color color5 =null;
            
            if(darkModech.isSelected()){
                color1 = new Color(51, 51, 51);
                color2 = new Color(220, 220, 220);
                color3 = new Color(100, 100, 100);
                color4 = new Color(250, 250, 250);
                color5 = new Color(78,78,78);
                menuBar.setBackground(color1);
                menuBar.setBorder(BorderFactory.createLineBorder(color1));
                tfDisplay.setBackground(color1);
                tfDisplay.setBorder(BorderFactory.createLineBorder(color1));
                tfDisplay.setForeground(color2);
                
                js.setBorder(BorderFactory.createLineBorder(color1));
                taHistory.setBackground(color1);
                tfDisplay.setForeground(color2);
                taHistory.setForeground(color2);
                panelDispaly.setBackground(color1);
                
                for(int i =0;i<btnNumbers.length;i++){
                    btnNumbers[i].setBackground(color3);
                    btnNumbers[i].setForeground(color4);
                    btnNumbers[i].setBorder(BorderFactory.createLineBorder(color1));
                }
                
                for(int i=0;i<btnOperation.length-1;i++){
                    btnOperation[i].setBackground(color5);
                    btnOperation[i].setForeground(color2);
                    btnOperation[i].setBorder(BorderFactory.createLineBorder(color1));
                }

                for(int i =0;i<3;i++){
                    btnNumbers[i].setBackground(color5);
                    btnNumbers[i].setFont(new Font("arial",Font.PLAIN,25));
                    btnNumbers[i].setForeground(color2);
                }

                fileMenu.setForeground(color2);
                editMenu.setForeground(color2);
                
                panelDispaly.setBackground(color1);
                panelOperation.setBackground(color1);
                panelNumBtn.setBackground(color1);  
            }
            else{
                tfDisplay.setForeground(Color.black);
                taHistory.setForeground(Color.black);
                fileMenu.setForeground(Color.black);
                editMenu.setForeground(Color.black);
                ///
                for(int i=0;i<btnOperation.length-1;i++){
                    btnOperation[i].setBackground(Color.decode("#f0f0f0"));
                    btnOperation[i].setForeground(Color.GRAY);
                    btnOperation[i].setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
                }
                equalBtn.setBackground(Color.decode("#1c577c"));
                equalBtn.setForeground(Color.WHITE);
                ///
                for(int i =0;i<btnNumbers.length;i++){
                    btnNumbers[i].setBackground(Color.WHITE);
                    btnNumbers[i].setForeground(Color.black);
                    btnNumbers[i].setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
                }
                
                for(int i =0;i<3;i++){
                    btnNumbers[i].setBackground(Color.decode("#f0f0f0"));
                    btnNumbers[i].setForeground(Color.GRAY);
                }
                
                ///
                panelDispaly.setBackground(Color.decode("#e6e6e6"));
                taHistory.setBackground(Color.decode("#e6e6e6"));
                menuBar.setBackground(Color.decode("#e6e6e6"));
                menuBar.setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
                js.setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
                /////
                tfDisplay.setBackground(Color.decode("#e6e6e6"));
                tfDisplay.setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
            }
        });
    }
    
    private void setupMainDisplay(){
        tfDisplay= new JTextField("0",9);
        tfDisplay.setHorizontalAlignment(JTextField.RIGHT);
        tfDisplay.setBackground(Color.decode("#e6e6e6"));
        tfDisplay.setBorder(BorderFactory.createLineBorder(Color.decode("#e6e6e6")));
        tfDisplay.setEditable(false);
        tfDisplay.setFont(new Font("arial",Font.BOLD,30));
        panelDispaly.add(tfDisplay);
    }
     
    
    private void setupMainFrame(){
        setTitle("Calculator");
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Wahid\\Documents\\NetBeansProjects\\Calculater\\src\\c.png");
        this.setIconImage(icon.getImage());
        setLayout(new BorderLayout());
        
        add(panelDispaly, BorderLayout.NORTH);
        add(panelOperation,BorderLayout.EAST);
        add(panelNumBtn,BorderLayout.CENTER);
        
        setSize(350,450);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    class AboutFrame extends JFrame{
        AboutFrame(){
            
            ImageIcon img= new ImageIcon("src//smile.png");
            JLabel l = new JLabel(img);
            add(l);
            
            setSize(250,200);
            setTitle("About");
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }
    

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new calculatorV3();
            }
        });
    }
}
