package ru.geekbrains.architecture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection implements JDBC_Connection {
    private static Connection connection;


    public boolean init()  {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:cloud2.db");
            return true;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace ();
        }
        return false;
    }

    public Connection getConnection() {
        return connection;
    }

//    public PreparedStatement setPreparedStatement(String sql){
//        try {
//            return connection.prepareStatement (sql);
//        } catch (SQLException | NullPointerException throwables) {
//            throwables.printStackTrace ();
//        }
//        return

    public void disconnect() {
        try {
            connection.close ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
    }

}
