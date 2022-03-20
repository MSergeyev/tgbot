package ru.demoprojects.tgbot.entity;

import javax.persistence.*;

@Entity
@Table(name = "active_chat")
public class ActiveChat {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;


    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}
