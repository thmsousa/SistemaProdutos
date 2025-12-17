package com.example.sistemaprodutos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/produtos")//ver carrinho do usuário logado
public class Produtos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        List<Produto> produtos=(List<Produto>) getServletContext().getAttribute("produtos");
        HttpSession sessao= request.getSession(false);
        out.print("<h1>Produtos</h1>");
        out.print("<table border=1>\n" +
                "        <thead><tr>\n" +
                "            <th>Id</th>\n" +
                "            <th>Nome</th>\n" +
                "            <th>Preço</th>\n" +
                "            <th>Adicionar</th>\n" +
                "        </tr></thead>\n" +
                "        <tbody>");
        for(Produto p:produtos){
            out.println("<tr><td>"+p.getId()+"</td>" +
                            "<td>"+p.getNome()+"</td>" +
                            "<td>"+p.getPreco()+"</td>");
                            out.print(sessao!=null ?
                                    "<td><a href='adicionarCarrinho?id="+p.getId()+ "'>Adicionar</a></td>"
                                    : "<td>Adicionar</td>");
                    out.print("</tr>");
        }
        out.print("</tbody>\n" +
                "    </table>");


    }
}
