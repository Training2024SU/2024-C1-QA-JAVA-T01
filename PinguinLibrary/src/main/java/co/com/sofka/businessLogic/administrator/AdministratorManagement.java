package co.com.sofka.businessLogic.administrator;

import co.com.sofka.DAO.Impl.UserDAOImpl;
import co.com.sofka.businessLogic.administrator.interf.UserManagement;
import co.com.sofka.model.User;

import java.util.List;

public class AdministratorManagement implements UserManagement {
    private final UserDAOImpl userDAO = new UserDAOImpl();
    @Override
    public void insertUser(User user) {
        userDAO.insertUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }
}
