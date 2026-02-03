package com.example.sistemaprodutos.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection pegaConexao() throws ErroDao {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/sistemasprodutos",
                    "postgres",
                    "Thiagom18!"
            );
        } catch (Exception e) {
            throw new ErroDao(e);
        }
    }
}
