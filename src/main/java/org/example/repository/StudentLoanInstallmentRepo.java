package org.example.repository;

import jakarta.persistence.NoResultException;
import org.example.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentLoanInstallmentRepo {
    public static final String UPDATE_PAYMENT_STATUS = "from StudentLoanInstallment where paymentStatus = null and userAccount =: userAccount order by paymentDate ASC ";

    public static final String SHOW_PAYMENT = "from StudentLoanInstallment where paymentStatus = null and userAccount =: userAccount order by paymentDate ASC ";
    public static final String SHOW_PAYMENT_FOR_STATUS_YES = "from StudentLoanInstallment where paymentStatus != null and userAccount =: userAccount order by paymentDate ASC ";

    public static void insertStudentLoanPayment(int paymentAmount, List<StudentLoanInstallment> installmentPlan) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoanInstallment.class)
                .addAnnotatedClass(StudentLoanPayment.class)
                .addAnnotatedClass(StudentLoan.class)
                .addAnnotatedClass(CardInfo.class)
                .addAnnotatedClass(Grade.class)
                .addAnnotatedClass(BigCity.class)
                .addAnnotatedClass(LoanForStudent.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            StudentLoanPayment payment = new StudentLoanPayment();
            payment.setPaymentAmount(paymentAmount);
            payment.setInstallmentPlan(installmentPlan);

            session.save(payment);

            session.getTransaction().commit();
            System.out.println("Student loan payment processed and recorded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }


    public StudentLoanInstallment updatePaymentStatus(int number , int userAccount) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .addAnnotatedClass(StudentLoanInstallment.class)
                .addAnnotatedClass(StudentLoanPayment.class)
                .buildSessionFactory();
        if (number == 1) {
            Session session = factory.openSession();
            try {
                session.beginTransaction();
                StudentLoanInstallment query = session.createQuery(UPDATE_PAYMENT_STATUS, StudentLoanInstallment.class).setParameter("userAccount",userAccount).setMaxResults(1).uniqueResult();

                if (query != null) {
                    query.setPaymentStatus(LocalDate.now());
                    session.update(query);
                    session.getTransaction().commit();
                }



            } catch (NoResultException ex) {
                ex.getCause();
            } finally {
                session.close();
                factory.close();
            }
        }
        return null;
    }



    public ArrayList<StudentLoanInstallment> showTableForPayment(int id) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .addAnnotatedClass(StudentLoanInstallment.class)
                .addAnnotatedClass(StudentLoanPayment.class)
                .buildSessionFactory();

        Session session = factory.openSession();


        try {
            session.beginTransaction();
            ArrayList<StudentLoanInstallment> studentLoanInstallments = new ArrayList<>();
            Query<StudentLoanInstallment> query = session.createQuery(SHOW_PAYMENT, StudentLoanInstallment.class);
            query.setParameter("userAccount",id);
            List installments = query.getResultList();
            System.out.println(installments);
            session.getTransaction().commit();
            System.out.println("Your table for product");
            return studentLoanInstallments;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public ArrayList<StudentLoanInstallment> showTableForPaymentStatusYes(int id) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .addAnnotatedClass(StudentLoanInstallment.class)
                .addAnnotatedClass(StudentLoanPayment.class)
                .buildSessionFactory();

        Session session = factory.openSession();


        try {
            session.beginTransaction();
            ArrayList<StudentLoanInstallment> studentLoanInstallments = new ArrayList<>();
            Query<StudentLoanInstallment> query = session.createQuery(SHOW_PAYMENT_FOR_STATUS_YES, StudentLoanInstallment.class);
            query.setParameter("userAccount",id);
            List installments = query.getResultList();
            System.out.println("\n" + installments);
            session.getTransaction().commit();
            System.out.println("Your table for product");
            return studentLoanInstallments;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
