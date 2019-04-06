import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf

// Command
class Cpu (val memory: Ram){
    abstract class Instruction(private vararg val operands: Int){
        abstract fun execute() : Int?
    }

    // State
    abstract class OperationMode {
        abstract fun executeInstruction(instruction: Instruction, vararg operands: Int)
    }

    // Observer
    interface CpuListener {
        fun update(register: Int)
    }

    @Throws(NoSuchElementException::class)
    fun executeNext(): Int? {
        //mode.executeInstruction(memory.instructions.remove())
        val iterator = memory.getIterator()
        if (iterator.hasNext())
            mode.executeInstruction(iterator.next())
        else throw NoSuchElementException("No instructions left")
        notifyListeners()
        return register
    }

    fun reset() {
        mode = real;
        register = 0;
    }

    fun addStateListener(l: CpuListener) {
        stateListeners.add(l)
    }

    private fun notifyListeners() {
        stateListeners.forEach {
            it.update(register)
        }
    }

    private val protected = object : OperationMode() {
        override fun executeInstruction(instruction: Instruction, vararg operands: Int) {
            if (instruction !is SwitchToProtected) {
                register = instruction.execute()!!
                //println(register)
            } else
                throw Exception("Can't execute privileged instructions in protected mode!")
        }
    }

    private val real = object : OperationMode() {
        override fun executeInstruction(instruction: Instruction, vararg operands: Int) {
            register = instruction.execute() ?: 0
            //println(register)
            if (instruction is SwitchToProtected)
                mode = protected
        }
    }

    val stateListeners: MutableList<CpuListener> = mutableListOf()

    var mode: OperationMode = real
        private set
    var register: Int = 0
}