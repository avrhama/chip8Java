import java.io.IOException;
import java.nio.file.Files;
import java.io.File;

public class Bus {
    Cpu cpu;
    Ram ram;
    Display display;
    Joypad joypad;
    Object mutex;
    public void configBus() {
       mutex = new Object();
        Cpu cpu = new Cpu();
        cpu.configCpu();
        Ram ram = new Ram();
        this.cpu = cpu;
        this.ram = ram;
        cpu.bus = this;

    }

    public Boolean loadRom(String romPath) {
        try {

            File f = new File(romPath);
            byte[] data = Files.readAllBytes(f.toPath());

            for (int i = 0; i < data.length; i++) {
                ram.write(cpu.PC + i,Byte.toUnsignedInt(data[i]));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public void turnOn() {
        final GuiDisplay guiDisplay = new GuiDisplay();
        final GuiJoypad guiJoypad = new GuiJoypad();

       
        new Thread(new Runnable() {
            public void run() {
                guiDisplay.config();
                guiJoypad.config();
                guiDisplay.frame.addKeyListener(guiJoypad);
            }
        }).start();

        joypad=guiJoypad;
        display=guiDisplay;
        joypad.bus=this;
        while (true) {
            try {
                int oPTT = 10;
                int oPTTCounter = 0;


                oPTTCounter = 0;
                while(oPTTCounter < oPTT) {
                    synchronized(mutex){
                        cpu.execute();
                    }
                   
                    oPTTCounter++;
                }
                cpu.dt.tick();
                cpu.st.tick();
                display.draw();
                if (cpu.st.value > 0) {
                    //bus.apu.play();
                    System.out.println("Beep!");
                }
                //approxmiately 60HZ(60th of a second)
                Thread.sleep(16);




               
            } catch (Exception e) {
            }

        }
    }
}
