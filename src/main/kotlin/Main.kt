fun main(args: Array<String>) {
    println("Приложение для работы с заметками")
    val archives = mutableListOf<Archive>()
    val menu = Menu()
    var currentArchive: Archive? = null
    var running = true

    while (running) {
        println("---")
        println("Вы находитесь в главном меню")
        println("Всего архивов: ${archives.size}")
        menu.takeAction(
            onExit = { message ->
                println (message)
                running = false
            },
            onCreate = { items, name -> items.add(Archive(name)) },
            onSelect = { selected -> currentArchive = selected },
            archives,
            "архив",
            "Выход из программы"
        )
        while (currentArchive != null) {
            var currentNote: Note? = null
            println("---")
            println("Вы находитесь в архиве \"$currentArchive\"")
            println("Заметок в архиве: ${currentArchive!!.notes.size}")
            menu.takeAction(
                onExit = { message ->
                    println(message)
                    currentArchive = null
                },
                onCreate = { items, name ->
                    val note = Note(name)
                    items.add(note)
                    currentNote = note
                },
                onSelect = { selected -> currentNote = selected },
                currentArchive!!.notes,
                "заметку",
                "Выход в главное меню"
            )
            while (currentNote != null) {
                println("---")
                println("Вы находитесь в заметке \"$currentNote\"")
                if (currentNote!!.text.size == 0) {
                    println("Заметка пустая. Введите текст заметки.")
                    currentNote!!.addText()
                } else {
                    println("Текст заметки:")
                    for (s in currentNote!!.text) println(s)
                    println("---")
                    menu.takeNoteAction(
                        currentNote!!,
                        onExit = {
                            println("Выход в архив")
                            currentNote = null
                        }
                    )
                }
            }
        }
    }
}