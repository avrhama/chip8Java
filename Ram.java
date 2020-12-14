
public class Ram {

   private byte[] mem;

    public Ram() {
        mem = new byte[4096];
    }

    public byte read(int address) {
        if (address < mem.length) {
            return mem[address];
        }
        return 0;
    }

    public void write(int address, byte data) {
        if (address < mem.length) {
            mem[address] = data;
        }

    }

   
}
