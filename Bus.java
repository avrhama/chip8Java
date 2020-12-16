import java.io.IOException;
import java.nio.file.Files;
import java.io.File;

public class Bus {
    // public for cpu instruction
    Cpu cpu;
    Ram ram;
    Display display;
    Joypad joypad;
    private Apu apu;
    private boolean paused = true;

    public void restartBus() {
        cpu.restart();
        display.clearScreen();
    }

    public void configBus() {
        Cpu cpu = new Cpu();
        cpu.configCpu();
        Ram ram = new Ram();
        ClipApu apu = new ClipApu();
        apu.config("beep.wav");

        this.cpu = cpu;
        this.ram = ram;
        this.apu = apu;
        cpu.bus = this;

        restartBus();
    }

    public Boolean loadRom(String romPath) {
        File f = new File(romPath);
        return loadRom(f);
    }

    public Boolean loadRom(File f) {
        try {
            byte[] data = Files.readAllBytes(f.toPath());

            for (int i = 0; i < data.length; i++) {
                ram.write(cpu.PC + i, Byte.toUnsignedInt(data[i]));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void turnOn() {

        int oPTT = 10;
        int oPTTCounter = 0;
        while (true) {
            try {
                if (!paused) {
                    oPTTCounter = 0;
                    while (oPTTCounter < oPTT) {
                        cpu.execute();
                        oPTTCounter++;
                    }
                    cpu.dt.tick();
                    cpu.st.tick();
                    display.draw();
                    if (cpu.st.value > 0) {
                        apu.play();
                    }
                    // approxmiately 60HZ(60th of a second)
                }

                Thread.sleep(16);

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }

        }
    }

    public void setPaused(boolean value) {
        paused = value;
    }

}
