package com.example.sistemaprodutos.DAO;

import com.example.sistemaprodutos.Model.Usuario;

import java.util.List;

public interface UsuarioDao extends AutoCloseable {

    void inserir(Usuario u) throws ErroDao;
    void editar(Usuario u) throws ErroDao;
    Usuario buscar(String login, String senha) throws ErroDao;
    Usuario buscarPorLogin(String login) throws ErroDao;
    List<Usuario> buscar() throws ErroDao;
    void sair() throws ErroDao;
}
