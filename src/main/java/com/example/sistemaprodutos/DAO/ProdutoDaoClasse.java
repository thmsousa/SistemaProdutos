package com.example.sistemaprodutos.DAO;

import com.example.sistemaprodutos.Model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoClasse implements ProdutoDao {

    private Connection con;

    public ProdutoDaoClasse() throws ErroDao {
        con = Conexao.pegaConexao();
    }

    @Override
    public void inserir(Produto p) throws ErroDao {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO produtos (nome, preco) VALUES (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                p.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public void editar(Produto p) throws ErroDao {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE produtos SET nome=?, preco=? WHERE id=?"
            );
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.setInt(3, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public void deletar(int id) throws ErroDao {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM produtos WHERE id=?"
            );
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public List<Produto> buscar() throws ErroDao {
        List<Produto> lista = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM produtos"
            );
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                lista.add(p);
            }

        } catch (SQLException e) {
            throw new ErroDao(e);
        }

        return lista;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }

}
