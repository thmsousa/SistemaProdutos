package com.example.sistemaprodutos;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class Ouvinte implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent evento) {
        //System.out.println("Aplicação inicializada");
        ServletContext aplicacao=evento.getServletContext();
        List<Produto> produtos=new ArrayList<>();
        List<Usuario> usuarios=new ArrayList<Usuario>();

        produtos.add(new Produto(1,"Caneta",3));
        produtos.add(new Produto(2,"Mouse",25));
        produtos.add(new Produto(3,"Caderno",40));
        produtos.add(new Produto(4,"Computador",4500));
        produtos.add(new Produto(5,"Camiseta",150));

        usuarios.add(new Usuario(1,"Admin","admin","admin","funcionario"));
        usuarios.add(new Usuario(2,"José","jose","123","cliente"));
        usuarios.add(new Usuario(3,"Maria","maria","abcd","cliente"));

        aplicacao.setAttribute("produtos",produtos);
        aplicacao.setAttribute("usuarios",usuarios);
        aplicacao.setAttribute("serial",8);
    }
}
