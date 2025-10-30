package org.example.util;

import javax.print.attribute.standard.MediaSize;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private final static String URL = "jdbc:mysql://localhost:3306/loja?useSSL=false&serverTimezone=UTC";
    private final static String NAME = "root";
    private final static String SENHA = "mysqlPW";
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, NAME,SENHA);
    }
}
