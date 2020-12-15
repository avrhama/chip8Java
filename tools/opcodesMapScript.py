t='''
cpu.opcodes["0.0"] = Opcode{name: "op0nnn", operation: cpu.op0nnn}
cpu.opcodes["0.1"] = Opcode{name: "op00E0", operation: cpu.op00E0}
cpu.opcodes["0.2"] = Opcode{name: "op00EE", operation: cpu.op00EE}

cpu.opcodes["1"] = Opcode{name: "op1nnn", operation: cpu.op1nnn}
cpu.opcodes["2"] = Opcode{name: "op2nnn", operation: cpu.op2nnn}
cpu.opcodes["3"] = Opcode{name: "op3xkk", operation: cpu.op3xkk}
cpu.opcodes["4"] = Opcode{name: "op4xkk", operation: cpu.op4xkk}
cpu.opcodes["5"] = Opcode{name: "op5xy0", operation: cpu.op5xy0}
cpu.opcodes["6"] = Opcode{name: "op6xkk", operation: cpu.op6xkk}
cpu.opcodes["7"] = Opcode{name: "op7xkk", operation: cpu.op7xkk}

cpu.opcodes["8.0"] = Opcode{name: "op8xy0", operation: cpu.op8xy0}
cpu.opcodes["8.1"] = Opcode{name: "op8xy1", operation: cpu.op8xy1}
cpu.opcodes["8.2"] = Opcode{name: "op8xy2", operation: cpu.op8xy2}
cpu.opcodes["8.3"] = Opcode{name: "op8xy3", operation: cpu.op8xy3}
cpu.opcodes["8.4"] = Opcode{name: "op8xy4", operation: cpu.op8xy4}
cpu.opcodes["8.5"] = Opcode{name: "op8xy5", operation: cpu.op8xy5}
cpu.opcodes["8.6"] = Opcode{name: "op8xy6", operation: cpu.op8xy6}
cpu.opcodes["8.7"] = Opcode{name: "op8xy7", operation: cpu.op8xy7}
cpu.opcodes["8.8"] = Opcode{name: "op8xyE", operation: cpu.op8xyE}

cpu.opcodes["9"] = Opcode{name: "op9xy0", operation: cpu.op9xy0}
cpu.opcodes["A"] = Opcode{name: "opAnnn", operation: cpu.opAnnn}
cpu.opcodes["B"] = Opcode{name: "opBnnn", operation: cpu.opBnnn}
cpu.opcodes["C"] = Opcode{name: "opCxkk", operation: cpu.opCxkk}
cpu.opcodes["D"] = Opcode{name: "opDxyn", operation: cpu.opDxyn}

cpu.opcodes["E.0"] = Opcode{name: "opEx9E", operation: cpu.opEx9E}
cpu.opcodes["E.1"] = Opcode{name: "opExA1", operation: cpu.opExA1}

cpu.opcodes["F.0"] = Opcode{name: "opFx07", operation: cpu.opFx07}
cpu.opcodes["F.1"] = Opcode{name: "opFx0A", operation: cpu.opFx0A}
cpu.opcodes["F.2"] = Opcode{name: "opFx15", operation: cpu.opFx15}
cpu.opcodes["F.3"] = Opcode{name: "opFx18", operation: cpu.opFx18}
cpu.opcodes["F.4"] = Opcode{name: "opFx1E", operation: cpu.opFx1E}
cpu.opcodes["F.5"] = Opcode{name: "opFx29", operation: cpu.opFx29}
cpu.opcodes["F.6"] = Opcode{name: "opFx33", operation: cpu.opFx33}
cpu.opcodes["F.7"] = Opcode{name: "opFx55", operation: cpu.opFx55}
cpu.opcodes["F.8"] = Opcode{name: "opFx65", operation: cpu.opFx65}
'''

lines=t.split('\n')
for i in range(len(lines)):
    line=lines[i].replace('\n','')
    if line=='':
        print('')
        continue
    line=line[13:]
    k=line.find('"')
    opcodeKey=line[:k]
    opcodeName=line[k+19:k+25]
    newLine='opcodes.put("{}",new Opcode("{}",cpu->{}(cpu)));'.format(opcodeKey,opcodeName,opcodeName)
    print(newLine)


