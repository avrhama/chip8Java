import java.awt.event.*;


//Gui, named gui because its tightly depending on the gui(JFrame).
public class GuiJoypad extends Joypad implements KeyListener {
    public void keyPressed(KeyEvent event){
        int keyCode=event.getKeyCode();
        if(keys.containsKey(keyCode)){
                keys.get(event.getKeyCode()).pressed=true;  
        }
    }
    public void keyTyped(KeyEvent event){

    }
    public void keyReleased(KeyEvent event){
        int keyCode=event.getKeyCode();
        if(keys.containsKey(keyCode)){
                keys.get(event.getKeyCode()).pressed=false;
        }
    }
    
   
}
