package ru.demoprojects.tgbot.repository;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.demoprojects.tgbot.entity.ActiveChat;

import java.util.Optional;


@DataJpaTest
class ActiveChatRepositoryTest {
    @Autowired
    private ActiveChatRepository activeChatRepository;

    @Test
    public void testRepo(){
        final ActiveChat activeChat = new ActiveChat();
        activeChat.setChatId(12345L);
        activeChatRepository.save(activeChat);
        Optional<ActiveChat> activeChatByChatId = activeChatRepository.findActiveChatByChatId(12345L);
        Assert.assertTrue(activeChatByChatId.isPresent());
        Assert.assertEquals(Optional.of(1345L), activeChatByChatId.get().getChatId());
    }


    @Test
    public void testRepo_notFound(){
        final ActiveChat activeChat =new ActiveChat();
        activeChat.setChatId(12345L);
        activeChatRepository.save(activeChat);
        Optional<ActiveChat> activeChatByChatId = activeChatRepository.findActiveChatByChatId(54321L);
        Assert.assertFalse(activeChatByChatId.isPresent());
    }

}