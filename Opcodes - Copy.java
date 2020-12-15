import java.awt.Color;
import java.util.TreeMap;

public class Opcodes {
    public class Opcode {
        String name;
        Runnable operation;
        public Opcode(String name,Runnable operation){
            this.name=name;
            this.operation=operation;
        }
    }

    Cpu cpu;
    TreeMap<String, Opcode> opcodes;

    public Opcodes() {
        opcodes = new TreeMap<>();
    }

    public void configOpcodes(Cpu cpu) {
        this.cpu = cpu;
        Runnable r=Opcodes::op00E0;
        //opcodes.put("0.0",new Opcode("op0nnn",Opcodes::op0nnn));
     
    }

    public void execute(int opcode) {

    }

    public String getOperationKey(int opcode) {
        int prefix = opcode >> 12;

        if (prefix != 0 && prefix != 8 && prefix != 0xe && prefix != 0xf)
            return String.format("%X", prefix);
        int s;
        switch (prefix) {
            case 0:
                s = opcode & 0xff;
                if (s == 0xe0) {
                    return "0.1";
                } else if (s == 0xee) {
                    return "0.2";
                }
                return "0.0";
            case 8:
                s = opcode & 0x0f;
                if (s == 1) {
                    return "8.1";
                } else if (s == 2) {
                    return "8.2";
                } else if (s == 3) {
                    return "8.3";
                } else if (s == 4) {
                    return "8.4";
                } else if (s == 5) {
                    return "8.5";
                } else if (s == 6) {
                    return "8.6";
                } else if (s == 7) {
                    return "8.7";
                } else if (s == 0xe) {
                    return "8.8";
                }
                return "8.0";
            case 0xe:
                s = opcode & 0xff;
                if (s == 0xa1) {
                    return "E.1";
                }
                return "E.0";
            case 0xf:
                s = opcode & 0xff;
                if (s == 0x0a) {
                    return "F.1";
                } else if (s == 0x15) {
                    return "F.2";
                } else if (s == 0x18) {
                    return "F.3";
                } else if (s == 0x1e) {
                    return "F.4";
                } else if (s == 0x29) {
                    return "F.5";
                } else if (s == 0x33) {
                    return "F.6";
                } else if (s == 0x55) {
                    return "F.7";
                } else if (s == 0x65) {
                    return "F.8";
                }
                return "F.0";

        }
        return "";
    }

    /*
     * nnn or addr - A 12-bit value, the lowest 12 bits of the instruction n or
     * nibble - A 4-bit value, the lowest 4 bits of the instruction x - A 4-bit
     * value, the lower 4 bits of the high byte of the instruction y - A 4-bit
     * value, the upper 4 bits of the low byte of the instruction kk or byte - An
     * 8-bit value, the lowest 8 bits of the instruction
     */
    public int getnnn(int v) {
        return v & 0xfff;
    }

    public byte getn(int v) {
        return (byte) (v & 0xf);
    }

    public byte getx(int v) {
        return (byte) ((v >> 8) & 0xf);
    }

    public byte gety(int v) {
        return (byte) ((v >> 4) & 0xf);
    }

    public byte getkk(int v) {
        return (byte) (v & 0xff);
    }

    public void op0nnn() {
        System.out.printf("not suported opcoded %X \n", cpu.opcode);
    }

    /*
     * 00E0 - CLS Clear the display.
     */
    public void op00E0() {
        // cpu.bus.display.clear()
    }

    /*
     * 00EE - RET Return from a subroutine. The interpreter sets the program counter
     * to the address at the top of the stack, then subtracts 1 from the stack
     * pointer.
     */
    public void op00EE() {
        cpu.PC = cpu.stack[cpu.SP];
        cpu.SP--;
    }

    /*
     * 1nnn - JP addr Jump to location nnn. The interpreter sets the program counter
     * to nnn.
     */
    public void op1nnn() {
        int nnn = getnnn(cpu.opcode);
        cpu.PC = nnn;
    }

    /*
     * 2nnn - CALL addr Call subroutine at nnn. The interpreter increments the stack
     * pointer, then puts the current PC on the top of the stack. The PC is then set
     * to nnn.
     */
    public void op2nnn() {
        int nnn = getnnn(cpu.opcode);
        cpu.SP++;
        cpu.stack[cpu.SP] = cpu.PC;
        cpu.PC = nnn;
    }

    /*
     * 3xkk - SE Vx, byte Skip next instruction if Vx = kk. The interpreter compares
     * register Vx to kk, and if they are equal, increments the program counter by
     * 2.
     */
    public void op3xkk() {
        byte x = getx(cpu.opcode);
        byte kk = getkk(cpu.opcode);
        if (cpu.registers[x] == kk) {
            cpu.PC += 2;
        }
    }

