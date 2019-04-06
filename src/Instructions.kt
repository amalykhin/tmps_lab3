class Add(private val op1: Int, private val op2: Int) : Cpu.Instruction(op1, op2) {
    override fun execute() : Int? {
        return op1 + op2
    }
}

class Subtract(private val op1: Int, private val op2: Int) : Cpu.Instruction(op1, op2) {
    override fun execute() : Int? {
        return op1 - op2
    }
}
class Multiply(private val op1: Int, private val op2: Int) : Cpu.Instruction(op1, op2) {
    override fun execute() : Int? {
        return op1 * op2
    }
}
class Divide(private val op1: Int, private val op2: Int) : Cpu.Instruction(op1, op2) {
    override fun execute() : Int? {
        return op1 / op2
    }
}

class SwitchToProtected : Cpu.Instruction() {
    override fun execute() : Int? { return null }
}
