package com.example.sistemaprodutos.Controller;

import com.example.sistemaprodutos.DAO.*;
import com.example.sistemaprodutos.Model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/logar")
public class Logar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        if (login == null || senha == null || login.isEmpty() || senha.isEmpty()) {
            response.sendRedirect("index.jsp?mensagem=Informe login e senha");
            return;
        }

        try (UsuarioDao dao = new UsuarioDaoClasse()) {

            Usuario u = dao.buscar(login, senha);
            if (u != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", u);
                response.sendRedirect("produtos");
            } else {
                response.sendRedirect("index.jsp?mensagem=Login ou senha inválidos");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/index.jsp?mensagem=Erro ao logar");
        }
    }
}