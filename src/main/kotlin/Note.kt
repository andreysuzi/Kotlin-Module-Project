import java.util.*

class Note(override val name: String) : Managed {

    var text = mutableListOf<String>()

    override fun toString(): String {
        return name
    }

    fun addText(){
        println("Вы можете ввести несколько строк. Для завершения введите пустую строку.")
        while (true) {
            val line = Scanner(System.`in`).nextLine()
            if (line == "")
                break
            else
                this.text.add(line)
        }
    }
}
