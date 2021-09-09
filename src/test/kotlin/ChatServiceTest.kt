import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun createChat() {
        val service = ChatService()
        val expected = Chat(1, 1, 2)

        val result = service.createChat(1, 2)
        assertEquals(result, expected)
    }

    @Test
    fun deleteChat() {
        val service = ChatService()
        service.createChat(1,2)

        val result = service.deleteChat(1)


        assertTrue(result)
    }

    @Test
    fun createMsg() {
        val service = ChatService()
        val msg1 = Message(1, 1, 2, "Тестовое сообщение 1", isReaded = true)
        val msg2 = Message(2, 1, 2, "Тестовое сообщение 2", isReaded = true)
        val expected = mutableListOf<Message>()
        expected.add(msg1)
        expected.add(msg2)

        service.createMsg(msg1.senderId, msg1.receiverId, msg1.text)
        service.createMsg(msg2.senderId, msg2.receiverId, msg2.text)
        val result = service.getMessagesFromChat(1, 0, 3)

        assertEquals(result, expected)
    }

    @Test
    fun deleteMsg() {
        val service = ChatService()
        val msg1 = Message(1, 1, 2, "Тестовое сообщение 1")
        val msgList = mutableListOf<Message>()
        msgList.add(msg1)
        val expected = Chat(1, 1, 2, messages = msgList)

        service.createMsg(1, 2, "Тестовое сообщение 1")
        service.createMsg(1, 2, "Тестовое сообщение 2")
        service.deleteMsg(2)
        val chatsFromService = service.getChats(1)
        val result = chatsFromService.last()

        assertEquals(result, expected)
    }

    @Test(expected = ChatNotFoundException::class)
    fun getMessagesFromChat_Exception() {
        val service = ChatService()
        service.createMsg(1, 2, "Тест1")
        service.getMessagesFromChat(2, 0, 3)
    }

    @Test
    fun getMessagesFromChat() {
        val service = ChatService()
        val msg1 = Message(1, 1, 2, "Тестовое сообщение 1", isReaded = true)
        val msg2 = Message(2, 1, 2, "Тестовое сообщение 2", isReaded = true)
        val expected = mutableListOf<Message>()
        expected.add(msg1)
        expected.add(msg2)

        service.createMsg(msg1.senderId, msg1.receiverId, msg1.text)
        service.createMsg(msg2.senderId, msg2.receiverId, msg2.text)
        val result = service.getMessagesFromChat(1, 0, 3)

        assertEquals(result, expected)
    }


    @Test
    fun getUnreadChatsCount() {
        val service = ChatService()
        service.createMsg(1, 2, "Тестовое сообщение 1")
        service.createMsg(1, 2, "Тестовое сообщение 2")
        service.createMsg(3, 2, "Тестовое сообщение 3")

        val result = service.getUnreadChatsCount(2)
        assertEquals(result, 2)
    }

    @Test
    fun getChats() {
        val service = ChatService()
        val chat1 = Chat(1, 1, 2)
        val chat2 = Chat(2, 2, 3)
        val expected = mutableListOf<Chat>()
        expected.add(chat1)
        expected.add(chat2)

        service.createChat(1, 2)
        service.createChat(2, 3)
        val result = service.getChats(2)

        assertEquals(result, expected)
    }
}