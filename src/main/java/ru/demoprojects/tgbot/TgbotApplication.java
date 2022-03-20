package ru.demoprojects.tgbot;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.demoprojects.tgbot.entity.Spend;

@EnableScheduling
@SpringBootApplication
public class TgbotApplication {

	public static void main(String[] args) {

		SpringApplication.run(TgbotApplication.class, args);

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Spend.class)
				.buildSessionFactory();
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Spend sp = session.get(Spend.class,1);
			sp.getChatId();
			session.getTransaction().commit();

		}finally {
			factory.close();
		}
	}

}