    /*
     * 4xkk - SNE Vx, byte Skip next instruction if Vx != kk. The interpreter
     * compares register Vx to kk, and if they are not equal, increments the program
     * counter by 2.
     */
    public void op4xkk() {
        byte x = getx(cpu.opcode);
        byte kk = getkk(cpu.opcode);
        if (cpu.registers[x] != kk) {
            cpu.PC += 2;
        }
    }

    /*
     * 5xy0 - SE Vx, Vy Skip next instruction if Vx = Vy. The interpreter compares
     * register Vx to register Vy, and if they are equal, increments the program
     * counter by 2.
     */
    public void op5xy0() {
        byte x = getx(cpu.opcode);
        byte y = gety(cpu.opcode);
        if (cpu.registers[x] == cpu.registers[y]) {
            cpu.PC += 2;
        }
    }

    /*
     * 6xkk - LD Vx, byte Set Vx = kk. The interpreter puts the value kk into
     * register Vx.
     */
    public void op6xkk() {
        byte x = getx(cpu.opcode);
        byte kk = getkk(cpu.opcode);
        cpu.registers[x] = kk;
    }

    /*
     * 7xkk - ADD Vx, byte Set Vx = Vx + kk. Adds the value kk to the value of
     * register Vx, then stores the result in Vx.
     */
    public void op7xkk() {
        byte x = getx(cpu.opcode);
        byte kk = getkk(cpu.opcode);
        cpu.registers[x] += kk;
    }

    /*
     * 8xy0 - LD Vx, Vy Set Vx = Vy. Stores the value of register Vy in register Vx.
     */
    public void op8xy0() {
        byte x = getx(cpu.opcode);
        byte y = gety(cpu.opcode);
        cpu.registers[x] = cpu.registers[y];
    }

    /*
     * 8xy1 - OR Vx, Vy Set Vx = Vx OR Vy. Performs a bitwise OR on the values of Vx
     * and Vy, then stores the result in Vx. A bitwise OR compares the corrseponding
     * bits from two values, and if either bit is 1, then the same bit in the result
     * is also 1. Otherwise, it is 0.
     */
    public void op8xy1() {
        byte x = getx(cpu.opcode);
        byte y = gety(cpu.opcode);
        cpu.registers[x] = (byte) (cpu.registers[x] | cpu.registers[y]);
    }

    /*
     * 8xy2 - AND Vx, Vy Set Vx = Vx AND Vy. Performs a bitwise AND on the values of
     * Vx and Vy, then stores the result in Vx. A bitwise AND compares the
     * corrseponding bits from two values, and if both bits are 1, then the same bit
     * in the result is also 1. Otherwise, it is 0.
     */
    public void op8xy2() {
        byte x = getx(cpu.opcode);
        byte y = gety(cpu.opcode);
        cpu.registers[x] = (byte) (cpu.registers[x] & cpu.registers[y]);
    }

    /*
     * 8xy3 - XOR Vx, Vy Set Vx = Vx XOR Vy. Performs a bitwise exclusive OR on the
     * values of Vx and Vy, then stores the result in Vx. An exclusive OR compares
     * the corrseponding bits from two values, and if the bits are not both the
     * same, then the corresponding bit in the result is set to 1. Otherwise, it is
     * 0.
     */
    public void op8xy3() {
        byte x = getx(cpu.opcode);
        byte y = gety(cpu.opcode);
        cpu.registers[x] = (byte) (cpu.registers[x] ^ cpu.registers[y]);
    }

    /*
     * 8xy4 - ADD Vx, Vy Set Vx = Vx + Vy, set VF = carry. The values of Vx and Vy
     * are added together. If the result is greater than 8 bits (i.e., > 255,) VF is
     * set to 1, otherwise 0. Only the lowest 8 bits of the result are kept, and
     * stored in Vx.
     */
    public void op8xy4() {
        byte x = getx(cpu.opcode);
        byte y = gety(cpu.opcode);
        int res = cpu.registers[x] + cpu.registers[y];
        cpu.registers[x] = (byte) (cpu.registers[x] + cpu.registers[y]);

        if (res > 255) {
            cpu.registers[0xf] = 1;
        } else {
            cpu.registers[0xf] = 0;
        }
    }

    /*
     * 8xy5 - SUB Vx, Vy Set Vx = Vx - Vy, set VF = NOT borrow. If Vx > Vy, then VF
     * is set to 1, otherwise 0. Then Vy is subtracted from Vx, and the results
     * stored in Vx.
     */
    public void op8xy5() {
        byte x = getx(cpu.opcode);
        byte y = gety(cpu.opcode);
        if (cpu.registers[x] > cpu.registers[y]) {
            cpu.registers[0xf] = 1;
        } else {
            cpu.registers[0xf] = 0;
        }
        cpu.registers[x] = (byte) (cpu.registers[x] - cpu.registers[y]);
    }

