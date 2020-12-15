
public class Ram {

   private int[] mem;

    public Ram() {
        mem = new int[4096];
    }
public void configRam(){
    //fill the mem with hex digits representations
//0
mem[0] = 0xF0;
mem[1] = 0x90;
mem[2] = 0x90;
mem[3] = 0x90;
mem[4] = 0xF0;
//1          
mem[5] = 0x20;
mem[6] = 0x60;
mem[7] = 0x20;
mem[8] = 0x20;
mem[9] = 0x70;
//2
mem[10] = 0xF0;
mem[11] = 0x10;
mem[12] = 0xF0;
mem[13] = 0x80;
mem[14] = 0xF0;
//3
mem[15] = 0xF0;
mem[16] = 0x10;
mem[17] = 0xF0;
mem[18] = 0x10;
mem[19] = 0xF0;
//4
mem[20] = 0x90;
mem[21] = 0x90;
mem[22] = 0xF0;
mem[23] = 0x10;
mem[24] = 0x10;
//5
mem[25] = 0xF0;
mem[26] = 0x80;
mem[27] = 0xF0;
mem[28] = 0x10;
mem[29] = 0xF0;
//6
mem[30] = 0xF0;
mem[31] = 0x80;
mem[32] = 0xF0;
mem[33] = 0x90;
mem[34] = 0xF0;
//7
mem[35] = 0xF0;
mem[36] = 0x10;
mem[37] = 0x20;
mem[38] = 0x40;
mem[39] = 0x40;
//8
mem[40] = 0xF0;
mem[41] = 0x90;
mem[42] = 0xF0;
mem[43] = 0x90;
mem[44] = 0xF0;
//9              
mem[45] = 0xF0;
mem[46] = 0x90;
mem[47] = 0xF0;
mem[48] = 0x10;
mem[49] = 0xF0;
//A              
mem[50] = 0xF0;
mem[51] = 0x90;
mem[52] = 0xF0;
mem[53] = 0x90;
mem[54] = 0x90;
//B           
mem[55] = 0xE0;
mem[56] = 0x90;
mem[57] = 0xE0;
mem[58] = 0x90;
mem[59] = 0xE0;
//C           
mem[60] = 0xF0;
mem[61] = 0x80;
mem[62] = 0x80;
mem[63] = 0x80;
mem[64] = 0xF0;
//D           
mem[65] = 0xE0;
mem[66] = 0x90;
mem[67] = 0x90;
mem[68] = 0x90;
mem[69] = 0xE0;
//E           
mem[70] = 0xF0;
mem[71] = 0x80;
mem[72] = 0xF0;
mem[73] = 0x80;
mem[74] = 0xF0;
//F            
mem[75] = 0xF0;
mem[76] = 0x80;
mem[77] = 0xF0;
mem[78] = 0x80;
mem[79] = 0x80;
}
    public int read(int address) {
        if (address < mem.length) {
            return mem[address];
        }
        return 0;
    }

    public void write(int address, int data) {
        if (address < mem.length) {
            mem[address] = data;
        }

    }

   
}
