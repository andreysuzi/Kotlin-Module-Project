import java.util.Scanner

class Menu() {
    fun <E: Managed> takeAction(
        onExit: (String) -> Unit,
        onCreate: (MutableList<E>, String) -> Unit,
        onSelect: (E) -> Unit,
        items: MutableList<E>,
        exitMessage: String
    ){
        println("0: Создать")
        for(i in items.indices){
            println("${i + 1}: ${items.get(i)}")
        }
        println("${items.size + 1}: Выйти")
        println("Введите код действия:")
        val code = takeCode()
        when (code) {
                0 -> onCreate.invoke(items, takeName())
                in 1 .. items.size -> onSelect.invoke(items.get(code - 1))
                items.size + 1 -> onExit.invoke(exitMessage)
                else -> println("Неверный код")
            }

    }

    fun takeNoteAction(
        note: Note,
        onExit: () -> Unit
                       ){
        println("Что вы хотите сделать с заметкой?")
        println("0: Отредактировать")
        println("1: Выйти")
        println("Введите код действия:")
        val code = takeCode()
        when(code) {
            0 -> editNote(note!!.text)
            1 -> onExit.invoke()
            else -> println("Неверный код")
        }
    }

    fun editNote(text: MutableList<String>){
        while (true) {
            println("---")
            println("Текст заметки:")
            printNumberedLines(text)
            println("Доступные действия:")
            println("0: Добавить строку в конец заметки")
            println("1: Вставить строку выше")
            println("2: Удалить строку")
            println("3: Завершить редактирование")
            println("Введите код действия:")
            val code = takeCode()
            when (code) {
                0 -> text.add(takeLine())
                1 -> {
                    println("Введите номер строки, перед которой хотите вставить новую строку:")
                    val number = takeCode()
                    if (number in 1 .. text.size) {
                        println("Введите строку, которую хотите доавить:")
                        text.add(number - 1, Scanner(System.`in`).nextLine())
                    }
                    else
                        println("Такой строки нет")
                }
                2 -> {
                    println("Введите номер строки, которую хотите удалить:")
                    val number = takeCode()
                    if (number in 1 .. text.size)
                        text.removeAt(number - 1)
                    else
                        println("Такой строки нет")
                }
                3 -> break
                else -> println("Неверный код")
            }
        }
    }

    private fun takeCode(): Int {
        while (true){
            val line = Scanner(System.`in`).nextLine()
            val code = line.toIntOrNull()
            if(code == null)
                println("Необходимо ввести целое число:")
            else if (code < 0)
                println("Необходимо ввести целое неотрицательное число:")
            else
                return code
        }
    }

    private fun takeName(): String {
        println("Введите название:")
        return Scanner(System.`in`).nextLine()
    }

    private fun takeLine(): String {
        println("Введите строку:")
        return Scanner(System.`in`).nextLine()
    }

    private fun printNumberedLines(text: MutableList<String>){
        for (i in text.indices)
            println("${i + 1}: ${text.get(i)}")
    }
}