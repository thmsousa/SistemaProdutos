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

@WebServlet("/logar")
public class Logar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String login=request.getParameter("login");
        String senha=request.getParameter("senha");

        if(login!=null && !login.isBlank() &&
                senha!=null && !senha.isBlank()){
            List<Usuario> usuarios=(List<Usuario>) getServletContext().getAttribute("usuarios");
            boolean logado=false;
            for(Usuario u:usuarios){
                if(u.getLogin().equals(login)&&u.getSenha().equals(senha)){
                    logado=true;
                    HttpSession sessao=request.getSession();
                    sessao.setAttribute("usuario",u);
                    sessao.setAttribute("carrinho",new Carrinho());
                    out.print("<p>Logado com sucesso");
                    break;
                }
            }
            if(!logado)
                out.println("<p>Login ou senha incorretos.");
        }else{
            out.print("<p>Você precisa informar todo os dados.");
            out.print("<p><a href='index.html'>Home</a></p>");
        }
    }
}
