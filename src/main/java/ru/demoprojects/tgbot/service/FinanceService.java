package ru.demoprojects.tgbot.service;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.demoprojects.tgbot.entity.Income;
import ru.demoprojects.tgbot.entity.Spend;
import ru.demoprojects.tgbot.repository.IncomeRepository;
import ru.demoprojects.tgbot.repository.SpendRepository;

import java.math.BigDecimal;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class FinanceService {

    private static final String ADD_INCOME = "/addincome";
    private final IncomeRepository incomeRepository;
    private final SpendRepository spendRepository;

    public String addFinanceOperation(String operationType, String price, Long chatId) {
        String priceInfo ="";
        String message;

        Pattern pattern = Pattern.compile("([0-9])");
        Matcher matcher = pattern.matcher(price);
        if (matcher.find()) {
            MatchResult mr = matcher.toMatchResult();
            priceInfo = mr.group(0);}

             if (ADD_INCOME.equalsIgnoreCase(operationType)) {
                if (priceInfo.equals("")){
                    message = "Вы ввели не число";
                }else {
                Income income = new Income();
                income.setChatId(chatId);
                income.setIncome(new BigDecimal(price));
                incomeRepository.save(income);
                message = "Доход в размере " + price + " был успешно добавлен";}
            } else {
                 if (priceInfo.equals("")){
                     message = "Вы ввели не число";
                 }else {
                Spend spend = new Spend();
                spend.setChatId(chatId);
                spend.setSpend(new BigDecimal(price));
                spendRepository.save(spend);
                message = "Расход в размере " + price + " был успешно добавлен";}
            }
            return message;
        }

    }