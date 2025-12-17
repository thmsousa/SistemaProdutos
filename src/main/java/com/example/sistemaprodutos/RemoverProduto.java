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

@WebServlet("/removerproduto")
public class RemoverProduto extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        HttpSession sessao = request.getSession();

        if (sessao == null || sessao.getAttribute("usuario") == null) {
            out.print("<p>Você precisa estar logado como funcionário.</p>");
            return;
        }

        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        if (!usuario.getTipo().equalsIgnoreCase("funcionario")) {
            out.print("<p>Somente funcionários podem remover produtos.</p>");
            return;
        }

        String tid = request.getParameter("id");
        if (tid == null || tid.isBlank()) {
            out.print("<p>Informe o id do produto a remover.</p>");
            return;
        }

        int id = Integer.parseInt(tid);
        List<Produto> produtos = (List<Produto>) getServletContext().getAttribute("produtos");

        boolean removido = produtos.removeIf(p -> p.getId() == id);

        if (removido)
            out.print("<p>Produto removido com sucesso!</p>");
        else
            out.print("<p>Produto não encontrado.</p>");
    }
}