package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), lastName VARCHAR(50), age int not NULL)").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Hib.Таблица успешно создана.");
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Hib.Таблица удалена.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("Hib. User с именем " + name + " добавлен в таблицу");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM user WHERE ID = " + id);
            session.getTransaction().commit();
            System.out.println("Hib.User удален.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            users = session.createSQLQuery("SELECT * FROM user").addEntity(User.class).list();
            //list = session.createSQLQuery("SELECT * FROM USER").addEntity(User.class).list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM user").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Him.Таблица очищена.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}

