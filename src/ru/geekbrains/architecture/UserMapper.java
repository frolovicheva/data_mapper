package ru.geekbrains.architecture;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper {
    private final JDBC_Connection connection;

    public UserMapper(JDBC_Connection connection) {
        this.connection = connection;
    }

    public boolean connect() {
        return JDBC_Connection.init ();
    }

    public void disconnect() {
        connection.disconnect();
    }

    public boolean insert (User user){
        try {
            if (connection.getConnection () != null){
            PreparedStatement insertStatement = connection.getConnection ().prepareStatement ("INSERT INTO users(login, password, nickname) VALUES (? ,? ,? );");
            insertStatement.setString(2, user.getLogin ());
            insertStatement.setString(3, user.getPassword ());
            insertStatement.setString(4, user.getNickname ());
            insertStatement.executeUpdate();
            return true;
            }
            return false;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }

    }
    public User findByLoginAndPassword(String login, String password){
        User user = new User ();
        try {
            if (connection.getConnection () != null){
            PreparedStatement findUserStatement = connection.getConnection ().prepareStatement  ("SELECT id, nickname,  FROM users WHERE login = ? AND password = ?;");
            findUserStatement.setString (1,login);
            findUserStatement.setString (2,password);
            ResultSet resultSet = findUserStatement.executeQuery();
            while (resultSet.next()) {
                user.setId (resultSet.getInt (1));
                user.setNickname (resultSet.getString(4));
            }
            resultSet.close ();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        user.setLogin (login);
        user.setPassword (password);
        return user;
    }

}
