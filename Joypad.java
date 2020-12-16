import java.util.TreeMap;
import java.awt.event.KeyEvent;

public abstract class Joypad {
    public class Key {
        boolean pressed;
        int keyCode;
    }

    TreeMap<Integer, Key> keys = null;
    int[] keysMapper;

    public void config() {
        keys = new TreeMap<>();
        keysMapper = new int[16];

        keys.put(KeyEvent.VK_0, new Key());
        keys.put(KeyEvent.VK_1, new Key());
        keys.put(KeyEvent.VK_2, new Key());
        keys.put(KeyEvent.VK_3, new Key());
        keys.put(KeyEvent.VK_4, new Key());
        keys.put(KeyEvent.VK_5, new Key());
        keys.put(KeyEvent.VK_6, new Key());
        keys.put(KeyEvent.VK_7, new Key());
        keys.put(KeyEvent.VK_8, new Key());
        keys.put(KeyEvent.VK_9, new Key());
        keys.put(KeyEvent.VK_A, new Key());
        keys.put(KeyEvent.VK_B, new Key());
        keys.put(KeyEvent.VK_C, new Key());
        keys.put(KeyEvent.VK_D, new Key());
        keys.put(KeyEvent.VK_E, new Key());
        keys.put(KeyEvent.VK_F, new Key());
    


        Integer  [] keyCodes=new Integer[keys.size()];
        keys.keySet().toArray(keyCodes);
        for (int i = 0; i < 16; i++) {
            keysMapper[i] = keyCodes[i];
        }

    }

    public Key getKey() {
        try {
            while (true) {
                for (int i = 0; i < 16; i++) {
                    Key k = keys.get(keysMapper[i]);
                    if (k.pressed)
                        return k;
                }
                Thread.sleep(16);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;

    }

}
