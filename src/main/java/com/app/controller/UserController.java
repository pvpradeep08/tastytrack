package com.app.controller;

import java.util.List;

import com.app.dao.UserDao;
import com.app.entity.User;

public class UserController {

    public static User validateUser(String username, String password) {
        return UserDao.validateUser(username, password);
    }

    public static boolean addUser(User user) {
        return UserDao.addUser(user);
    }

    public static boolean updateUser(User user) {
        return UserDao.updateUser(user);
    }

    public static boolean updateLastLogin(User user) {
        return UserDao.updateLastLogin(user);
    }

    public static boolean deleteUserById(int userId) {
        return UserDao.deleteUserById(userId);
    }

    public static boolean deleteUserByUsername(String username) {
        return UserDao.deleteUserByUsername(username);
    }

    public static User getUserById(int userId) {
        return UserDao.getUserById(userId);
    }

    public static User getUserByUsername(String username) {
        return UserDao.getUserByUsername(username);
    }

    public static List<User> getAllUsers() {
        return UserDao.getAllUsers();
    }

    public static boolean updatePassword(String username, String password) {
        return UserDao.updatePassword(username, password);
    }

    public static boolean deleteAllUsers() {
        return UserDao.deleteAllUsers();
    }

}
