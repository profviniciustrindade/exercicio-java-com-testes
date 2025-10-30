package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    public static final String url = "jdbc:mysql://localhost:3307/Produtos?useSSL=false&serverTimezone=UTC";
    public static final String user = "root";
    public static final String password = "mysqlPW";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
