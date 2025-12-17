package com.example.sistemaprodutos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

@WebServlet ("/removercarrinho")
public class RemoverCarrinho extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        HttpSession sessao = request.getSession(false);

        if (sessao == null) {
            out.print("<p>Voce precisa estar logado.</p>");
            return;
        }

        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        if (usuario == null || !usuario.getTipo().equalsIgnoreCase("cliente")) {
            out.print("<p>somente clientes podem remover produtos do carrinho</p>");
            return;
        }

        String tid = request.getParameter("id");
        if (tid == null || tid.isBlank()) {
            out.print("<p>Informe o ID do produto a remover.</p>");
            return;
        }

        int id = Integer.parseInt(tid);
        Carrinho carrinho = (Carrinho) sessao.getAttribute("carrinho");

        boolean removido = false;
        Iterator<Produto> it = carrinho.getProdutos().iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                removido = true;
                break;
            }
        }


        if (removido)
            out.print("<p>Produto removido do carrinho com sucesso!</p>");
        else
            out.print("<p>Produto não encontrado no carrinho.</p>");
    }
}
