package co.com.sofka.DAO;

import co.com.sofka.model.User;

import java.util.List;

public interface UserDAO {
    public void insertUser(User user);
    public List<User> getAllUsers();
    public User getUserById(String id);
    public void updateUser(User user);
    public void deleteUser(User user);
}
