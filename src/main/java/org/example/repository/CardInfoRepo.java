package org.example.repository;

import org.example.model.CardInfo;
import org.example.model.StudentLoan;
import org.example.model.UserAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;

public class CardInfoRepo {

    public static final String SELECT_BY_CARD = "from CardInfo where cardNumber =: cardNumber and cvv =: cvv and ex =: ex and userId =: userId";

    public void createCard(CardInfo cardInfo) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .addAnnotatedClass(CardInfo.class)
                .buildSessionFactory();


        Session session = factory.openSession();

        try {
            session.beginTransaction();
            session.save(cardInfo);
            session.getTransaction().commit();
            System.out.println("Done!");


        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
    }

    public CardInfo findCard(Long cardNumber , int cvv , LocalDate ex , int id) {
        SessionFactory connection = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .addAnnotatedClass(CardInfo.class)
                .buildSessionFactory();
        Session session = connection.openSession();
        try {
            session.beginTransaction();
            Query<CardInfo> query = session.createQuery(SELECT_BY_CARD);
            query.setParameter("cardNumber",cardNumber);
            query.setParameter("cvv",cvv);
            query.setParameter("ex",ex);
            query.setParameter("userId",id);
            CardInfo cardInfo = query.uniqueResult();
            System.out.println(cardInfo);
            session.getTransaction().commit();
            return cardInfo;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            connection.close();
        }
        return null;
    }
}
