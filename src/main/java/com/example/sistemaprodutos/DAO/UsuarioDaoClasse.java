package com.example.sistemaprodutos.DAO;

import com.example.sistemaprodutos.Model.Usuario;
import java.sql.*;
import java.util.*;

public class UsuarioDaoClasse implements UsuarioDao {

    private Connection con;

    public UsuarioDaoClasse() throws ErroDao {
        con = Conexao.pegaConexao();
    }

    @Override
    public void inserir(Usuario u) throws ErroDao {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO usuarios(nome,login,senha,tipo) VALUES (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, u.getNome());
            ps.setString(2, u.getLogin());
            ps.setString(3, u.getSenha());
            ps.setString(4, u.getTipo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                u.setId(rs.getInt(1));

        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public Usuario buscar(String login, String senha) throws ErroDao {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM usuarios WHERE login=? AND senha=?"
            );
            ps.setString(1, login);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setLogin(rs.getString("login"));
                u.setTipo(rs.getString("tipo"));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public Usuario buscarPorLogin(String login) throws ErroDao {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM usuarios WHERE login=?"
            );
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setLogin(rs.getString("login"));
                u.setTipo(rs.getString("tipo"));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    @Override
    public List<Usuario> buscar() throws ErroDao {
        List<Usuario> lista = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setLogin(rs.getString("login"));
                u.setTipo(rs.getString("tipo"));
                lista.add(u);
            }
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
        return lista;
    }

    @Override
    public void editar(Usuario u) throws ErroDao {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE usuarios SET nome=?, senha=? WHERE id=?"
            );
            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());
            ps.setInt(3, u.getId());
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