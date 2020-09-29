package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.UserDao;
import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                statement.close();
                user.setRoles(getRoleFromUser(user.getId(), connection));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get user with login " + login, e);
        }
        return Optional.empty();
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (name, login, password, salt) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement =
                           connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setBytes(4, user.getSalt());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            statement.close();
            return insertRoles(user, connection);
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't create user " + user, e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users WHERE user_id = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                statement.close();
                user.setRoles(getRoleFromUser(user.getId(), connection));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get user with id " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users WHERE deleted = FALSE";
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                users.add(user);
            }
            statement.close();
            for (User user : users) {
                user.setRoles(getRoleFromUser(user.getId(), connection));
            }
            return users;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get all users", e);
        }
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, login = ?, password = ?, salt = ? "
                + "WHERE user_id = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setBytes(4, user.getSalt());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
            statement.close();
            deleteUserFromUsersRoles(user.getId(), connection);
            insertRoles(user, connection);
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't update user" + user, e);
        }
        return user;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE users SET deleted = TRUE WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete user with id " + id, e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        byte[] salt = resultSet.getBytes("salt");
        return new User(userId, name, login, password, salt);
    }

    private User insertRoles(User user, Connection connection) {
        String query = "INSERT INTO users_roles(user_id, role_id) "
                + "VALUES(?, (SELECT role_id FROM roles WHERE role_name = ?))";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (Role role : user.getRoles()) {
                statement.setLong(1, user.getId());
                statement.setString(2, role.getRoleName().toString());
                statement.executeUpdate();
            }
            user.setRoles(getRoleFromUser(user.getId(), connection));
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't update role of user " + user, e);
        }
        return user;
    }

    private Set<Role> getRoleFromUser(Long userId, Connection connection) {
        String query = "SELECT r.role_id, role_name FROM roles r JOIN users_roles ur "
                + "ON r.role_id = ur.role_id WHERE ur.user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Set<Role> roles = new HashSet<>();
            while (resultSet.next()) {
                Long roleId = resultSet.getLong("role_id");
                String roleName = resultSet.getString("role_name");
                Role role = Role.of(roleName);
                role.setId(roleId);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get roles from user with id " + userId, e);
        }
    }

    private void deleteUserFromUsersRoles(Long userId, Connection connection) {
        String query = "DELETE FROM users_roles WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete roles from user with id "
                    + userId, e);
        }
    }
}
