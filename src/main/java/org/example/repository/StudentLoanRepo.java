package org.example.repository;

import jakarta.persistence.NoResultException;
import org.example.model.StudentLoan;
import org.example.model.UserAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class StudentLoanRepo {

    private static final String SELECT_BY_LOAN_TYPE = "from StudentLoan where loanType =: loanType and userAccount =: userAccount";
    private static final String SELECT_BY_NATIONALCODE = "from StudentLoan where loanType =: loanType and nationalCode =: nationalCode";

    public void createLoan(StudentLoan studentLoan) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .buildSessionFactory();


        Session session = factory.openSession();

        try {
            session.beginTransaction();
            session.save(studentLoan);
            session.getTransaction().commit();
            System.out.println("Done!");


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

    public StudentLoan findLoanByLoanType(String inputLoanType , int userAccount) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query<StudentLoan> query = session.createQuery(SELECT_BY_LOAN_TYPE, StudentLoan.class);
            query.setParameter("loanType", inputLoanType);
            query.setParameter("userAccount",userAccount);
            StudentLoan studentLoan = query.getSingleResult();
            System.out.println(studentLoan);
            session.getTransaction().commit();
            return studentLoan;

        } catch (NoResultException ex) {
            ex.getCause();
        } finally {
            session.close();
            factory.close();
        }
        return null;
    }


    public StudentLoan findLoanByNationalCode(String loanType,String nationalCode) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query<StudentLoan> query = session.createQuery(SELECT_BY_NATIONALCODE, StudentLoan.class);
            query.setParameter("loanType",loanType);
            query.setParameter("nationalCode", nationalCode);
            StudentLoan studentLoan = query.uniqueResult();
            System.out.println(studentLoan);
            session.getTransaction().commit();
            return studentLoan;

        } catch (NoResultException ex) {
            ex.getCause();
        } finally {
            session.close();
            factory.close();
        }
        return null;
    }

}