package com.example.sistemaprodutos;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> produtos=new ArrayList<>();
    public List<Produto> getProdutos(){
        return produtos;
    }

    public void limpar() {
        produtos.clear();
    }

    public float getPreçoTotal(){
        float total=0;
        for(Produto p:produtos)
            total+=p.getPreco();
        return total;
    }
}