    /*
     * 8xy6 - SHR Vx {, Vy} Set Vx = Vx SHR 1. If the least-significant bit of Vx is
     * 1, then VF is set to 1, otherwise 0. Then Vx is divided by 2.
     */
    public void op8xy6() {
        byte x = getx(cpu.opcode);
        cpu.registers[0xf] = (byte) (cpu.registers[x] & 1);
        cpu.registers[x] = (byte) (cpu.registers[x] >> 1);
    }

    /*
     * 8xy7 - SUBN Vx, Vy Set Vx = Vy - Vx, set VF = NOT borrow. If Vy > Vx, then VF
     * is set to 1, otherwise 0. Then Vx is subtracted from Vy, and the results
     * stored in Vx.
     */
    public void op8xy7() {
        byte x = getx(cpu.opcode);
        byte y = gety(cpu.opcode);
        if (cpu.registers[y] > cpu.registers[x]) {
            cpu.registers[0xf] = 1;
        } else {
            cpu.registers[0xf] = 0;
        }
        cpu.registers[x] = (byte) (cpu.registers[y] - cpu.registers[x]);
    }

    /*
     * 8xyE - SHL Vx {, Vy} Set Vx = Vx SHL 1. If the most-significant bit of Vx is
     * 1, then VF is set to 1, otherwise to 0. Then Vx is multiplied by 2.
     */
    public void op8xyE() {
        byte x = getx(cpu.opcode);
        cpu.registers[0xf] = (byte) (cpu.registers[x] >> 7);
        cpu.registers[x] = (byte) (cpu.registers[x] << 1);
    }

    /*
     * 9xy0 - SNE Vx, Vy Skip next instruction if Vx != Vy. The values of Vx and Vy
     * are compared, and if they are not equal, the program counter is increased by
     * 2.
     */
    public void op9xy0() {
        byte x = getx(cpu.opcode);
        byte y = gety(cpu.opcode);
        if (cpu.registers[x] != cpu.registers[y]) {
            cpu.PC += 2;
        }
    }

    /*
     * Annn - LD I, addr Set I = nnn. The value of register I is set to nnn.
     */
    public void opAnnn() {
        int nnn = getnnn(cpu.opcode);
        cpu.I = nnn;
    }

    /*
     * Bnnn - JP V0, addr Jump to location nnn + V0. The program counter is set to
     * nnn plus the value of V0.
     */
    public void opBnnn() {
        int nnn = getnnn(cpu.opcode);
        cpu.PC = cpu.registers[0] + nnn;
    }

    /*
     * Cxkk - RND Vx, byte Set Vx = random byte AND kk. The interpreter generates a
     * random number from 0 to 255, which is then ANDed with the value kk. The
     * results are stored in Vx. See instruction 8xy2 for more information on AND.
     */
    public void opCxkk() {
        byte x = getx(cpu.opcode);
        byte kk = getkk(cpu.opcode);
        // TODO
        // rand.Seed(time.Now().UnixNano());
        // byte t = uint8(rand.Intn(256))
        // cpu.registers[x] = kk & t
    }

