
public class Cpu {
    public class Timer {
        int value;
        public void tick() {
            if (value == 0)
                return;
            value--;
        }
    }
    //public for opcodes.
    public Bus bus;
    public int I;
    public int PC;
    public int SP;
    public int opcode;
    public int[] registers;
    public int[] stack;
    public Timer st;
    public Timer dt;
    private Opcodes opcodes;

    public Cpu() {
        registers = new int[16];
        stack = new int[16];
        st = new Timer();
        dt = new Timer();
        opcodes = new Opcodes();

    }

    public void configCpu() {
        PC = 0x200;
        opcodes.configOpcodes();
    }

    public void restart() {
        PC = 0x200;
        dt.value=0;
        st.value=0;
        SP=0;
        I=0;
        opcode=0;
        for (int i = 0; i < 16; i++) {
            registers[i] = 0;
        }
    }

    public void execute() {

        int opcodePrefix = bus.ram.read(PC);
        int opcodeSuffix = bus.ram.read(PC + 1);
        PC += 2;
        opcode = (opcodePrefix << 8) | opcodeSuffix;
        String operationKey = opcodes.getOperationKey(opcode);
        Opcodes.Opcode op = null;
        if (opcodes.opcodesTable.containsKey(operationKey)) {
            op = opcodes.opcodesTable.get(operationKey);
            op.operation.accept(this);
        } else {
            System.out.printf("such opcode is not supported! opcode:%X prefix:%X suffix:%X\n", opcode, opcodePrefix,
                    opcodeSuffix);
            System.exit(1);
        }

    }

}
