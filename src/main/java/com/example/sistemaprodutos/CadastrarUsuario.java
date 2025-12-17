package com.example.sistemaprodutos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/cadastrarusuario")
public class CadastrarUsuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        if (nome == null || login == null || senha ==null ||
                nome.isBlank() || login.isBlank() || senha.isBlank()) {
            out.print("<p>Preencha todos os campos</p>");
            return;
        }

        List<Usuario> usuarios = (List<Usuario>) getServletContext().getAttribute("usuarios");

        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                out.print("<p>Esse login já esta sendo usado, escolha outro.</p>");
                return;
            }
        }

        int serial = (int) getServletContext().getAttribute("serial");
        Usuario novo = new Usuario(serial, nome, login, senha, "cliente");
        usuarios.add(novo);
        getServletContext().setAttribute("serial", serial + 1);

        out.print("<p>Usuário cadastrado com sucesso</p>");
        out.print("<p><a href='index.html'>Home</a></p>");
    }
}
