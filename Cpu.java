

public class Cpu {
    public class Timer{
        int value;
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
    public int SP;
    public int opcode;
    public int [] registers;
    public int [] stack;
    public Timer st;
    public Timer dt;
    private Opcodes opcodes;
    public Cpu(){
        registers=new int[16];
        stack=new int[16];
        st=new Timer();
        dt=new Timer();
        opcodes=new Opcodes();

    }
    public void configCpu(){
        PC=0x200;
        opcodes.configOpcodes();
    }
    public void execute(){
       
        int opcodePrefix =bus.ram.read(PC);
        int opcodeSuffix = bus.ram.read(PC+1);
        PC += 2;
        opcode = (opcodePrefix << 8) | opcodeSuffix;
        String operationKey =  opcodes.getOperationKey(opcode);
        Opcodes.Opcode op=null;
        if(opcodes.opcodesTable.containsKey(operationKey)){
           op=opcodes.opcodesTable.get(operationKey);
           // System.out.printf("name:%s opcode:%X prefix:%X suffix:%X\n",op.name,opcode,opcodePrefix,opcodeSuffix);
            op.operation.accept(this);
        }else{
            System.out.printf("such opcode not supportd! opcode:%X prefix:%X suffix:%X\n",opcode,opcodePrefix,opcodeSuffix);
            System.exit(1);
        }

        for(int i=0;i<16;i++){
            if(registers[i]>255||registers[i]<0)
            {
                System.out.printf("Weird... opcode:%X prefix:%X suffix:%X op:%s\n",opcode,opcodePrefix,opcodeSuffix,op.name);
                
                System.exit(1);
            }
        }
       
    }
    
}
