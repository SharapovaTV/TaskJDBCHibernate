package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("LOP", "LOPP", (byte) 5);
        userService.saveUser("POP", "POPP", (byte) 6);
        userService.saveUser("TOP", "TOPP", (byte) 9);
        userService.saveUser("NOP", "NOPP", (byte) 7);

        System.out.println(userService.getAllUsers());


        userService.removeUserById(1);
        userService.cleanUsersTable();

        userService.dropUsersTable();



    }
}
