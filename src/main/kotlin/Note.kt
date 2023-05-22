class Note (override val name: String): Managed{

    var text = mutableListOf<String>()

    override fun toString(): String{
        return name
    }
}
