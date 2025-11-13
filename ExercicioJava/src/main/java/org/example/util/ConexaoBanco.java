package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    public static final String URL = "jdbc:mysql://localhost:3306/testes?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "mysqlPW";

    public static Connection conectar()throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
