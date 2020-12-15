import javax.swing.*;

public class Chip8 {

    public static void sayHello() {
        try {
            Thread.sleep(20000);
            System.out.println("sdsds");
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        Bus bus = new Bus();
        bus.configBus();
        bus.loadRom("C:\\Users\\Epsilon\\Documents\\roms\\chip8\\Space Invaders [David Winter].ch8");
        Opcodes op = new Opcodes();
        System.out.println(op.getOperationKey(0xA000));
        new Thread(new Runnable() {
            public void run() {
                GuiDisplay display=new GuiDisplay();
                display.config();
                GuiJoypad joypad = new GuiJoypad();
                display.frame.addKeyListener(joypad);
            }
        }).start();

        System.out.println("1");
        boolean t = false;
        while (t) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

        }

        System.out.println("2");

    }
}
