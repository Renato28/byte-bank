package br.com.github.renato28.bytebank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    public static void main(String... x) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/byte_bank?user=root&password=root");

            System.out.println("Conectado com sucesso!");

            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
