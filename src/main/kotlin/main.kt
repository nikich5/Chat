fun main() {
    val chatService = ChatService()
    chatService.createMsg(1, 1, "Привет")
    chatService.createMsg(1, 1, "Пока")
    chatService.createMsg(1, 2, "Привет nikich5")
    chatService.createMsg(2, 1, "Привет KEXA76")
    println(chatService.getAllMessagesFromChat(1))
    println(chatService.getAllMessagesFromChat(2))
    println(chatService.getAllMessagesFromChat(3))
    println()
    chatService.deleteChat(3)
    chatService.deleteMsg(4)
    println(chatService.getMessagesFromChat(1,0))
    chatService.getUnreadChatsCount(1)
    chatService.getChats(1)

}