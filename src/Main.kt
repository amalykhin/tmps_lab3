fun main() {
    val cpu = Cpu(Ram())
    cpu.memory.instructions.addAll(
        listOf(
            Add(2, 3),
            Multiply(5, 5),
            Subtract(2, 100),
            SwitchToProtected(),
            Add(2, 2),
            SwitchToProtected()
        )
    )

    cpu.addStateListener(object : Cpu.CpuListener {
        override fun update(register: Int) {
            println(register)
        }
    })
    try {
        while (true) {
            //println(cpu.executeNext())
            cpu.executeNext()
        }
    } catch (e: NoSuchElementException) {
        println("All instructions were executed!")
    } catch (e: Exception) {
        System.err.println(e.message)
    }

    val calculator = Calculator(cpu)
    println(calculator.add(1,2,3,4))
}
