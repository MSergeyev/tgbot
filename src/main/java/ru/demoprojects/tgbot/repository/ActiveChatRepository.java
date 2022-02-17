package ru.demoprojects.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demoprojects.tgbot.entity.ActiveChat;

import java.util.Optional;


public interface ActiveChatRepository extends JpaRepository<ActiveChat, Long> {
    Optional<ActiveChat> findActiveChatByChatId (Long chatId);

//JpaRepository и CrudRepository для работы с базой операции (вставки, удаления, чтения и обновления
}
