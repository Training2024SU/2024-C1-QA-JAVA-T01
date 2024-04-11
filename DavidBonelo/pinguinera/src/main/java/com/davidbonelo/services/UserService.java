package com.davidbonelo.services;

import com.davidbonelo.models.User;
import com.davidbonelo.persistance.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private final Connection connection;
    private final UserDAO userDAO;
    private User loggedUser;

    public UserService(Connection connection, UserDAO userDAO) {
        this.connection = connection;
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
