import java.util.*

class Ram {
    val instructions: Queue<Cpu.Instruction> = ArrayDeque()

    // Iterator
    inner class RamIterator(private val ram: Ram) {
        fun hasNext(): Boolean {
            return !ram.instructions.isEmpty();
        }

        @Throws(NoSuchElementException::class)
        fun next(): Cpu.Instruction {
            return ram.instructions.remove()
        }
    }

    fun getIterator() : RamIterator {
        return RamIterator(this);
    }
}
