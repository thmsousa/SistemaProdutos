package com.example.sistemaprodutos;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sair")
public class Sair extends HttpServlet {
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws IOException {
        HttpSession sessao = request.getSession(false);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (sessao != null && sessao.getAttribute("usuario") != null) {
            Usuario u = (Usuario) sessao.getAttribute("usuario");
            sessao.invalidate();

            out.println("<p>Voce foi desconectado </p>" + u.getNome());

        } else {
            out.println("<p>Voce não estava logado.</p>");
        }

    }
}
