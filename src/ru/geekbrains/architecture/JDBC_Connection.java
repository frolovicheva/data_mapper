package ru.geekbrains.architecture;

import java.sql.Connection;
import java.sql.DriverManager;

public interface JDBC_Connection {

   static boolean init() {
      return false;
   }
   void disconnect();

   default Connection getConnection() {
      return null;
   }


//   PreparedStatement setPreparedStatement(String sql);
}
