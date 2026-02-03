package com.example.sistemaprodutos.Controller;

import com.example.sistemaprodutos.Model.Usuario;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/sair")
public class Sair extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sessao = request.getSession(false);

        String nomeUsuario = null;
        if (sessao != null && sessao.getAttribute("usuario") != null) {
            Usuario u = (Usuario) sessao.getAttribute("usuario");
            nomeUsuario = u.getNome();
            sessao.invalidate();
        }

        if (nomeUsuario != null) {
            response.sendRedirect("index.jsp?mensagem=Logout realizado: " + nomeUsuario);
        } else {
            response.sendRedirect("index.jsp?mensagem=Você não estava logado.");
        }
    }
}
