public class Cpu {
    public class Timer{
        byte value;
        boolean active=false;
        public void tick(){
            if(value==0)
            return;
            value--;
            active=value==0?false:true;
        }
    }
    public Bus bus;
    public int I;
    public int PC;
    public byte SP;
    public int opcode;
    public byte [] registers;
    public int [] stack;
    public Timer st;
    public Timer dt;
    public Cpu(){
        registers=new byte[16];
        stack=new int[16];
        st=new Timer();
        dt=new Timer();

    }
    public void configCpu(){
        I=0x200;
    }
    
}