package ru.demoprojects.tgbot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class TgbotApplication {

	private static Logger logger = LoggerFactory.getLogger(TgbotApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(TgbotApplication.class, args);
		logger.trace("It is a trace logger.");

//		SessionFactory factory = new Configuration()
//				.configure("hibernate.cfg.xml")
//				.addAnnotatedClass(Spend.class)
//				.buildSessionFactory();
//		try {
//			Session session = factory.getCurrentSession();
//			session.beginTransaction();
////			Spend sp = session.get(Spend.class,1);
////			sp.getChatId();
//			session.createQuery("update Spend set id=1 where spend=123").executeUpdate();
//			session.getTransaction().commit();
//
//		}finally {
//			factory.close();
//		}
//	}

}}
