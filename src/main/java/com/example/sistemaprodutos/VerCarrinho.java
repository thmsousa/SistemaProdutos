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

@WebServlet("/vercarrinho")//ver carrinho do usuário logado
public class VerCarrinho extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        HttpSession sessao=request.getSession(false);
        if(sessao!=null){
            out.print("<h1>Carrinh de Compras</h1>");
            Usuario usuarioLogado=(Usuario) sessao.getAttribute("usuario");
            Carrinho carrinho=(Carrinho)sessao.getAttribute("carrinho");
            out.print("<p>Ola "+usuarioLogado.getNome());

            out.print("<table border=1>\n" +
                    "        <thead><tr>\n" +
                    "            <th>Id</th>\n" +
                    "            <th>Nome</th>\n" +
                    "            <th>Preço</th>\n" +
                    "        </tr></thead>\n" +
                    "        <tbody>");
            for(Produto p:carrinho.getProdutos()){
                out.println("<tr><td>"+p.getId()+"</td>" +
                                "<td>"+p.getNome()+"</td>" +
                                "<td>"+p.getPreco()+"</td>" +
                        "</tr>");
            }
            out.print("</tbody>\n" +
                    "    </table>");

            out.println("<p>O total do carrinho de compras é: "+carrinho.getPreçoTotal());
        }else{
            out.print("<p>Você precisa estar logado");
        }
    }
}
