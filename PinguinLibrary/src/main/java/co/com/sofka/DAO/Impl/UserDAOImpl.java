package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.UserDAO;
import co.com.sofka.model.User;
import co.com.sofka.enums.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.businessLogic.Library.mySqlOperation;

public class UserDAOImpl implements UserDAO {
    private static final String insertIntoQuery = "INSERT INTO user " +
            "(id, name, email, password, role) " +
            "VALUES ('%s', '%s', '%s', '%s', '%s');";
    private static final String selectAllQuery = "SELECT id, " +
            "name, " +
            "email, " +
            "password, " +
            "role " +
            "FROM user ";
    private static final String updateQuery = "UPDATE user SET " +
            "name = '%s', " +
            "email = '%s', " +
            "password = '%s', " +
            "role = '%s' " +
            "WHERE id = '%s';";
    private static final String deleteQuery = "DELETE FROM user WHERE id = '%s';";


    @Override
    public void insertUser(User user) {
        String query = String.format(insertIntoQuery,
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().toString());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        mySqlOperation.setSqlStatement(selectAllQuery);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSet = mySqlOperation.getResultSet()) {
            while (resultSet.next()) {
                User user = getUserResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching users: " + e.getMessage(), e);
        }
        return users;
    }

    @Override
    public User getUserById(String id) {
        return getAllUsers()
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateUser(User user) {
        String query = String.format(updateQuery,
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().toString(),
                user.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public void deleteUser(User user) {
        String query = String.format(deleteQuery, user.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }


    private User getUserResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(UserType.valueOf(resultSet.getString("role")));
        return user;
    }

    public User findUserByEmail(String email){
        return getAllUsers()
                .stream()
                .filter(userInDatabase -> userInDatabase.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

}
