class Archive (override val name: String): Managed {

    var notes = mutableListOf<Note>()

    override fun toString(): String{
        return name
    }
}