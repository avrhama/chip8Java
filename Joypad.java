import java.awt.event.*;
import javax.swing.*;


import java.awt.*;  
public class Joypad extends JFrame implements KeyListener {

    public void keyPressed(KeyEvent event){
        System.out.printf("down:%d\n",event.getKeyCode());
    }
    public void keyTyped(KeyEvent event){
        System.out.printf("typed:%d\n",event.getKeyCode());
    }
    public void keyReleased(KeyEvent event){
        System.out.printf("up:%d\n",event.getKeyCode());
    }
    TextArea area;
    public Joypad(){ 
        setSize(0,0);  
        setLayout(null);  
        setVisible(true);  
        
        addKeyListener(this);
    }
}
