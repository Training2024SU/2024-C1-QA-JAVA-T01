package co.com.sofka.businessLogic.administrator.interf;

import co.com.sofka.model.User;

import java.util.List;

public interface UserManagement {
    public void insertUser(User user);
    public List<User> getAllUsers();
    public User getUserByEmail(String email);
    public void updateUser(User user);
    public void deleteUser(User user);
}
