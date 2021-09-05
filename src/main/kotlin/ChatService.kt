class ChatService {
    private var chatId = 0
    private var msgId = 0
    private val chats = mutableListOf<Chat>()

    private fun createChat(senderId: Int, receiverId: Int): Chat {
        chatId++
        val chat = Chat(chatId, senderId, receiverId)
        chats += chat
        return chats.last()
    }

    fun deleteChat(chatId: Int): Boolean {
        return chats.removeIf { it.chatId == chatId }
    }

    fun createMsg(senderId: Int, receiverId: Int, textMsg: String) {
        msgId++
        val newMsg = Message(msgId, senderId, receiverId, textMsg)

        chats.find { it.receiverId == receiverId }?.messages?.add(newMsg) ?:
        createChat(senderId, receiverId).messages.add(newMsg)
    }

    fun deleteMsg(msgId: Int) {
        chats.forEach { it.messages.removeIf{ message: Message -> message.msgId == msgId }
        if (it.messages.size == 0) {
            deleteChat(it.chatId)
        }}
    }

    fun getMessagesFromChat(chatId: Int, lastMsgId: Int): MutableList<Message> {
        val messagesList = mutableListOf<Message>()
        val messages = chats.find { it.chatId == chatId }?.messages ?: throw ChatNotFoundException()

        messages.forEach { if(it.msgId >= lastMsgId) {
            it.isReaded = true
            messagesList.add(it)
        } }

        return messagesList
    }

    fun getAllMessagesFromChat(chatId: Int): MutableList<Message> {
        return chats.find { it.chatId == chatId }?.messages ?: throw ChatNotFoundException()
    }

    fun getUnreadChatsCount(userId: Int): Int {
        return chats.filter { (it.senderId == userId || it.receiverId == userId) && it.haveUnreadMsg }.size
    }

    fun getChats(id: Int): List<Chat> {
        return chats.filter { (it.senderId == id || it.receiverId == id)}
    }
}