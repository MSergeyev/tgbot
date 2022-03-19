package ru.demoprojects.tgbot.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.demoprojects.tgbot.entity.Income;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@DataJpaTest
class IncomeRepositoryTest {

    @Autowired
    private IncomeRepository incomeRepository;

    @Test
    public void testRepo(){
        for (int i =0; i< 10; i++, incomeRepository.save(new Income())){
            final List<Income> found = incomeRepository.findAll();
            Assert.assertEquals(10, found.size());      }
    }
    @Test
    public void testDataScripts(){
       Optional<Income> income= incomeRepository.findById(12345L);
       Assert.assertTrue(income.isPresent());
       Assert.assertEquals(new BigDecimal("3000.00"), income.get().getIncome());
    }

}