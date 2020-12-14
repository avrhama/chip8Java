import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
public class Bus {
    Cpu cpu;
    Ram ram;
    Display display;
    public void configBus(){
        Cpu cpu=new Cpu();
        Ram ram=new Ram();
        this.cpu=cpu;
        this.ram=ram;
        cpu.bus=this;
    } 
    public Boolean loadRom(String romPath){
        try {
            
            File f = new File(romPath);
            byte [] data=Files.readAllBytes(f.toPath());
    
            for(int i =0;i<data.length;i++){
                ram.write(cpu.I+i,data[i]);
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }


        return true;
    }
}
