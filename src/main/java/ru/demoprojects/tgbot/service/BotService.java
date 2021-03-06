package ru.demoprojects.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.demoprojects.tgbot.TgbotApplication;
import ru.demoprojects.tgbot.dto.ValuteCursOnDate;
import ru.demoprojects.tgbot.entity.ActiveChat;
import ru.demoprojects.tgbot.llogger.StepsLog;
import ru.demoprojects.tgbot.repository.ActiveChatRepository;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;



@Service
@Slf4j
@RequiredArgsConstructor
public class BotService extends TelegramLongPollingBot {

    private static Logger logger = LoggerFactory.getLogger(BotService.class);
    private final CentralRussianBankService centralRussianBankService;
    private final ActiveChatRepository activeChatRepository;
    private final Map<Long, List<String>> previousCommands = new ConcurrentHashMap<>();
    private static final String ADD_INCOME = "/addincome";
    private static final String ADD_SPEND = "/addspend";
    private final String CURRENT_RATES = "/currentrates";
    private final FinanceService financeService;




    @Value("${bot.api.key}")
    private String apiKey;

    @Value("${bot.name}")
    private String name;


    private void putPreviousCommand(Long chatId, String command) {
        if (previousCommands.get(chatId) == null) {
            List<String> commands = new ArrayList<>();
            commands.add(command);
            previousCommands.put(chatId, commands);
        } else {
            previousCommands.get(chatId).add(command);
        }
    }


    private String getPreviousCommand(Long chatId) {
        return previousCommands.get(chatId).get(previousCommands.get(chatId).size() - 1);
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        try {
            SendMessage response = new SendMessage();
            Long chatId = message.getChatId();
            response.setChatId(String.valueOf(chatId));
            if (CURRENT_RATES.equalsIgnoreCase(message.getText())) {
                for (ValuteCursOnDate valuteCursOnDate : centralRussianBankService.getCurrenciesFromCbr()) {
                    response.setText(StringUtils.defaultIfBlank(response.getText(), " ") + valuteCursOnDate.getName() + " - " + valuteCursOnDate.getCourse() + "\n");
                }
            } else if (ADD_INCOME.equalsIgnoreCase(message.getText())) {
                response.setText("?????????????????? ?????? ?????????? ?????????????????????? ????????????");
            } else if (ADD_SPEND.equalsIgnoreCase(message.getText())) {
                response.setText("?????????????????? ?????? ?????????? ????????????????");
            } else {
                response.setText(financeService.addFinanceOperation(getPreviousCommand(message.getChatId()), message.getText(), message.getChatId()));
            }

            logger.error("It is an error logger.");

            putPreviousCommand(message.getChatId(), message.getText());
            execute(response);
            if (activeChatRepository.findActiveChatByChatId(chatId).isEmpty()) {
                ActiveChat activeChat = new ActiveChat();
                activeChat.setChatId(chatId);
                activeChatRepository.save(activeChat);
            }
        } catch (Exception e) {
            logger.error("???????????????? ?????????????????????? ????????????????, ???????????????? ???????????????????? ????????????????????????????", e);
        }
    }

    @PostConstruct
    public void start() {
        log.info("username: {}, token: {}", name, apiKey);
    }


    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return apiKey;
    }

    public void sendNotificationToAllActiveChats(String message, Set<Long> chatId) {
        for (Long id : chatId) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(id));
            sendMessage.setText(message);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                logger.error("???? ?????????????? ?????????????????? ??????????????????", e);
            }
        }
    }
}

