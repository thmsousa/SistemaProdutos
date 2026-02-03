package com.example.sistemaprodutos.DAO;

import com.example.sistemaprodutos.Model.Carrinho;
import com.example.sistemaprodutos.Model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDaoClasse implements CarrinhoDao {

    private Connection con;

    public CarrinhoDaoClasse() throws ErroDao {
        con = Conexao.pegaConexao();
    }

    @Override
    public void adicionar(int idUsuario, int idProduto) throws ErroDao {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO carrinho (id_usuario, id_produto, quantidade) " +
                            "VALUES (?, ?, 1) " +
                            "ON CONFLICT (id_usuario, id_produto) " +
                            "DO UPDATE SET quantidade = carrinho.quantidade + 1"
            );
            ps.setInt(1, idUsuario);
            ps.setInt(2, idProduto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public List<Carrinho> buscar(int idUsuario) throws ErroDao {
        List<Carrinho> lista = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT p.id, p.nome, p.preco, c.quantidade " +
                            "FROM carrinho c " +
                            "JOIN produtos p ON p.id = c.id_produto " +
                            "WHERE c.id_usuario = ?"
            );

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Carrinho c = new Carrinho();
                c.setIdProduto(rs.getInt("id"));
                c.setNomeProduto(rs.getString("nome"));
                c.setPreco(rs.getDouble("preco"));
                c.setQuantidade(rs.getInt("quantidade"));
                lista.add(c);
            }

        } catch (SQLException e) {
            throw new ErroDao(e);
        }

        return lista;
    }

    @Override
    public void remover(int idUsuario, int idProduto) throws ErroDao {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM carrinho WHERE id_usuario=? AND id_produto=?"
            );
            ps.setInt(1, idUsuario);
            ps.setInt(2, idProduto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public void sair() throws ErroDao {
        try {
            con.close();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public void close() throws Exception {
        sair();
    }

}