
fun main(args: Array<String>) {
    println("Приложение для работы с заметками")
    val archives = mutableListOf<Archive>()
    val menu = Menu()
    var currentArchive: Archive? = null
    var running = true

    while(running){
        println("---")
        println("Вы находитесь в главном меню")
        println("Всего архивов: ${archives.size}")
        menu.takeAction(
            onExit = {message -> println(message);
            running = false
            },
            onCreate = {items, name -> items.add(Archive(name))},
            onSelect = {selected -> currentArchive = selected},
            archives,
            "Выход из программы"
        )
        while (currentArchive != null){
            var currentNote: Note? = null
            println("---")
            println("Вы находитесь в архиве \"$currentArchive\"")
            println("Заметок в архиве: ${currentArchive!!.notes.size}")
            menu.takeAction(
                onExit = { message ->
                    println(message);
                    currentArchive = null
                },
                onCreate = {items, name -> items.add(Note(name))},
                onSelect = {selected -> currentNote = selected},
                currentArchive!!.notes,
                "Выход в главное меню"
                )
            while (currentNote != null){
                println("---")
                println("Вы находитесь в заметке \"$currentNote\"")
                if(currentNote!!.text.size == 0){
                    println("Заметка пустая")
                }
                else {
                    println("Текст заметки:")
                    for (s in currentNote!!.text) println(s)
                }
                menu.takeNoteAction(
                    currentNote!!,
                    onExit = {
                        println("Выход в архив");
                        currentNote = null
                    }
                )
            }
        }
    }
}