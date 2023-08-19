package org.example.repository;

import org.example.model.StudentLoan;
import org.example.model.UserAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UserAccountRepo {

    private static final String SELECT_BY_PASSWORD = "from UserAccount where password =: password and nationalCode =: nationalCode";
    public static final String SELECT_BY_NATIONALCODE = "from UserAccount where nationalCode =: nationalCode";
    public static final String SELECT_BY_UNIVERSITY_TYPE = "from UserAccount where universityType =: uinversityType and nationalCode =: nationalCode";
    public static final String SELECT_BY_SPOUSE = "from UserAccount where spouseName =: spouse and nationalCode =: nationalCode";
    public static final String SELECT_BY_GRADE = "from UserAccount where garde =: grade and nationalCode =: nationalCode";
    public static final String SELECT_BY_CITY = "from UserAccount where city =: city and nationalCode =: nationalCode";



    public void createAdmin(UserAccount userAccount) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .buildSessionFactory();


        Session session = factory.openSession();

        try {
            session.beginTransaction();
            session.save(userAccount);
            session.getTransaction().commit();
            System.out.println("Done!");


        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
    }

    public  UserAccount findUserAccountByPassword(String nationalcode,String inputPassword) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query<UserAccount> query = session.createQuery(SELECT_BY_PASSWORD, UserAccount.class);
            query.setParameter("nationalCode",nationalcode);
            query.setParameter("password",inputPassword);
            UserAccount userAccount = query.uniqueResult();
            System.out.println(userAccount);
            session.getTransaction().commit();
            return userAccount;

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
        return null;
    }


    public  UserAccount findUserAccountByNationalCode(String inputNationalCode) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query<UserAccount> query = session.createQuery(SELECT_BY_NATIONALCODE, UserAccount.class);
            query.setParameter("nationalCode",inputNationalCode);
            UserAccount userAccount = query.uniqueResult();
            System.out.println(userAccount);
            session.getTransaction().commit();
            return userAccount;

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
        return null;
    }



    public  UserAccount findUserAccountByGrade(String grade, String nationalCode) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query<UserAccount> query = session.createQuery(SELECT_BY_GRADE, UserAccount.class);
            query.setParameter("grade",grade);
            query.setParameter("nationalCode",nationalCode);
            UserAccount userAccount = query.uniqueResult();
            System.out.println(userAccount);
            session.getTransaction().commit();
            return userAccount;

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
        return null;
    }

    public  UserAccount findUserAccountByCity(String city , String nationalCode) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query<UserAccount> query = session.createQuery(SELECT_BY_CITY, UserAccount.class);
            query.setParameter("city",city);
            query.setParameter("nationalCode",nationalCode);
            UserAccount userAccount = query.uniqueResult();
            System.out.println(userAccount);
            session.getTransaction().commit();
            return userAccount;

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
        return null;
    }

    public  UserAccount findUserAccountByUniversityType(String UniversityType , String nationalCode) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query<UserAccount> query = session.createQuery(SELECT_BY_UNIVERSITY_TYPE, UserAccount.class);
            query.setParameter("universityType",UniversityType);
            query.setParameter("nationalCode",nationalCode);
            UserAccount userAccount = query.uniqueResult();
            System.out.println(userAccount);
            session.getTransaction().commit();
            return userAccount;

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
        return null;
    }


    public  UserAccount findSpouse(String nationalCode,String spouse) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(StudentLoan.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query<UserAccount> query = session.createQuery(SELECT_BY_SPOUSE, UserAccount.class);
            query.setParameter("spouse",spouse);
            query.setParameter("nationalCode",nationalCode);
            UserAccount userAccount = query.uniqueResult();
            System.out.println(userAccount);
            session.getTransaction().commit();
            return userAccount;

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
        return null;
    }
}
