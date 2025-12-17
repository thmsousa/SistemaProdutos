package com.example.sistemaprodutos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cadastrarfuncionario")
public class CadastrarFuncionario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession sessao = request.getSession(false);
        PrintWriter out = response.getWriter();

        if (sessao == null || sessao.getAttribute("usuario") == null) {
            out.print("<p>Você precisa estar logado como funcionário.</p>");
            return;
        }
        Usuario funcionario = (Usuario) sessao.getAttribute("usuario");
        if (!funcionario.getTipo().equalsIgnoreCase("funcionario")) {
            out.print("<p>Somente funcionários podem cadastrar outros funcionários.</p>");
            return;
        }

        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        if (nome == null || login == null || senha == null ||
                nome.isBlank() || login.isBlank() || senha.isBlank()) {
            out.print("<p>Preencha todos os campos.</p>");
            return;
        }

        List<Usuario> usuarios = (List<Usuario>) getServletContext().getAttribute("usuarios");

        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                out.print("<p>Esse login já está sendo usado.</p>");
                return;
            }
        }

        int serial = Serial.proximo(getServletContext());
        Usuario novo = new Usuario(serial, nome, login, senha, "funcionario");
        usuarios.add(novo);

        out.print("<p>Funcionário cadastrado com sucesso!</p>");
        out.print("<p><a href='index.html'>Home</a></p>");

    }
}
