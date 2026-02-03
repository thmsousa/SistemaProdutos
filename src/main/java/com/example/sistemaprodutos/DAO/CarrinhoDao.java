package com.example.sistemaprodutos.DAO;

import com.example.sistemaprodutos.Model.Carrinho;
import java.util.List;

public interface CarrinhoDao extends AutoCloseable {

    void adicionar(int idUsuario, int idProduto) throws ErroDao;
    List<Carrinho> buscar(int idUsuario) throws ErroDao;
    void remover(int idUsuario, int idProduto) throws ErroDao;
    void sair() throws ErroDao;
}
