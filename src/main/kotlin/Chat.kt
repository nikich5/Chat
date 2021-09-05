data class Chat (
    val chatId: Int = 0,
    val senderId: Int = 0,
    val receiverId: Int = 0,
    val messages: MutableList<Message> = mutableListOf(),
    var haveUnreadMsg: Boolean = true,
        )