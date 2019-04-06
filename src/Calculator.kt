// Facade
class Calculator(val cpu: Cpu) {
    fun add(vararg operands: Int) : Int {
        cpu.reset()
        var result = 0
        operands.forEach {
            cpu.memory.instructions.add(Add(result, it))
            result = cpu.executeNext()!!
        }
        return result;
    }

    fun subtract(vararg operands: Int) : Int {
        cpu.reset()
        var result = operands[0]
        operands
            .takeLast(operands.size - 1)
            .forEach {
                cpu.memory.instructions.add(Subtract(result, it))
                result = cpu.executeNext()!!
            }
        return result
    }

    fun multiply(vararg operands: Int) : Int {
        cpu.reset()
        var result = 1
        operands.forEach {
            cpu.memory.instructions.add(Multiply(result, it))
            result = cpu.executeNext()!!
        }
        return result
    }

    fun divide(vararg operands: Int) : Int {
        cpu.reset()
        var result = operands[0]
        operands
            .take(operands.size)
            .forEach {
                cpu.memory.instructions.add(Divide(result, it))
                result = cpu.executeNext()!!
            }
        return result
    }
}