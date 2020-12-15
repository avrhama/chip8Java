import java.util.TreeMap;
import java.awt.event.KeyEvent;
public  abstract class Joypad {
    public class Key{
        boolean pressed;
        byte keyCode;
    }
    TreeMap<Integer,Key>keys=null;
    byte [] keysMapper;
    public  void config(){
        keys=new TreeMap<>();
        keysMapper=new byte[16];

        keys.put(KeyEvent.VK_0,new Key());
        keysMapper[0]=KeyEvent.VK_0;
        keys.put(KeyEvent.VK_1,new Key());
        keysMapper[1]=KeyEvent.VK_1;
        keys.put(KeyEvent.VK_2,new Key());
        keysMapper[2]=KeyEvent.VK_2;
        keys.put(KeyEvent.VK_3,new Key());
        keysMapper[3]=KeyEvent.VK_3;
        keys.put(KeyEvent.VK_4,new Key());
        keysMapper[4]=KeyEvent.VK_4;
        keys.put(KeyEvent.VK_5,new Key());
        keysMapper[5]=KeyEvent.VK_5;
        keys.put(KeyEvent.VK_6,new Key());
        keysMapper[6]=KeyEvent.VK_6;
        keys.put(KeyEvent.VK_7,new Key());
        keysMapper[7]=KeyEvent.VK_7;
        keys.put(KeyEvent.VK_8,new Key());
        keysMapper[8]=KeyEvent.VK_8;
        keys.put(KeyEvent.VK_9,new Key());
        keysMapper[9]=KeyEvent.VK_9;
        keys.put(KeyEvent.VK_A,new Key());
        keysMapper[0xA]=KeyEvent.VK_A;
        keys.put(KeyEvent.VK_B,new Key());
        keysMapper[0xB]=KeyEvent.VK_B;
        keys.put(KeyEvent.VK_C,new Key());
        keysMapper[0xC]=KeyEvent.VK_C;
        keys.put(KeyEvent.VK_D,new Key());
        keysMapper[0xD]=KeyEvent.VK_D;
        keys.put(KeyEvent.VK_E,new Key());
        keysMapper[0xE]=KeyEvent.VK_E;
        keys.put(KeyEvent.VK_F,new Key());
        keysMapper[0xF]=KeyEvent.VK_F;


    }
    public Key getKey(){
        Key key=null;
        while(true){
            for(byte i=0;i<16;i++){
                
            }
        }
        return key;
    }
    
}
