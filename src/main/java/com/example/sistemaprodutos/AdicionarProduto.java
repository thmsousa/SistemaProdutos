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

@WebServlet("/adicionarproduto")
public class AdicionarProduto extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession sessao = request.getSession(false);
        PrintWriter out = response.getWriter();

        if (sessao == null || sessao.getAttribute("usuario") == null) {
            out.print("<p>Voce precisa estar logado como funcionário.</p>");
            return;
        }

        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        if (!usuario.getTipo().equalsIgnoreCase("funcionario")) {
            out.print("<p>Somente funcionários podem adicionar produtos.</p>");
            return;
        }

        String nome = request.getParameter("nome");
        String precoStr = request.getParameter("preco");

        if (nome == null || precoStr == null || nome.isBlank() || precoStr.isBlank()) {
            out.print("<p>Informe nome e preço.</p>");
            return;
        }

        List<Produto> produtos = (List<Produto>) getServletContext().getAttribute("produtos");
        int serial = Serial.proximo(getServletContext());
        Produto novo = new Produto(serial, nome, Float.parseFloat(precoStr));
        produtos.add(novo);

        out.print("<p>Produto adicionado com sucesso!</p>");
    }
}
