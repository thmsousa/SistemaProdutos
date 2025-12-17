package com.example.sistemaprodutos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

@WebServlet("/editarusuario")
public class EditarUsuario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        HttpSession sessao = request.getSession(false);
        if (sessao == null || sessao.getAttribute("usuario") == null) {
            out.print("<p>Você precisa estar logado.</p>");
            return;
        }

        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        String novoNome = request.getParameter("nome");
        String novaSenha = request.getParameter("senha");

        if (novoNome != null && !novoNome.isBlank()) {
            usuario.setNome(novoNome);
        }

        if (novaSenha != null && !novaSenha.isBlank()) {
            usuario.setSenha(novaSenha);
        }

        out.print("<p>Dados atualizados com sucesso!</p>");
        out.print("<p><a href='index.html'>Home</a></p>");
    }
}
