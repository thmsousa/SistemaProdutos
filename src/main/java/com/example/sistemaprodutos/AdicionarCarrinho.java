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

@WebServlet("/adicionarCarrinho")
public class AdicionarCarrinho extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        List<Produto> produtos=(List<Produto>) getServletContext().getAttribute("produtos");
        HttpSession sessao= request.getSession(false);
        String tid=request.getParameter("id");
        if(sessao!=null) {
            if (tid != null && !tid.isBlank()) {
                int id = Integer.parseInt(tid);
                for(Produto p:produtos)
                    if(p.getId()==id){
                        Carrinho carrinho=(Carrinho) sessao.getAttribute("carrinho");
                        carrinho.getProdutos().add(p);
                        out.println("<p>Produto adicionado ao carrinho com sucesso.");
                        break;
                    }
            }else{
                out.print("<p>Informe o Id do produto");
            }
        }else{
            out.print("<p>Você precisa estar logado");
        }

    }
}
