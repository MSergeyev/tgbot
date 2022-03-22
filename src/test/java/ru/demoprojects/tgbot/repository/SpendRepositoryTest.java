package ru.demoprojects.tgbot.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.demoprojects.tgbot.entity.Spend;

import java.util.List;

@DataJpaTest
class SpendRepositoryTest {

    @Autowired
    private SpendRepository spendRepository;

    @Test
    public void testRepo(){
        for (int i=0; i<10; i++, spendRepository.save(new Spend())){
            final List<Spend> found = spendRepository.findAll();
            Assert.assertEquals(10, found.size());

        }

    }}