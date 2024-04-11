package com.davidbonelo.services;

import com.davidbonelo.models.User;
import com.davidbonelo.persistance.UserDAO;

import java.sql.SQLException;

public class UserService {
    private final UserDAO userDAO;
    private User loggedUser;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String email, String password) {
        try {
            loggedUser = userDAO.validateUserCredentials(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loggedUser;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void logOut() {
        loggedUser = null;
    }

    public void register(User user, String password) throws SQLException {
        userDAO.createUser(user, password);
    }
}
