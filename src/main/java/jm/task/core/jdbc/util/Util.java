package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import java.sql.*;
import java.util.Properties;

public class Util {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/user?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties prop = new Properties();
                prop.setProperty("hibernate.connection.url", URL);
                prop.setProperty("hibernate.connection.username", LOGIN);
                prop.setProperty("hibernate.connection.password", PASSWORD);
                prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
                prop.setProperty("hibernate.connection.driver_class", DRIVER);
                prop.setProperty("hibernate.hbm2ddl.auto", "update");
                prop.setProperty("hibernate.connection.autocommit", "true");

                Configuration config = new Configuration();
                config.setProperties(prop);
                config.addAnnotatedClass(User.class);
                System.out.println("Соединение установлено");

                sessionFactory = config.buildSessionFactory();
                System.out.println("!!!Соединение установлено");
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}






