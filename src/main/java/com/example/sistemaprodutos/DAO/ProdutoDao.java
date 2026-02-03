package com.example.sistemaprodutos.DAO;

import com.example.sistemaprodutos.Model.Produto;
import java.util.List;

public interface ProdutoDao extends AutoCloseable {

    void inserir(Produto p) throws ErroDao;
    void editar(Produto p) throws ErroDao;
    void deletar(int id) throws ErroDao;
    List<Produto> buscar() throws ErroDao;
}