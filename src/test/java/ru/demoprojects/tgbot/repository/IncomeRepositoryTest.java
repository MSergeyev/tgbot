package ru.demoprojects.tgbot.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.demoprojects.tgbot.entity.Income;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@DataJpaTest //настройка теста для JPA
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IncomeRepositoryTest {

    @Autowired//заглушка проверяемого репозитория
    private IncomeRepository incomeRepository;

    //наполняем базу произвольными значениями
    @Test
    public void testRepo(){
        for (int i =0; i< 10; i++, incomeRepository.save(new Income())){
            final List<Income> found = incomeRepository.findAll();
            //проверяем в базе лежить то кол-во что полоижи
            Assert.assertEquals(10, found.size());      }
    }
    @Test
    public void testDataScripts(){
       Optional<Income> income= incomeRepository.findById(12345L);
       Assert.assertTrue(income.isPresent());
       Assert.assertEquals(new BigDecimal("3000.00"), income.get().getIncome());
    }

}