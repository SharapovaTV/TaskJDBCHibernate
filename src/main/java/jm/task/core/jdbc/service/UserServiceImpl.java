package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {

   // UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        //userDaoJDBC.createUsersTable();
        userDaoHibernate.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        //userDaoJDBC.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        //userDaoJDBC.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name, lastName, age);

    }

    @Override
    public void removeUserById(long id) {
        //userDaoJDBC.removeUserById(id);
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        //return userDaoJDBC.getAllUsers();
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        //userDaoJDBC.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();
    }
}