    /*
Dxyn - DRW Vx, Vy, nibble
Display n-byte sprite starting at memory location I at (Vx, Vy), set VF = collision.
The interpreter reads n bytes from memory, starting at the address stored in I. These bytes are then displayed as sprites on screen at coordinates (Vx, Vy). Sprites are XORed onto the existing screen. If this causes any pixels to be erased, VF is set to 1, otherwise it is set to 0. If the sprite is positioned so part of it is outside the coordinates of the display, it wraps around to the opposite side of the screen. See instruction 8xy3 for more information on XOR, and section 2.4, Display, for more information on the Chip-8 screen and sprites.
*/
public void opDxyn() {
	int n = getn(cpu.opcode);
	byte x = getx(cpu.opcode);
	byte y = gety(cpu.opcode);
	byte posX = cpu.registers[x];
	byte posY = cpu.registers[y];
	cpu.registers[0xf] = 0;
	for (byte i = 0; i < n; i++) {
		byte data = cpu.bus.ram.read(cpu.I + i);
		//pointer to the curr pixel(bit) in the data
		byte stepPixel = 7;
		posY =(byte)(posY % cpu.bus.display.height);
		for(; stepPixel >= 0; stepPixel--) {
			byte newPixelBit =(byte)((data >> stepPixel) & 0x1);
			if (newPixelBit == 1) {

				byte currPosX =(byte)(posX + (7 - stepPixel));
				currPosX = (byte)(currPosX % cpu.bus.display.width);

				Display.Color oldpixel = cpu.bus.display.getPixel(currPosX, posY);

				//check if there is collision
				if (oldpixel.value +newPixelBit == 2) {
					cpu.registers[0xf] = 1;
                }
                Display.Color c=Display.Color.Black;
				if ((oldpixel.value ^ newPixelBit) == 1) {
					c = Display.Color.White;

				}
				cpu.bus.display.setPixel(currPosX, posY, c);
			}

		}
		posY++;
	}
}

/*
Ex9E - SKP Vx
Skip next instruction if key with the value of Vx is pressed.
Checks the keyboard, and if the key corresponding to the value of Vx is currently in the down position, PC is increased by 2.
*/
public void opEx9E() {
	byte x = getx(cpu.opcode);
	String keyCode = String.format("%X", cpu.registers[x]);
	//if cpu.bus.joypad.keys[keyCode].pressed {
	//	cpu.PC = cpu.PC + 2
    //}
    //TODO
}

/*
ExA1 - SKNP Vx
Skip next instruction if key with the value of Vx is not pressed.
Checks the keyboard, and if the key corresponding to the value of Vx is currently in the up position, PC is increased by 2.
*/
public void opExA1() {
	byte x = getx(cpu.opcode);
	String keyCode = String.format("%X", cpu.registers[x]);
	//if !cpu.bus.joypad.keys[keyCode].pressed {
	//	cpu.PC = cpu.PC + 2;
	//}
}

/*
Fx07 - LD Vx, DT
Set Vx = delay timer value.
The value of DT is placed into Vx.
*/
public void opFx07() {
	byte x = getx(cpu.opcode);
	cpu.registers[x] = cpu.dt.value;
}

/*
Fx0A - LD Vx, K
Wait for a key press, store the value of the key in Vx.
All execution stops until a key is pressed, then the value of that key is stored in Vx.
*/
public void opFx0A() {
	byte x = getx(cpu.opcode);
	//key := cpu.bus.joypad.getKey()
    //cpu.registers[x] = key.value
    //TODO
}

/*
Fx15 - LD DT, Vx
Set delay timer = Vx.
DT is set equal to the value of Vx.
*/
public void opFx15() {
	byte x = getx(cpu.opcode);
	cpu.dt.value = cpu.registers[x];
}

/*
Fx18 - LD ST, Vx
Set sound timer = Vx.
ST is set equal to the value of Vx.
*/
public void opFx18() {
	byte x = getx(cpu.opcode);
	cpu.st.value = cpu.registers[x];
}

/*
Fx1E - ADD I, Vx
Set I = I + Vx.
The values of I and Vx are added, and the results are stored in I.
*/
public void opFx1E() {
	byte x = getx(cpu.opcode);
	cpu.I = cpu.I + cpu.registers[x];
}

/*
Fx29 - LD F, Vx
Set I = location of sprite for digit Vx.
The value of I is set to the location for the hexadecimal sprite corresponding to the value of Vx. See section 2.4, Display, for more information on the Chip-8 hexadecimal font.
*/
public void opFx29() {
	byte x = getx(cpu.opcode);
	cpu.I = 5 * cpu.registers[x];
}

/*
Fx33 - LD B, Vx
Store BCD representation of Vx in memory locations I, I+1, and I+2.
The interpreter takes the decimal value of Vx, and places the hundreds digit in memory at location in I, the tens digit at location I+1, and the ones digit at location I+2.
*/
public void opFx33() {
	byte x = getx(cpu.opcode);
	byte v = cpu.registers[x];
	byte o =(byte)(v % 10);
	v =(byte)(v / 10);
	byte t =(byte)(v % 10);
	v =(byte)(v / 10);
	byte h =(byte)(v % 10);

	cpu.bus.ram.write(cpu.I, h);
	cpu.bus.ram.write(cpu.I+1, t);
	cpu.bus.ram.write(cpu.I+2, o);

}

/*
Fx55 - LD [I], Vx
Store registers V0 through Vx in memory starting at location I.
The interpreter copies the values of registers V0 through Vx into memory, starting at the address in I.
*/
public void opFx55() {
	byte x =getx(cpu.opcode);
	for (byte i = 0; i <= x; i++) {
		cpu.bus.ram.write(cpu.I+i, cpu.registers[i]);
	}
	cpu.I = cpu.I + x + 1;
}

/*
Fx65 - LD Vx, [I]
Read registers V0 through Vx from memory starting at location I.
The interpreter reads values from memory starting at location I into registers V0 through Vx.
*/
public void opFx65() {
	byte x =getx(cpu.opcode);
	for (byte i = 0; i <= x; i++) {
		cpu.registers[i] = cpu.bus.ram.read(cpu.I + i);
	}
	cpu.I = cpu.I + x + 1;
}
}
