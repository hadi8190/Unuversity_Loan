package org.example.config;

import org.example.model.StudentLoan;
import org.example.model.UserAccount;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {

    String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String pass = "2002";


    Connection myConn;

    {
        try {
            myConn = DriverManager.getConnection(jdbcUrl, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
