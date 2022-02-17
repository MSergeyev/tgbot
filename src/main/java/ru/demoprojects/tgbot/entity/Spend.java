package ru.demoprojects.tgbot.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "spend")
@Data
public class Spend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "spend")
    private BigDecimal spend;



}
