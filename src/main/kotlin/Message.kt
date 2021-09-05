data class Message (
    var msgId: Int = 0,
    val senderId: Int = 0,
    val receiverId: Int = 0,
    val text: String,
    var isReaded: Boolean = false
        )