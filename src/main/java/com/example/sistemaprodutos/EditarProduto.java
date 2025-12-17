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

@WebServlet("/editarproduto")
public class EditarProduto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        HttpSession sessao = request.getSession(false);

        if (sessao == null || sessao.getAttribute("usuario") == null) {
            out.print("<p>Você precisa estar logado como funcionário</p>");
            return;
        }

        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        if (!usuario.getTipo().equalsIgnoreCase("funcionario")) {
            out.print("<p>Somente funcionários podem editar produtos.</p>");
            return;
        }

        String tid = request.getParameter("id");
        String nome = request.getParameter("nome");
        String precoStr = request.getParameter("preco");
        if (tid == null || tid.isBlank()) {
            out.print("<p>Informe o ID do produto a editar.</p>");
            return;
        }

        int id = Integer.parseInt(tid);
        List<Produto> produtos = (List<Produto>) getServletContext().getAttribute("produtos");
        for (Produto p : produtos) {
            if (p.getId() == id) {
                if (nome != null && !nome.isBlank()) p.setNome(nome);
                if (precoStr != null && !precoStr.isBlank()) p.setPreco(Float.parseFloat(precoStr));
                out.print("<p>Produto atualizado com sucesso!</p>");
                return;
            }
        }

        out.print("<p>Produto não encontrado.</p>");
    }
}
